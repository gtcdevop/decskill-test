package com.decskill.exerciciodeteste.service;


import com.decskill.exerciciodeteste.exceptions.NotFoundException;
import com.decskill.exerciciodeteste.model.OrderEntity;
import com.decskill.exerciciodeteste.model.UserEntity;
import com.decskill.exerciciodeteste.repository.OrderRepository;
import com.decskill.exerciciodeteste.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    StockService stockService;

    /**
     * This method use the order data, and query the stock, then try to fullfill the items present on order
     * @param newOrder the order instance with item list
     */
    public void createAnOrderAndTryToFullFillItemsUsingItemsOnStock(OrderEntity newOrder) {
        UserEntity userEntity = userRepository.findById(newOrder.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        newOrder.setUserEntity(userEntity);
        newOrder.setCreationDate(new Date());

        boolean isOrderFullFilled = newOrder.getOrderItem().stream().map(eachItem -> {
            // try fullFill the items, return true,
            boolean isItemFullfilled =  this.stockService.tryFullFillItemListFromStockItems(eachItem);
            if(isItemFullfilled) {
                eachItem.setFullFilled(true);
            }
            return isItemFullfilled;
        }).reduce(true, (a, b) -> a && b);

        // Set the remaining data
        newOrder.setFullfilled(isOrderFullFilled);
        newOrder.setUserEntity(userEntity);
        newOrder.setCreationDate(new Date());
        orderRepository.save(newOrder); // persist the order

    }

    public void updateOrder(OrderEntity existingOrder, OrderEntity newOrder) {

    }

}
