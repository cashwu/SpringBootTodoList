package com.cashwu.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author cash
 */
@Controller
public class AppController {

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

}
