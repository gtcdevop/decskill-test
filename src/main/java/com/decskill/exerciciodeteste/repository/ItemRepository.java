package com.decskill.exerciciodeteste.repository;

import com.decskill.exerciciodeteste.model.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

}