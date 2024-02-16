package com.cashwu.todolist.controller;

import com.cashwu.todolist.entity.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author cash
 */
@Controller
public class AppController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("helloKey", "Hello World ~");
        Todo todo = new Todo();
        todo.setId(1);
        todo.setTask("tt");
        todo.setStatus(123);
        model.addAttribute("todo", todo);
        return "hello";
    }

}
