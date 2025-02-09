package com.example.cabservice.util;

import com.example.cabservice.dto.DriverDTO;
import com.example.cabservice.entity.Driver;

public class Mapper {
    public static Driver toDriver(DriverDTO driverDTO) {
        Driver driver = new Driver();
        driver.setName(driverDTO.getName());
        driver.setAddress(driverDTO.getAddress());
        driver.setDob(java.sql.Date.valueOf(driverDTO.getDob()));
        return driver;
    }
    public static DriverDTO toDriverDto(Driver driver) {
        DriverDTO driverDto = new DriverDTO();
        driverDto.setName(driver.getName());
        driverDto.setAddress(driver.getAddress());
        driverDto.setDob(driver.getDob().toLocalDate().toString());
        return driverDto;
    }
}
