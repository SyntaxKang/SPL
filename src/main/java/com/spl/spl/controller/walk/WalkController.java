package com.spl.spl.controller.walk;

import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.users.Users;
import com.spl.spl.dto.walk.Walk;
import com.spl.spl.repository.walk.WalkRepository;
import com.spl.spl.service.group.GroupServiceImpl;
import com.spl.spl.service.group_walk.GroupWalkService;
import com.spl.spl.service.user_group.UsersGroupServiceImpl;
import com.spl.spl.service.users.UsersServiceImpl;
import com.spl.spl.service.walk.WalkService;
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
public class WalkController {

    private UsersServiceImpl usersService;
    private UsersGroupServiceImpl usersGroupService;
    private GroupServiceImpl groupService;
    private WalkService walkService;
    private WalkRepository repository;
    private GroupWalkService groupWalkService;

    @GetMapping("/walk")
    public String walkHome(Model model, @RequestParam(name = "groupidx") String idx) {


        List<Walk> list = new ArrayList<Walk>();

        List<Users> uList = new ArrayList<Users>();

        List<String> walkUserList = new ArrayList<String>();


        int useridx = 0;


        Users user = new Users();
        Walk walk = new Walk();
        List<Object[]> gbList = groupWalkService.findByGroupIdx(Integer.parseInt(idx));

        for (int j = 0; j < gbList.size(); j++) {
            Object[] obj = gbList.get(j);

            if (useridx != (int) obj[3]) {
                if (useridx != 0) {
                    list.add(walk);
                }
                useridx = (int) obj[3];
                user = usersService.findByIdx((Integer) obj[3]);
                walk = new Walk();
                walkUserList.add(user.getName());
            }
        }

        list = walkService.findAll();
        List<Object[]> list2 = usersGroupService.findByGroup(Integer.parseInt(idx));

        for (int j = 0; j < list2.size(); j++) {
            Object[] obj = list2.get(j);

            Users getUser = usersService.findByIdx((Integer) obj[1]);

            uList.add(getUser);
        }


        model.addAttribute("walkList", list);
        model.addAttribute("userList", uList);


        model.addAttribute("walkUserList", walkUserList);
        model.addAttribute("groupIdx", idx);


        return "walk/walkRecode";


    }


    @RequestMapping(value = "/walk/insert", method = RequestMethod.POST)
    public String walkInsert(Walk walk, @RequestParam("userIdx") int userIdx, @RequestParam("groupIdx") String groupIdx) {

        Users users = usersService.findByIdx(userIdx);
        Groups groups = groupService.findByIdx(Integer.parseInt(groupIdx));

        Walk w = repository.save(Walk.builder()
                .range(walk.getRange())
                .date(walk.getDate())
                .goal(walk.getGoal())
                .build());

        groupWalkService.insert(groups, users, w);

        return "redirect:/walk";
    }


}

