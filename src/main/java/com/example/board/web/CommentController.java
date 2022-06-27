package com.example.board.web;

import com.example.board.web.dto.comment.CommentSaveRequestDto;
import org.springframework.web.bind.annotation.PostMapping;

public class CommentController {


    @PostMapping("/comment/save")
    public String save(CommentSaveRequestDto requestDto){
        System.out.println(requestDto.toString());
        return "board/boardUpdate";
    }
}
