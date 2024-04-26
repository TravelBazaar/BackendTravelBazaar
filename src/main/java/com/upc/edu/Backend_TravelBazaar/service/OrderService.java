package com.upc.edu.Backend_TravelBazaar.service;

import com.upc.edu.Backend_TravelBazaar.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(int id);
    Order saveOrder(Order order);
    Order updateOrder(int id, Order order);
    void deleteOrder(int id);
}
