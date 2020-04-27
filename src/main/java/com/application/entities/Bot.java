package com.application.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"id", "identifier", "name"})
@EqualsAndHashCode(of = {"id"})
@Document(collection = "bots")
public class Bot {
    @Getter
    @Setter
    @Id
    private String id;

    @Getter
    @Setter
    @Indexed
    private String identifier;

    @Getter
    @Setter
    private String name;
}
