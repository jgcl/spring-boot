package com.application.services;

import com.application.dtos.BotDto;
import com.application.entities.Bot;
import com.application.repositories.BotRepository;
import com.application.services.exceptions.ObjectNotFoundException;
import com.application.services.exceptions.ValidateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BotService {
    @Autowired
    private BotRepository botRepository;

    public List<Bot> getAll() {
        return botRepository.findAll();
    }

    public Bot findByIdentifier(String id) {
        Optional<Bot> bot = botRepository.findByIdentifier(id);
        return bot.orElseThrow(() -> new ObjectNotFoundException("Bot not found: "+id));
    }

    public Bot fromDto(BotDto dto) {
        return new Bot(null, dto.getId(), dto.getName());
    }

    private void validation(String id, String name) {
        if(id == null) {
            throw new ValidateException("The id field is empty");
        }

        if(name == null) {
            throw new ValidateException("The name field is empty");
        }
    }

    public Bot insert(Bot bot) {
        validation(bot.getIdentifier(), bot.getName());
        if(botRepository.findByIdentifier(bot.getIdentifier()).isPresent()) {
            throw new ValidateException("The ID is already in use");
        }

        return botRepository.insert(bot);
    }

    public Bot updateByIdentifier(String identifier, BotDto dto) {
        validation(identifier, dto.getName());

        Bot bot = findByIdentifier(identifier);
        bot.setName(dto.getName());
        return botRepository.save(bot);
    }

    public void deleteByIdentifier(String identifier) {
        Bot bot = findByIdentifier(identifier);
        botRepository.delete(bot);
    }
}
