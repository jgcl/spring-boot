package com.application.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"id", "conversationId", "timestamp", "from", "to", "text"})
@EqualsAndHashCode(of = {"id"})
@Document(collection = "messages")
public class Message {
    @Getter
    @Setter
    @Id
    private String id;

    @Getter
    @Setter
    @Indexed
    private String conversationId;

    @Getter
    @Setter
    @Indexed
    private Instant timestamp;

    @Getter
    @Setter
    @Indexed
    private String from;

    @Getter
    @Setter
    @Indexed
    private String to;

    @Getter
    @Setter
    private String text;
}
