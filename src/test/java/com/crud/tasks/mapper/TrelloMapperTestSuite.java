
package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class TrelloMapperTestSuite {

    private TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void testMapToBoard() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("L1", "List #1", false);
        List<TrelloListDto> listsDto = new ArrayList<>();
        listsDto.add(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("B1", "Board #1", listsDto);

        //When
        TrelloBoard trelloBoard = trelloMapper.mapToBoard(trelloBoardDto);

        //Then
        Assertions.assertEquals("B1", trelloBoard.getId());
        Assertions.assertEquals("Board #1", trelloBoard.getName());
        Assertions.assertEquals(1, trelloBoard.getLists().size());
    }

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("L1", "List #1", false);
        List<TrelloListDto> listsDto = new ArrayList<>();
        listsDto.add(trelloListDto);

        //When
        List<TrelloList> trelloList = trelloMapper.mapToList(listsDto);

        //Then
        Assertions.assertEquals(1, trelloList.size());
        Assertions.assertEquals("L1", trelloList.get(0).getId());
        Assertions.assertEquals("List #1", trelloList.get(0).getName());
        Assertions.assertEquals(false, trelloList.get(0).isClosed());

    }

    @Test
    public void testMapToBoards() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("L1", "List #1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("L2", "List #2", true);
        List<TrelloListDto> listsDto1 = new ArrayList<>();
        List<TrelloListDto> listsDto2 = new ArrayList<>();
        listsDto1.add(trelloListDto1);
        listsDto2.add(trelloListDto2);
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("B1", "Board #1", listsDto1);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("B2", "Board #2", listsDto2);
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(trelloBoardDto1);
        trelloBoardsDto.add(trelloBoardDto2);

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);

        //Then
        Assertions.assertEquals(2, trelloBoards.size());
        Assertions.assertEquals("B1", trelloBoards.get(0).getId());
        Assertions.assertEquals("B2", trelloBoards.get(1).getId());
        Assertions.assertEquals("L1", trelloBoards.get(0).getLists().get(0).getId());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("L1", "List #1", false);
        TrelloList trelloList2 = new TrelloList("L2", "List #2", true);
        List<TrelloList> lists1 = new ArrayList<>();
        List<TrelloList> lists2 = new ArrayList<>();
        lists1.add(trelloList1);
        lists2.add(trelloList2);
        TrelloBoard trelloBoard1 = new TrelloBoard("B1", "Board #1", lists1);
        TrelloBoard trelloBoard2 = new TrelloBoard("B2", "Board #2", lists2);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);

        //When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        Assertions.assertEquals(2, trelloBoardsDto.size());
        Assertions.assertEquals("B1", trelloBoardsDto.get(0).getId());
        Assertions.assertEquals("B2", trelloBoardsDto.get(1).getId());
        Assertions.assertEquals("L1", trelloBoardsDto.get(0).getLists().get(0).getId());
    }

    @Test
    public void testToListDto() {
        //Given
        TrelloList trelloList = new TrelloList("L1", "List #1", false);
        List<TrelloList> lists = new ArrayList<>();
        lists.add(trelloList);

        //When
        List<TrelloListDto> trelloListDto = trelloMapper.mapToListDto(lists);

        //Then
        Assertions.assertEquals(1, trelloListDto.size());
        Assertions.assertEquals("L1", trelloListDto.get(0).getId());
        Assertions.assertEquals("List #1", trelloListDto.get(0).getName());
        Assertions.assertEquals(false, trelloListDto.get(0).isClosed());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("C1", "Card #1", "P1", "ID1");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assertions.assertEquals("C1", trelloCardDto.getName());
        Assertions.assertEquals("Card #1", trelloCardDto.getDescription());
        Assertions.assertEquals("P1", trelloCardDto.getPos());
        Assertions.assertEquals("ID1", trelloCardDto.getListId());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("C1", "Card #1", "P1", "ID1");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        Assertions.assertEquals("C1", trelloCard.getName());
        Assertions.assertEquals("Card #1", trelloCard.getDescription());
        Assertions.assertEquals("P1", trelloCard.getPos());
        Assertions.assertEquals("ID1", trelloCard.getListId());
    }
}