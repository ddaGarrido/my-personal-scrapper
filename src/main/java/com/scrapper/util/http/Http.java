package com.scrapper.util.http;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scrapper.service.OperationService;

@Component
public class Http {

    private static final Logger log = LoggerFactory.getLogger(Http.class);
    public Connection connection = Jsoup.newSession();

    public Response get(String url) {
        connection = Jsoup.connect(url).method(Connection.Method.GET);
        Connection.Response response;

        try {
            response = connection.execute();
        } catch (IOException e) {
            log.error("Error executing GET request to {}: {}", url, e.getMessage());
            return null;
        }

        response.bufferUp();
        return new Response(response);
    }

}
