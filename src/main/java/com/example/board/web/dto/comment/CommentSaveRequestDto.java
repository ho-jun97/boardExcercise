package com.example.board.web.dto.comment;

import com.example.board.domain.board.Board;
import com.example.board.domain.comment.Comment;
import com.example.board.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentSaveRequestDto {
    private String comment;

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Comment toEntity() {
        return Comment.builder()
                .comment(comment)
                .build();
    }
}
