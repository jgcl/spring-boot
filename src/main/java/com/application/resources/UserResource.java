package com.application.resources;

import com.application.dtos.UserRequestDto;
import com.application.entities.User;
import com.application.services.UserService;
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
@RequestMapping(value = "/users")
public class UserResource {
    @Autowired
    private UserService botService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> list = botService.getAll();
        return ResponseEntity.ok().body(list);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserRequestDto.class)) })
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findByIdentifier(@PathVariable String id) {
        User bot = botService.findById(id);
        return ResponseEntity.ok().body(bot);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserRequestDto.class)) })
    })
    @PostMapping
    public ResponseEntity<User> insert(@RequestBody UserRequestDto dto) {
        User bot =  botService.insert(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bot.getId())
                .toUri();

        return ResponseEntity.created(uri).body(bot);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable String id, @RequestBody UserRequestDto dto) {
        User user = botService.updateById(id, dto);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        botService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
