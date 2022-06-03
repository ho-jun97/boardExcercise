package com.example.board.web;

import com.example.board.domain.board.Board;
import com.example.board.service.board.BoardService;
import com.example.board.web.dto.BoardSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/board/save")
    public String save(BoardSaveRequestDto requestDto){
        boardService.save(requestDto);
        return "index";
    }
}
