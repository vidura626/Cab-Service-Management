package com.example.cabservice.util;

import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonUtil {
    // Get JSON string from request body
    public static String getJsonFromRequest(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        return stringBuilder.toString();
    }

    // Helper method to extract value from JSON string
    public static String extractJsonValue(String json, String key) {
        // This pattern matches either a quoted string value or an unquoted value (e.g., boolean or number)
        String pattern = "\"" + key + "\"\\s*:\\s*(?:\"(.*?)\"|([^\",}\\s]+))";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(json);
        if (matcher.find()) {
            // If group(1) is not null, a quoted value was found.
            if (matcher.group(1) != null) {
                return matcher.group(1);
            } else if (matcher.group(2) != null) {
                return matcher.group(2).trim();
            }
        }
        return null;
    }

}
