package com.example.cabservice.factory;

import com.example.cabservice.enums.JsonDtoMappingTypes;
import com.example.cabservice.exceptions.NotFoundException;
import com.example.cabservice.util.JsonDtoMappingInterface;
import com.example.cabservice.util.json_util_mapping_impl.DriverMappingImpl;
import com.example.cabservice.util.json_util_mapping_impl.VehicleTypeMappingImpl;
import jdk.jshell.spi.ExecutionControl;

public class JsonDtoMappingFactory {
    public static JsonDtoMappingInterface createJsonDtoMapping(JsonDtoMappingTypes type) {
        switch (type) {
            case DRIVER -> {
                return new DriverMappingImpl();
            }
            case VEHICLE_TYPE -> {
                return new VehicleTypeMappingImpl();
            }
            case DRIVER_VEHICLE_TYPE -> {
                return new VehicleTypeMappingImpl();
            }
            case VEHICLE -> {
                return null;
            }
        }
        return null;
    }
}
