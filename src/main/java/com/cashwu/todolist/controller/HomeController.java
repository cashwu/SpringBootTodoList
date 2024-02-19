package com.cashwu.todolist.controller;

import com.cashwu.todolist.service.JwtTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

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

    @PostMapping("/api/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile uploadFile) throws IOException {

        String originalFilename = uploadFile.getOriginalFilename();

        String projectPath = System.getProperty("user.dir");
        String uploadPath = projectPath + File.separator + "upload";

        Path filePath = Paths.get(uploadPath + File.separator + originalFilename);
        Files.createDirectories(filePath.getParent());
        Files.copy(uploadFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return ResponseEntity.status(HttpStatus.OK)
                .body(String.format("%s upload success", originalFilename));

    }
}
