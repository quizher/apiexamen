package com.enrique.apiexamen.mapper;

import com.enrique.apiexamen.dto.request.OrderRequest;
import com.enrique.apiexamen.dto.response.OrderResponse;
import com.enrique.apiexamen.entity.Order;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final DriverMapper driverMapper;

    public Order toEntity(OrderRequest request) {
        Order order = new Order();
        order.setOrigin(request.getOrigin());
        order.setDestination(request.getDestination());
        return order;
    }

    public OrderResponse toResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setOrigin(order.getOrigin());
        response.setDestination(order.getDestination());
        response.setStatus(order.getStatus());
        response.setCreatedAt(order.getCreatedAt());
        response.setUpdatedAt(order.getUpdatedAt());

        if (order.getDriver() != null) {
            response.setDriver(driverMapper.toResponse(order.getDriver()));
        }
        response.setAttachmentPath(order.getAttachmentPath());
        response.setImagePath(order.getImagePath());

        return response;
    }

}
