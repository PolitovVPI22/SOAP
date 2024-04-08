package org.donstu;

import org.donstu.service.OrderService;

import javax.xml.ws.Endpoint;

public class ServiceHost {
    public static void main(String[] args) {
        System.out.println("Start Web service");
        Endpoint.publish("http://127.0.0.1:8090/orders", new OrderService());
    }
}