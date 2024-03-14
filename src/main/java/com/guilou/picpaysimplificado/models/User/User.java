package com.guilou.picpaysimplificado.models.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.guilou.picpaysimplificado.dtos.UserRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_users")
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public User(UserRequestDTO data) {
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.document = data.document();
        this.email = data.email();
        this.password = data.password();
        this.balance = data.balance();
        this.userType = UserType.valueOf(data.userType());
    }
}
