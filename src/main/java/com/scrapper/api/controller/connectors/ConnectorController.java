package com.scrapper.api.controller.connectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scrapper.api.controller.WebController;
import com.scrapper.api.dto.AuthenticateDTO;
import com.scrapper.api.dto.SiteStatusDTO;

@RestController
@RequestMapping("/connector")
public class ConnectorController extends WebController{
    private static final Logger log = LoggerFactory.getLogger(ConnectorController.class);

    @GetMapping("/")
    public ResponseEntity<?> getConnectors() {
        log.info("Fetching all connectors");

        return ResponseEntity.ok(webService.getConnectors());
    }

    @GetMapping("/{connectorId}")
    public ResponseEntity<?> getConnectorSiteStatus(@PathVariable int connectorId) {
        log.info("Checking site availability for connector: {}", connectorId);

        SiteStatusDTO siteStatus = webService.checkSiteStatus(connectorId).join();

        if (siteStatus.getStatusCode() != 200) {
            return ResponseEntity.badRequest().body(siteStatus.getMessage());
        } else {
            return ResponseEntity.ok(siteStatus);
        }
    }

    @GetMapping("/{connectorId}/authenticate")
    public ResponseEntity<?> login(
            @PathVariable int connectorId,
            @Param(value = "") String username,
            @Param(value = "") String password) {

        log.info("Attempting login for connector: {}", connectorId);

        AuthenticateDTO auth = webService.login(connectorId, username, password).join();

        if (auth.getStatusCode() != 200) {
            return ResponseEntity.badRequest().body(auth.getMessage());
        } else {
            return ResponseEntity.ok(auth);
        }
    }

    @GetMapping("/{connectorId}/logoff")
    public ResponseEntity<?> logoff(
            @PathVariable int connectorId,
            @Param(value = "") String username,
            @Param(value = "") String password) {

        log.info("Attempting logoff for connector: {}", connectorId);

        AuthenticateDTO auth = webService.logoff(connectorId).join();

        if (auth.getStatusCode() != 200) {
            return ResponseEntity.badRequest().body(auth.getMessage());
        } else {
            return ResponseEntity.ok(auth);
        }
    }
}
