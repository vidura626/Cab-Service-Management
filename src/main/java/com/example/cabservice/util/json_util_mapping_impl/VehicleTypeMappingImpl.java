package com.example.cabservice.util.json_util_mapping_impl;

import com.example.cabservice.dto.request.VehicleTypeRequestDto;
import com.example.cabservice.dto.response.VehicleTypeResponseDto;
import com.example.cabservice.entity.VehicleType;
import com.example.cabservice.util.JsonDtoMappingInterface;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.cabservice.util.static_utils.JsonUtil.extractJsonValue;

public class VehicleTypeMappingImpl implements JsonDtoMappingInterface<VehicleTypeRequestDto, VehicleType, VehicleTypeResponseDto> {
    @Override
    public VehicleTypeRequestDto fromJsonToRequestDto(String json) {
        String description = extractJsonValue(json, "description");

        return new VehicleTypeRequestDto.Builder()
                .setDescription(description)
                .build();
    }

    @Override
    public VehicleType toEntity(VehicleTypeRequestDto request) {
        return new VehicleType.Builder()
                .description(request.getDescription())
                .build();
    }

    @Override
    public VehicleType toEntity(VehicleTypeRequestDto request, Long id) {
        return new VehicleType.Builder()
                .id(id)
                .description(request.getDescription())
                .build();
    }


    @Override
    public VehicleTypeResponseDto toResponseDto(VehicleType request) {
        return new VehicleTypeResponseDto.Builder()
                .id(request.getId())
                .description(request.getDescription())
                .build();
    }

    @Override
    public String responseDtoToJsonString(VehicleTypeResponseDto request) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");

        jsonBuilder.append("\"id\":").append(request.getId()).append(",");
        jsonBuilder.append("\"description\":\"").append(request.getDescription()).append("\"");

        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }

    @Override
    public VehicleType responseDtoToJsonString(ResultSet resultSet) throws SQLException {
        return null;
    }


}
