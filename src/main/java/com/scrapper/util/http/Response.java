package com.scrapper.util.http;

import java.io.IOException;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;

public class Response {
    private int statusCode;
    private String body;
    private Map<String, String> headers;
    private Document document;

    public Response(Connection.Response response) {
        statusCode = response.statusCode();
        body = response.body();
        headers = response.headers();

        try {
            document = response.parse();
        } catch (IOException e) {
            System.out.print(1);
        }
    }

    public int statusCode() {
        return statusCode;
    }

    public Document document() {
        return document;
    }
}