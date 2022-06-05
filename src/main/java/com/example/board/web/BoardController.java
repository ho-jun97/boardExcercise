package com.example.board.web;

import com.example.board.domain.board.Board;
import com.example.board.service.board.BoardService;
import com.example.board.web.dto.BoardSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/board/save")
    public String boardSave(){
        return "/board/boardForm";
    }

    @PostMapping("/board/save")
    public String save(BoardSaveRequestDto requestDto){
        boardService.save(requestDto);
        return "index";
    }
}
