package com.scrapper.api.controller;

import com.scrapper.api.dto.ConnectorsDTO;
import com.scrapper.service.ConnectorService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/connectors")
public class ConnectorController {
    private static final Logger log = LoggerFactory.getLogger(ConnectorController.class);

    @Resource
    ConnectorService connectorService;

    @GetMapping(value = {""})
    public ResponseEntity<?> getConnectors() {
        log.info("getConnectors - Fetching all connectors");


        List<ConnectorsDTO> connList = connectorService.getConnectors();

        log.info("getConnectors - Connectors fetched: {}", connList.size());

        return ResponseEntity.ok(connList);
    }

//    @GetMapping("/{connectorId}")
//    public ResponseEntity<?> getConnectorSiteStatus(@PathVariable int connectorId) {
//        log.info("Checking site availability for connector: {}", connectorId);
//
//        Status connStatus = connectorService.checkConnStatus(connectorId).join();
//
//        if (connStatus.getStatusCode() != 200) {
//            return ResponseEntity.badRequest().body(connStatus);
//        } else {
//            return ResponseEntity.ok(connStatus);
//        }
//    }
//
//    @GetMapping("/{connectorId}/authenticate")
//    public ResponseEntity<?> authenticate(
//            @PathVariable int connectorId,
//            @Param(value = "") String username,
//            @Param(value = "") String password) {
//
//        log.info("Attempting login for connector: {}", connectorId);
//
//        AuthenticateDTO auth = connectorService.authenticate(connectorId, username, password).join();
//
//        if (auth.getStatusCode() != 200) {
//            return ResponseEntity.badRequest().body(auth.getMessage());
//        } else {
//            return ResponseEntity.ok(auth);
//        }
//    }
//
//    @GetMapping("/{connectorId}/logoff")
//    public ResponseEntity<?> logoff(
//            @PathVariable int connectorId,
//            @Param(value = "") String username,
//            @Param(value = "") String password) {
//
//        log.info("Attempting logoff for connector: {}", connectorId);
//
//        AuthenticateDTO auth = connectorService.logoff(connectorId).join();
//
//        if (auth.getStatusCode() != 200) {
//            return ResponseEntity.badRequest().body(auth.getMessage());
//        } else {
//            return ResponseEntity.ok(auth);
//        }
//    }
}
