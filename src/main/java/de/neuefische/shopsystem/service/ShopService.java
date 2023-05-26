package de.neuefische.shopsystem.service;

import de.neuefische.shopsystem.repository.OrderRepository;
import de.neuefische.shopsystem.model.Order;
import de.neuefische.shopsystem.model.Product;
import de.neuefische.shopsystem.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShopService {

    // Abhängigkeiten -> Der ShopService BRAUCHT diese Repositories
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public ShopService(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public Order getOrderById(String id) {
        return orderRepository.getOrderById(id);
    }

    public Product getProductById(String id) {
        return productRepository.getProductById(id);
    }

    public List<Product> listProducts() {
        return productRepository.list();
    }

    // ShopService greift auf das Repository zu
    public List<Order> listOrders() {
        return orderRepository.list();
    }

    public void addOrder(Order orderToAdd) {
        orderRepository.addOrder(orderToAdd);
    }
}
