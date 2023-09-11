package com.scrapper.util.http;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Http {

    private static final Logger log = LoggerFactory.getLogger(Http.class);
    public Connection connection;

    public Http() {
        connection = Jsoup.newSession();
    }

    public Response get(String url, FormData formData, Headers headers, Cookies cookies) {
        try {
            connection = Jsoup.connect(url).method(Connection.Method.GET);
            if (formData != null) {
                connection.data(formData.getData());
            }
            if (headers != null) {
                connection.headers(headers.getAll());
            }
            if (cookies != null) {
                connection.cookies(cookies.getAll());
            }
            Response response = connection.execute();
            response.bufferUp();
            return response;
        } catch (IOException e) {
            log.error("Error executing GET request to {}: {}", url, e.getMessage());
            return null;
        }
    }

    public Response get(String url) {
        return get(url, null, null, null);
    }

    public Response get(String url, FormData formData) {
        return get(url, formData, null, null);
    }

    public Response post(String url, Headers headers, Cookies cookies) {
        try {
            connection = Jsoup.connect(url).method(Connection.Method.POST);
            if (headers != null) {
                connection.headers(headers.getAll());
            }
            if (cookies != null) {
                connection.cookies(cookies.getAll());
            }
            Response response = connection.execute();
            response.bufferUp();
            return response;
        } catch (IOException e) {
            log.error("Error executing POST request to {}: {}", url, e.getMessage());
            return null;
        }
    }

    public Response post(String url) {
        return post(url, null, null);
    }

    public Response post(String url, String jsonBody, Headers headers, Cookies cookies) {
        try {
            connection = Jsoup.connect(url)
                .header("Content-Type", "application/json")
                .requestBody(jsonBody)
                .method(Connection.Method.POST);
            if (headers != null) {
                connection.headers(headers.getAll());
            }
            if (cookies != null) {
                connection.cookies(cookies.getAll());
            }
            Response response = connection.execute();
            response.bufferUp();
            return response;
        } catch (IOException e) {
            log.error("Error executing POST request with JSON to {}: {}", url, e.getMessage());
            return null;
        }
    }

    public Response post(String url, String jsonBody) {
        return post(url, jsonBody, null, null);
    }

    public Response post(String url, FormData formData, Headers headers, Cookies cookies) {
        try {
            connection = Jsoup.connect(url).data(formData.getData()).method(Connection.Method.POST);
            if (headers != null) {
                connection.headers(headers.getAll());
            }
            if (cookies != null) {
                connection.cookies(cookies.getAll());
            }
            Response response = connection.ignoreContentType(true).execute();
            response.bufferUp();
            return response;
        } catch (IOException e) {
            log.error("Error executing POST request with form data to {}: {}", url, e.getMessage());
            return null;
        }
    }

    public Response post(String url, FormData formData) {
        return post(url, formData, null, null);
    }

    public Document getDocument(Response response) {
        try {
            return response.parse();
        } catch (IOException e) {
            log.error("Error parsing response: {}", e.getMessage());
            return null;
        }
    }

    public Document getDocument() {
        try {
            return connection.response().parse();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
