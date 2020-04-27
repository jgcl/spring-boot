package com.application.resources;

import com.application.dtos.BotDto;
import com.application.entities.Bot;
import com.application.services.BotService;
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
@RequestMapping(value = "/bots")
public class BotResource {
    @Autowired
    private BotService botService;

    @GetMapping
    public ResponseEntity<List<BotDto>> findAll() {
        List<Bot> list = botService.getAll();
        List<BotDto> listDto = list.stream().map(x -> new BotDto(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BotDto.class)) })
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<BotDto> findByIdentifier(@PathVariable String id) {
        Bot bot = botService.findByIdentifier(id);
        return ResponseEntity.ok().body(new BotDto(bot));
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BotDto.class)) })
    })
    @PostMapping
    public ResponseEntity<BotDto> insert(@RequestBody BotDto dto) {
        Bot bot =  botService.fromDto(dto);
        bot =  botService.insert(bot);
        dto = new BotDto(bot);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BotDto> update(@PathVariable String id, @RequestBody BotDto dto) {
        Bot bot = botService.updateByIdentifier(id, dto);
        return ResponseEntity.ok().body(new BotDto(bot));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        botService.deleteByIdentifier(id);
        return ResponseEntity.ok().build();
    }
}
