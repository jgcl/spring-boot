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
    private UserService userService;

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success", content = { @Content(mediaType = "application/json") })
    })
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> list = userService.getAll();
        return ResponseEntity.ok().body(list);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) })
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findByIdentifier(@PathVariable String id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Success", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) })
    })
    @PostMapping
    public ResponseEntity<User> insert(@RequestBody UserRequestDto dto) {
        User user =  userService.insert(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) })
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable String id, @RequestBody UserRequestDto dto) {
        User user = userService.updateById(id, dto);
        return ResponseEntity.ok().body(user);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
