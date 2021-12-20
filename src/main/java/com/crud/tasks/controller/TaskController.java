package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {
    @GetMapping(value = "getTaskList")
    public List<TaskDto> getTasks() {
        return new ArrayList<>();
    }

    @GetMapping(value = "getTasks")
    public TaskDto getTask(Long taskId) {
        return new TaskDto(1L, "test title", "test_content");
    }

    @DeleteMapping(value = "deleteTasks")
    public void deleteTask(Long taskId) {
    }

    @PutMapping(value = "updateTasks")
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto(1L, "Edited test title", "Test content");
    }

    @PostMapping(value = "createTasks")
    public void createTask(TaskDto taskDto) {
    }
}
