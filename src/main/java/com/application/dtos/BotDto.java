package com.application.dtos;

import com.application.entities.Bot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class BotDto {
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String name;

    public BotDto(Bot obj) {
        this.id = obj.getIdentifier();
        this.name = obj.getName();
    }
}
