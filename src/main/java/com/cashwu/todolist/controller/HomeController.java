package com.cashwu.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cash
 */
@RestController
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "Hello World";
    }

}
