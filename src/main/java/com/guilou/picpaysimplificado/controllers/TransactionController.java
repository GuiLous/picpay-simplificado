package com.guilou.picpaysimplificado.controllers;

import com.guilou.picpaysimplificado.dtos.TransactionDTO;
import com.guilou.picpaysimplificado.models.Transaction.Transaction;
import com.guilou.picpaysimplificado.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody @Valid TransactionDTO data) throws Exception {
        Transaction newTransaction = this.transactionService.createTransaction(data);
        return ResponseEntity.ok(newTransaction);
    }
}
