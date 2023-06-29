package com.boards.study_board.web.controller;

import com.boards.study_board.biz.service.ViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.HashMap;

@Controller
@RequiredArgsConstructor
public class ViewController {
    private final ViewService viewService;
    @GetMapping("/view-list")
    public String viewList(Model model) throws IOException {
        // View : 퍼블리싱 된 html 페이지
        model.addAttribute("viewList" , viewService.getViewList());
        return "view-list";
    }


}
