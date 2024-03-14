package com.guilou.picpaysimplificado.services;

import com.guilou.picpaysimplificado.models.Transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.guilou.picpaysimplificado.dtos.TransactionDTO;
import com.guilou.picpaysimplificado.models.User.User;
import com.guilou.picpaysimplificado.repositories.ITransactionRepository;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;

    @Autowired
    private ITransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        this.userService.validateTransaction(sender, transaction.value());

        boolean isAuthorized = this.authorizeTransaction(sender, transaction.value());

        if(!isAuthorized) {
            throw new Exception("Transaction not authorized");
        }

        Transaction newTransaction = new Transaction(transaction, sender, receiver);

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.transactionRepository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(sender, "Transação realizada com sucesso!");
        this.notificationService.sendNotification(receiver, "Você recebeu uma transação de " + sender.getFirstName() + " " + sender.getLastName() + " no valor de R$" + transaction.value() + "!");

        return newTransaction;
    }

    public boolean authorizeTransaction(User sender, BigDecimal value) {
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);

        var isOkStatus = authorizationResponse.getStatusCode() == HttpStatus.OK;
        var isAuthorized = Objects.requireNonNull(authorizationResponse.getBody()).get("message").equals("Autorizado");

        return isOkStatus && isAuthorized;
    }
}
