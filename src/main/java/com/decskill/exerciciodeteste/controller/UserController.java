package com.decskill.exerciciodeteste.controller;

import com.decskill.exerciciodeteste.controller.exceptionHandler.CommonExceptionHandler;
import com.decskill.exerciciodeteste.exceptions.NotFoundException;
import com.decskill.exerciciodeteste.model.UserEntity;
import com.decskill.exerciciodeteste.repository.UserRepository;
import com.decskill.exerciciodeteste.responses.ErrorResponse;
import com.decskill.exerciciodeteste.service.UserService;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@Validated
public class UserController extends CommonExceptionHandler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping(value ="", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@Valid @RequestBody UserEntity user) {
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserEntity user) {
        UserEntity existingUser = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found to update"));
        userService.updateUser(existingUser, user);
        userRepository.save(existingUser);
        return ResponseEntity.ok(existingUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found to delete"));
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
