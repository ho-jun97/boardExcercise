package com.example.board.web;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class indexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/board/save")
    public String boardSave(){
        return "/board/boardForm";
    }
}
