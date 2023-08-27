package com.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController //Controller + ResponseBody
public class MainController {

    @GetMapping("")
    public String hello(){
        return "Connection Successful";
    }
}
