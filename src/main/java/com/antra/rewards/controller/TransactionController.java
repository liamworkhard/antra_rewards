package com.antra.rewards.controller;

import com.antra.rewards.cache.CustomerThreadLocal;
import com.antra.rewards.entity.Transaction;
import com.antra.rewards.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<Transaction>> getByCustomerId() {
        logger.info("In TransactionController::getByCustomerId method start");
        List<Transaction> transactions = transactionService.findByCustomerId(CustomerThreadLocal.get().getId());

        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
