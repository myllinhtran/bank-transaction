package service.impl;

import service.CategoryService;
import service.entities.Category;
import service.entities.Transaction;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class CategoryServiceImpl implements CategoryService {

    @Override
    public List<Category> aggregateByCategory(List<Transaction> transactions) {

        List<Category> categories = new LinkedList<>();

        for (int i = 0; i < transactions.size(); i++) {
            String cate = transactions.get(i).getCategory();
            String curr = transactions.get(i).getCurrency();
            double totalAmount = 0;
            int totalCount = 0;

            for (int j = 0; j < transactions.size(); j++) {
                if (cate.equals(transactions.get(j).getCategory()) &&
                    curr.equals(transactions.get(j).getCurrency())) {
                    totalAmount = totalAmount + transactions.get(j).getAmount();
                    totalCount = totalCount + 1;
                }
            }

            Category category = new Category(cate, curr, totalAmount, totalCount);
            if (!categories.contains(category)) {
                categories.add(category);
            }
        }

        Collections.sort(categories);

        return categories;
    }

    @Override
    public void createNewFile(String folder, String fileName) {

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
    public void writeToFile(List<Category> categories, File f) throws IOException {

        if (!(f.exists() && f.isFile())) {
            System.out.println(" File does not exist. ");
            return;
        }

        BufferedReader reader = new BufferedReader(new FileReader(f));
        if (reader.readLine() != null) {
            System.out.printf(" Overwriting file '%s' ... %n", f.getName());
        }

        try {
            FileWriter fileWriter = new FileWriter(f);
            fileWriter.write("CATEGORY,CURRENCY,TOTAL-AMOUNT,TRANSACTION-COUNT\n");
            for (Category c : categories) {
                fileWriter.write(c.toString());
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
