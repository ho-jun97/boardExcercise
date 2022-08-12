package com.example.board.web;

import com.example.board.config.auth.LoginUser;
import com.example.board.config.auth.dto.SessionUser;
import com.example.board.service.board.BoardService;
import com.example.board.service.comment.CommentService;
import com.example.board.web.dto.board.BoardResponseDto;
import com.example.board.web.dto.comment.CommentResponseDto;
import com.example.board.web.dto.comment.CommentSaveRequestDto;
import com.example.board.web.dto.comment.CommentUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class CommentController {

    private final CommentService commentService;
    private final BoardService boardService;

    @PostMapping("/comment/save/{boardId}")
    public String save(@PathVariable Long boardId, @RequestBody CommentSaveRequestDto requestDto,
                       @LoginUser SessionUser user, Model model){
        System.out.println("==> 댓글 작성 작동중()");
        Long commentId = commentService.commentSave(boardId, user.getEmail() , requestDto);

        CommentResponseDto commentDto = commentService.getComment(commentId);
        System.out.println("데이터베이스에 저장한 정보 " + commentDto.toString());

        BoardResponseDto boardDto = boardService.findById(boardId);

        model.addAttribute("commentList", boardDto.getComments());
        model.addAttribute("board", boardDto);
        model.addAttribute("user", user);
        return "/fragments/comment/comment :: #commentContainer";
    }

    @DeleteMapping("/comment/delete/{id}")
    public String delete(@PathVariable Long id,@RequestBody Long boardId, Model model,@LoginUser SessionUser user){
        commentService.delete(id);
        BoardResponseDto boardDto = boardService.findById(boardId);
        model.addAttribute("commentList", boardDto.getComments());
        model.addAttribute("board", boardDto);
        model.addAttribute("user", user);
        return "/fragments/comment/comment :: #commentContainer";
    }

    @PutMapping("/comment/update/{id}")
    public String update(@PathVariable Long id,Model model,
                         @RequestBody CommentUpdateRequestDto requestDto, @LoginUser SessionUser user){
        commentService.update(id, requestDto);
        CommentResponseDto commentDto = commentService.getComment(id);
        BoardResponseDto boardDto = boardService.findById(commentDto.getBoardId());
        model.addAttribute("commentList", boardDto.getComments());
        model.addAttribute("board", boardDto);
        model.addAttribute("user", user);

        return "/fragments/comment/comment :: #commentContainer";
    }
}
