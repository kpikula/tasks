
package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
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
        TrelloListDto trelloListDto2 = new TrelloListDto("L2", "List #2", false);
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


}
//
//    public List<TrelloBoardDto> mapToBoardsDto(final List<TrelloBoard> trelloBoards) {
//        return trelloBoards.stream()
//                .map(trelloBoard ->
//                        new TrelloBoardDto(trelloBoard.getId(), trelloBoard.getName(), mapToListDto(trelloBoard.getLists())))
//                .collect(toList());
//    }
//
//    public List<TrelloListDto> mapToListDto(final List<TrelloList> trelloLists) {
//        return trelloLists.stream()
//                .map(trelloList -> new TrelloListDto(trelloList.getId(), trelloList.getName(), trelloList.isClosed()))
//                .collect(toList());
//    }
//
//    public TrelloCardDto mapToCardDto(final TrelloCard trelloCard) {
//        return new TrelloCardDto(trelloCard.getName(), trelloCard.getDescription(), trelloCard.getPos(), trelloCard.getListId());
//    }
//
//    public TrelloCard mapToCard(final TrelloCardDto trelloCardDto) {
//        return new TrelloCard(trelloCardDto.getName(), trelloCardDto.getDescription(), trelloCardDto.getPos(), trelloCardDto.getListId());
//    }