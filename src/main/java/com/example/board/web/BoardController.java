package com.example.board.web;

import com.example.board.config.auth.LoginUser;
import com.example.board.config.auth.dto.SessionUser;
import com.example.board.domain.user.User;
import com.example.board.service.board.BoardService;
import com.example.board.web.dto.board.BoardSaveRequestDto;
import com.example.board.web.dto.board.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/board/save")
    public String save(BoardSaveRequestDto requestDto, @LoginUser SessionUser user){
        boardService.save(user.getEmail(),requestDto);
        return "redirect:/boardList";
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
        return "redirect:/boardList";
    }
}
