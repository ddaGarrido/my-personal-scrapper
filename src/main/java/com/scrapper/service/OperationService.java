package com.scrapper.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scrapper.api.dto.AuthenticateDTO;
import com.scrapper.connectors.Connector;
import com.scrapper.models.Operation;
import com.scrapper.models.RequestLog;

@Service
public class OperationService {

    private Operation currentOperation;

    public void startNewOperation() {
        if (currentOperation == null){
            currentOperation = new Operation();
        }
    }

    public void addRequest(RequestLog requestLog) {
        if (currentOperation == null) {
            startNewOperation();
        }
        currentOperation.getRequests().add(requestLog);
    }

    public void saveOperationToFile() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(currentOperation);
            Files.write(Paths.get("operation_" + currentOperation.getTimestamp() + ".json"), json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Async
    public CompletableFuture<AuthenticateDTO> executeOperation(int connectorId, String username, String password) {
        Connector connector = ConnectorService.getConnector(connectorId);
        AuthenticateDTO authenticateDto = connector.executeOperation(null, username, password);
        saveOperationToFile();

        return CompletableFuture.completedFuture(authenticateDto);
    }
}
