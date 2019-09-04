package com.spl.spl.controller.timeline;

import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.photo.Photo;
import com.spl.spl.dto.timeline.Article;
import com.spl.spl.dto.users.Users;
import com.spl.spl.service.group.GroupServiceImpl;
import com.spl.spl.service.photo.PhotoServiceImpl;
import com.spl.spl.service.timeline.ArticleServiceImpl;
import com.spl.spl.service.users.UsersServiceImpl;
import lombok.AllArgsConstructor;
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
public class TimelineController {

    private GroupServiceImpl groupService;

    private UsersServiceImpl usersService;

    private ArticleServiceImpl articleService;

    private PhotoServiceImpl photoService;

    //모음클릭시 화면
    @GetMapping("/timeline")
    public String timeline(@RequestParam("groupIdx")String groupIdx, Model model) {

//        List<Photo> photoList = photoService.findAll();
        List<Photo> photoList = new ArrayList<>();
        List<Article> articleList = articleService.findByIdx(Integer.parseInt(groupIdx));
        Groups groups = groupService.findByIdx(Integer.parseInt(groupIdx));

        for (Article article: articleList) {
            Photo photo = photoService.findByIdx(article.getArticleIdx());

            photoList.add(photo);
        }

        List<String> fileList = new ArrayList();

        fileList.add("MyImage.jpg");
        fileList.add("re_MyImage.jpg");
        fileList.add("LOGO.png");

        model.addAttribute("imgList",fileList);
        model.addAttribute("photoList",photoList);
        model.addAttribute("articleList",articleList);
        model.addAttribute("groups",groups);

        return "timeline";
    }

    @RequestMapping(value = "/timeline/insert", method = RequestMethod.POST)
    public String timelineInsert(@RequestParam(value = "imageFile") MultipartFile image, @RequestParam(value = "message") String content
    ,@RequestParam(value = "groupIdx")String groupIdx){
        System.out.println("Time Line Insert!!");
        System.out.println("Content : " + content);

        String filePath = "C:\\Users\\NaSangYeon\\IdeaProjects\\spl\\src\\main\\resources\\static\\UploadFile";

        Users user = usersService.findByIdx(1);
        Groups groups = groupService.findByIdx(1);

        Article article = articleService.insert(groups,user,content,false);



        if (!image.isEmpty()) {
            String fileName = UUID.randomUUID()+ "_" + image.getOriginalFilename();


            filePath += File.separator + fileName;

            try {

                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                bos.write(image.getBytes());
                bos.close();

                photoService.insert(groups,article,fileName);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "redirect:/timeline?groupIdx="+groupIdx;
    }
}
