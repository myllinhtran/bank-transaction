package service.entities;

/**
 * Represents a transaction of a balance of a bank.
 * A bank can have many transactions.
 */
public class Transaction implements Comparable<Transaction> {

    private String day;
    private String id;
    private String source;
    private String destination;
    private double amount;
    private String currency;
    private String category;

    /**
     * Create a new transaction with the given data above.
     * @param day ISO 8601 date of transaction.
     * @param id transaction id, UUID of transaction.
     * @param src account IDs of source of transaction.
     * @param des account IDs of destination of transaction.
     * @param amount amount of money transferred by transaction.
     * @param currency ISO 4217 currency code (e.g. EUR or USD).
     * @param category some internal for this bank identifier of transaction category.
     *                 String with character from range [0-9A-Za-z_.-] e.g. 'taxi', 'groceries'
     */
    public Transaction(String day,
                       String id,
                       String src,
                       String des,
                       double amount,
                       String currency,
                       String category) {
        this.day = day;
        this.id = id;
        this.source = src;
        this.destination = des;
        this.amount = amount;
        this.currency = currency;
        this.category = category;
    }

    public String getDay() {
        return day;
    }

    public String getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCategory() {
        return category;
    }


    @Override
    public String toString() {
        return "Transaction {" +
               "day='" + day + '\'' +
               ", id='" + id + '\'' +
               ", source='" + source + '\'' +
               ", destination='" + destination + '\'' +
               ", amount=" + amount +
               ", currency='" + currency + '\'' +
               ", category='" + category + '\'' +
               '}';
    }

    /**
     * Transactions are ordered by day.
     *
     * @param o object to be compared to.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to,
     * or greater than the specified object.
     */
    @Override
    public int compareTo(Transaction o) {
        return this.getDay().substring(0, 10).compareTo(o.getDay().substring(0, 10));
    }
}
