package com.example.OnlineBookstoreManagementBackend.service;

import com.example.OnlineBookstoreManagementBackend.dto.OrderDTO;
import com.example.OnlineBookstoreManagementBackend.model.Order;
import com.example.OnlineBookstoreManagementBackend.model.OrderItem;
import com.example.OnlineBookstoreManagementBackend.model.User;
import com.example.OnlineBookstoreManagementBackend.repository.OrderRepository;
import com.example.OnlineBookstoreManagementBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public OrderDTO addOrder(OrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        List<OrderItem> orderItems = orderDTO.getBooks().stream()
                .map(bookDTO -> new OrderItem(null, bookDTO.getId(), bookDTO.getTitle(), bookDTO.getPrice(), bookDTO.getQuantity()))
                .collect(Collectors.toList());

        Order order = new Order();
        order.setUser(user);
        order.setOrderItems(orderItems);
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setOrderDate(new Date());

        Order savedOrder = orderRepository.save(order);
        return convertToDTO(savedOrder);
    }

    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        // Find the existing order
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Update order details
        existingOrder.setUser(userRepository.findById(orderDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));

        List<OrderItem> orderItems = orderDTO.getBooks().stream()
                .map(bookDTO -> new OrderItem(null, bookDTO.getId(), bookDTO.getTitle(), bookDTO.getPrice(), bookDTO.getQuantity()))
                .collect(Collectors.toList());

        existingOrder.setOrderItems(orderItems);
        existingOrder.setTotalAmount(orderDTO.getTotalAmount());
        existingOrder.setOrderDate(orderDTO.getOrderDate());

        // Save the updated order
        Order updatedOrder = orderRepository.save(existingOrder);
        return convertToDTO(updatedOrder);
    }

    public OrderDTO getOrderById(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        return orderOptional.map(this::convertToDTO).orElse(null);
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private OrderDTO convertToDTO(Order order) {
        List<com.example.OnlineBookstoreManagementBackend.dto.BookDTO> bookDTOs = order.getOrderItems().stream()
                .map(orderItem -> new com.example.OnlineBookstoreManagementBackend.dto.BookDTO(
                        orderItem.getBookId(), orderItem.getTitle(), null, null, orderItem.getPrice(), orderItem.getQuantity()
                ))
                .collect(Collectors.toList());

        return new OrderDTO(order.getId(), order.getUser().getId(), bookDTOs, order.getTotalAmount(), order.getOrderDate());
    }
}
