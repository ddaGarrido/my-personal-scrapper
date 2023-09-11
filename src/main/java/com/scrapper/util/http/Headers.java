package com.scrapper.util.http;

import java.util.HashMap;
import java.util.Map;

public class Headers {
    private Map<String, String> headers = new HashMap<>();

    public void set(String key, String value) {
        headers.put(key, value);
    }

    public void setAll(Map<String, String> newHeaders) {
        headers = newHeaders;
    }

    public void putAll(Map<String, String> newHeaders) {
        headers.putAll(newHeaders);
    }

    public String get(String key) {
        return headers.get(key);
    }

    public void remove(String key) {
        headers.remove(key);
    }

    public Map<String, String> getAll() {
        return headers;
    }
}
