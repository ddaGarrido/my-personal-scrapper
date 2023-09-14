package com.scrapper.util.http;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import com.scrapper.models.RequestLog;
import com.scrapper.util.http.Response;
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

    public Browser() {
        this.history = new ArrayList<>();
        this.httpUtil = new Http();
        this.formData = new FormData();
        this.cookies = new Cookies();
        this.headers = new Headers();
    }

    public Response navigate(String url) {
        Response response = httpUtil.get(url);

        return response;
    }

    // public Response send(String url, Map<String, String> customHeaders, String body) {
    //     Headers combinedHeaders = new Headers();
    //     combinedHeaders.setAll(headers.getAll());
    //     combinedHeaders.putAll(customHeaders);
    //     Response response = httpUtil.post(url, body, combinedHeaders, cookies);
    //     updateState(response);
    //     history.add(response);
    //     operationService.addRequest(new RequestLog(url, response.headers(), response.cookies(), response.body(), "POST", response.body()));
    //     return response;
    // }

    // private void updateState(Response response) {
    //     cookies.setAll(response.cookies());
    //     headers.setAll(response.headers());
    // }
}
