package com.scrapper.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scrapper.service.OperationService;

@RestController
@RequestMapping("/operation")
public class OperationController extends ApiController{

    @Autowired
    private OperationService operationService;

    @PostMapping("/{connectorId}/execute")
    public ResponseEntity<String> executeOperation(
        @PathVariable int connectorId,
        @Param(value = "") String username,
        @Param(value = "") String password) {
        // Execute o conector aqui
        operationService.executeOperation(connectorId, username, password);
        return ResponseEntity.ok("Conector executado com sucesso!");
    }
}
