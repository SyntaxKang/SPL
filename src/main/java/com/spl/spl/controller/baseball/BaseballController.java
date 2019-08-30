package com.spl.spl.controller.baseball;

import com.spl.spl.dto.baseball.Baseball;
import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.users.Users;
import com.spl.spl.repository.baseball.BaseballRepository;
import com.spl.spl.service.baseball.BaseballServiceImpl;
import com.spl.spl.service.group.GroupServiceImpl;
import com.spl.spl.service.group_baseball.GroupBaseballServiceImpl;
import com.spl.spl.service.user_group.UsersGroupServiceImpl;
import com.spl.spl.service.users.UsersServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class BaseballController {

    private BaseballServiceImpl baseballService;

    private UsersServiceImpl usersService;

    private UsersGroupServiceImpl usersGroupService;

    private GroupServiceImpl groupService;

    private GroupBaseballServiceImpl groupBaseballService;

    private BaseballRepository repository;

    @GetMapping("/baseball")
    public String baseballHome(Model model, @RequestParam(name = "groupIdx") String idx) {
        List<Baseball> list;
        List<Users> uList = new ArrayList<Users>();


//        List<Users_group> ugList;

        list = baseballService.findAll();
//        ugList = usersGroupService.findAll();
//
//
//        for (Users_group u_g : ugList) {
//            System.out.println("유저_그룹 번호: "+u_g.getUsersGroupIdx());
//            System.out.println("유저번호: "+u_g.getUsers().getUsersIdx());
//            System.out.println("그룹번호: "+u_g.getGroups().getGroupIdx());
//        }

        List<Object[]> list2 = usersGroupService.findByGroup(Integer.parseInt(idx));

        if (list2 == null) {
            System.out.println("응 안되");
        } else {
            System.out.println("시발 된다 오오오");
        }

        for (int j = 0; j < list2.size(); j++) {
            Object[] obj = list2.get(j);

            Users user = usersService.findByIdx((Integer) obj[1]);

            System.out.println("이름: " + user.getName());

            uList.add(user);
        }


        model.addAttribute("baseballList", list);
        model.addAttribute("userList", uList);
        model.addAttribute("groupIdx",idx);
        model.addAttribute("gameCount",list.size());

        return "baseball/baseballRecode";
    }

    @RequestMapping(value = "/baseball/insert",method = RequestMethod.POST)
    public String baseballInsert(Baseball baseball,@RequestParam("userIdx")int userIdx,@RequestParam("groupIdx")String groupIdx){
        System.out.println("baseball 값이 넘어옴? "+baseball.getScore());
        System.out.println("idx 값이 넘어옴? "+userIdx);
        System.out.println("idx 값이 넘어옴? "+groupIdx);

        Users users = usersService.findByIdx(userIdx);
        Groups groups = groupService.findByIdx(Integer.parseInt(groupIdx));

        System.out.println("셀렉트 유저 번호 : "+users.getUsersIdx());
        System.out.println("셀렉트 그룹 번호 : "+groups.getGroupIdx());

        Baseball bb = repository.save(Baseball.builder().ball(baseball.getBall()).strike(baseball.getStrike()).score(baseball.getScore())
                .rbi(baseball.getRbi()).homerun(baseball.getHomerun()).hit(baseball.getHit())
                .atbat(baseball.getAtbat()).build());

        groupBaseballService.insert(groups,users,bb);

        return "redirect:/baseball";
    }
}
