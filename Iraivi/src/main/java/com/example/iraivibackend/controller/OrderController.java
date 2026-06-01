package com.example.iraivibackend.controller;
import org.springframework.web.bind.annotation.RestController;

import com.example.iraivibackend.model.Order;
import com.example.iraivibackend.repository.OrderRepository;
import com.example.iraivibackend.service.OrderService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;
import java.util.List;

@RestController
@CrossOrigin(origins = "${frontend.url}")
@RequestMapping("/api")
public class OrderController 
{

    @Autowired
    private OrderService orderService;

    @PostMapping("/saveOrder")
    public String receiveOrder(@RequestBody Map<String, Object> cartData) 
    {
        try 
        {
            orderService.saveCartItems(cartData); // Service moolama save panrom
            currentCart.clear();
            return "{\"status\":\"Success\"}";
        } 
        catch (Exception e) 
        {
            return "{\"status\":\"Error\", \"message\":\"" + e.getMessage() + "\"}";
        }
    }

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/viewOrders")
    public List<Order> getAllOrders() 
    {
        return orderRepository.findAll();
    }

        // Inga oru temporary storage venum (in-memory cart)
    private Map<String, Object> currentCart = new java.util.HashMap<>();

    @PostMapping("/update-cart")
    public String updateCart(@RequestBody Map<String, Object> data) {
        // Menu-la "+" click pannumbodhu inga dhaan data varum
        String id = data.get("productId").toString();
        int qty = Integer.parseInt(data.get("qty").toString());

        if (qty > 0) 
        {
            currentCart.put(id, data); // Cart-la item add panrom
        } else 
        {
            currentCart.remove(id); // Count 0-na remove panrom
        }
        // Inga unga logic-padi cart-ai update pannunga
        return "{\"status\":\"Updated\"}";
    }

    @GetMapping("/get-cart")
    public Map<String, Object> getCart() {
        // Idhu dhaan orders.js-ku data-vai anuppum
        // Ippo test panna dummy data anuppalaam, apram unga actual logic-ai podunga
        return currentCart; 
    }
}
