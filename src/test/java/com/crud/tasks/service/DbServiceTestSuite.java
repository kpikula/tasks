package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DbServiceTestSuite {

    @Autowired
    private DbService dbService;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void testSaveTask() {
        //Given
        Task task = Task.builder()
                .title("task1")
                .build();

        //When
        Task task1 = dbService.saveTask(task);

        //Then
        assertEquals(1, taskRepository.count());
        assertEquals("task1", task1.getTitle());

        //Cleanup
        taskRepository.deleteAll();
        dbService.deleteTask(1L);
    }
}

//    public List<Task> getAllTasks() {
//        return repository.findAll();
//    }
//
//    public Task saveTask(final Task task) {
//        return repository.save(task);
//    }
//
//    public Optional<Task> getTask(final Long id) {
//        return repository.findById(id);
//    }
//
//    public void deleteTask(final Long id) {
//        repository.deleteById(id);
//    }