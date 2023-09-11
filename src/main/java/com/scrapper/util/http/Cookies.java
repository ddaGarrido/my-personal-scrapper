package com.scrapper.util.http;

import java.util.HashMap;
import java.util.Map;

public class Cookies {
    private Map<String, String> cookies = new HashMap<>();

    public void set(String key, String value) {
        cookies.put(key, value);
    }

    public void setAll(Map<String, String> newCookies) {
        cookies = newCookies;
    }

    public void putAll(Map<String, String> newCookies) {
        cookies.putAll(newCookies);
    }

    public String get(String key) {
        return cookies.get(key);
    }

    public void remove(String key) {
        cookies.remove(key);
    }

    public Map<String, String> getAll() {
        return cookies;
    }
}
