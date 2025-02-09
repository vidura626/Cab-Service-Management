package com.example.cabservice.util;

import com.example.cabservice.dto.DriverDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

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
        String value = json.substring(start, end);
        return value;
    }

    // Convert DriverDTO to JSON string
    public static String convertDriverToJson(DriverDTO driverDTO) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"name\":\"").append(driverDTO.getName()).append("\",");
        json.append("\"address\":\"").append(driverDTO.getAddress()).append("\",");
        json.append("\"dob\":\"").append(driverDTO.getDob()).append("\"");
        json.append("}");
        return json.toString();
    }

    // Convert a List of DriverDTOs to a JSON array string
    public static String convertDriversToJson(List<DriverDTO> driverList) {
        StringBuilder json = new StringBuilder();
        json.append("[");

        for (int i = 0; i < driverList.size(); i++) {
            DriverDTO driverDTO = driverList.get(i);
            json.append(convertDriverToJson(driverDTO));
            if (i < driverList.size() - 1) {
                json.append(","); // Add a comma if not the last driver
            }
        }

        json.append("]");
        return json.toString();
    }
}
