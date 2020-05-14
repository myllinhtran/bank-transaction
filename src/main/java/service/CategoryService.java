package service;

import service.entities.Category;
import service.entities.Transaction;

import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * This interface contains methods of object Category.
 */
public interface CategoryService {


    /**
     * Create a new list of categories of all transactions submitted by a bank.
     *
     * @param transactions list of all transactions submitted by a bank.
     * @return a new list of categories and their data.
     */
    List<Category> aggregateByCategory(List<Transaction> transactions);


    /**
     * Create a new file in a specified folder with a specified name.
     *
     * @param folder path of the folder where the file is supposed to be created.
     * @param fileName
     */
    void createNewFile(String folder, String fileName);


    /**
     * Write to a file given the input and path of specified file.
     *
     * @param categories list of categories as input to be written to file.
     * @param f file to write to.
     * @throws IOException
     */
    void writeToFile(List<Category> categories, File f) throws IOException;
}
