package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBadgeDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
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
                        .queryParam("lists", "all")
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

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/cards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId())
                .build()
                .encode()
                .toUri();

        return restTemplate.postForObject(url, null, CreatedTrelloCard.class);
    }

    public List<TrelloBadgeDto> getBadge() {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/boards/61be354380d85c7f0e9a725e/cards")
                .queryParam("fields", "id,badges")
                .queryParam("card_fields", "id,badges")
                .queryParam("badges_fields", "votes,attachmentsByType")
                .queryParam("attachmentsByType_fields", "trello")
                .queryParam("trello_fields", "board,card")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .build()
                .encode()
                .toUri();

        TrelloBadgeDto[] badgeResponse = restTemplate.getForObject(url, TrelloBadgeDto[].class);

        return Optional.ofNullable(badgeResponse)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    public List<TrelloCardDto> getBadges() {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/boards/61be354380d85c7f0e9a725e/cards")
                .queryParam("fields", "id,badges")
                .queryParam("card_fields", "id,badges")
                .queryParam("badges_fields", "votes,attachmentsByType")
                .queryParam("attachmentsByType_fields", "trello")
                .queryParam("trello_fields", "board,card")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .build()
                .encode()
                .toUri();

        TrelloCardDto[] cardResponse = restTemplate.getForObject(url, TrelloCardDto[].class);

        return Optional.ofNullable(cardResponse)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }
}