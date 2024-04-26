package com.upc.edu.Backend_TravelBazaar.controller;


import com.upc.edu.Backend_TravelBazaar.model.Order;
import com.upc.edu.Backend_TravelBazaar.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Orders", description = "the order API")
@RestController
@RequestMapping("/api/travelbazaar/v1")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Endpoint: /api/travelbazaar/v1/orders
    // Method: GET
    // Obtiene todos los Orders
    @Transactional
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    // Endpoint: /api/travelbazaar/v1/orders/{id}
    // Method: GET
    // Obtiene el Order por ID
    @Transactional
    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id) {
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    // Endpoint: /api/travelbazaar/v1/orders
    // Method: POST
    // Crea el Order
    @Transactional
    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.saveOrder(order), HttpStatus.CREATED);
    }

    // Endpoint: /api/travelbazaar/v1/orders/{id}
    // Method: PUT
    // Actualiza el Order
    @Transactional
    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable int id, @RequestBody Order order) {
        return new ResponseEntity<>(orderService.updateOrder(id, order), HttpStatus.OK);
    }

    // Endpoint: /api/travelbazaar/v1/orders/{id}
    // Method: DELETE
    // Elimina el Order por ID
    @Transactional
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
    }
}
