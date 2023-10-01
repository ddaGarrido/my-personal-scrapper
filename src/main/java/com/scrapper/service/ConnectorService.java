package com.scrapper.service;

import com.scrapper.api.dto.ConnectorsDTO;
import com.scrapper.connectors.Connector;
import com.scrapper.util.http.Browser;
import db.models.ConnectorModel;
import db.repositories.ConnectorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class ConnectorService {
    private static final Logger log = LoggerFactory.getLogger(ConnectorService.class);

    @Autowired
    private ConnectorRepository connectorRepository;

    private final Browser browser = new Browser();

    public ConnectorService(List<Connector> availableConnectors) {
        log.info("\nNumber of connectors detected: " + availableConnectors.size() + "\n");
        for (Connector connector : availableConnectors) {
            log.info("Connector: " + connector.getName());
            ConnectorModel connectorModel = new ConnectorModel(connector.getName(), connector.getName(), connector.getName());
            connectorRepository.save(connectorModel);
        }
    }

    @Cacheable("connectors")
    public List<ConnectorsDTO> getConnectors() {
        List<ConnectorsDTO> connectorsDTO = new ArrayList<>();
        List<ConnectorModel> connectorsFromDb = connectorRepository.findAll();
        for (ConnectorModel connector : connectorsFromDb) {
            connectorsDTO.add(new ConnectorsDTO(connector.getConnectorId(), connector.getName()));
        }
        return connectorsDTO;
    }

//    @Cacheable("connectorStatus")
//    @Async
//    public CompletableFuture<Status> checkConnStatus(int connectorId) {
//        Connector connector = getConnector(connectorId);
//        Status checkStatus = connector.checkConnStatus(browser);
//
//        return CompletableFuture.completedFuture(checkStatus);
//    }
//
//    @Async
//    public CompletableFuture<AuthenticateDTO> authenticate(int connectorId, String username, String password) {
//        Connector connector = getConnector(connectorId);
//        AuthenticateDTO authenticateDto = connector.authenticate(browser, username, password);
//
//        return CompletableFuture.completedFuture(authenticateDto);
//    }
//
//    @Async
//    public CompletableFuture<AuthenticateDTO> logoff(int connectorId) {
//        Connector connector = getConnector(connectorId);
//        AuthenticateDTO authenticateDto = connector.logoff(browser);
//
//        return CompletableFuture.completedFuture(authenticateDto);
//    }
//
//    public static Connector getConnector(ObjectId connectorId) {
//        return connectorRepository.findById(connectorId);
//    }
}
