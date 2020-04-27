package com.application.services;

import com.application.entities.Bot;
import com.application.entities.Message;
import com.application.repositories.BotRepository;
import com.application.repositories.MessageRepository;
import com.application.services.exceptions.ObjectNotFoundException;
import com.application.services.exceptions.ValidateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private BotRepository botRepository;

    public Message findById(String id) {
        Optional<Message> message = messageRepository.findById(id);
        return message.orElseThrow(() -> new ObjectNotFoundException("Message not found: "+id));
    }

    public List<Message> findByConversationId(String conversationId) {
        return messageRepository.findByConversationId(conversationId);
    }

    public Message insert(Message message) {
        insertUpdateValidations(message);
        return messageRepository.insert(message);
    }

    private void insertUpdateValidations(Message message) {
        validation("conversationId", message.getConversationId());
        validation("from", message.getFrom());
        validation("to", message.getTo());
        validation("text", message.getText());
        validateToOrFromBot(message);
    }

    private void validateToOrFromBot(Message message) {
        Optional<Bot> from = botRepository.findByIdentifier(message.getFrom());
        Optional<Bot> to = botRepository.findByIdentifier(message.getTo());

        if(!from.isPresent() && !to.isPresent()) {
            throw new ValidateException("To or From must be a bot ID");
        }
    }

    private void validation(String field, String value) {
        if(value == null) {
            throw new ValidateException("The '"+field+"' field is empty");
        }
    }
}
