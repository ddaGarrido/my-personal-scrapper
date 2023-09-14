package com.scrapper.connectors.web.lesteTelecom;

import org.springframework.stereotype.Component;

import com.scrapper.api.dto.AuthenticateDTO;
import com.scrapper.api.dto.ConnectorStatusDTO;
import com.scrapper.connectors.Connector;
import com.scrapper.util.http.FormData;
import com.scrapper.util.http.Headers;
import com.scrapper.util.http.Http;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class LesteTelecom implements Connector {

    private static final Logger log = LoggerFactory.getLogger(LesteTelecom.class);
    private final Http http;

    protected static final int ID = 1;
    protected static final String NAME = "Leste Telecom";
    protected static final String BASE_URL = "https://central.lestetelecom.com.br/";

    public LesteTelecom() {
        http = new Http();
    }

    @Override
    public ConnectorStatusDTO checkConnStatus() {
        ConnectorStatusDTO response = new ConnectorStatusDTO();
        long start = System.currentTimeMillis();

        Response resp = http.get(BASE_URL);
        long end = System.currentTimeMillis();

        Document doc = http.getDocument(resp);

        response.setStatusCode(resp.statusCode());
        response.setTitle(doc.title());
        response.setSiteName(doc.getElementsByClass("name").text());
        response.setResponseTime(end - start);

        response.setDescription(null);
        response.setLogoURL(doc.select("img").attr("src"));

        boolean hasLoginForm = doc.getElementsByClass("form") != null;
        boolean hasUsernameField = doc.selectFirst("input[name=login]") != null;
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

        Headers headers = new Headers();
        headers.set("Content-Type", "application/x-www-form-urlencoded");

        Response resp = http.get(BASE_URL);
        Document doc = http.getDocument(resp);

        FormData formData = new FormData();
        formData.put("cpf_cnpj", "F");
        formData.put("login", formatarCPF(username));
        formData.put("password", password);
        formData.put("typeLogin", doc.select("input[name=typeLogin]").attr("value"));
        formData.put("op", "Logar");
        formData.put("form_build_id", doc.select("input[name=form_build_id]").attr("value"));
        formData.put("form_id", doc.select("input[name=form_id]").attr("value"));

        resp = http.post(BASE_URL, formData, headers, null);
        doc = http.getDocument(resp);

        if (!doc.select("div[class*=alert]").isEmpty()) { 
            response.setStatusCode(resp.statusCode());
            response.setMessage(doc.select("div[class*=alert]").text());
            response.setSuccess(false);
            return response;
        }

        response.setStatusCode(resp.statusCode());
        response.setMessage(null);
        response.setSuccess(true);

        return response;
    }

    @Override
    public AuthenticateDTO logoff() {
        AuthenticateDTO response = new AuthenticateDTO();
        
        Document doc = http.getDocument();

        if (!doc.getElementsContainingOwnText("Sair").isEmpty()) {
            Response resp = http.get("https://central.lestetelecom.com.br/soucliente/sair");
            response.setStatusCode(resp.statusCode());
            response.setSuccess(true);
            response.setMessage("Logout realizado com sucesso");
        } else {
            response.setStatusCode(500);
            response.setSuccess(false);
            response.setMessage("Erro ao realizar logout");
        }

        log.info("Logoff method called for {}", NAME);
        return response;
    }

    @Override
    public AuthenticateDTO executeOperation(String username, String password) {
        AuthenticateDTO response = new AuthenticateDTO();
        
        checkConnStatus();
        authenticate(username, password);
        logoff();
        
        response.setStatusCode(200);
        response.setSuccess(true);
        response.setMessage("Operação realizada com sucesso");
        return response;
    }

    public static String formatarCPF(String cpf){
        String cpfCompleto = StringUtils.leftPad(cpf, 11, '0');
        return cpfCompleto.substring(0,3)+"."+cpfCompleto.substring(3,6)+"."+cpfCompleto.substring(6,9)+"-"+cpfCompleto.substring(9,11);
    }

    public int getId() {
        return ID;
    }

    public String getName() {
        return NAME;
    }
}
