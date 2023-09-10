package com.scrapper.connectors.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scrapper.api.dto.AuthenticateDTO;
import com.scrapper.api.dto.SiteStatusDTO;
import com.scrapper.connectors.Connector;
import com.scrapper.util.FormData;
import com.scrapper.util.Http;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class BaseConnector implements Connector {

    private static final Logger log = LoggerFactory.getLogger(BaseConnector.class);
    private final Http http;

    public static int ID = 1;
    protected static final String NAME = "Scrap This Site";
    protected static final String BASE_URL = "http://www.scrapethissite.com/login/";

    @Autowired
    public BaseConnector() {
        http = new Http();
    }

    @Override
    public SiteStatusDTO checkSiteStatus() {
        SiteStatusDTO response = new SiteStatusDTO();
        long start = System.currentTimeMillis();

        Response resp = http.get(BASE_URL);
        long end = System.currentTimeMillis();

        Document doc = http.getDocument(resp);

        response.setStatusCode(resp.statusCode());
        response.setTitle(doc.title());
        response.setSiteName(doc.title());
        response.setResponseTime(end - start);

        response.setDescription(doc.selectFirst("meta[name=description]").attr("content"));
        response.setLogoURL(doc.selectFirst("img[id=nav-logo]").attr("src"));

        boolean hasLoginForm = doc.getElementsByClass("form") != null;
        boolean hasUsernameField = doc.selectFirst("input[name=email]") != null;
        boolean hasPasswordField = doc.selectFirst("input[name=password]") != null;

        response.setHasLoginForm(hasLoginForm);
        response.setHasUsernameField(hasUsernameField);
        response.setHasPasswordField(hasPasswordField);

        // Set message
        response.setMessage("Site status check completed successfully");

        return response;
    }

    @Override
    public AuthenticateDTO authenticate(String username, String password) {
        AuthenticateDTO response = new AuthenticateDTO();

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        FormData formData = new FormData();
        formData.put("email", username);
        formData.put("password", password);

        Response resp = http.post(BASE_URL, formData, headers, null);

        Document doc = http.getDocument(resp);

        if (doc.html().contains("new PNotify(")) { 
            int idx = doc.html().indexOf("title: ") + 7;
            String title = doc.html().substring(idx, idx + 40);
            response.setStatusCode(resp.statusCode());
            response.setMessage(title);
            response.setSuccess(false);
            return response;
        }

        response.setStatusCode(resp.statusCode());
        response.setMessage(password);
        response.setSuccess(true);

        return response;
    }

    @Override
    public AuthenticateDTO logoff() {
        AuthenticateDTO response = new AuthenticateDTO();

        // if
        // (driver.getCurrentUrl().contains("practicetestautomation.com/logged-in-successfully"))
        // {
        // response.setStatusCode(200);
        // response.setMessage("Login realizado com sucesso");
        // response.setSuccess(true);

        // log.info("Authentication successful for {}", NAME);
        // } else {
        // response.setStatusCode(400);
        // response.setMessage("Falha ao realizar login");
        // response.setSuccess(false);

        // log.warn("Authentication failed for {}", NAME);
        // }
        log.info("Logoff method called for {}", NAME);
        return response;
    }

    public int getId() {
        return ID;
    }

    public String getName() {
        return NAME;
    }
}
