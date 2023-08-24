package com.project.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController //Controller + ResponseBody
@RequestMapping("/") //해당하는 패턴을 왔을 때 해당 클래스를 실행
public class MainController {

    @GetMapping("")
    public String hello(){
        return "Connection Successful";
    }
}
