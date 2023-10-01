package com.scrapper.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @GetMapping("/")
    private ResponseEntity<?> info() {
        return ResponseEntity.ok("API Scrapper - Versão 1.0.0");
    }
}
