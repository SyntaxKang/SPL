package com.spl.spl.controller.photo;

import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.photo.Photo;
import com.spl.spl.dto.timeline.Article;
import com.spl.spl.service.group.GroupServiceImpl;
import com.spl.spl.service.photo.PhotoServiceImpl;
import com.spl.spl.service.timeline.ArticleServiceImpl;
import com.spl.spl.service.users.UsersServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class PhotoController {

    private GroupServiceImpl groupService;

    private UsersServiceImpl usersService;

    private ArticleServiceImpl articleService;

    private PhotoServiceImpl photoService;

    @GetMapping(value = "/Photo")
    public String PhotoGallery(@RequestParam("groupIdx")String groupIdx, Model model){

        List<Photo> photoList = new ArrayList<>();

        List<Article> articleList = articleService.findByIdx(Integer.parseInt(groupIdx));

        Groups groups = groupService.findByIdx(Integer.parseInt(groupIdx));

        for (Article article: articleList) {
            Photo photo = photoService.findByIdx(article.getArticleIdx());

            photoList.add(photo);
        }

        model.addAttribute("photoList",photoList);
        model.addAttribute("articleList",articleList);
        model.addAttribute("groups",groups);

        return "photo/Photo";
    }
}
