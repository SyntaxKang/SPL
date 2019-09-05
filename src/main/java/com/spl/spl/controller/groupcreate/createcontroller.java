package com.spl.spl.controller.groupcreate;

import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.user_group.users_group;
import com.spl.spl.dto.users.Users;
import com.spl.spl.repository.group.GroupsRepository;
import com.spl.spl.repository.user_group.UsersGroupRepository;
import com.spl.spl.repository.users.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
@AllArgsConstructor
public class createcontroller {

    private GroupsRepository groupsRepository;
    private UsersGroupRepository usersGroupRepository;
    private UsersRepository usersRepository;

    //<모임 생성 입력폼
    @GetMapping(value = "/groupcreate")
    public String groupcreate(HttpSession session,Model model) {
        Users user = (Users) session.getAttribute("local");

        System.out.println(user.getUsersIdx());

        model.addAttribute("userIdx",user.getUsersIdx());

        return "groupcreate";
    }

    @GetMapping("/groupjoin")
    public String groupjoin() {
        return "groupjoin";
    }


    @RequestMapping(value = ("/group/create"), method = RequestMethod.POST)
    public String groupinsert(@RequestParam("groupprofile") MultipartFile file, Groups groups, @RequestParam("userIdx") int userIdx) throws IOException {

        System.out.println(file);
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getOriginalFilename().getClass());

        String upload = "C:\\Users\\NaSangYeon\\IdeaProjects\\spl\\src\\main\\resources\\static\\UploadFile";
        //파일이있다면
        if (!file.getOriginalFilename().isEmpty()) {

            // 저장소 생성
            Path path = Paths.get(upload + "\\" + file.getOriginalFilename());
            System.out.println(path);
            //파일 저장
            file.transferTo(path);
        }


        Groups newgroups = groupsRepository.save(Groups.builder()
                .name(groups.getName())
                .profile(file.getOriginalFilename())
                .religion(groups.getReligion())
                .content(groups.getContent())
                .category(groups.getCategory())
                .build());

        System.out.println("유저들어옴?" + userIdx);
        Users users = usersRepository.findByIdx(userIdx);

        usersGroupRepository.save(users_group.builder()
                .groups(newgroups)
                .grade(2)
                .users(users)
                .nick(users.getNickname())
                .build());


        return "redirect:/index2";
    }


}
