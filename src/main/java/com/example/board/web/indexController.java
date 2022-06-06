package com.example.board.web;


import com.example.board.domain.board.Board;
import com.example.board.service.board.BoardService;
import com.example.board.web.dto.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class indexController {

    private final BoardService boardService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/boardList")
    public String indexBoard(Model model){
        model.addAttribute("boardList", boardService.findAllDesc());
        return "/board/boardList";
    }
    @GetMapping("/boardDetail/{id}")
    public String boardDetail(@PathVariable Long id, Model model){
        BoardResponseDto board = boardService.findById(id);
        model.addAttribute("board", board);

        return "/board/boardUpdate";
    }
}
