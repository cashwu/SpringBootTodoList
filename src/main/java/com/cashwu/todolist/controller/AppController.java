package com.cashwu.todolist.controller;

import com.cashwu.todolist.entity.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

/**
 * @author cash
 */
@Controller
public class AppController {
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("helloKey", "Hello World ~");
        Todo todo = new Todo();
        todo.setId(1);
        todo.setTask("tt");
        todo.setStatus(123);
        model.addAttribute("todo", todo);

        model.addAttribute("gender", "female");

        var list = new ArrayList<Integer>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        model.addAttribute("list", list);

        return "hello";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Todo todo, Model model) {
        model.addAttribute("todo", todo);
        return "add";
    }

}
