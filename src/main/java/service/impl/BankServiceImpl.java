package service.impl;

import service.BankService;
import service.entities.Bank;
import service.entities.Transaction;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class BankServiceImpl implements BankService {

    private static final String transactionsCSV = "src/main/java/bank/transactions/transactions.csv";

    @Override
    public List<Transaction> getAllTransactions(File f) throws IOException {

        if (!(f.exists() && f.isFile())) {
            System.out.println(" File does not exist. ");
            return null;
        }

        String line;
        List<Transaction> transactions = new LinkedList<>();

        BufferedReader reader = new BufferedReader(new FileReader(f));
        reader.readLine(); // skip first line

        while ((line = reader.readLine()) != null) {
            String[] content = line.split(",");

            String day = content[0];
            String id = content[1];
            String source = content[2];
            String destination = content[3];
            double amount = Double.parseDouble((content[4]));
            String currency = content[5];
            String category = content[6];

            Transaction transaction = new Transaction(day,
                                                      id,
                                                      source,
                                                      destination,
                                                      amount,
                                                      currency,
                                                      category);
            transactions.add(transaction);
        }

        Collections.sort(transactions);

        return transactions;
    }

    @Override
    public void createNewFile(String folder, String fileName) throws IOException {

        File dir = new File(folder);

        if (!(dir.exists() && dir.isDirectory())) {
            System.out.printf(" Directory does not exist. %n");
            return;
        }

        try {
            File f = new File(folder + fileName);
            if (f.createNewFile()) {
                System.out.printf(" File created: '%s'. %n", f.getName());
            }
            else {
                System.out.printf(" File '%s' already exists. %n", f.getName());
            }

        } catch (IOException e) {
            System.out.printf(" An error has occurred. %n");
            e.printStackTrace();
        }
    }

    @Override
    public void writeToFile(File f) throws IOException {

        if (!(f.exists() && f.isFile())) {
            System.out.println(" File does not exist. ");
            return;
        }

        BankServiceImpl bank = new BankServiceImpl();
        List<Bank> readOutput = bank.readLogFile();

        BufferedReader reader = new BufferedReader(new FileReader(f));
        if (reader.readLine() != null) {
            System.out.printf(" Overwriting file '%s'... %n", f.getName());
        }

        try {
            File file = new File(String.valueOf(f));
            if (file.exists() && file.isFile()) {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write("BANK-CODE,BANK-NAME\n");
                for (Bank b : readOutput) {
                    fileWriter.write(b + "\n");
                }
                fileWriter.close();
                System.out.printf(" File '%s' written successfully. %n", file.getName());
                System.out.printf(" File path: %s. %n", file.getPath());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method to read log files submitted by a bank.
     *
     * @return a new list of Bank.
     */
    public List<Bank> readLogFile() {

        String line;
        List<Bank> banks = new LinkedList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(BankServiceImpl.transactionsCSV));
            reader.readLine(); //skip first line

            while ((line = reader.readLine()) != null) {
                String[] content = line.split(",");
                String code = content[1];
                String name = content[4];
                Bank bank = new Bank(code, name);
                if (!banks.contains(bank)) {
                    banks.add(bank);
                }
            }

            Collections.sort(banks);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return banks;
    }

}
