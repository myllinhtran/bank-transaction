package service;

import service.entities.Balance;
import service.entities.Category;
import service.entities.Transaction;
import service.impl.BalanceServiceImpl;
import service.impl.BankServiceImpl;
import service.impl.CategoryServiceImpl;
import service.util.Folder;

import java.io.File;
import java.util.List;

public class Result {

    public static void main(String[] args) throws Exception {

        final String logs = "src/main/java/bank/transactions/logs/";
        final String resultFolder = "src/main/java/bank/result/";
        final String balanceFolder = "src/main/java/bank/result/daily_balance/";
        final String categoryFolder = "src/main/java/bank/result/categories/";

        // create new folder "result"
        Folder folder = new Folder();
        folder.createNewFolder(resultFolder);

        // create a new file "banks.csv" listing all codes and name of banks
        BankService bankService = new BankServiceImpl();
        bankService.createNewFile(resultFolder, "banks.csv");

        String banksCSV = resultFolder + "banks.csv";
        bankService.writeToFile(new File(banksCSV));

        // create new folder "daily_balance"
        Folder dailyBalance = new Folder();
        dailyBalance.createNewFolder(balanceFolder);

        // create new folder "categories"
        Folder category = new Folder();
        category.createNewFolder(categoryFolder);

        // get all transactions from log files of banks, create and write to new files
        File dir = new File(logs);


        for (File log : dir.listFiles()) {

            String bankCode = log.getName().substring(0, 4);
            List<Transaction> transactions = bankService.getAllTransactions(log);

            // calculate output for files in "daily_balance"
            BalanceService balanceService = new BalanceServiceImpl();
            List<Balance> balances = balanceService.aggregateByTransaction(transactions, bankCode);
            List<Balance> balancesOut = balanceService.aggregateByDay(balances);

            // naming new files
            String balanceFile = bankCode + "_daily_balance.csv";
            balanceService.createNewFile(balanceFolder, balanceFile);

            // write to newly created files
            String balanceFilePath = balanceFolder + balanceFile;
            balanceService.writeToFile(balancesOut, new File(balanceFilePath));


            // calculate output for files in "categories"
            CategoryService categoryService = new CategoryServiceImpl();
            List<Category> cateOut = categoryService.aggregateByCategory(transactions);

            // naming new files
            String cateFile = bankCode + "_categories.csv";
            categoryService.createNewFile(categoryFolder, cateFile);

            // write to newly created files
            String cateFilePath = categoryFolder + cateFile;
            categoryService.writeToFile(cateOut, new File(cateFilePath));
        }
    }
}
