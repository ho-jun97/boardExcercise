package com.example.board.web.dto;

import com.example.board.domain.board.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardSaveRequestDto {
    private String title;
    private String content;
    private String author;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
