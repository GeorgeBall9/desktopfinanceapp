package com.georgeBall.service;

import com.georgeBall.dao.TransactionDao;
import com.georgeBall.model.Transaction;

public class TransactionService {
    private TransactionDao transactionDao = new TransactionDao();

    public void addTransaction(Transaction transaction) {
        transactionDao.save(transaction);
    }

    // Additional methods for retrieving, updating, and deleting transactions
}
