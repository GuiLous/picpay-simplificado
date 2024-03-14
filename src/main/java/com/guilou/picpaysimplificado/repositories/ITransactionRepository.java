package com.guilou.picpaysimplificado.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guilou.picpaysimplificado.models.Transaction.Transaction;

public interface ITransactionRepository extends JpaRepository<Transaction, UUID> {
}
