package com.decskill.exerciciodeteste.repository;

import com.decskill.exerciciodeteste.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
