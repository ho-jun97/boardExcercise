package com.example.board.web.dto.comment;

import com.example.board.domain.board.Board;
import com.example.board.domain.comment.Comment;
import com.example.board.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentSaveRequestDto {
    private Long id;
    private String comment;
    private User user;
    private Board board;

    public void setId(Long id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Comment toEntity(){
        return Comment.builder()
                .id(id)
                .comment(comment)
                .user(user)
                .board(board)
                .build();
    }
}
