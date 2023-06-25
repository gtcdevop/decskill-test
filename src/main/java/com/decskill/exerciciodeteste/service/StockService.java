package com.decskill.exerciciodeteste.service;

import com.decskill.exerciciodeteste.exceptions.NotFoundException;
import com.decskill.exerciciodeteste.model.ItemEntity;
import com.decskill.exerciciodeteste.model.OrderItemEntity;
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


    /**
     * Try to fullfill item by querying their stock, and update the stock with new quantity
     * @param itemOnOrder the item requested by an OrderEntity
     * @return true if the order was fullfilld, false if there are no available items on stock
     */
    public boolean tryFullFillItemListFromStockItems(OrderItemEntity itemOnOrder) {
        StockEntity itemStock = stockRepository.findById(itemOnOrder.getItemId()).orElse(null);
        if(itemStock != null) {
            // check if there are items available on stock
            int stockDifference = itemStock.getQuantity() - itemOnOrder.getQuantity();
            if( stockDifference >= 0) {
                itemStock.setQuantity(stockDifference);
                stockRepository.save(itemStock);
                return true;
            }
        }
        return false;
    }

    /**
     * If the stock exists for the item id, add more products, otherwise initialize the new stock
     * @param stockEntity
     * @return created or updated Stock entity
     */
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
