package org.donstu.domain;

import java.util.List;

public class Order {
    private String orderId;
    private String customerId;
    private List<Product> products;
    private double totalCost;

    public Order(String orderId, String customerId, List<Product> products, double totalCost) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.products = products;
        this.totalCost = totalCost;
    }

    public Order() {
    }

    public String getOrderId() {
        return this.orderId;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public double getTotalCost() {
        return this.totalCost;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
