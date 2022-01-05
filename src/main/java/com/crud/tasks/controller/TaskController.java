package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {
//    @GetMapping(value = "getTaskList")
//    public List<TaskDto> getTasks() {
//        return new ArrayList<>();
//    }
//
//    @GetMapping(value = "getTasks")
//    public TaskDto getTask(Long taskId) {
//        return new TaskDto(1L, "test title", "test_content");
//    }
//
//    @DeleteMapping(value = "deleteTasks")
//    public void deleteTask(Long taskId) {
//    }
//
//    @PutMapping(value = "updateTasks")
//    public TaskDto updateTask(TaskDto taskDto) {
//        return new TaskDto(1L, "Edited test title", "Test content");
//    }
//
//    @PostMapping(value = "createTasks")
//    public void createTask(TaskDto taskDto) {
//    }

    private final DbService service;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskController(DbService service, TaskMapper taskMapper) {
        this.service = service;
        this.taskMapper = taskMapper;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasksById() {
        List<Task> tasks = service.getTaskById();
        return taskMapper.mapToTaskDtoList(tasks);
    }

}
