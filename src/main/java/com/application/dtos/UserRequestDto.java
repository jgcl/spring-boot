package com.application.dtos;

import com.application.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @Getter
    @Setter
    private String name;

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
