package com.guilou.picpaysimplificado.repositories;

import com.guilou.picpaysimplificado.models.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByDocument(String document);
    Optional<User> findUserById(UUID id);
}
