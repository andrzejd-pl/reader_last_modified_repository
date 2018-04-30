package com.dybowski_andrzej;

import java.util.HashMap;
import java.util.Map;

public class HeaderLinkParser {
    Map<String, String> links = new HashMap<>();
    public HeaderLinkParser(String text) {
        String[] links = text.split(", ");
        for(String link : links) {
            String key = link.split(";[ ]rel=\"")[1].replace("\"", "").replaceAll(" ", "");
            String value = link.split(">;[ ]rel=\"")[0].replace("<", "").replaceAll(" ", "");
            this.links.put(key, value);
        }
    }

    public boolean isKeyExist(String key) {
        return links.containsKey(key);
    }

    public String getLink(String key) {
        return links.get(key);
    }
}
