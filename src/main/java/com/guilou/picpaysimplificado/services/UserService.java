package com.guilou.picpaysimplificado.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.guilou.picpaysimplificado.dtos.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilou.picpaysimplificado.models.User.User;
import com.guilou.picpaysimplificado.models.User.UserType;
import com.guilou.picpaysimplificado.repositories.IUserRepository;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Merchant cannot send money");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Insufficient funds");
        }
    }

    public User findUserById(UUID id) throws Exception {
        return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("User not found"));
    }

    public User createuser(UserRequestDTO data) {
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }
}
