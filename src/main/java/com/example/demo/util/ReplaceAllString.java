package com.example.demo.util;


import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceAllString {
    public static void main(String[] args) {
        // Replace with custom property
        Map<String,String> tokens = new HashMap<String,String>();
        tokens.put("cat", "Garfield");
        tokens.put("beverage", "coffee");

        String template = "{cat} really needs some {beverage}.";

        String patternString = "\\{(" + StringUtils.join(tokens.keySet(), "|") + ")\\}";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(template);

        StringBuffer sb = new StringBuffer();
        while(matcher.find()) {
            matcher.appendReplacement(sb, tokens.get(matcher.group(1)));
        }
        matcher.appendTail(sb);

        System.out.println(sb.toString());
    }
}
