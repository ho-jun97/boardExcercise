package com.example.board.domain.board;

import com.example.board.domain.BaseTimeEntity;
import com.example.board.domain.comment.Comment;
import com.example.board.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Board(String title, String content, User user){
        this.title = title;
        this.content = content;
        this.user = user;
    }

    // 영속성, 더티체크
    public void update(String title, String content){
        System.out.println("Board Entity update 진행중()");
        this.title = title;
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addComment(Comment comment){
        comment.setBoard(this);
        this.comments.add(comment);
    }
}
