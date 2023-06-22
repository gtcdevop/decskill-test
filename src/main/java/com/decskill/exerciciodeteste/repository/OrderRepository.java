package com.decskill.exerciciodeteste.repository;

import com.decskill.exerciciodeteste.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
