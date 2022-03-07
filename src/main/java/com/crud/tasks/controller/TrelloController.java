package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBadgeDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
public class TrelloController {

    private final TrelloClient trelloClient;

    @GetMapping("getTrelloBoards")
    public void getTrelloBoards() {
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
        trelloBoards.forEach(trelloBoardDto -> {
            System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName());

            System.out.println("This board contains lists: ");

            trelloBoardDto.getLists().forEach(trelloList -> {
                System.out.println(
                        trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()
                );
            });
        });
    }

    @PostMapping("createTrelloCard")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloClient.createNewCard(trelloCardDto);
    }

    @GetMapping("getTrelloBadges")
    public void getTrelloBadge() {
        List<TrelloBadgeDto> trelloBadges = trelloClient.getBadge();
        trelloBadges.forEach((trelloBadgeDto -> {
            System.out.println("Badge votes: " + trelloBadgeDto.getVotes());
        }));
    }
}