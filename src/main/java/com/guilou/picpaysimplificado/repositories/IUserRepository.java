package com.guilou.picpaysimplificado.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guilou.picpaysimplificado.models.User.User;

public interface IUserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByDocument(String document);

    Optional<User> findUserById(UUID id);
}
