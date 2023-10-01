package com.scrapper.api.controller;

import com.scrapper.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operation")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @PostMapping("/{connectorId}")
    public ResponseEntity<String> executeOperation(
        @PathVariable int connectorId,
        @Param(value = "") String username,
        @Param(value = "") String password) {
        // Execute o conector aqui
//        operationService.executeOperation(connectorId, username, password);
        return ResponseEntity.ok("Conector executado com sucesso!");
    }
}
