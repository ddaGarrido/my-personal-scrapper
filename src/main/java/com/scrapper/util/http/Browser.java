package com.scrapper.util.http;

import org.jsoup.Connection.Response;
import org.springframework.beans.factory.annotation.Autowired;

import com.scrapper.models.RequestLog;
import com.scrapper.service.OperationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Browser {
    private List<Response> history;
    private Http httpUtil;
    private FormData formData;
    private Cookies cookies;
    private Headers headers;

    @Autowired
    private OperationService operationService;

    public Browser() {
        this.history = new ArrayList<>();
        this.httpUtil = new Http();
        this.formData = new FormData();
        this.cookies = new Cookies();
        this.headers = new Headers();
    }

    public Response get(String url) {
        Response response = httpUtil.get(url, formData, headers, cookies);
        updateState(response);
        history.add(response);
        operationService.addRequest(new RequestLog(url, response.headers(), response.cookies(), response.body(), "GET", response.body()));
        return response;
    }

    public Response post(String url, Map<String, String> customHeaders, String body) {
        Headers combinedHeaders = new Headers();
        combinedHeaders.setAll(headers.getAll());
        combinedHeaders.putAll(customHeaders);
        Response response = httpUtil.post(url, body, combinedHeaders, cookies);
        updateState(response);
        history.add(response);
        operationService.addRequest(new RequestLog(url, response.headers(), response.cookies(), response.body(), "POST", response.body()));
        return response;
    }

    private void updateState(Response response) {
        cookies.setAll(response.cookies());
        headers.setAll(response.headers());
    }
}
