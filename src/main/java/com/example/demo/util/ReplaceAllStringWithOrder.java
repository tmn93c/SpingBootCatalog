package com.example.demo.util;


import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceAllStringWithOrder {
    public static String changeString(String msg, Object... args) {
        return MessageFormat.format(msg, args);
    }
    public static void main(String[] args) {
        // Replace with custom property
        String msg = "Hello {0} Please find attached {1} which is due on {2}";
        String[] values = {
                "John Doe", "invoice #123", "2009-06-30","test"
        };
        String test = changeString(msg,"John Doe", "invoice #123", "2009-06-30","test");
        System.out.println(MessageFormat.format(msg, values));
    }

}
