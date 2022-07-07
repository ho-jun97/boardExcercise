package com.example.board.web.dto.comment;

import com.example.board.domain.comment.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentResponseDto {
    private Long id;
    private String comment;
    private String name;
    private String modifiedDate;
    private Long userId;
    private Long boardId;

    public CommentResponseDto(Comment comment){
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.name = comment.getUser().getName();
        this.modifiedDate = comment.getModifiedDate();
        this.userId = comment.getUser().getId();
        this.boardId = comment.getBoard().getId();
    }

    @Override
    public String toString() {
        return "CommentResponseDto{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", nickname='" + name + '\'' +
                ", modifiedDate='" + modifiedDate + '\'' +
                ", boardId=" + boardId +
                '}';
    }
}
