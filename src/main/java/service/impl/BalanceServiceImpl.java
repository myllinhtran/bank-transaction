package service.impl;

import service.BalanceService;
import service.entities.Balance;
import service.entities.Transaction;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * Class that implements interface BalanceService.
 */
public class BalanceServiceImpl implements BalanceService {


    @Override
    public List<Balance> aggregateByTransaction(List<Transaction> transactions, String bankCode) {

        String day;
        String currency;
        double totalOutgoingAmount = 0;
        double totalIncomingAmount = 0;
        int outgoingCount = 0;
        int incomingCount = 0;

        List<Balance> balances = new LinkedList<>();

        for (int i = 0; i <= transactions.size() - 1; i++) {

            // substring e.g. '2019-05-01' of given string e.g '2019-05-01T19:52:01+02:00'
            day = transactions.get(i).getDay().substring(0, 10);
            currency = transactions.get(i).getCurrency();
            String src = transactions.get(i).getSource().substring(0, 4);
            String des = transactions.get(i).getDestination().substring(0, 4);

            int countOut = 0;
            int countIn = 0;

            if (src.equals(bankCode) && !src.equals(des)) {
                totalOutgoingAmount = transactions.get(i).getAmount();
                outgoingCount = countOut + 1;
                totalIncomingAmount = 0;
                incomingCount = 0;

            }
            else if (des.equals(bankCode) && !des.equals(src)) {
                totalOutgoingAmount = 0;
                outgoingCount = 0;
                totalIncomingAmount = transactions.get(i).getAmount();
                incomingCount = countIn + 1;

            }
            else {
                continue;
            }

            Balance balance = new Balance(day,
                                          currency,
                                          totalOutgoingAmount,
                                          outgoingCount,
                                          totalIncomingAmount,
                                          incomingCount);
            balances.add(balance);
        }

        return balances;
    }


    @Override
    public List<Balance> aggregateByDay(List<Balance> balances) {

        List<Balance> newBalances = new LinkedList<>();

        for (int i = 0; i < balances.size(); i++) {
            String date = balances.get(i).getDay();
            String curr = balances.get(i).getCurrency();
            double outAmount = 0;
            double inAmount = 0;
            int outCount = 0;
            int inCount = 0;

            for (Balance value : balances) {
                if (date.equals(value.getDay()) &&
                    curr.equals(value.getCurrency())) {

                    outAmount = outAmount + value.getTotalOutgoingAmount();
                    inAmount = inAmount + value.getTotalIncomingAmount();
                    outCount = outCount + value.getOutgoingTransactionCount();
                    inCount = inCount + value.getIncomingTransactionCount();
                }
            }

            Balance balance = new Balance(date, curr, outAmount, outCount, inAmount, inCount);

            if (!newBalances.contains(balance)) {
                newBalances.add(balance);
            }
        }

        Collections.sort(newBalances);

        return newBalances;
    }


    @Override
    public void createNewFile(String folder, String fileName) throws IOException {

        File dir = new File(String.valueOf(folder));
        if (!(dir.exists() && dir.isDirectory())) {
            System.out.println(" Directory does not exist. ");
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
            e.printStackTrace();
        }
    }


    @Override
    public void writeToFile(List<Balance> balances, File f) throws IOException {

        if (!(f.exists() && f.isFile())) {
            System.out.println(" File does not exist. ");
            return;
        }

        BufferedReader reader = new BufferedReader(new FileReader(f));
        if (reader.readLine() != null) {
            System.out.printf(" Overwriting file '%s'... %n", f.getName());
        }

        try {
            FileWriter fileWriter = new FileWriter(f);
            fileWriter.write("DAY,CURRENCY,TOTAL-OUTGOING-AMOUNT,OUTGOING-TRANSACTION-COUNT,TOTAL-INCOMING-AMOUNT,INCOMING-TRANSACTION-COUNT\n");
            for (Balance d : balances) {
                fileWriter.write(d.toString());
                fileWriter.write("\n");
            }
            fileWriter.close();
            System.out.printf(" File '%s' written successfully. %n", f.getName());
            System.out.printf(" File path: %s. %n", f.getPath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
