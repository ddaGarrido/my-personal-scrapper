package com.scrapper.models;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestLog {

    private String url;
    private Map<String, String> headers;
    private Map<String, String> cookies;
    private String body;
    private String type;
    private String response;

    // Construtores, getters e setters
}