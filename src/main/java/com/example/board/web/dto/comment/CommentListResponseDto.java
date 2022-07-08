package com.example.board.web.dto.comment;

import com.example.board.domain.comment.Comment;
import com.example.board.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentListResponseDto {
    private Long id;
    private String comment;
    private User user;
    private String modifiedDate;

    @Builder
    public CommentListResponseDto(Comment comment){
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.user = comment.getUser();
        this.modifiedDate = comment.getModifiedDate();
    }
}
