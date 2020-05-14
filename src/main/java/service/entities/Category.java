package service.entities;

import java.util.Objects;

/**
 * Represents a category of a transaction of a bank.
 * A transaction can belong to only one category.
 */
public class Category implements Comparable<Category> {

    private String category;
    private String currency;
    private double totalAmount;
    private int transactionCount;

    /**
     * Create a new category with the given data above.
     *
     * @param category of the transaction.
     * @param currency of the transaction following ISO 4217 currency code.
     * @param totalAmount of the transactions of the specified category.
     * @param transactionCount numbers of transaction within the specified category.
     */
    public Category(String category, String currency, double totalAmount, int transactionCount) {
        this.category = category;
        this.currency = currency;
        this.totalAmount = totalAmount;
        this.transactionCount = transactionCount;
    }

    public String getCategory() {
        return category;
    }

    public String getCurrency() {
        return currency;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    /**
     * Method to return a high level string message.
     * @return newly formatted string e.g. 'groceries,EUR,392.74,159'.
     */
    @Override
    public String toString() {
        return category +
               "," + currency +
               "," + String.format("%.2f", totalAmount) +
               "," + transactionCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category1 = ( Category ) o;
        return Double.compare(category1.totalAmount, totalAmount) == 0 &&
               transactionCount == category1.transactionCount &&
               Objects.equals(category, category1.category) &&
               Objects.equals(currency, category1.currency);
    }

    /**
     * Categories are ordered by category, and then by currency.
     *
     * @param o object to be compared to.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to,
     * or greater than the specified object.
     */

    @Override
    public int compareTo(Category o) {
        if (this.category.compareTo(o.category) != 0) {
            return this.category.compareTo(o.category);
        }
        else {
            return this.currency.compareTo(o.currency);
        }
    }
}
