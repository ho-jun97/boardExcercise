package com.example.board.web.dto;

import com.example.board.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserListResponseDto {
    private Long id;
    private String name;
    private String email;
    private String modifiedDate;

    @Builder
    public UserListResponseDto(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.modifiedDate = user.getModifiedDate();
    }
}
