package com.example.cabservice.util.json_util_mapping_impl;

import com.example.cabservice.dto.request.VehicleRequestDto;
import com.example.cabservice.dto.response.VehicleResponseDto;
import com.example.cabservice.entity.Vehicle;
import com.example.cabservice.enums.VehicleStatus;
import com.example.cabservice.util.JsonDtoMappingInterface;
import com.example.cabservice.util.static_utils.DateUtil;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.cabservice.util.static_utils.JsonUtil.extractJsonValue;

public class VehicleMappingImpl implements JsonDtoMappingInterface<VehicleRequestDto, Vehicle, VehicleResponseDto> {

    @Override
    public VehicleRequestDto fromJsonToRequestDto(String json) {
        String make = extractJsonValue(json, "make");
        String model = extractJsonValue(json, "model");
        int year = Integer.parseInt(extractJsonValue(json, "year"));
        String licensePlate = extractJsonValue(json, "licensePlate");
        String fuelType = extractJsonValue(json, "fuelType");
        String status = extractJsonValue(json, "status");
        int vehicleTypeId = Integer.parseInt(extractJsonValue(json, "vehicleTypeId"));

        return new VehicleRequestDto.Builder()
                .make(make)
                .model(model)
                .year(year)
                .licensePlate(licensePlate)
                .fuelType(fuelType)
                .status(status)
                .vehicleTypeId(vehicleTypeId)
                .build();
    }

    @Override
    public Vehicle toEntity(VehicleRequestDto request) {
        return new Vehicle.Builder()
                .make(request.getMake())
                .model(request.getModel())
                .year(request.getYear())
                .licensePlate(request.getLicensePlate())
                .fuelType(request.getFuelType())
                .status(VehicleStatus.valueOf(request.getStatus()))
                .vehicleTypeId(request.getVehicleTypeId())
                .build();
    }

    @Override
    public Vehicle toEntity(VehicleRequestDto request, Long id) {
        return new Vehicle.Builder()
                .id(Math.toIntExact(id))
                .make(request.getMake())
                .model(request.getModel())
                .year(request.getYear())
                .licensePlate(request.getLicensePlate())
                .fuelType(request.getFuelType())
                .status(VehicleStatus.valueOf(request.getStatus()))
                .vehicleTypeId(request.getVehicleTypeId())
                .build();
    }

    @Override
    public VehicleResponseDto toResponseDto(Vehicle request) {
        return new VehicleResponseDto.Builder()
                .id((long) request.getId())
                .make(request.getMake())
                .model(request.getModel())
                .year(request.getYear())
                .licensePlate(request.getLicensePlate())
                .fuelType(request.getFuelType())
                .status(request.getStatus().name())
                .vehicleTypeId(request.getVehicleTypeId())
                .build();
    }

    @Override
    public String responseDtoToJsonString(VehicleResponseDto request) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");

        jsonBuilder.append("\"id\":").append(request.getId()).append(",");
        jsonBuilder.append("\"make\":\"").append(request.getMake()).append("\",");
        jsonBuilder.append("\"model\":\"").append(request.getModel()).append("\",");
        jsonBuilder.append("\"year\":").append(request.getYear()).append(",");
        jsonBuilder.append("\"licensePlate\":\"").append(request.getLicensePlate()).append("\",");
        jsonBuilder.append("\"fuelType\":\"").append(request.getFuelType()).append("\",");
        jsonBuilder.append("\"status\":\"").append(request.getStatus()).append("\",");
        jsonBuilder.append("\"vehicleTypeId\":").append(request.getVehicleTypeId());

        jsonBuilder.append("}");

        return jsonBuilder.toString();
    }

    @Override
    public Vehicle responseDtoToJsonString(ResultSet resultSet) throws SQLException {
        return new Vehicle.Builder()
                .id(resultSet.getInt("id"))
                .make(resultSet.getString("make"))
                .model(resultSet.getString("model"))
                .year(resultSet.getInt("year"))
                .licensePlate(resultSet.getString("licensePlate"))
                .fuelType(resultSet.getString("fuelType"))
                .status(VehicleStatus.valueOf(resultSet.getString("status")))
                .vehicleTypeId(resultSet.getInt("vehicleTypeId"))
                .build();
    }
}
