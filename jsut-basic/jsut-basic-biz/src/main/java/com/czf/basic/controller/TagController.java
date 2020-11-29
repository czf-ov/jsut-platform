package com.czf.basic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    @GetMapping
    public List<String> getAllTags() {
        List<String> tagList = new ArrayList<>();
        tagList.add("校园介绍");
        tagList.add("教师介绍");
        return tagList;
    }
}
