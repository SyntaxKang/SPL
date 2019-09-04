package com.spl.spl.controller.groupcreateController;

import com.spl.spl.dto.group.Groups;
import com.spl.spl.repository.group.GroupsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
@AllArgsConstructor
public class createcontroller {

    private GroupsRepository groupsRepository;
    //<모임 생성 입력폼
    @GetMapping(value = "/groupcreate")
    public String groupcreate(){
        return "groupcreate";
    }

    @GetMapping("/groupjoin")
    public String groupjoin(){
        return "groupjoin";
    }



    @RequestMapping( value = ("/group/create") , method = RequestMethod.POST)
    public String groupinsert(@RequestParam("groupprofile") MultipartFile file,Groups groups)  throws  IOException{

        System.out.println(file);
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getOriginalFilename().getClass());

        String upload= "C:\\SpringBoot\\SPL\\src\\main\\resources\\static\\UploadFile";
        //파일이있다면
        if(!file.getOriginalFilename().isEmpty()){

            // 저장소 생성
            Path path = Paths.get(upload+"\\"+file.getOriginalFilename());
            System.out.println(path);
            //파일 저장
            file.transferTo(path);
        }


        groupsRepository.save(Groups.builder()
                .name(groups.getName())
                .profile(file.getOriginalFilename())
                .religion(groups.getReligion())
                .content(groups.getContent())
                .category(groups.getCategory())
                .build());

        return "redirect:/index2";
    }


}
