package com.application;

import com.application.dtos.BotDto;
import com.application.entities.Bot;
import com.application.repositories.BotRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {
    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate = new TestRestTemplate();

    @Autowired
    BotRepository botRepository;

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testIndexPage() {
        String body = this.restTemplate.getForObject("/", String.class);
        assertThat(body).isEqualTo("ok");
    }

    @Test
    public void testGetBotsAPI() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        String expected = getJsonString(botRepository.findAll().stream().map(BotDto::new).collect(Collectors.toList()));
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/bots"), HttpMethod.GET, entity, String.class);

        JSONAssert.assertEquals(expected, response.getBody(), false);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void testGetBotByIdAPI() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        Bot bot = botRepository.findFirstByOrderByIdAsc();
        if(bot == null) {
            assertThat(true).isEqualTo(true);
            return;
        }
        String expected = getJsonString(new BotDto(bot));
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/bots/"+bot.getIdentifier()), HttpMethod.GET, entity, String.class);

        JSONAssert.assertEquals(expected, response.getBody(), false);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    private String getJsonString(Object object) throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
