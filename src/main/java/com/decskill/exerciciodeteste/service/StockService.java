package com.decskill.exerciciodeteste.service;

import com.decskill.exerciciodeteste.exceptions.NotFoundException;
import com.decskill.exerciciodeteste.model.ItemEntity;
import com.decskill.exerciciodeteste.model.StockEntity;
import com.decskill.exerciciodeteste.repository.ItemRepository;
import com.decskill.exerciciodeteste.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ItemRepository itemRepository;


    public StockEntity createOrUpdateStock(StockEntity stockEntity) {
        ItemEntity itemEntity = itemRepository.findById(stockEntity.getItemId()).orElseThrow(() -> new NotFoundException("Item Id not found"));
        stockEntity.setItemEntity(itemEntity);
        // Try finding the stock
        StockEntity foundExistingStock = stockRepository.findById(stockEntity.getItemId()).orElse(null);
        if (foundExistingStock != null) {
            foundExistingStock.setQuantity(stockEntity.getQuantity() + foundExistingStock.getQuantity());
            stockRepository.save(foundExistingStock);
            return foundExistingStock;
        } else {
            stockEntity.setCreationDate(new Date());
            stockRepository.save(stockEntity);
            return stockEntity;
        }
    }
}
