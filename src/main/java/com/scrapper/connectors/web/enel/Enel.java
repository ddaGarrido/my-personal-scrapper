package com.scrapper.connectors.web.enel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scrapper.api.dto.AuthenticateDTO;
import com.scrapper.api.dto.SiteStatusDTO;
import com.scrapper.connectors.Connector;
import com.scrapper.connectors.example.BaseConnector;
import com.scrapper.util.FormData;
import com.scrapper.util.Http;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class Enel implements Connector {

    private static final Logger log = LoggerFactory.getLogger(BaseConnector.class);
    private final Http http;

    protected static final int ID = 3;
    protected static final String NAME = "Enel";
    protected static final String BASE_URL = "https://www.enel.com.br/pt/login.html";

    @Autowired
    public Enel() {
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
        response.setSiteName(doc.getElementsByClass("name").text());
        response.setResponseTime(end - start);

        response.setDescription(null);
        response.setLogoURL(doc.select("img").attr("src"));

        boolean hasLoginForm = doc.getElementsByClass("form") != null;
        boolean hasUsernameField = doc.selectFirst("input[name=username]") != null;
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

        Response resp = http.get(BASE_URL);
        Document doc = http.getDocument(resp);
        
        FormData formData = new FormData();
        formData.put("spEntityID", "ENEL_RJO_WEB_BRA");

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");

        resp = http.get("https://accounts.enel.com/samlsso", formData, headers, null);
        doc = http.getDocument(resp);

        Map<String, String> headers2 = new HashMap<String, String>();
        headers2.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        headers2.put("Accept", "application/json, text/javascript, */*; q=0.01");
        headers2.put("X-Requested-With", "XMLHttpRequest");
        
        FormData formData2 = new FormData();
        formData2.put("commonAuthCallerPath", "/samlsso");
        formData2.put("forceAuth", "false");
        formData2.put("passiveAuth", "false");
        formData2.put("spEntityID", "ENEL_RJO_WEB_BRA");
        formData2.put("tenantDomain", "carbon.super");
        formData2.put("sessionDataKey", "080e9a54-60f2-4d7a-9ec2-ca6f23f12f39");
        formData2.put("relyingParty", "ENEL_RJO_WEB_BRA");
        formData2.put("type", "samlsso");
        formData2.put("sp", "ENEL_RJO_WEB_BRA");
        formData2.put("isSaaSApp", "false");
        formData2.put("authenticators", "");
        
        resp = http.get("https://accounts.enel.com/authenticationendpoint/login.do", formData2, headers2, null);

        // FormData formData = new FormData();
        // formData.put("customerType", "personas");
        // formData.put("username", username);
        // formData.put("password", password);
        // formData.put("sessionDataKey", doc.select("input[name=sessionDataKey]").attr("value"));
        // formData.put("tocommonauth", true);
        // formData.put("socialOneHub", "");
        // formData.put("socialOneHubMail", "");
        // formData.put("socialOneHubName", "");
        // formData.put("socialOneHubLastName", "");
        // formData.put("socialOneHubMobile", "");
        // formData.put("socialOneHubUid", "");

        // resp = http.post("https://www.enel.com.br/pt/login.mdwedgeohl.loginuniqueid.html", formData, headers, null);
        // doc = http.getDocument(resp);

        // if (!doc.select("div[class*=alert]").isEmpty()) { 
        //     response.setStatusCode(resp.statusCode());
        //     response.setMessage(doc.select("div[class*=alert]").text());
        //     response.setSuccess(false);
        //     return response;
        // }

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
