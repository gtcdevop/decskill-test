package com.decskill.exerciciodeteste.controller;

import com.decskill.exerciciodeteste.controller.exceptionHandler.CommonExceptionHandler;
import com.decskill.exerciciodeteste.exceptions.NotFoundException;
import com.decskill.exerciciodeteste.model.ItemEntity;
import com.decskill.exerciciodeteste.model.OrderEntity;
import com.decskill.exerciciodeteste.model.StockEntity;
import com.decskill.exerciciodeteste.repository.ItemRepository;
import com.decskill.exerciciodeteste.repository.StockRepository;
import com.decskill.exerciciodeteste.service.StockService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("stock")
@Validated
public class StockMovementController extends CommonExceptionHandler {

    @Autowired
    private StockService stockService;

    @Autowired
    private StockRepository stockRepository;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> moveItemInStock(@Valid @RequestBody StockEntity stockEntity) {
        return ResponseEntity.ok(stockService.createOrUpdateStock(stockEntity));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateItemInStock(@Valid @PathVariable Long id, @Valid @RequestBody StockEntity stockEntity) {
        stockEntity.setItemId(id);
        return ResponseEntity.ok(stockService.createOrUpdateStock(stockEntity));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteStockByItemId(@Valid @PathVariable Long id) {
        stockRepository.findById(id).orElseThrow(() -> new NotFoundException("Stock not found"));
        stockRepository.deleteByItemId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("stock removido com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStockByItemId(@PathVariable Long id) {
        StockEntity stockEntity = stockRepository.findById(id).orElseThrow(() -> new NotFoundException("Stock not found"));
        return ResponseEntity.ok(stockEntity);
    }

}
