package com.boards.study_board.web.controller;

import com.boards.study_board.biz.service.PostService;
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
public class PostController {
    private final PostService postService;

    @GetMapping("/")
    public String index() {
        return "post/blog-home";
    }

    @GetMapping("/post/blog-post")
    public String blogPost() {
        return "post/blog-post";
    }
    @GetMapping("/view-list")
    public String viewList(Model model) throws IOException {
        HashMap<String, String> view_list = new HashMap<>();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:/templates/post/*.html");

        for (Resource resource : resources) {
            String fullFileName = resource.getFilename();
            String fileName = fullFileName.substring(0, fullFileName.indexOf(".html"));
            view_list.put(fileName, fullFileName);
        }
        model.addAttribute("viewList" ,view_list);
        return "view-list";
    }
}
