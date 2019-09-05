package com.spl.spl.controller.user;


import com.spl.spl.anotation.SocialUser;
import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.users.UserAuthority;
import com.spl.spl.dto.users.Users;
import com.spl.spl.repository.users.UsersRepository;
import com.spl.spl.service.group.GroupServiceImpl;
import com.spl.spl.service.user_group.UsersGroupServiceImpl;
import com.spl.spl.service.users.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller

public class Usercontroller {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private UsersServiceImpl usersService;

    @Autowired
    private UsersGroupServiceImpl usersGroupService;

    @Autowired
    private GroupServiceImpl groupService;


    public Usercontroller(UsersServiceImpl usersService){this.usersService=usersService;}



// 일반 사용자 /소셜로그인 인증 사용자 로그인

    @RequestMapping(value = "/index")
    public String index(Users user, HttpServletRequest request, HttpSession session)
    {
        if(session.getAttribute("local") != null){
            return "redirect:/index2";
        }

        session.setAttribute("local",user);

        return "index";
    }

    //회원가입
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String registerget(Model model){

        return "register";
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@ModelAttribute Users user, HttpServletRequest request, HttpServletResponse response,HttpSession session, Model model)throws Exception {
        PasswordEncoder pe = new BCryptPasswordEncoder();
        Users searchuser = userRepository.findByEmail(user.getEmail());
        System.out.println("SearchUser : "+searchuser.getEmail());

        if (searchuser == null) {
            HashSet<UserAuthority> a = new HashSet<>();
            a.add(UserAuthority.USER);
            user.setAuthorities(a);
            user.setPassword(pe.encode(user.getPassword()));
            user.setCreatedDate(LocalDateTime.now());
            user = userRepository.save(user);
            session.setAttribute("local",user);
            System.out.println(user.getEmail());
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('회원가입을 축하드립니다.');</script>");
            out.flush();
            return "/index";
        } else {
            //이미 회원가입이 되어있는지 확인
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('가입된 사용자입니다.');</script>");
            out.flush();
            return "register";

        }
    }

    // 로그인
    @GetMapping(value = "/loginSuccess")
    public String loginComplete(@SocialUser Users user, HttpSession session, Model model) throws Exception {


        if(userRepository.findByEmail(user.getEmail()) == null ){

            return "redirect:/join";
        }else{
            Users users = userRepository.findByEmail(user.getEmail());

            if(users.getArea() == null){

                return "redirect:/join";
            }
        }

        Users finduser = userRepository.findByEmail(user.getEmail());

        session.setAttribute("local",finduser);

        Users checkUser = (Users)session.getAttribute("local");

        if(checkUser.getArea() == null){

            return "redirect:/join";
        }

        System.out.println("Scuccess Session Email: "+checkUser.getEmail());


        return "redirect:/index2";
    }

    // 소셜로그인후 추가정보
    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String joinget(Users user,Model model,HttpSession session,HttpServletRequest request){

        Users findUser = (Users) session.getAttribute("local");

        model.addAttribute("local",findUser);
        return "join";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join(Users user, HttpServletRequest request, Model model, HttpSession session){

        PasswordEncoder pe= new BCryptPasswordEncoder();
        Users findUser = (Users) session.getAttribute("user");

        System.out.println("UserIdx : "+findUser.getUsersIdx());
        System.out.println("이메일 : "+findUser.getEmail());
        System.out.println("소셜타입 : "+findUser.getSocialType());
        System.out.println("이름 : "+findUser.getName());
        System.out.println("지역 : "+user.getArea());

           HashSet<UserAuthority> a = new HashSet<>();
           a.add(UserAuthority.USER);
           user.setAuthorities(a);

        user = userRepository.save(Users.builder()
                .createdDate(findUser.getCreatedDate()).email(findUser.getEmail()).name(findUser.getName()).socialType(findUser.getSocialType())
                .pincipal(findUser.getPincipal())
                .usersIdx(findUser.getUsersIdx()).password(pe.encode(user.getPassword()))
                .nickname(user.getNickname()).category(user.getCategory())
                .area(user.getArea()).areaa(user.getAreaa())
                .updatedDate(LocalDateTime.now())
                .build());

        session.setAttribute("local",user);

        Users u = (Users)session.getAttribute("local");


        System.out.println("Session 에 담긴 Email : "+u.getEmail());

        System.out.println(user);


        return "redirect:/index2";
    }

    @GetMapping("/loginFailure")
    public String loginfail(HttpServletRequest request) throws Exception{
        return "/index";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
            session.invalidate();
        return "redirect:/index";
    }

    @GetMapping("/index2")
    public String index2(HttpSession session,Model model) {
        Users users = (Users) session.getAttribute("local");

        System.out.println("Index2 UserIdx : "+users.getUsersIdx());

        List<Object[]> list = usersGroupService.findByUserIdx(users.getUsersIdx());
        List<Groups> groupsList = new ArrayList<>();

        for (Object[] obj: list) {
            Groups groups = groupService.findByIdx((int)obj[2]);
            groupsList.add(groups);
        }

        model.addAttribute("groupList",groupsList);



        model.addAttribute("local",users);


        return "index2";
    }

    /*@GetMapping("/toolbar")
    public String tool(User user, HttpSession session,Model model)throws Exception{
        User findUser = (User) session.getAttribute("local");

        System.out.println("Tool bar Session Email : "+findUser.getEmail());

        model.addAttribute("user",findUser);
        return "toolbar";
    }*/

    @GetMapping("/userview")
    public String userview(Users user, HttpSession session, Model model)throws Exception{
       Users search=(Users)session.getAttribute("local");

        model.addAttribute("user",search);

        return "userview";

    }

    @GetMapping("/userlist")
    public String list(Pageable pageable, Users user, Model model) {

        model.addAttribute("userList", usersService.findAll(pageable));
        model.addAttribute("user", new Users());

        return "userlist";
    }


    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
}
