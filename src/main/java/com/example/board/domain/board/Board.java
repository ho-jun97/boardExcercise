package com.example.board.domain.board;

import com.example.board.domain.BaseTimeEntity;
import com.example.board.domain.comment.Comment;
import com.example.board.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @Builder
    public Board(String title, String content, User user){
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void update(String title, String content){
        System.out.println("Board Entity update 진행중()");
        this.title = title;
        this.content = content;
    }
}
