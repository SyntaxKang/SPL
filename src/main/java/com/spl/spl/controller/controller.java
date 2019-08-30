package com.spl.spl.controller;

import com.spl.spl.repository.baseball.BaseballRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@AllArgsConstructor
@Controller
public class controller {

   private BaseballRepository repository;
    //메인화면
    @GetMapping("/index")
    public String index(){ return "index";}
    //로그인성공시 화면
    @GetMapping("/index2")
    public String index2(){ return "index2";}
    //모음클릭시 화면
    @GetMapping("/")
    public String timeline(){
        return "timeline";
    }
    //기록실 클릭시 화면
    @GetMapping("/RecodeRoom")
    public String RecordRoom(){
        return "RecodeRoom";
    }







}
