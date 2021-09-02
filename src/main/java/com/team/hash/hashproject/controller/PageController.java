package com.team.hash.hashproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    @PostMapping("/save")
    public String save(HttpSession session) {
        return "";
    }

}
