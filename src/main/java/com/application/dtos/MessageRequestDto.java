package com.application.dtos;

import com.application.entities.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
public class MessageRequestDto {
    @Getter
    @Setter
    private String conversationId;

    @Getter
    @Setter
    private Instant timestamp;

    @Getter
    @Setter
    private String from;

    @Getter
    @Setter
    private String to;

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
