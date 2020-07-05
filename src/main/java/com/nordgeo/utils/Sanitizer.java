package com.nordgeo.utils;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class Sanitizer {

    public static String html(String string) {
        return Jsoup.clean(string, Whitelist.basicWithImages());
    }

    public static String text(String string) {
        if(string.isEmpty())
            return string;

        String content = Jsoup.clean(string, Whitelist.basicWithImages());
        return Jsoup.parse(content).text();
    }
}
