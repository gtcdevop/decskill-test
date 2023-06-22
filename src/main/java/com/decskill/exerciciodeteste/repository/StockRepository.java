package com.decskill.exerciciodeteste.repository;

import com.decskill.exerciciodeteste.model.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockEntity, Long> {
}
