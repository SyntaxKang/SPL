package com.spl.spl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {

    @GetMapping("/index")
    public String index()
    {
        return "index";
    }
    @GetMapping("/")
    public String timeline(){
        return "timeline";
    }
    @GetMapping("/RecordRoom")
    public String RecordRoom(){
        return "RecordRoom";
    }
}
