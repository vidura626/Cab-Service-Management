//package com.example.cabservice.controller;
//
//import com.example.cabservice.dto.request.VehicleRequestDto;
//import com.example.cabservice.service.VehicleServiceInterface;
//import com.example.cabservice.exceptions.AlreadyAvailableException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class VehicleControllerTest {
//
//    @Mock
//    private VehicleServiceInterface vehicleService;
//
//    @InjectMocks
//    private VehicleController vehicleController;
//
//    private HttpServletRequest request;
//    private HttpServletResponse response;
//
//    @BeforeEach
//    public void setUp() {
//        request = mock(HttpServletRequest.class);
//        response = mock(HttpServletResponse.class);
//    }
//
//    @Test
//    public void testCreateVehicleSuccess() throws Exception {
//        // Arrange
//        String json = "{\"make\":\"Toyota\",\"model\":\"Corolla\",\"year\":2020,\"licensePlate\":\"ABC1234\",\"fuelType\":\"Petrol\",\"status\":\"Available\",\"vehicle_type_id\":1}";
//        when(request.getReader()).thenReturn(new java.io.BufferedReader(new java.io.StringReader(json)));
//
//        VehicleRequestDto vehicleRequestDto = new VehicleRequestDto.Builder()
//                .setMake("Toyota")
//                .setModel("Corolla")
//                .setYear(2020)
//                .setLicensePlate("ABC1234")
//                .setFuelType("Petrol")
//                .setStatus("Available")
//                .setVehicleTypeId(1)
//                .build();
//
//        doNothing().when(vehicleService).addVehicle(vehicleRequestDto);
//
//        // Act
//        vehicleController.doPost(request, response);
//
//        // Assert
//        verify(response, times(1)).setStatus(HttpServletResponse.SC_CREATED);
//        verify(response.getWriter(), times(1)).write("{\"message\":\"Vehicle created successfully\"}");
//    }
//
//    @Test
//    public void testCreateVehicleConflict() throws Exception {
//        // Arrange
//        String json = "{\"make\":\"Toyota\",\"model\":\"Corolla\",\"year\":2020,\"licensePlate\":\"ABC1234\",\"fuelType\":\"Petrol\",\"status\":\"Available\",\"vehicle_type_id\":1}";
//        when(request.getReader()).thenReturn(new java.io.BufferedReader(new java.io.StringReader(json)));
//
//        VehicleRequestDto vehicleRequestDto = new VehicleRequestDto.Builder()
//                .setMake("Toyota")
//                .setModel("Corolla")
//                .setYear(2020)
//                .setLicensePlate("ABC1234")
//                .setFuelType("Petrol")
//                .setStatus("Available")
//                .setVehicleTypeId(1)
//                .build();
//
//        doThrow(new AlreadyAvailableException("Vehicle with this license plate already exists")).when(vehicleService).addVehicle(vehicleRequestDto);
//
//        // Act
//        vehicleController.doPost(request, response);
//
//        // Assert
//        verify(response, times(1)).setStatus(HttpServletResponse.SC_CONFLICT);
//        verify(response.getWriter(), times(1)).write("{\"error\":\"Vehicle with this license plate already exists\"}");
//    }
//}
