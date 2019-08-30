package com.spl.spl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class controller {

    private GroupServiceImpl groupService;

    //메인화면
    @GetMapping("/index")
    public String index(){ return "index";}
    //로그인성공시 화면
    @GetMapping("/index2")
    public String index2(Model model){
        List<Groups> groupsList = groupService.findAll();

        for (Groups groups : groupsList) {
            System.out.println(groups.getGroupIdx());
        }

        model.addAttribute("groupsList", groupsList);

        return "index2";
    }
    //모음클릭시 화면
    @GetMapping("/")
    public String timeline(){
        return "timeline";
    }
    //기록실 클릭시 화면
    @GetMapping("/RecodeRoom")
    public String RecordRoom(){
        return "record/RecodeRoom";
    }

    @GetMapping("/table")
    public String table(){
        return "tables";
    }

    @GetMapping("/Bowling/Recode")
    public String Recode(){
        return "/Bowling/RecodeRoom";
    }
    @GetMapping("/Bowling/MVP")
    public String Mvp(){
        return "Mvp";
    }
}
