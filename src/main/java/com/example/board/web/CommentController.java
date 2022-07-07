package com.example.board.web;

import com.example.board.config.auth.LoginUser;
import com.example.board.config.auth.dto.SessionUser;
import com.example.board.domain.user.User;
import com.example.board.service.comment.CommentService;
import com.example.board.web.dto.comment.CommentResponseDto;
import com.example.board.web.dto.comment.CommentSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/board/{id}/comment/save")
    public String save(@PathVariable Long id, CommentSaveRequestDto requestDto,
                       @LoginUser SessionUser user){
        Long commentId = commentService.commentSave(id, user.getEmail() , requestDto);

        CommentResponseDto commentDto = commentService.getComment(commentId);
        System.out.println(commentDto.toString());
        return "redirect:/boardDetail/{id}";
    }

    @GetMapping("/board/{boardId}/comment/delete/{commentId}")
    public String delete(@PathVariable("boardId") Long boardId,
                         @PathVariable("commentId") Long commentId){
        commentService.delete(commentId);

        return "redirect:/boardDetail/{boardId}";
    }
}
