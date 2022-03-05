package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Component
public class TrelloClient {

    private final RestTemplate restTemplate;

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.api.username}")
    private String trelloUsername;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;

    private URI urlCreate() {
        return
                UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + trelloUsername + "/boards")
                        .queryParam("key", trelloAppKey)
                        .queryParam("token", trelloToken)
                        .queryParam("fields", "name,id")
                        .build()
                        .encode()
                        .toUri();
    }

    public List<TrelloBoardDto> getTrelloBoards() {

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(urlCreate(), TrelloBoardDto[].class);

        return Optional.ofNullable(boardsResponse)
                .map(Arrays::asList)
                .orElse(Collections.emptyList())
                .stream()
                .filter(n -> Objects.nonNull(n.getId()) && Objects.nonNull(n.getName()))
                .filter(n -> n.getName().contains("Kodilla"))
                .collect(Collectors.toList());
    }
}