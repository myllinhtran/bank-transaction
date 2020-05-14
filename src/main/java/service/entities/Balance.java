package service.entities;

import java.util.Objects;

/**
 * Represents a balance record of a bank.
 * A bank can have many balances.
 */
public class Balance implements Comparable<Balance> {

    private String day;
    private String currency;
    private double totalOutgoingAmount;
    private double totalIncomingAmount;
    private int outgoingTransactionCount;
    private int incomingTransactionCount;

    /**
     * Create a new balance with the given data above.
     *
     * @param day that the transaction occurs, format is based on ISO 8601 day in bank timezone.
     * @param currency of the transaction following ISO 4217 currency code.
     * @param totalOutgoingAmount outgoing transactions of a bank in a day.
     * @param outgoingTransactionCount total numbers of outgoing transaction of a bank in a day.
     * @param totalIncomingAmount incoming transactions of a bank in a day.
     * @param incomingTransactionCount total numbers of incoming transaction of a bank in a day.
     */
    public Balance(String day,
                   String currency,
                   double totalOutgoingAmount,
                   int outgoingTransactionCount,
                   double totalIncomingAmount,
                   int incomingTransactionCount) {
        this.day = day;
        this.currency = currency;
        this.totalOutgoingAmount = totalOutgoingAmount;
        this.outgoingTransactionCount = outgoingTransactionCount;
        this.totalIncomingAmount = totalIncomingAmount;
        this.incomingTransactionCount = incomingTransactionCount;
    }

    public String getDay() {
        return day;
    }

    public String getCurrency() {
        return currency;
    }

    public double getTotalOutgoingAmount() {
        return totalOutgoingAmount;
    }

    public int getOutgoingTransactionCount() {
        return outgoingTransactionCount;
    }

    public double getTotalIncomingAmount() {
        return totalIncomingAmount;
    }

    public int getIncomingTransactionCount() {
        return incomingTransactionCount;
    }

    /**
     * Method to return a high level string message.
     * @return newly formatted string e.g. '2019-05-01,EUR,15643.32,1043,943.09,571'.
     */
    @Override
    public String toString() {
        return day +
               "," + currency +
               "," + String.format("%.2f", totalOutgoingAmount) +
               "," + outgoingTransactionCount +
               "," + String.format("%.2f", totalIncomingAmount) +
               "," + incomingTransactionCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balance balance = ( Balance ) o;
        return Double.compare(balance.totalOutgoingAmount, totalOutgoingAmount) == 0 &&
               outgoingTransactionCount == balance.outgoingTransactionCount &&
               Double.compare(balance.totalIncomingAmount, totalIncomingAmount) == 0 &&
               incomingTransactionCount == balance.incomingTransactionCount &&
               Objects.equals(day, balance.day) &&
               Objects.equals(currency, balance.currency);
    }

    /**
     * Balances are ordered by date, then by currency.
     *
     * @param o object to be compared to.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to,
     * or greater than the specified object.
     */
    @Override
    public int compareTo(Balance o) {
        if (this.day.compareTo(o.day) != 0) {
            return this.day.compareTo(o.day);
        }
        else {
            return this.currency.compareTo(o.currency);
        }
    }
}
