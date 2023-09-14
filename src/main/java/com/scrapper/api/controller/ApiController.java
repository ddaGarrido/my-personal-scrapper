package com.scrapper.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.scrapper.service.WebService;

import java.util.Map;

@RestController
public class ApiController {
    @Autowired
    protected WebService webService;

    //private static final Logger log = LoggerFactory.getLogger(WebController.class);

    @GetMapping("/")
    public ResponseEntity<?> info() {
        return ResponseEntity.ok("API Scrapper - Versão 1.0.0");
    }

    @PostMapping("/executeFlow")
    public ResponseEntity<Map<String, Object>> executeFlow(@RequestParam String username, @RequestParam String password) {
        // Map<String, Object> data = webService.executeFullFlow(username, password);
        return ResponseEntity.ok(null);
    }
}
