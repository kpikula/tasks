package com.crud.tasks.service;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloListDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloServiceTestSuite {

    @Autowired
    private TrelloService trelloService;

    @Test
    void shouldFetchTrelloBoards() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "TrelloListDto1", false);
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "TrelloBoardDto name", trelloListDtos);

        //When
        List<TrelloBoardDto> fetchedBoards = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(1, fetchedBoards.size());
    }

    @Test
    void createTrelloCard() {
    }
}
//public class TrelloListDto {//
//    private String id;
//    private String name;
//    private boolean isClosed;
//public class TrelloBoardDto
//    private String id;
//    private String name;
//    private List<TrelloListDto> lists;

//    public List<TrelloBoardDto> fetchTrelloBoards() {
//        return trelloClient.getTrelloBoards();
//    }
//
//    public CreatedTrelloCardDto createTrelloCard(final TrelloCardDto trelloCardDto) {
//        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);
//        ofNullable(newCard).ifPresent(card -> emailService.send(
//                new Mail(
//                        adminConfig.getAdminMail(),
//                        SUBJECT,
//                        "New card: " + trelloCardDto.getName() + " has been created on your Trello account",
//                        null
//                )));
//        return newCard;
//    }