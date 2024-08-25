package com.example.OnlineBookstoreManagementBackend.dto;

import java.util.Date;
import java.util.List;

public class OrderDTO {

    private Long id;
    private Long userId;
    private List<BookDTO> books;
    private Double totalAmount;
    private Date orderDate;

    // Constructors
    public OrderDTO() {}

    public OrderDTO(Long id, Long userId, List<BookDTO> books, Double totalAmount, Date orderDate) {
        this.id = id;
        this.userId = userId;
        this.books = books;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
