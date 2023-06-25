package com.decskill.exerciciodeteste.service;

import com.decskill.exerciciodeteste.model.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {

    public void updateUser(UserEntity existingUser, UserEntity newUser) {
        if(!StringUtils.isEmpty(newUser.getName())) {
            existingUser.setName(newUser.getName());
        }
        if(!StringUtils.isEmpty(newUser.getEmail())) {
            existingUser.setEmail(newUser.getEmail());
        }
        if(!StringUtils.isEmpty(newUser.getPassword())) {
            existingUser.setPassword(newUser.getPassword());
        }
    }

    private void sendEmailToUser(String email, String body) {

    }

    public void sendEmailToUserNotifyCompleteOrder(String email, int orderId) {

    }
}
