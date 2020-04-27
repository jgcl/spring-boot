package com.application.resources;

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

@RestController
@RequestMapping(value = "/messages")
public class MessageResource {
    @Autowired
    private MessageService messageService;

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success", content = { @Content(mediaType = "application/json") })
    })
    @GetMapping
    public ResponseEntity<List<Message>> findByConversationId(@RequestParam(value="conversationId") String conversationId) {
        List<Message> list = messageService.findByConversationId(conversationId);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Message.class)) })
    })
    public ResponseEntity<Message> findById(@PathVariable String id) {
        Message message = messageService.findById(id);
        return ResponseEntity.ok().body(message);
    }

    @PostMapping(consumes = { "application/json" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Success", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Message.class)) })
    })
    public ResponseEntity<Message> insert(@RequestBody MessageRequestDto dto) {
        Message message = messageService.insert(dto.toMessage());

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(message.getId())
                .toUri();

        return ResponseEntity.created(uri).body(message);
    }
}
