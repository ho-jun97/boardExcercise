package com.example.board.domain.user;

import com.example.board.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String age;
    private String myself;
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Board> boardList = new ArrayList<>();

    public void setId(Long id) {
        Id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setAge(String age) {
        this.age = age;
    }
    public void setMyself(String myself) {
        this.myself = myself;
    }

    public void addBoard(Board board){
        this.boardList.add(board);
    }
}
