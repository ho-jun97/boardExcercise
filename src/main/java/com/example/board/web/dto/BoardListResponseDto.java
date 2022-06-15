package com.example.board.web.dto;

import com.example.board.domain.board.Board;
import com.example.board.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class BoardListResponseDto {
    private Long id;
    private String title;
    private User user;
    private LocalDateTime modifiedDate;

    public BoardListResponseDto(Long id, String title, User user, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.user = user;
        this.modifiedDate = modifiedDate;
    }

    @Builder
    public BoardListResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.user = entity.getUser();
        this.modifiedDate = entity.getModifiedDate();
    }
}
