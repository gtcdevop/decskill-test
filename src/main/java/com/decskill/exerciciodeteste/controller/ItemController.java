package com.decskill.exerciciodeteste.controller;


import com.decskill.exerciciodeteste.exceptions.NotFoundException;
import com.decskill.exerciciodeteste.model.ItemEntity;
import com.decskill.exerciciodeteste.model.UserEntity;
import com.decskill.exerciciodeteste.repository.ItemRepository;
import com.decskill.exerciciodeteste.repository.UserRepository;
import com.decskill.exerciciodeteste.service.ItemService;
import com.decskill.exerciciodeteste.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/item")
@Validated
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createItem(@Valid @RequestBody ItemEntity item) {
        itemRepository.save(item);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Long id) {
        ItemEntity item = itemRepository.findById(id).orElseThrow(() -> new NotFoundException("item not found"));
        return ResponseEntity.ok(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItemById(@PathVariable Long id, @RequestBody ItemEntity item) {
        ItemEntity existingItem = itemRepository.findById(id).orElseThrow(() -> new NotFoundException("item not found to update"));
        itemService.updateItem(existingItem, item);
        itemRepository.save(existingItem);
        return ResponseEntity.ok(existingItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItemById(@PathVariable Long id) {
        itemRepository.findById(id).orElseThrow(() -> new NotFoundException("item not found to delete"));
        itemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
