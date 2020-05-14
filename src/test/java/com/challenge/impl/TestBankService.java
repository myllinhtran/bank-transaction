package com.challenge.impl;

import org.junit.Test;
import service.BankService;
import service.entities.Bank;
import service.entities.Transaction;
import service.impl.BankServiceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class TestBankService {

    private static final String transactionsCSV = "src/main/java/bank/transactions/transactions.csv";


    @Test
    public void testReadingTransactionFileShouldReturnFalseIfEmpty() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(transactionsCSV));
        assertTrue(new File(transactionsCSV).exists());
        assertNotNull(reader.readLine());
    }

    @Test
    public void testIfFolderLogsFileExistsShouldReturnFalseIfNot() throws IOException {
        final String logs = "src/main/java/bank/transactions/logs";
        File folder = new File(logs);
        assertTrue(folder.exists()); // check if folder logs exists
        for (File f : folder.listFiles()) {
            assertTrue(f.exists()); // check if file exists.
        }
    }


    @Test
    public void testReadLogFileShouldReturnAListOfBanks() throws Exception {
        BankServiceImpl service = new BankServiceImpl();
        List<Bank> output = service.readLogFile();
        Bank actual = output.get(0);
        Bank expected = new Bank("B001", "Bankia");
        assertEquals(actual, expected);
    }

    @Test
    public void testGetAllTransactionsShouldReturnFalseIfListIsEmpty() throws IOException {
        final String logs = "src/main/java/bank/transactions/logs/B001_001.csv";
        BankService service = new BankServiceImpl();
        List<Transaction> transactions = service.getAllTransactions(new File(logs));
        assertFalse(transactions.isEmpty());
    }
}