package com.scrapper.connectors.web.lesteTelecom;

import com.scrapper.api.dto.AuthenticateDTO;
import com.scrapper.api.dto.ConnectorStatusDTO;
import com.scrapper.connectors.Connector;
import com.scrapper.models.connector.Status;
import com.scrapper.util.http.Browser;
import com.scrapper.util.http.Response;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LesteTelecom implements Connector {
    protected static final int ID = 1;
    protected static final String NAME = "Leste Telecom";
    protected static final String BASE_URL = "https://central.lestetelecom.com.br/";

    @Override
    public Status checkConnStatus(Browser browser) {
        Status status = new Status();

        Response response = browser.navigate(BASE_URL);
        Document doc = response.document();

        status.setStatusCode(response.statusCode());
        status.setTitle(doc.title());
        status.setName(doc.getElementsByClass("name").text());
        // dto.setResponseTime(end - start);

        return status;
    }

    @Override
    public AuthenticateDTO authenticate(Browser browser, String username, String password) {
        AuthenticateDTO response = new AuthenticateDTO();

        response.setMessage("Not Implemented");
        return response;
    }

    @Override
    public AuthenticateDTO logoff(Browser browser) {
        AuthenticateDTO response = new AuthenticateDTO();

        response.setMessage("Not Implemented");

        return response;
    }

    @Override
    public AuthenticateDTO executeOperation(Browser browser, String username, String password) {
        AuthenticateDTO response = new AuthenticateDTO();

        response.setMessage("Not Implemented");

        return response;
    }

    public int getId() {
        return ID;
    }

    public String getName() {
        return NAME;
    }
}
