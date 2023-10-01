package com.scrapper.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scrapper.models.Operation;
import com.scrapper.models.RequestLog;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

//    @Async
//    public CompletableFuture<AuthenticateDTO> executeOperation(int connectorId, String username, String password) {
//        Connector connector = ConnectorService.getConnector(connectorId);
//        AuthenticateDTO authenticateDto = connector.executeOperation(null, username, password);
//        saveOperationToFile();
//
//        return CompletableFuture.completedFuture(authenticateDto);
//    }
}
