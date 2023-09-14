package com.scrapper.util.http;

import java.util.LinkedHashMap;

public class Cookies {
    private LinkedHashMap<String, String> cookies = new LinkedHashMap<>();

    public void set(String key, String value) {
        cookies.put(key, value);
    }

    public void setAll(LinkedHashMap<String, String> newCookies) {
        cookies = newCookies;
    }

    public void putAll(LinkedHashMap<String, String> newCookies) {
        cookies.putAll(newCookies);
    }

    public String get(String key) {
        return cookies.get(key);
    }

    public void remove(String key) {
        cookies.remove(key);
    }

    public LinkedHashMap<String, String> getAll() {
        return cookies;
    }
}
