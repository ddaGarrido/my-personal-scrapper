package com.scrapper.util.http;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class FormData {
    private LinkedHashMap<String, String> data;

    public FormData() {
        this.data = new LinkedHashMap<String, String>();
    }

    public FormData(LinkedHashMap<String, String> data) {
        this.data = data;
    }

    public LinkedHashMap<String, String> getData() {
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
