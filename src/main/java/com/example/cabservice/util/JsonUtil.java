package com.example.cabservice.util;

import com.example.cabservice.dto.request.DriverRequestDto;
import com.example.cabservice.dto.response.DriverResponseDto;
import com.example.cabservice.util.enums.DriverStatus;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
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

    // Parse the JSON string to DriverDTO
    public static DriverRequestDto parseDriverRequestJson(String json) {
        DriverRequestDto driverDTO = new DriverRequestDto();

        driverDTO.setName(extractJsonValue(json, "name"));
        driverDTO.setNic(extractJsonValue(json, "nic"));
        driverDTO.setAddress(extractJsonValue(json, "address"));
        driverDTO.setDob(extractJsonValue(json, "dob"));
        driverDTO.setLicence(extractJsonValue(json, "licence"));

        // Handle enum value for status (Available or Busy)
        String status = extractJsonValue(json, "status");
        if (status != null && !status.isEmpty()) {
            driverDTO.setStatus(DriverStatus.fromString(status));
        }

        driverDTO.setImage(extractJsonValue(json, "image"));

        // Parse isActive as boolean
        String isActiveStr = extractJsonValue(json, "isActive");
        if (isActiveStr != null && !isActiveStr.isEmpty()) {
            driverDTO.setActive(Boolean.parseBoolean(isActiveStr));
        }

        return driverDTO;
    }

    // Helper method to extract value from JSON string
    private static String extractJsonValue(String json, String key) {
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

    // Convert DriverDTO to JSON string

    public static String convertDriverResponseToJson(DriverResponseDto dto) {
        StringBuilder json = new StringBuilder();
        json.append("{");

        // Add the fields to the JSON string
        json.append("\"id\":").append(dto.getId()).append(",");
        json.append("\"name\":\"").append(dto.getName()).append("\",");
        json.append("\"nic\":\"").append(dto.getNic()).append("\",");
        json.append("\"address\":\"").append(dto.getAddress()).append("\",");

        // Format the dob (java.util.Date) to a String in the format "yyyy/MM/dd"
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        json.append("\"dob\":\"").append(dateFormat.format(dto.getDob())).append("\",");

        json.append("\"licence\":\"").append(dto.getLicence()).append("\",");

        // Convert enum value to string
        json.append("\"status\":\"").append(dto.getStatus().getStatus()).append("\",");

        json.append("\"image\":\"").append(dto.getImage()).append("\",");
        json.append("\"isActive\":").append(dto.isActive());

        json.append("}");

        return json.toString();
    }

    // Convert a List of DriverDTOs to a JSON array string
    public static String convertDriversToJson(List<DriverResponseDto> driverList) {
        StringBuilder json = new StringBuilder();
        json.append("[");

        for (int i = 0; i < driverList.size(); i++) {
            DriverResponseDto driverDTO = driverList.get(i);
            json.append(convertDriverResponseToJson(driverDTO));
            if (i < driverList.size() - 1) {
                json.append(","); // Add a comma if not the last driver
            }
        }

        json.append("]");
        return json.toString();
    }
}
