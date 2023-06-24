package com.decskill.exerciciodeteste.service;


import com.decskill.exerciciodeteste.model.OrderEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public void createAnOrder(OrderEntity newOrder) {
        // Try to look into stock and try to full fill the order
        // Get the list of items, with their quantity
        // Verify if there is anything in stock
        // update the stock and the order
    }

    public void updateOrder(OrderEntity existingOrder, OrderEntity newOrder) {

    }

}
