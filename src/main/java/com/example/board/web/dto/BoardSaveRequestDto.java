package com.example.board.web.dto;

import com.example.board.domain.board.Board;
import com.example.board.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardSaveRequestDto {
    private String title;
    private String content;
    private User user;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
    }
}
