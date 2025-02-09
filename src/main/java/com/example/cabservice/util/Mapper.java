package com.example.cabservice.util;

import com.example.cabservice.dto.request.DriverRequestDto;
import com.example.cabservice.dto.response.DriverResponseDto;
import com.example.cabservice.entity.Driver;

import java.sql.Date;
import java.util.Calendar;

public class Mapper {

    // Convert DriverRequestDto to Driver entity
    public static Driver toDriver(DriverRequestDto driverDTO) {
        Driver driver = new Driver();
        driver.setName(driverDTO.getName());
        driver.setNic(driverDTO.getNic());
        driver.setAddress(driverDTO.getAddress());
        // Convert String date to java.sql.Date
        driver.setDob(DateUtil.convertStringToSqlDate(driverDTO.getDob()));
        driver.setLicence(driverDTO.getLicence());
        driver.setStatus(driverDTO.getStatus());
        driver.setImage(driverDTO.getImage());
        driver.setActive(driverDTO.isActive());
        return driver;
    }

    // Convert Driver entity to DriverResponseDto
    public static DriverResponseDto toDriverResponseDto(Driver driver) {
        DriverResponseDto driverDTO = new DriverResponseDto();
        driverDTO.setId(driver.getId());
        driverDTO.setName(driver.getName());
        driverDTO.setNic(driver.getNic());
        driverDTO.setAddress(driver.getAddress());
        // Convert java.sql.Date to java.util.Date for response DTO
        driverDTO.setDob(DateUtil.convertSqlDateToUtilDate(driver.getDob()));
        driverDTO.setLicence(driver.getLicence());
        driverDTO.setStatus(driver.getStatus());
        driverDTO.setImage(driver.getImage());
        driverDTO.setActive(driver.isActive());
        return driverDTO;
    }
}
