package com.example.cabservice.util;
import com.example.cabservice.dto.DriverDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;

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

    // Parse the JSON string to DriverDTO
    public static DriverDTO parseDriverJson(String json) {
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setName(extractJsonValue(json, "name"));
        driverDTO.setAddress(extractJsonValue(json, "address"));
        driverDTO.setDob(extractJsonValue(json, "dob"));
        return driverDTO;
    }

    // Helper method to extract value from JSON string
    private static String extractJsonValue(String json, String key) {
        String search = "\"" + key + "\":\"";
        int start = json.indexOf(search) + search.length();
        int end = json.indexOf("\"", start);
        return json.substring(start, end);
    }
}
