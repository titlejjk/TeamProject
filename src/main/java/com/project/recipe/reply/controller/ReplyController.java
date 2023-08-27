package com.project.recipe.reply.controller;

import com.project.project.recipe.reply.dto.ReplyDto;
import com.project.project.recipe.reply.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipe/reply")
public class ReplyController {

    @Autowired
    private ReplyService service;

    public ResponseEntity<?> insert (@RequestBody ReplyDto dto){
        service.saveReply(dto);
        return new ResponseEntity<>("Insert Complete!", HttpStatus.CREATED);
    }

}
