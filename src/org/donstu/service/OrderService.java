package org.donstu.service;

import org.donstu.domain.Order;
import org.donstu.domain.Product;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import java.util.ArrayList;
import java.util.List;

import static javax.jws.soap.SOAPBinding.ParameterStyle.WRAPPED;
import static javax.jws.soap.SOAPBinding.Style.DOCUMENT;
import static javax.jws.soap.SOAPBinding.Use.LITERAL;

@WebService(serviceName = "OrderService", portName = "OrderPort", targetNamespace = "https://donstu.org/orders")
@SOAPBinding(style = DOCUMENT, use = LITERAL, parameterStyle = WRAPPED)
public class OrderService {

    List<Product> products = new ArrayList<>();
    List<Order> orders = new ArrayList<>();

    public OrderService() {
        products.add(new Product(1, "Первый товар", "Описание первого товара", 100));
        products.add(new Product(2, "Второй товар", "Описание второго товара", 200));
        products.add(new Product(3, "Третий товар", "Описание третьего товара", 150));
        products.add(new Product(4, "Четвертый товар", "Описание четвёртого товара", 120));
        products.add(new Product(5, "Пятый товар", "Описание пятого товара", 180));
        products.add(new Product(6, "Шестой товар", "Описание шестого товара", 250));
        products.add(new Product(7, "Седьмой товар", "Описание седьмого товара", 300));
        products.add(new Product(8, "Восьмой товар", "Описание восьмого товара", 170));
        products.add(new Product(9, "Девятый товар", "Описание девятого товара", 220));
        products.add(new Product(10, "Десятый товар", "Описание десятого товара", 190));
    }

    @WebMethod(operationName = "getAllProducts")
    public List<Product> getAllProducts() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @WebMethod(operationName = "getAllOrders")
    public List<Order> getAllOrders() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    @WebMethod(operationName = "placeOrder")
    public Order placeOrder(String customerId, List<Product> products) {
        Order order = new Order();
        order.setCustomerId(customerId);

        double totalCost = 0;
        List<Product> orderedProducts = new ArrayList<>();
        for (Product product : products) {
            orderedProducts.add(product);
            totalCost += product.getPrice();
        }

        order.setProducts(orderedProducts);
        order.setTotalCost(totalCost);
        order.setOrderId("Order-" + (orders.size() + 1));

        orders.add(order);
        return order;
    }
}
