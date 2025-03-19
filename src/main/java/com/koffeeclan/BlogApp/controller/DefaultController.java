package com.koffeeclan.BlogApp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DefaultController {

    @GetMapping
    public ResponseEntity<String> getDefault(){
        return ResponseEntity.ok("Blog App Default Request Working");
    }
}
