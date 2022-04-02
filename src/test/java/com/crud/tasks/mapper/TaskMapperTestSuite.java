package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskMapperTestSuite {

    private TaskMapper taskMapper = new TaskMapper();

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "taskDto1", "taskDto1 content");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals("taskDto1", task.getTitle());
        assertEquals("taskDto1 content", task.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "task1", "task1 content");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals("task1", taskDto.getTitle());
        assertEquals("task1 content", taskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        Task task = new Task(1L, "task1", "task1 content");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);

        //When
        List<TaskDto> taskDto = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals(1, taskList.size());
        assertEquals("task1", taskDto.get(0).getTitle());
        assertEquals("task1 content", taskDto.get(0).getContent());
    }
}