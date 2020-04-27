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

    public UserRequestDto(User obj) {
        this.name = obj.getName();
    }

    public User toUser() {
        return new User(null, name);
    }
}
