package com.enrique.apiexamen.service;

import com.enrique.apiexamen.dto.request.OrderRequest;
import com.enrique.apiexamen.dto.response.OrderResponse;
import com.enrique.apiexamen.entity.Order;
import com.enrique.apiexamen.entity.OrderStatus;
import com.enrique.apiexamen.mapper.OrderMapper;
import com.enrique.apiexamen.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderService orderService;

    @Test
    void testCreateOrder() {
        OrderRequest request = new OrderRequest();
        request.setOrigin("CDMX");
        request.setDestination("Monterrey");

        Order order = new Order();
        order.setOrigin("CDMX");
        order.setDestination("Monterrey");
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.CREATED);

        OrderResponse response = new OrderResponse();
        response.setOrigin("CDMX");
        response.setDestination("Monterrey");
        response.setStatus(OrderStatus.CREATED);

        when(orderMapper.toEntity(request)).thenReturn(order);
        when(orderRepository.save(order)).thenReturn(order);
        when(orderMapper.toResponse(order)).thenReturn(response);

        OrderResponse result = orderService.create(request);

        assertEquals(OrderStatus.CREATED, result.getStatus());
        assertEquals("CDMX", result.getOrigin());
        verify(orderRepository, times(1)).save(order);
    }

}
