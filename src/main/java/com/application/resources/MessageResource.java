package com.application.resources;

import com.application.dtos.MessageDto;
import com.application.dtos.MessageRequestDto;
import com.application.entities.Message;
import com.application.services.MessageService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/messages")
public class MessageResource {
    @Autowired
    private MessageService messageService;

    @GetMapping
    public ResponseEntity<List<MessageDto>> findByConversationId(@RequestParam(value="conversationId") String conversationId) {
        List<Message> list = messageService.findByConversationId(conversationId);
        List<MessageDto> listDto = list.stream().map(x -> new MessageDto(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class)) })
    })
    public ResponseEntity<MessageDto> findById(@PathVariable String id) {
        Message message = messageService.findById(id);
        return ResponseEntity.ok().body(new MessageDto(message));
    }

    @PostMapping(consumes = { "application/json" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class)) })
    })
    public ResponseEntity<MessageDto> insert(@RequestBody MessageRequestDto dto) {
        MessageDto responseDto = new MessageDto(messageService.insert(dto.toMessage()));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(responseDto);
    }
}
