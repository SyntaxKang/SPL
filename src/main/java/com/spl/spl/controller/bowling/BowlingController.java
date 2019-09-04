package com.spl.spl.controller.bowling;


import com.spl.spl.dto.bowling.Bowling;
import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.users.Users;
import com.spl.spl.repository.Bowling.BowlingRepository;
import com.spl.spl.service.bowling.BowlingServiceImpl;
import com.spl.spl.service.group.GroupServiceImpl;
import com.spl.spl.service.group_bowling.GroupBowlingServiceImpl;
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
public class BowlingController {

    private BowlingServiceImpl bowlingService;

    private UsersServiceImpl usersService;

    private UsersGroupServiceImpl usersGroupService;

    private BowlingRepository bowlingRepository;

    private GroupBowlingServiceImpl groupBowlingService;

    private GroupServiceImpl groupService;

    @GetMapping("/Bowling/BowlingMain")
    public String Main(Model model){
        return "Bowling/BowlingRecode";
    }
    @GetMapping("/Bowling/BowlingMVP")
    public String Mvp(){ return "/Bowling/Mvp"; }

    @GetMapping("/Bowling/chart")
    public String chart(){
        return "/Bowling/charts";
    }

    @GetMapping("/Bowling")
    public String bowlingHome(Model model, @RequestParam(name = "groupIdx") String idx) {
        List<Bowling> list = new ArrayList<Bowling>();
        List<Users> uList = new ArrayList<Users>();
        List<String> buList = new ArrayList<String>();
        List gameCount = new ArrayList();

        int useridx = 0;
        int count = 0;

        String name = "";

        Bowling bowling = new Bowling();

        List<Object[]> gbList = groupBowlingService.findByGroupIdx(Integer.parseInt(idx));

        for (int i = 0; i < gbList.size(); i++) {
            Object[] obj = gbList.get(i);

            if(useridx != (int)obj[3]) {
                if(useridx != 0){
                    list.add(bowling);
                    gameCount.add(count);
                    count = 0;
                }
                useridx = (int) obj[3];
                Users user = usersService.findByIdx((Integer) obj[3]);
                bowling = new Bowling();
                buList.add(user.getName());
                name = user.getName();
            }

            Bowling getBowling = bowlingService.findByIdx((Integer)obj[2]);
            bowling.setSpare(bowling.getSpare() + getBowling.getSpare());
            bowling.setStrike(bowling.getStrike() + getBowling.getStrike());
            bowling.setPerfectgame(bowling.getPerfectgame() + getBowling.getPerfectgame());
            bowling.setTotal(bowling.getTotal() + getBowling.getTotal());
            bowling.setBowlingdate(getBowling.getBowlingdate());
            count++;

            if(i == gbList.size() - 1){
                list.add(bowling);
                gameCount.add(count);
            }
        }


        List<Object[]> list2 = usersGroupService.findByGroup(Integer.parseInt(idx));

        for (int j = 0; j < list2.size(); j++) {
            Object[] obj = list2.get(j);

            Users getUser = usersService.findByIdx((Integer) obj[2]);

            uList.add(getUser);
        }


        model.addAttribute("bowlingList", list);
        model.addAttribute("userList", uList);
        model.addAttribute("userBowlingList",buList);
        model.addAttribute("groupIdx",idx);
        model.addAttribute("gameCount",gameCount);
        model.addAttribute("name",name);

        return "Bowling/Recode";
    }


    @RequestMapping(value = "/Bowling/insert", method = RequestMethod.POST)
    public String bowlingInsert(Bowling bowling, @RequestParam("userIdx")int userIdx, @RequestParam("groupIdx")String groupIdx){
        System.out.println("bowling 값이 넘어옴? "+bowling.getStrike());

        System.out.println("userIdx: "+userIdx);
        System.out.println("groupIdx: "+groupIdx);

        Users users = usersService.findByIdx(userIdx);
        Groups groups = groupService.findByIdx(Integer.parseInt(groupIdx));

        System.out.println("셀렉트 유저 번호 : "+users.getUsersIdx());
        System.out.println("셀렉트 그룹 번호 : "+groups.getGroupIdx());

        Bowling bb = bowlingRepository.save(Bowling.builder()
                .spare(bowling.getSpare())
                .strike(bowling.getStrike())
                .perfectgame(bowling.getPerfectgame())
                .total(bowling.getTotal())
                .build());

        groupBowlingService.insert(groups,users,bb);

        return "redirect:/Bowling/Recode";
    }
}

