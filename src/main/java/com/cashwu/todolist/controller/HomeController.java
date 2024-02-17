package com.cashwu.todolist.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author cash
 */
@RestController
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public String index() {

//        var bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        var encode = bCryptPasswordEncoder.encode("cash-1234");
//        logger.info(encode);

        logger.info("index");

        return "Hello World";
    }

}
