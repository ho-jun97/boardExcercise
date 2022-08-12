package com.example.board.domain.comment;

import com.example.board.domain.BaseTimeEntity;
import com.example.board.domain.board.Board;
import com.example.board.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment; // 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board; // 보드

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 작성자

    @Builder
    public Comment(Long id, String comment, Board board, User user){
        this.id = id;
        this.comment = comment;
        this.board = board;
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void update(String comment){
        this.comment = comment;
    }
}
