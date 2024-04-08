package org.donstu;

import org.donstu.client.*;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ServiceClient {
    private static final QName FQDN = new QName("https://donstu.org/orders", "OrderService");

    private static URL getWsdlUrl(String urlStr) {
        URL url;
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
        List<Product> products = port.getAllProducts();
        products.forEach(product -> System.out.println("Product{" +
                "productId='" + product.getProductId() + '\'' +
                ", name=" + product.getName() +
                ", description=" + product.getDescription() +
                ", price=" + product.getPrice() +
                '}')
        );
    }

    public static void main(String[] args) {
        URL wsdlUrl = getWsdlUrl("http://127.0.0.1:8090/orders?wsdl");
        ServiceClient client = new ServiceClient();
        client.processProducts(wsdlUrl);
    }
}
