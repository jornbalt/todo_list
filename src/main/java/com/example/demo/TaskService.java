package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService implements ITaskService {

    @Autowired
    private TaskRepository repository;

    @Override
    public List<Task> findAll() {

        var tasks = (List<Task>) repository.findAll();

        return tasks;
    }

    @Override
    public List<Task> findCategory(String category) {

        var allTasks = this.findAll();

        List<Task> tasks = new ArrayList<Task>();

        for (Task task : allTasks) {
            if (task.getCategory().equals(category)) {
                tasks.add(task);
            }
        }

        return tasks;
    }

    @Override
    public void deleteTask(Task task) {
        repository.delete(task);
    }

    @Override
    public void addTask(Task task) {

        List<Task> allTasks = this.findAll();

        for (Task itTask : allTasks) {
            if (itTask.getName().equals(task.getName()) &&
                    itTask.getCategory().equals(task.getCategory()))
            {
                return;
            }
        }

        repository.save(task);
    }
}
