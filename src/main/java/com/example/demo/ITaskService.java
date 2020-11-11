package com.example.demo;

import java.util.List;

public interface ITaskService {
        List<Task> findAll();
        List<Task> findCategory(Category category);
        void deleteTask(Task task);
        void addTask(Task task);
}
