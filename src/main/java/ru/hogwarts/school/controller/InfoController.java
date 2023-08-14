package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/getPort")
public class InfoController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping
    public ResponseEntity<String> getServerPort() {
        return ResponseEntity.ok("server port = " + serverPort);
    }
}
