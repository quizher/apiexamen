package com.enrique.apiexamen.service;

import com.enrique.apiexamen.dto.request.AssignmentRequest;
import com.enrique.apiexamen.dto.request.OrderRequest;
import com.enrique.apiexamen.dto.response.OrderResponse;
import com.enrique.apiexamen.entity.Driver;
import com.enrique.apiexamen.entity.Order;
import com.enrique.apiexamen.entity.OrderStatus;
import com.enrique.apiexamen.exception.ResourceNotFoundException;
import com.enrique.apiexamen.mapper.OrderMapper;
import com.enrique.apiexamen.repository.DriverRepository;
import com.enrique.apiexamen.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final DriverRepository driverRepository;
    private final OrderMapper mapper;

    public OrderResponse create(OrderRequest request) {
        Order order = mapper.toEntity(request);
        return mapper.toResponse(orderRepository.save(order));
    }

    public OrderResponse getById(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return mapper.toResponse(order);
    }

    public List<OrderResponse> getAll() {
        return orderRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<OrderResponse> findByFilters(OrderStatus status, LocalDateTime fromDate, LocalDateTime toDate, String origin, String destination) {
        List<Order> orders = orderRepository.findWithFilters(status, fromDate, toDate, origin, destination);
        return orders.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse updateStatus(UUID id, OrderStatus newStatus) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada"));

        if (!order.getStatus().canTransitionTo(newStatus)) {
            throw new IllegalStateException("Transición de estado no válida: "
                    + order.getStatus() + " -> " + newStatus);
        }

        order.setStatus(newStatus);
        order.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);
        return mapper.toResponse(order);
    }

    public OrderResponse assignDriverAndFiles(UUID orderId, UUID driverId,
                                              MultipartFile file, MultipartFile image) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada"));

        if (order.getStatus() != OrderStatus.CREATED) {
            throw new IllegalStateException("Solo se pueden asignar órdenes en estado CREATED");
        }

        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Conductor no encontrado"));

        if (!driver.isActive()) {
            throw new IllegalStateException("El conductor no está activo");
        }

        // Guardar archivos
        String uploadDir = "uploads/" + orderId;
        new File(uploadDir).mkdirs();

        String filePath = saveFile(file, uploadDir);
        String imagePath = saveFile(image, uploadDir);

        order.setDriver(driver);
        order.setAttachmentPath(filePath);
        order.setImagePath(imagePath);
        order.setUpdatedAt(LocalDateTime.now());

        return mapper.toResponse(orderRepository.save(order));
    }

    private String saveFile(MultipartFile file, String dir) {
        try {
            File folder = new File(dir);
            if (!folder.exists()) folder.mkdirs();

            String filePath = folder.getAbsolutePath() + "/" + file.getOriginalFilename();
            file.transferTo(new File(filePath));

            return filePath;
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el archivo: " + file.getOriginalFilename(), e);
        }
    }


}
