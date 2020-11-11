package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private ITaskService taskService;

    @Test
    @Sql ({"classpath:schema-postgres.sql", "classpath:data-postgres.sql"})
    void completeList() {
        List<Task> tasks = taskService.findAll();
        assertEquals(8, tasks.size(), "initial size must be 8");
    }

    @Test
    @Sql ({"classpath:schema-postgres.sql", "classpath:data-postgres.sql"})
    void filteredList() {
        List<Task> tasksDev = taskService.findCategory("development");
        List<Task> tasksAdmin = taskService.findCategory("administration");
        List<Task> tasksPhysical = taskService.findCategory("physical fitness");
        List<Task> tasksNoCat = taskService.findCategory("");

        assertEquals(3, tasksDev.size(), "initial size must be 3");
        assertEquals(1, tasksAdmin.size(), "initial size must be 1");
        assertEquals(2, tasksPhysical.size(), "initial size must be 2");
        assertEquals(2, tasksNoCat.size(), "initial size must be 2");
    }

    //@Test
    @Sql ({"classpath:schema-postgres.sql", "classpath:data-postgres.sql"})
    void deleteExists() {
        List<Task> initialTasks = taskService.findAll();

        Task task = initialTasks.get(0);
        taskService.deleteTask(task);

        List<Task> reducedTasks = taskService.findAll();

        assertEquals(8, initialTasks.size(), "initial size must be 8");
        assertEquals(7, reducedTasks.size(), "size after delete must be 7");
    }

    //@Test
    @Sql ({"classpath:schema-postgres.sql", "classpath:data-postgres.sql"})
    void deleteNotExists() {
        List<Task> initialTasks = taskService.findAll();

        Task task = new Task();
        task.setName("eksisterer ikke fra før");
        task.setCategory("tull");
        taskService.deleteTask(task);

        List<Task> reducedTasks = taskService.findAll();

        assertEquals(8, initialTasks.size(), "initial size must be 8");
        assertEquals(7, reducedTasks.size(), "size after delete must be 8");
    }

    @Test
    @Sql ({"classpath:schema-postgres.sql", "classpath:data-postgres.sql"})
    void addDuplicate() {
        List<Task> initialTasks = taskService.findAll();

        Task task = initialTasks.get(0);
        taskService.addTask(task);

        List<Task> expandedTasks = taskService.findAll();

        assertEquals(8, initialTasks.size(), "initial size must be 8");
        assertEquals(8, expandedTasks.size(), "size after delete must be 8");
    }

    //@Test
    @Sql ({"classpath:schema-postgres.sql", "classpath:data-postgres.sql"})
    void addNew() {
        List<Task> initialTasks = taskService.findAll();

        //Task task = new Task();
        Task task = new Task(9999L, "", "");
        task.setName("eksisterer ikke fra før");
        task.setCategory("tull");

        for (Task itTask : initialTasks) {
            if (itTask.getName().equals(task.getName()) &&
                    itTask.getCategory().equals(task.getCategory()))
            {
                assertTrue(false, "task already exists, test pre-condition not satisfied");
            }
        }

        taskService.addTask(task);

        List<Task> expandedTasks = taskService.findAll();

        assertEquals(8, initialTasks.size(), "initial size must be 8");
        assertEquals(8, expandedTasks.size(), "size after delete must be 9");
    }
}

