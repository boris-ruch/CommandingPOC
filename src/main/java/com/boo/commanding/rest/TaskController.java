package com.boo.commanding.rest;

import com.boo.commanding.repo.TaskRepository;
import com.boo.commanding.tasks.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    private final TaskRepository repository;

    @Autowired
    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{gatewayId}/tasks")
    public List<Task> findOpenTasks(@PathVariable String gatewayId) {
        return repository.findOpenTasks(gatewayId);
    }

}
