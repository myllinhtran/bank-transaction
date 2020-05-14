package service;

import service.entities.Transaction;

import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * This interface contains a list of methods of object Bank.
 */
public interface BankService {

    /**
     * Create a new list of all transactions that has occurred of a bank.
     * Input data is retrieved from logs file submitted by each bank.
     *
     * @param f logs file.
     * @return a new list of all transactions.
     * @throws IOException
     */
    List<Transaction> getAllTransactions(File f) throws IOException;


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
     * @param f file to write to.
     * @throws IOException
     */
    void writeToFile(File f) throws IOException;

}
