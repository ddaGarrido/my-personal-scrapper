package com.scrapper.util.http;

import java.util.LinkedHashMap;

public class Headers {
    private LinkedHashMap<String, String> headers = new LinkedHashMap<>();

    public void set(String key, String value) {
        headers.put(key, value);
    }

    public void setAll(LinkedHashMap<String, String> newHeaders) {
        headers = newHeaders;
    }

    public void putAll(LinkedHashMap<String, String> newHeaders) {
        headers.putAll(newHeaders);
    }

    public String get(String key) {
        return headers.get(key);
    }

    public void remove(String key) {
        headers.remove(key);
    }

    public LinkedHashMap<String, String> getAll() {
        return headers;
    }
}
