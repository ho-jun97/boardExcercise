package com.example.board.web;

import com.example.board.config.auth.LoginUser;
import com.example.board.config.auth.dto.SessionUser;
import com.example.board.service.board.BoardService;
import com.example.board.service.comment.CommentService;
import com.example.board.web.dto.board.BoardResponseDto;
import com.example.board.web.dto.comment.CommentResponseDto;
import com.example.board.web.dto.comment.CommentSaveRequestDto;
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
        String userName= user==null?"":user.getName();
        model.addAttribute("userName", userName);
        model.addAttribute("board", boardDto);
        model.addAttribute("user", user);
        return "/fragments/comment::#commentContainer";
    }

    @DeleteMapping("/comment/delete/{id}")
    public @ResponseBody String delete(@PathVariable Long id){
        System.out.println("===> " + id +"댓글 삭제 진행중");
//        commentService.delete(commentId);
        return "글이 삭제되었습니다.";
    }
}
