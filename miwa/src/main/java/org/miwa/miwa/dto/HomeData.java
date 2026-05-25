package org.miwa.miwa.dto;

import lombok.Data;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
public class HomeData {
    private Map<String, String> hero;
    private List<Map<String, String>> marquee;
    private List<Map<String, Object>> services;
    private List<Map<String, String>> stats;
    private List<Map<String, Object>> projects;
    private List<Map<String, String>> process;
    private List<Map<String, String>> clients;
    private Map<String, String> philosophy;
    private Map<String, String> about;
    private Map<String, Object> footer;

    public static HomeData createDefault() {
        HomeData d = new HomeData();
        d.hero = new LinkedHashMap<>();
        d.hero.put("zh", "");
        d.hero.put("en", "");
        d.hero.put("sub", "");
        return d;
    }
}
