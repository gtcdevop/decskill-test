package com.decskill.exerciciodeteste.service;

import com.decskill.exerciciodeteste.model.ItemEntity;
import com.decskill.exerciciodeteste.model.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ItemService {

    public void updateItem(ItemEntity existingItem, ItemEntity newItem) {
        if(!StringUtils.isEmpty(newItem.getName())) {
            existingItem.setName(newItem.getName());
        }
    }
}
