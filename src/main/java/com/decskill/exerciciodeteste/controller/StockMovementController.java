package com.decskill.exerciciodeteste.controller;

import com.decskill.exerciciodeteste.exceptions.NotFoundException;
import com.decskill.exerciciodeteste.model.OrderEntity;
import com.decskill.exerciciodeteste.model.StockEntity;
import com.decskill.exerciciodeteste.repository.StockRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("stock-movement")
public class StockMovementController {

    @Autowired
    private StockRepository stockRepository;

    @PostMapping("")
    public ResponseEntity<?> moveItemInStock(@RequestBody OrderEntity orderEntity) {
        return ResponseEntity.ok(orderEntity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStockMovementById(@PathVariable Long id) {
        StockEntity stockEntity = stockRepository.findById(id).orElseThrow(() -> new NotFoundException("Stock Movement not found"));
        return ResponseEntity.ok(stockEntity);
    }


}

