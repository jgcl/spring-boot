package com.application.dtos;

import com.application.entities.Message;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
public class MessageRequestDto {
    @Schema(example="5ea6f1925a50e86a4e0a266b", required=true)
    @Getter
    @Setter
    private String conversationId;

    @Getter
    @Setter
    private Instant timestamp;

    @Schema(example="5ea6e5f13d8c1f5b4730747d", required=true)
    @Getter
    @Setter
    private String from;

    @Schema(example="5ea6e5f33d8c1f5b4730747e", required=true)
    @Getter
    @Setter
    private String to;

    @Schema(example="Hello, how are you?", required=true)
    @Getter
    @Setter
    private String text;

    public MessageRequestDto(Message obj) {
        this.conversationId = obj.getConversationId();
        this.timestamp = obj.getTimestamp();
        this.from = obj.getFrom();
        this.to = obj.getTo();
        this.text = obj.getText();
    }

    public Message toMessage() {
        Message message = new Message();
            message.setConversationId(this.conversationId);
            message.setTimestamp(this.timestamp);
            message.setFrom(this.from);
            message.setTo(this.to);
            message.setText(this.text);
        return message;
    }
}
