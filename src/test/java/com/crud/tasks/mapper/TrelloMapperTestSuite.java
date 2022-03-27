
package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloListDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

class TrelloMapperTestSuite {

    @Autowired
    TrelloMapper trelloMapper;

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
    }
}