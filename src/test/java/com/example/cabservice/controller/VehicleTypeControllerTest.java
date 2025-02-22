package com.example.cabservice.controller;

import com.example.cabservice.controller.VehicleTypeController;
import com.example.cabservice.dto.VehicleTypeDTO;
import com.example.cabservice.service.VehicleTypeServiceInterface;
import com.example.cabservice.util.JsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class VehicleTypeControllerTest {

    @Mock
    private VehicleTypeServiceInterface vehicleTypeService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private PrintWriter writer;

    @Captor
    private ArgumentCaptor<VehicleTypeDTO> vehicleTypeCaptor;

    private VehicleTypeController vehicleTypeController;

    @BeforeEach
    public void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        vehicleTypeController = new VehicleTypeController();
        vehicleTypeController.vehicleTypeService = vehicleTypeService;  // Inject mock service

        // Mock the HttpServletResponse's getWriter() method to return the mock PrintWriter
        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    public void testDoPost_Success() throws Exception {
        // Arrange
        String json = "{\"description\":\"Sedan\"}";
        when(request.getReader()).thenReturn(new java.io.BufferedReader(new java.io.StringReader(json)));

        // Act
        vehicleTypeController.doPost(request, response);

        // Assert
        verify(vehicleTypeService, times(1)).addVehicleType(vehicleTypeCaptor.capture());  // Capture the argument
        vehicleTypeCaptor.getValue();  // Get captured argument
//        assertEquals("Sedan", capturedVehicleType.getDescription());  // Verify the content of the captured argument
        verify(response).setStatus(HttpServletResponse.SC_CREATED);  // Verify response status
        verify(writer).write("{\"message\":\"Vehicle Type created successfully\"}");  // Verify response message
    }

    @Test
    public void testDoPost_Failure_MissingDescription() throws Exception {
        // Arrange
        String json = "{\"description\":\"\"}"; // Empty description
        when(request.getReader()).thenReturn(new java.io.BufferedReader(new java.io.StringReader(json)));

        // Act
        vehicleTypeController.doPost(request, response);

        // Assert
        verify(vehicleTypeService, times(0)).addVehicleType(any());  // Ensure the service method is not called
        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);  // Verify the 400 status for missing description
        verify(writer).write("{\"error\":\"Description is required\"}");  // Verify the error message
    }

    @Test
    public void testDoPost_Failure_Exception() throws Exception {
        // Arrange
        String json = "{\"description\":\"Sedan\"}";
        VehicleTypeDTO vehicleTypeDTO = JsonUtil.parseVehicleTypeJson(json);
        when(request.getReader()).thenReturn(new java.io.BufferedReader(new java.io.StringReader(json)));
        doThrow(new Exception("Database error")).when(vehicleTypeService).addVehicleType(vehicleTypeCaptor.capture());

        // Act
        vehicleTypeController.doPost(request, response);

        // Assert
        verify(vehicleTypeService, times(1)).addVehicleType(vehicleTypeCaptor.capture());  // Capture the argument
        verify(response).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);  // Verify response status
        verify(writer).write("{\"error\":\"Error creating vehicle type: Database error\"}");  // Verify response error message
    }

    @Test
    public void testDoPut_Success() throws Exception {
        // Arrange
        String json = "{\"description\":\"SUV\"}";
        int id = 1;
        VehicleTypeDTO vehicleTypeDTO = JsonUtil.parseVehicleTypeJson(json);
        when(request.getParameter("id")).thenReturn(String.valueOf(id));
        when(request.getReader()).thenReturn(new java.io.BufferedReader(new java.io.StringReader(json)));

        // Act
        vehicleTypeController.doPut(request, response);

        // Capture the argument passed to the updateVehicleType method
        verify(vehicleTypeService, times(1)).updateVehicleType(vehicleTypeCaptor.capture(), eq(id));

        // Assert
        VehicleTypeDTO capturedVehicleType = vehicleTypeCaptor.getValue();  // Get the captured argument
        assertEquals("SUV", capturedVehicleType.getDescription());  // Verify the description field
        verify(response).setStatus(HttpServletResponse.SC_OK);  // Verify response status
        verify(writer).write("{\"message\":\"Vehicle Type updated successfully\"}");  // Verify response message
    }

    @Test
    public void testDoPut_Failure_MissingDescription() throws Exception {
        // Arrange
        String json = "{\"description\":\"\"}"; // Empty description
        int id = 1;
        when(request.getParameter("id")).thenReturn(String.valueOf(id));
        when(request.getReader()).thenReturn(new java.io.BufferedReader(new java.io.StringReader(json)));

        // Act
        vehicleTypeController.doPut(request, response);

        // Assert
        verify(vehicleTypeService, times(0)).updateVehicleType(any(), eq(id));  // Ensure the service method is not called
        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);  // Verify the 400 status for missing description
        verify(writer).write("{\"error\":\"Description is required\"}");  // Verify the error message
    }

    @Test
    public void testDoPut_Failure_Exception() throws Exception {
        // Arrange
        String json = "{\"description\":\"SUV\"}";
        VehicleTypeDTO vehicleTypeDTO = JsonUtil.parseVehicleTypeJson(json);
        int id = 1;
        when(request.getParameter("id")).thenReturn(String.valueOf(id));
        when(request.getReader()).thenReturn(new java.io.BufferedReader(new java.io.StringReader(json)));
        doThrow(new Exception("Database error")).when(vehicleTypeService).updateVehicleType(vehicleTypeCaptor.capture(), eq(id));

        // Act
        vehicleTypeController.doPut(request, response);

        // Assert
        verify(vehicleTypeService, times(1)).updateVehicleType(vehicleTypeCaptor.capture(), eq(id));  // Verify the service method call
        verify(response).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);  // Verify response status
        verify(writer).write("{\"error\":\"Error updating vehicle type: Database error\"}");  // Verify response error message
    }
}
