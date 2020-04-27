package com.application.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = {"id"})
@Document(collection = "users")
public class User {
    @Getter
    @Setter
    @Id
    private String id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String email;
}
