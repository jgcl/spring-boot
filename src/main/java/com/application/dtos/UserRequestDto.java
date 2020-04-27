package com.application.dtos;

import com.application.entities.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @Schema(example="Jessica Abigail", required=true)
    @Getter
    @Setter
    private String name;

    @Schema(example="jessica@gmail.com", required=true)
    @Getter
    @Setter
    private String email;

    public UserRequestDto(User obj) {
        this.name = obj.getName();
        this.email = obj.getEmail();
    }

    public User toUser() {
        return new User(null, name, email);
    }
}
