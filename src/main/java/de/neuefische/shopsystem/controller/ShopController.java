package de.neuefische.shopsystem.controller;

import de.neuefische.shopsystem.model.Order;
import de.neuefische.shopsystem.model.Product;
import de.neuefische.shopsystem.service.ShopService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop")
public class ShopController {
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }
    @GetMapping("/orders/{id}")
    public Order getOrderById(@PathVariable String id){
        return shopService.getOrderById(id);
    }
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable String id){
        return shopService.getProductById(id);
    }
    @GetMapping("/products")
    public List<Product> listProducts(){
        return shopService.listProducts();
    }
    @GetMapping("/orders")
    public List<Order> listOrders(){
        return shopService.listOrders();
    }
}
