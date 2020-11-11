package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
@Controller
public class TodoListController {

    @Autowired
    private ITaskService taskService;

    @GetMapping("/showList")
    public String findTasks(Model model) {

        var tasks = (List<Task>) taskService.findAll();

        model.addAttribute("tasks", tasks);

        return "showList";
    }
}
