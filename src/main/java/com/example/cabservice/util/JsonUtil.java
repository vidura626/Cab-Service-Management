package com.example.cabservice.util;
import com.example.cabservice.dto.DriverDTO;
import com.example.cabservice.dto.VehicleDTO;
import com.example.cabservice.dto.VehicleTypeDTO;
import com.example.cabservice.enums.FuelTypes;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
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

    // Parse the JSON string to VehicleTypeDTO
    public static VehicleTypeDTO parseVehicleTypeJson(String json) {
        return new VehicleTypeDTO.Builder()
                .setDescription(extractJsonValue(json, "description"))
                .build();
    }

    // Parse the JSON string to DriverDTO
    public static DriverDTO parseDriverJson(String json) {
        return new DriverDTO.Builder()
                .setName(extractJsonValue(json, "name"))
                .setAddress(extractJsonValue(json, "address"))
                .setDob(extractJsonValue(json, "dob"))
                .build();
    }

    // Parse the JSON string to VehicleDTO
    public static VehicleDTO parseVehicleJson(String json) {
        Long id = json.contains("\"id\"") ? Long.parseLong(extractJsonValue(json, "id")) : null;
        String make = extractJsonValue(json, "make");
        String model = extractJsonValue(json, "model");
        int year = Integer.parseInt(extractJsonValue(json, "year"));
        String licensePlate = extractJsonValue(json, "licensePlate");
        FuelTypes fuelType = FuelTypes.valueOf(extractJsonValue(json, "fuelType"));
        String status = extractJsonValue(json, "status");
        String owner = extractJsonValue(json, "owner");
        List<String> vehicleImages = Arrays.asList(extractJsonValue(json, "vehicleImages").split(","));

        return new VehicleDTO.Builder(make,model, year)
                .setId(id)
                .setFuelType(fuelType)
                .setOwner(owner)
                .setStatus(status)
                .setVehicleImages(vehicleImages)
                .setLicensePlate(licensePlate)
                .build();
    }

    // Helper method to extract value from JSON string
    private static String extractJsonValue(String json, String key) {
        String search = "\"" + key + "\":\"";
        int start = json.indexOf(search) + search.length();
        int end = json.indexOf("\"", start);
        return json.substring(start, end);
    }
}
