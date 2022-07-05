package com.example.board.web;

import com.example.board.config.auth.PrincipalDetails;
import com.example.board.domain.board.Board;
import com.example.board.domain.user.User;
import com.example.board.service.board.BoardService;
import com.example.board.service.comment.CommentService;
import com.example.board.web.dto.comment.CommentResponseDto;
import com.example.board.web.dto.comment.CommentSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/board/{id}/comment/save")
    public String save(@PathVariable Long id, CommentSaveRequestDto requestDto,
                                     @AuthenticationPrincipal PrincipalDetails principalDetails){
        User user = principalDetails.getUser();
        Long commentId = commentService.commentSave(id, user.getId() , requestDto);

        CommentResponseDto commentDto = commentService.getComment(commentId);
        System.out.println(commentDto.toString());
        return "redirect:/boardDetail/{id}";
    }
}
