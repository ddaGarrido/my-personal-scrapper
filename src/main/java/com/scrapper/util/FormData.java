package com.scrapper.util;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class FormData {
    private Map<String, String> data;

    public FormData() {
        this.data = new java.util.HashMap<String, String>();
    }

    public FormData(Map<String, String> data) {
        this.data = data;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void put(String key, String value) {
        data.put(key, value);
    }

    public void put(String key, boolean value) {
        data.put(key, String.valueOf(value));
    }

    public String get(String key) {
        return data.get(key);
    }

    public void remove(String key) {
        data.remove(key);
    }
}
