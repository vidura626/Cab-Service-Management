package com.example.cabservice.util.json_util_mapping_impl;

import com.example.cabservice.dto.request.DriverRequestDto;
import com.example.cabservice.dto.response.DriverResponseDto;
import com.example.cabservice.entity.Driver;
import com.example.cabservice.enums.DriverStatus;
import com.example.cabservice.util.static_utils.DateUtil;
import com.example.cabservice.util.JsonDtoMappingInterface;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.cabservice.util.static_utils.JsonUtil.extractJsonValue;

public class DriverMappingImpl implements JsonDtoMappingInterface<DriverRequestDto, Driver, DriverResponseDto> {
    @Override
    public DriverRequestDto fromJsonToRequestDto(String json) {
        String name = extractJsonValue(json, "name");
        String nic = extractJsonValue(json, "nic");
        String address = extractJsonValue(json, "address");
        String dob = extractJsonValue(json, "dob");
        String licence = extractJsonValue(json, "licence");
        String status = extractJsonValue(json, "status");
        String image = extractJsonValue(json, "image");
        boolean isActive = Boolean.parseBoolean(extractJsonValue(json, "isActive"));

        return new DriverRequestDto.Builder()
                .name(name)
                .nic(nic)
                .address(address)
                .dob(dob)
                .licence(licence)
                .status(status)
                .image(image)
                .isActive(isActive)
                .build();
    }

    @Override
    public Driver toEntity(DriverRequestDto request) {
        DriverStatus status = DriverStatus.valueOf(request.getStatus());
        java.sql.Date dob = DateUtil.convertStringToSqlDate(request.getDob());
        return new Driver.Builder()
                .name(request.getName())
                .nic(request.getNic())
                .address(request.getAddress())
                .dob(dob)
                .licence(request.getLicence())
                .status(status)
                .image(request.getImage())
                .isActive(request.isActive())
                .build();
    }

    @Override
    public Driver toEntity(DriverRequestDto request, Long id) {
        DriverStatus status = DriverStatus.valueOf(request.getStatus());
        java.sql.Date dob = DateUtil.convertStringToSqlDate(request.getDob());
        return new Driver.Builder()
                .id(Math.toIntExact(id))
                .name(request.getName())
                .nic(request.getNic())
                .address(request.getAddress())
                .dob(dob)
                .licence(request.getLicence())
                .status(status)
                .image(request.getImage())
                .isActive(request.isActive())
                .build();
    }


    @Override
    public DriverResponseDto toResponseDto(Driver request) {
        return new DriverResponseDto.Builder()
                .id(request.getId())
                .name(request.getName())
                .nic(request.getNic())
                .address(request.getAddress())
                .dob((DateUtil.convertUtilDateToString(DateUtil.convertSqlDateToUtilDate(request.getDob()))))
                .licence(request.getLicence())
                .status(request.getStatus())
                .image(request.getImage())
                .isActive(request.isActive())
                .build();
    }

    @Override
    public String resultSetToResponseDto(DriverResponseDto request) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");

        jsonBuilder.append("\"id\":").append(request.getId()).append(",");
        jsonBuilder.append("\"name\":\"").append(request.getName()).append("\",");
        jsonBuilder.append("\"nic\":\"").append(request.getNic()).append("\",");
        jsonBuilder.append("\"address\":\"").append(request.getAddress()).append("\",");
        jsonBuilder.append("\"dob\":\"").append(request.getDob()).append("\",");
        jsonBuilder.append("\"licence\":\"").append(request.getLicence()).append("\",");
        jsonBuilder.append("\"status\":\"").append(request.getStatus()).append("\",");
        jsonBuilder.append("\"image\":\"").append(request.getImage()).append("\",");
        jsonBuilder.append("\"isActive\":").append(request.getActive());

        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }

    @Override
    public Driver resultSetToResponseDto(ResultSet rs) throws SQLException {
        return new Driver.Builder()
                .name(rs.getString("name"))
                .nic(rs.getString("nic"))
                .address(rs.getString("address"))
                .dob(rs.getDate("dob"))
                .licence(rs.getString("licence"))
                .status(DriverStatus.valueOf(rs.getString("driver_status")))
                .image(rs.getString("image"))
                .isActive(rs.getBoolean("isActive"))
                .createdBy(rs.getString("created_by"))
                .updatedBy(rs.getString("updated_by"))
                .createdDate(rs.getTimestamp("created_at").toLocalDateTime())
                .updatedDate(rs.getTimestamp("updated_at").toLocalDateTime())
                .build();
    }


}
