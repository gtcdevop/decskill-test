package com.decskill.exerciciodeteste.service;


import com.decskill.exerciciodeteste.exceptions.NotFoundException;
import com.decskill.exerciciodeteste.model.OrderEntity;
import com.decskill.exerciciodeteste.model.UserEntity;
import com.decskill.exerciciodeteste.repository.OrderRepository;
import com.decskill.exerciciodeteste.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OrderService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    StockService stockService;

    @Autowired
    EmailService emailService;

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
        if(isOrderFullFilled) { // send email to user
            this.notifyUserWhenOrderIsFullFilled(newOrder);
        }

        // Set the remaining data
        newOrder.setFullfilled(isOrderFullFilled);
        newOrder.setUserEntity(userEntity);
        newOrder.setCreationDate(new Date());
        orderRepository.save(newOrder); // persist the order
    }

    public void notifyUserWhenOrderIsFullFilled(OrderEntity orderEntity) {
        List<String> emailsList = new ArrayList();
        emailsList.add(orderEntity.getUserEntity().getEmail());
        String subject = new StringBuilder()
                .append("Order id: ").append(orderEntity.getId()).append(". is fullfilled").toString();
        ObjectMapper mapper = new ObjectMapper();
        String body = subject;
        try {
            mapper.writeValueAsString(orderEntity);
        } catch (JsonProcessingException e) {
            log.info("Failed to stringfy into JSON the orderEntity with Id: " + orderEntity.getId().toString());
        }
        this.emailService.sendEmail(emailsList, subject, body);
    }

    public OrderEntity updateExistingOrder(OrderEntity existingOrder, OrderEntity newOrder) {
        return new OrderEntity();
    }

}
