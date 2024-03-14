package com.guilou.picpaysimplificado.models.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.guilou.picpaysimplificado.dtos.TransactionDTO;
import com.guilou.picpaysimplificado.models.User.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_transactions")
@EqualsAndHashCode(of = "id")
public class Transaction {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;
    private LocalDateTime timestamp;

    public Transaction(TransactionDTO data, User sender, User receiver) {
        this.amount = data.value();
        this.sender = sender;
        this.receiver = receiver;
        this.timestamp = LocalDateTime.now();
    }
}
