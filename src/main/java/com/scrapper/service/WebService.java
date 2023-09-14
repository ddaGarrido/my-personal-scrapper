package com.scrapper.service;

import com.scrapper.api.dto.AuthenticateDTO;
import com.scrapper.api.dto.ConnectorsDTO;
import com.scrapper.api.dto.ConnectorStatusDTO;
import com.scrapper.connectors.Connector;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class WebService {
    private static List<Connector> connectors = new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(WebService.class);

    public WebService(List<Connector> availableConnectors) {
        log.info("\n\nNumber of connectors detected: " + availableConnectors.size() + "\n\n");
        connectors = availableConnectors;
    }

    @Cacheable("connectors")
    public List<ConnectorsDTO> getConnectors() {
        List<ConnectorsDTO> connectorsDTO = new ArrayList<>();
        for (Connector connector : connectors) {
            connectorsDTO.add(new ConnectorsDTO(connector.getId(), connector.getName()));
        }
        return connectorsDTO;
    }

    @Cacheable("connectorStatus")
    @Async
    public CompletableFuture<ConnectorStatusDTO> checkConnStatus(int connectorId) {
        Connector connector = getConnector(connectorId);
        ConnectorStatusDTO checkDto = connector.checkConnStatus();

        return CompletableFuture.completedFuture(checkDto);
    }

    @Async
    public CompletableFuture<AuthenticateDTO> authenticate(int connectorId, String username, String password) {
        Connector connector = getConnector(connectorId);
        AuthenticateDTO authenticateDto = connector.authenticate(username, password);

        return CompletableFuture.completedFuture(authenticateDto);
    }

    @Async
    public CompletableFuture<AuthenticateDTO> logoff(int connectorId) {
        Connector connector = getConnector(connectorId);
        AuthenticateDTO authenticateDto = connector.logoff();

        return CompletableFuture.completedFuture(authenticateDto);
    }

    public static Connector getConnector(int connectorId) {
        return connectors
            .stream()
            .filter(conn -> conn.getId() == connectorId)
            .findFirst()
            .orElse(null);
    }
}
