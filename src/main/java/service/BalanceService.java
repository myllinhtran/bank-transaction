package service;

import service.entities.Balance;
import service.entities.Transaction;

import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * This interface contains methods of object Balance of a bank.
 */
public interface BalanceService {


    /**
     * Create a new list of balances, aggregated by transaction which includes or only incoming transactions or
     * outgoing transactions.
     *
     * @param transactions all transactions occurred within a day of a specified bank given the bank code.
     * @param bankCode unique id of a specified bank.
     * @return a new list of balance.
     */
    List<Balance> aggregateByTransaction(List<Transaction> transactions, String bankCode);


    /**
     * Create a new list of balances, aggregated by day which includes both incoming and outgoing transactions
     * happening in a day.
     *
     * @param balances a list of balances as output of <b>aggregateByTransaction()</b>.
     * @return a newly aggregated by day list of balances.
     */
    List<Balance> aggregateByDay(List<Balance> balances);


    /**
     * Create a new file in a specified folder with a specified name.
     *
     * @param folder path of the folder where the file is supposed to be created.
     * @param fileName
     * @throws IOException
     */
    void createNewFile(String folder, String fileName) throws IOException;


    /**
     * Write to a file given the input and path of specified file.
     *
     * @param balances list of balances as input to be written to file.
     * @param f file to write to.
     * @throws IOException
     */
    void writeToFile(List<Balance> balances, File f) throws IOException;
}
