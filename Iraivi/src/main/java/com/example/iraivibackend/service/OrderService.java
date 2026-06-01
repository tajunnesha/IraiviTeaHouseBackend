package com.example.iraivibackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.iraivibackend.model.Order;
import com.example.iraivibackend.repository.OrderRepository;

import java.util.Map;

@Service
public class OrderService 
{

    @Autowired
    private OrderRepository orderRepository;

    @Transactional // Transactional annotation use panna, method la error varum bodhu DB operations rollback aagum
    public void saveCartItems(Map<String, Object> cartData) 
    {
        for (String key : cartData.keySet()) {

            @SuppressWarnings("unchecked")
            Map<String, Object> item =
                    (Map<String, Object>) cartData.get(key);

            Order order = new Order();
            order.setItemName((String) item.get("name"));
            order.setQuantity((Integer.valueOf(item.get("qty").toString())));
            order.setPrice(Double.valueOf(item.get("price").toString()));
            
            orderRepository.save(order);
        }
    }
}