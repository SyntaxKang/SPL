package com.spl.spl.controller;

import com.spl.spl.dto.group.Groups;
import com.spl.spl.service.group.GroupServiceImpl;
import com.spl.spl.service.users.UsersServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class controller extends FileUploadException {

    private GroupServiceImpl groupService;

    private UsersServiceImpl usersService;



    //메인화면
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    //로그인성공시 화면
    @GetMapping("/index2")
    public String index2(Model model) {
        List<Groups> groupsList = groupService.findAll();

        for (Groups groups : groupsList) {
            System.out.println(groups.getGroupIdx());
        }

        model.addAttribute("groupsList", groupsList);


        return "index2";
    }

    //기록실 클릭시 화면
    @GetMapping("/RecodeRoom")
    public String RecordRoom() {
        return "record/RecodeRoom";
    }

    @GetMapping("/table")
    public String table() {
        return "tables";
    }

    @GetMapping("/Bowling/Recode")
    public String Recode() {
        return "/Bowling/RecodeRoom";
    }

    @GetMapping("/Bowling/MVP")
    public String Mvp() {
        return "Mvp";
    }
}
