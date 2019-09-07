package com.spl.spl.controller.soccer;


import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.soccer.Soccer;
import com.spl.spl.dto.users.Users;
import com.spl.spl.repository.soccer.SoccerRepository;
import com.spl.spl.service.group.GroupServiceImpl;
import com.spl.spl.service.group_soccer.GroupSoccerServiceImpl;
import com.spl.spl.service.soccer.SoccerServiceImpl;
import com.spl.spl.service.user_group.UsersGroupServiceImpl;
import com.spl.spl.service.users.UsersServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
@AllArgsConstructor
public class SoccerController {

    private SoccerServiceImpl soccerService;

    private UsersServiceImpl usersService;

    private UsersGroupServiceImpl usersGroupService;

    private GroupServiceImpl groupService;

    private GroupSoccerServiceImpl groupSoccerService;

    private SoccerRepository repository;

    @GetMapping("/soccer")
    public String soccerHome(Model model, @RequestParam(name = "groupIdx",required = false) String idx) {
        List<Soccer> list = new ArrayList<Soccer>();
        List<Users> uList = new ArrayList<Users>();
        List<String> buList = new ArrayList<String>();
        List gameCount = new ArrayList();

        int useridx = 0;
        int count = 0;


        Soccer soccer = new Soccer();
        Users user= new Users();
        List<Object[]> gbList = groupSoccerService.findByGroupIdx(Integer.parseInt(idx));

        for (int j = 0; j < gbList.size(); j++) {
            Object[] obj = gbList.get(j);

            if(useridx != (int)obj[3]) {
                if(useridx != 0){
                    list.add(soccer);
                    gameCount.add(count);
                    count = 0;
                }
                useridx = (int) obj[3];
                user = usersService.findByIdx((Integer)obj[3]);
                soccer = new Soccer();
                buList.add(user.getName());

            }

            Soccer getSoccer = soccerService.findByIdx((Integer)obj[2]);
            soccer.setGoal(soccer.getGoal() + getSoccer.getGoal());
            soccer.setAssist(soccer.getAssist() + getSoccer.getAssist());
            soccer.setShoot(soccer.getShoot() + getSoccer.getShoot());
            soccer.setYellowcard(soccer.getYellowcard() + getSoccer.getYellowcard());
            soccer.setCorner(soccer.getCorner() + getSoccer.getCorner());
            soccer.setFoul(soccer.getFoul() + getSoccer.getFoul());
            soccer.setOffside(soccer.getOffside() + getSoccer.getOffside());
            soccer.setPenalty(soccer.getPenalty() + getSoccer.getPenalty());
            soccer.setRedcard(soccer.getRedcard() + getSoccer.getRedcard());
            soccer.setGame(soccer.getGame() + getSoccer.getGame());
            count++;

            if(j == gbList.size() - 1){
                list.add(soccer);
                gameCount.add(count);
            }
        }


        List<Object[]> list2 = usersGroupService.findByGroup(Integer.parseInt(idx));

        for (int j = 0; j < list2.size(); j++) {
            Object[] obj = list2.get(j);

            Users getUser = usersService.findByIdx((Integer) obj[5]);

            uList.add(getUser);
        }
  model.addAttribute("soccerList", list);
        model.addAttribute("userList", uList);
        model.addAttribute("usersoccerList",buList);
        model.addAttribute("groupIdx",idx);
        model.addAttribute("gameCount",gameCount);

        return "soccer/soccerRecode";
    }

    @RequestMapping(value = "/soccer/insert",method = RequestMethod.POST)
    public String soccerInsert(Soccer soccer,@RequestParam("userIdx")int userIdx,@RequestParam(name = "groupIdx",required = false)String groupIdx){



        Users users = usersService.findByIdx(userIdx);
        Groups groups = groupService.findByIdx(Integer.parseInt(groupIdx));



        Soccer ss = repository.save(Soccer.builder()
                .goal(soccer.getGoal()).assist(soccer.getAssist())
                .shoot(soccer.getShoot()).yellowcard(soccer.getYellowcard())
                .redcard(soccer.getRedcard()).foul(soccer.getFoul())
                .offside(soccer.getOffside()).penalty(soccer.getPenalty())
                .corner(soccer.getCorner())
                .game(soccer.getGame()).build());

        groupSoccerService.insert(groups,users,ss);

        return "redirect:/soccer";
    }

}
