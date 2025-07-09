package com.enrique.apiexamen.mapper;

import com.enrique.apiexamen.dto.response.DriverResponse;
import com.enrique.apiexamen.entity.Driver;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper {

    public DriverResponse toResponse(Driver driver) {
        DriverResponse response = new DriverResponse();
        response.setId(driver.getId());
        response.setName(driver.getName());
        response.setLicenseNumber(driver.getLicenseNumber());
        response.setActive(driver.isActive());
        return response;
    }
}
