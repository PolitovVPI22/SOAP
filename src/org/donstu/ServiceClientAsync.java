package org.donstu;

import org.donstu.async.*;

import javax.xml.namespace.QName;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ServiceClientAsync {

    private static final QName FQDN = new QName("https://donstu.org/orders", "OrderService");

    private static URL getWsdlUrl(String urlStr) {
        URL url = null;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return url;
    }

    public void processProducts(URL url) {
        OrderService_Service svc = new OrderService_Service(url, FQDN);
        OrderService port = svc.getOrderPort();
        Response<GetAllProductsResponse> response = port.getAllProductsAsync();
        while (!response.isDone()) {
            System.out.println("Async action...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            System.out.println("Result:");
            List<Product> products = response.get().getReturn();
            products.forEach(product -> System.out.println("Product{" +
                    "productId='" + product.getProductId() + '\'' +
                    ", name=" + product.getName() +
                    ", description=" + product.getDescription() +
                    ", price=" + product.getPrice() +
                    '}'));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    static class AllProductsAsyncHandler implements AsyncHandler<GetAllProductsResponse> {
        @Override
        public void handleResponse(Response<GetAllProductsResponse> res) {
            System.out.println("Result:");
            try {
                List<Product> products = res.get().getReturn();
                products.forEach(product -> System.out.println("Product{" +
                        "productId='" + product.getProductId() + '\'' +
                        ", name=" + product.getName() +
                        ", description=" + product.getDescription() +
                        ", price=" + product.getPrice() +
                        '}'));
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void processProductsCallback(URL url) {
        OrderService_Service svc = new OrderService_Service(url, FQDN);
        OrderService port = svc.getOrderPort();
        AllProductsAsyncHandler handler = new AllProductsAsyncHandler();
        Future<?> response = port.getAllProductsAsync(handler);
        while (!response.isDone()) {
            System.out.println("Async action...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        URL wsdlUrl = getWsdlUrl("http://127.0.0.1:8090/orders?wsdl");
        ServiceClientAsync client = new ServiceClientAsync();
        client.processProducts(wsdlUrl);
        client.processProductsCallback(wsdlUrl);
    }
}
