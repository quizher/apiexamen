package com.enrique.apiexamen.controller;

import com.enrique.apiexamen.dto.request.AssignmentRequest;
import com.enrique.apiexamen.dto.request.OrderRequest;
import com.enrique.apiexamen.dto.request.UpdateStatusRequest;
import com.enrique.apiexamen.dto.response.OrderResponse;
import com.enrique.apiexamen.entity.OrderStatus;
import com.enrique.apiexamen.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponse create(@Valid @RequestBody OrderRequest request) {
        return orderService.create(request);
    }

    @GetMapping("/{id}")
    public OrderResponse getById(@PathVariable UUID id) {
        return orderService.getById(id);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrdersWithFilters(
            @RequestParam(required = false) OrderStatus status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate,
            @RequestParam(required = false) String origin,
            @RequestParam(required = false) String destination
    ) {
        List<OrderResponse> orders = orderService.findByFilters(status, fromDate, toDate, origin, destination);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderResponse> updateStatus(@PathVariable UUID id,
                                                      @Valid @RequestBody UpdateStatusRequest request) {
        OrderResponse updated = orderService.updateStatus(id, request.getStatus());
        return ResponseEntity.ok(updated);
    }

    @PostMapping(value = "/{orderId}/assign", consumes = "multipart/form-data")
    public OrderResponse assignDriverAndFiles(
            @PathVariable UUID orderId,
            @ModelAttribute AssignmentRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        return orderService.assignDriverAndFiles(orderId, request.getDriverId(), file, image);
    }
}
