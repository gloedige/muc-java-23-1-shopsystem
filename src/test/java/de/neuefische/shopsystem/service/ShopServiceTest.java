package de.neuefische.shopsystem.service;

import de.neuefische.shopsystem.exception.ProductNotFoundException;
import de.neuefische.shopsystem.model.Order;
import de.neuefische.shopsystem.model.Product;
import org.junit.jupiter.api.Test;
import de.neuefische.shopsystem.repository.OrderRepository;
import de.neuefische.shopsystem.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Die Art & Weise dieser Test-Suite ist ziemlich redundant, da der gleiche & wesentliche Code bereits vom Repository abgedeckt ist
// Dies wird sich in Zukunft umdrehen, da wir Repositories (so gut wie) nicht mehr testen werden, sondern nur noch die Services

// Außerdem: Der Test ist bewusst in einem anderem Stil geschrieben, um zu zeigen, dass es auch anders geht
class ShopServiceTest {

    @Test
    void getProduct_whenNonExistingId_thenThrowProductNonFoundException() {
        //GIVEN
        List<Order> order = new ArrayList<>();
        OrderRepository orderRepository = new OrderRepository(order);
        List<Product> products = new ArrayList<>();
        ProductRepository productRepository = new ProductRepository(products);

        ShopService shopService = new ShopService(productRepository, orderRepository);

        //WHEN + THEN
        assertThrows(ProductNotFoundException.class, () -> shopService.getProductById("nonExistingId"));
    }

    @Test
    void getProduct_whenExistingId_thenReturnProduct() {
        //GIVEN
        List<Order> order = new ArrayList<>();
        OrderRepository orderRepository = new OrderRepository(order);

        Product product1 = new Product("123", "Banane");
        List<Product> products = new ArrayList<>();
        products.add(product1);

        ProductRepository productRepository = new ProductRepository(products);

        ShopService shopService = new ShopService(productRepository, orderRepository);

        //WHEN
        Product actual = shopService.getProductById("123");

        //THEN
        Product expected = new Product("123", "Banane");
        assertEquals(expected, actual);
    }

    @Test
    void listProducts_thenReturnProductList() {
        //GIVEN
        List<Order> order = new ArrayList<>();
        OrderRepository orderRepository = new OrderRepository(order);

        Product product1 = new Product("Banane", "123");
        List<Product> products = new ArrayList<>();
        products.add(product1);

        ProductRepository productRepository = new ProductRepository(products);

        ShopService shopService = new ShopService(productRepository, orderRepository);

        //WHEN
        List<Product> actual = shopService.listProducts();

        //THEN
        List<Product> expected = new ArrayList<>(products);
        assertEquals(expected, actual);
    }

    @Test
    void listOrders_whenNoOrdersExist_thenReturnEmptyList() {
        //GIVEN
        List<Order> orders = new ArrayList<>();
        OrderRepository orderRepository = new OrderRepository(orders);

        Product product1 = new Product("Banane", "123");
        List<Product> products = new ArrayList<>();
        products.add(product1);

        ProductRepository productRepository = new ProductRepository(products);

        ShopService shopService = new ShopService(productRepository, orderRepository);

        //WHEN
        List<Order> actual = shopService.listOrders();

        //THEN
        List<Order> expected = new ArrayList<>(orders);
        assertEquals(expected, actual);
    }

    @Test
    void getOrderById_whenValidOrderId_thenReturnOrder() {
        //GIVEN
        Product product1 = new Product("Banane", "123");
        List<Product> products = new ArrayList<>();
        products.add(product1);

        ProductRepository productRepository = new ProductRepository(products);

        List<Order> orders = new ArrayList<>();
        Order order = new Order("123456", products);
        orders.add(order);

        OrderRepository orderRepository = new OrderRepository(orders);

        ShopService shopService = new ShopService(productRepository, orderRepository);

        //WHEN
        Order actual = shopService.getOrderById("123456");

        //THEN
        Order expected = new Order("123456", products);
        assertEquals(expected, actual);
    }

    @Test
    void addOrder_whenOrderWasPlacedSuccessfully_thenOrdersListLengthShouldBeIncremented() {
        //GIVEN
        Product product1 = new Product("Apfel", "123456");
        Product product2 = new Product("Birne", "123");

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        ProductRepository productRepository = new ProductRepository(products);

        List<Order> orders = new ArrayList<>(List.of(new Order("Order-1", products), new Order("Order-2")));
        OrderRepository orderRepository = new OrderRepository(orders);

        ShopService shopService = new ShopService(productRepository, orderRepository);
        int initialOrdersListSize = shopService.listOrders().size();


        //WHEN
        String orderId = "ABC13";
        shopService.addOrder(new Order(orderId));
        int incrementedOrdersListSize = shopService.listOrders().size();

        //THEN
        assertEquals(incrementedOrdersListSize, initialOrdersListSize + 1);
    }

}