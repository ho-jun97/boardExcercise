package com.example.board.web;

import com.example.board.domain.board.Board;
import com.example.board.service.board.BoardService;
import com.example.board.web.dto.BoardSaveRequestDto;
import com.example.board.web.dto.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/board/save")
    public String save(BoardSaveRequestDto requestDto){
        boardService.save(requestDto);
        return "index";
    }
    @GetMapping("/board/delete/{id}")
    public String deleteBoard(@PathVariable Long id){
        System.out.println("delete 진행중()...");
        boardService.deleteBoard(id);
        return "index";
    }
    @PostMapping("/board/update/{id}")
    public String updateBoard(@PathVariable Long id, BoardUpdateRequestDto requestDto){
        System.out.println("board 수정중()...");
        boardService.update(id,requestDto);

        return "redirect:/boardDetail/{id}";
    }
}
