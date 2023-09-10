package com.scrapper.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import com.scrapper.api.dto.AuthenticateDTO;
import com.scrapper.api.dto.SiteStatusDTO;
import com.scrapper.service.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class WebController {
    @Autowired
    private WebService webService;

    private static final Logger log = LoggerFactory.getLogger(WebController.class);

    @GetMapping("/")
    public ResponseEntity<?> info() {
        return ResponseEntity.ok("API Scrapper - Versão 1.0.0");
    }

    @GetMapping("/connectors")
    public ResponseEntity<?> getConnectors() {
        log.info("Fetching all connectors");

        return ResponseEntity.ok(webService.getConnectors());
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<?> getConnectorSiteStatus(@PathVariable int id) {
        log.info("Checking site availability for connector: {}", id);

        SiteStatusDTO siteStatus = webService.checkSiteStatus(id).join();

        if (siteStatus.getStatusCode() != 200) {
            return ResponseEntity.badRequest().body(siteStatus.getMessage());
        } else {
            return ResponseEntity.ok(siteStatus);
        }
    }

    @GetMapping("/login/{id}")
    public ResponseEntity<?> login(
            @PathVariable int id,
            @Param(value = "") String username,
            @Param(value = "") String password) {

        log.info("Attempting login for connector: {}", id);

        AuthenticateDTO auth = webService.login(id, username, password).join();

        if (auth.getStatusCode() != 200) {
            return ResponseEntity.badRequest().body(auth.getMessage());
        } else {
            return ResponseEntity.ok(auth);
        }
    }

    @GetMapping("/logoff/{id}")
    public ResponseEntity<?> logoff(
            @PathVariable int id,
            @Param(value = "") String username,
            @Param(value = "") String password) {

        log.info("Attempting logoff for connector: {}", id);

        AuthenticateDTO auth = webService.logoff(id).join();

        if (auth.getStatusCode() != 200) {
            return ResponseEntity.badRequest().body(auth.getMessage());
        } else {
            return ResponseEntity.ok(auth);
        }
    }
}
