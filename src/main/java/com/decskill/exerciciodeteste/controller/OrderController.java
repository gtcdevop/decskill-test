package com.decskill.exerciciodeteste.controller;

import com.decskill.exerciciodeteste.controller.exceptionHandler.CommonExceptionHandler;
import com.decskill.exerciciodeteste.exceptions.NotFoundException;
import com.decskill.exerciciodeteste.exceptions.NotImplementedException;
import com.decskill.exerciciodeteste.model.ItemEntity;
import com.decskill.exerciciodeteste.model.OrderEntity;
import com.decskill.exerciciodeteste.model.UserEntity;
import com.decskill.exerciciodeteste.repository.ItemRepository;
import com.decskill.exerciciodeteste.repository.OrderRepository;
import com.decskill.exerciciodeteste.repository.UserRepository;
import com.decskill.exerciciodeteste.service.ItemService;
import com.decskill.exerciciodeteste.service.OrderService;
import com.decskill.exerciciodeteste.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/order")
@Validated
public class OrderController  extends CommonExceptionHandler {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    /**
     * Create an Order and try to fullfill the items by using the items present on Stock
     * @param order
     * @return ResponseEntity
     */
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderEntity order) {

        orderService.createAnOrderAndTryToFullFillItemsUsingItemsOnStock(order);

        return ResponseEntity.ok(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        OrderEntity order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException("order not found"));
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderById(@PathVariable Long id, @RequestBody OrderEntity order) {
        throw new NotImplementedException("Update order not implemented");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable Long id) {
        orderRepository.findById(id).orElseThrow(() -> new NotFoundException("order not found to delete"));
        orderRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
