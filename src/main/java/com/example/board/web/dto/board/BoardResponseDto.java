package com.example.board.web.dto.board;

import com.example.board.domain.board.Board;
import com.example.board.domain.user.User;
import com.example.board.web.dto.comment.CommentListResponseDto;
import com.example.board.web.dto.comment.CommentResponseDto;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private User user;
    private List<CommentListResponseDto> comments;

    public BoardResponseDto(Board entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.user = entity.getUser();
        this.comments = entity.getComments().stream().map(CommentListResponseDto::new).collect(Collectors.toList());
    }
}
