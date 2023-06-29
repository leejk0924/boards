package com.boards.study_board.biz.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

@Service
public class ViewService {
    public HashMap<String, String> getViewList() throws IOException {
        HashMap<String, String> view_list = new HashMap<>();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:/templates/post/*.html");

        for (Resource resource : resources) {
            String fullFileName = resource.getFilename();
            String fileName = fullFileName.substring(0, fullFileName.indexOf(".html"));
            view_list.put(fileName, fullFileName);
        }
        return view_list;
    }
}
