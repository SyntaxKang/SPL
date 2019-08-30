package com.spl.spl.controller.baseball;

import com.spl.spl.dto.baseball.Baseball;
import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.group_baseball.Group_baseball;
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
        List<Baseball> list = new ArrayList<Baseball>();
        List<Users> uList = new ArrayList<Users>();
        List<String> buList = new ArrayList<String>();
        List gameCount = new ArrayList();

        int useridx = 0;
        int count = 0;

        Users user = new Users();
        Baseball baseball = new Baseball();

        List<Object[]> gbList = groupBaseballService.findByGroupIdx(Integer.parseInt(idx));

        for (int j = 0; j < gbList.size(); j++) {
            Object[] obj = gbList.get(j);

            if(useridx != (int)obj[3]) {
                if(useridx != 0){
                    list.add(baseball);
                    gameCount.add(count);
                    count = 0;
                }
                useridx = (int) obj[3];
                user = usersService.findByIdx((Integer)obj[3]);
                baseball = new Baseball();
                buList.add(user.getName());
            }

            Baseball getBaseball = baseballService.findByIdx((Integer)obj[2]);

            baseball.setAtbat(baseball.getAtbat() + getBaseball.getAtbat());
            baseball.setBall(baseball.getBall() + getBaseball.getBall());
            baseball.setHit(baseball.getHit() + getBaseball.getHit());
            baseball.setHomerun(baseball.getHomerun() + getBaseball.getHomerun());
            baseball.setRbi(baseball.getRbi() + getBaseball.getRbi());
            baseball.setScore(baseball.getScore() + getBaseball.getScore());
            baseball.setStrike(baseball.getStrike() + getBaseball.getStrike());
            count++;

            if(j == gbList.size() - 1){
                list.add(baseball);
                gameCount.add(count);
            }
        }


        List<Object[]> list2 = usersGroupService.findByGroup(Integer.parseInt(idx));

        for (int j = 0; j < list2.size(); j++) {
            Object[] obj = list2.get(j);

            Users getUser = usersService.findByIdx((Integer) obj[1]);

            uList.add(getUser);
        }


        model.addAttribute("baseballList", list);
        model.addAttribute("userList", uList);
        model.addAttribute("userBaseballList",buList);
        model.addAttribute("groupIdx",idx);
        model.addAttribute("gameCount",gameCount);

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
