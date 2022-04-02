package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskRepositoryTestSuite {
    @Autowired
    TaskRepository taskRepository;

    @Test
    public void testSaveTask() {

       //Given
         Task task = Task.builder()
                 .id(1L)
                 .title("task1")
                 .content("task1 content")
                 .build();

         //When
        taskRepository.save(task);
        List<Task> tasks = taskRepository.findAll();

        //Then
        assertEquals("task1 content", tasks.get(1).getContent());

        //Cleanup
        taskRepository.deleteAll();
    }
}