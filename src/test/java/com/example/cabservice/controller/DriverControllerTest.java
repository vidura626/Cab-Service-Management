package com.example.cabservice.controller;

import com.example.cabservice.service.DriverServiceInterface;
import com.example.cabservice.service.VehicleServiceInterface;
import com.google.protobuf.ExperimentalApi;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DriverControllerTest {

    @Mock
    private DriverServiceInterface driverService;

    @InjectMocks
    private DriverController driverController;

    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void testCreateDriverValidJson() throws Exception {
        String json = "{\"name\":\"JohnDoe\",\"nic\":\"123456789V\",\"address\":\"123MainSt,City,Country\",\"dob\":\"1990-01-01\",\"licence\":\"ABC1234567\",\"status\":\"ACTIVE\",\"image\":\"image_url_here\",\"isActive\":true}";
        when(request.getReader()).thenReturn(new java.io.BufferedReader(new java.io.StringReader(json)));
    }

    @AfterEach
    void tearDown() {
    }
}