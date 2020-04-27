
package com.application.dtos;

import com.application.entities.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String conversationId;

    @Getter
    @Setter
    private String timestamp;

    @Getter
    @Setter
    private String from;

    @Getter
    @Setter
    private String to;

    @Getter
    @Setter
    private String text;

    public MessageDto(Message obj) {
        this.id = obj.getId();
        this.conversationId = obj.getConversationId();
        this.timestamp = obj.getTimestamp().toString();
        this.from = obj.getFrom();
        this.to = obj.getTo();
        this.text = obj.getText();
    }

    public Message toMessage() {
        Message message = new Message();
            message.setId(this.id);
            message.setConversationId(this.conversationId);
            message.setTimestamp(Instant.parse(this.timestamp));
            message.setFrom(this.from);
            message.setTo(this.to);
            message.setText(this.text);
        return message;
    }
}
