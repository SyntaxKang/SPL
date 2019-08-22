package com.spl.spl.controller.baseball;

import com.spl.spl.dto.baseball.baseball;
import com.spl.spl.repository.baseball.BaseballRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class BaseballController {

    private BaseballRepository repository;

    @GetMapping("/baseball")
    public String baseballHome(Model model){
        List<baseball> list;

        list = repository.findAll();

        model.addAttribute("baseballList",list);

        return "baseball/baseballRecode";
    }
}
