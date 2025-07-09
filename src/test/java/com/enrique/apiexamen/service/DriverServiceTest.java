package com.enrique.apiexamen.service;

import com.enrique.apiexamen.dto.request.DriverRequest;
import com.enrique.apiexamen.dto.response.DriverResponse;
import com.enrique.apiexamen.entity.Driver;
import com.enrique.apiexamen.repository.DriverRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriverServiceTest {

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private DriverService driverService;

    @Test
    void testCreateDriver() {
        // Arrange
        DriverRequest request = new DriverRequest();
        request.setName("Luis Guzmán");
        request.setLicenseNumber("ABC123");

        Driver savedDriver = Driver.builder()
                .id(UUID.randomUUID())
                .name("Luis Guzmán")
                .licenseNumber("ABC123")
                .active(true)
                .build();

        when(driverRepository.save(any(Driver.class))).thenReturn(savedDriver);

        DriverResponse result = driverService.create(request);

        assertNotNull(result);
        assertEquals("Luis Guzmán", result.getName());
        assertEquals("ABC123", result.getLicenseNumber());
        verify(driverRepository, times(1)).save(any(Driver.class));
    }
}
