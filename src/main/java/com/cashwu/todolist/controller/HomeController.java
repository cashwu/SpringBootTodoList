package com.cashwu.todolist.controller;

import com.cashwu.todolist.service.JwtTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author cash
 */
@RestController
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final JwtTokenService jwtTokenService;

    public HomeController(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }


    @GetMapping("/")
    public String index() {

//        var bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        var encode = bCryptPasswordEncoder.encode("cash-1234");
//        logger.info(encode);

        logger.info("index");

        return "Hello World";
    }

    @PostMapping("/api/login")
    public ResponseEntity<String> login() {

        var token = jwtTokenService.generateToken();

        return ResponseEntity.status(HttpStatus.OK)
                .body(token);

    }
}
