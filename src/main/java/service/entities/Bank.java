package service.entities;

import java.util.*;

/**
 * Represents a record of a bank.
 * A bank can only have one unique bank code and name.
 */
public class Bank implements Comparable<Bank> {

    private String code;
    private String name;

    /**
     * Create a new bank with given data above.
     *
     * @param code unique id of a bank, start with letter 'B' and followed by 3 integer digits.
     * @param name of a bank, may or may not be unique.
     * @throws Exception if a newly created bank does not follow the given specifications.
     */
    public Bank(String code, String name) throws Exception {
        if (code.length() < 1)
            throw new Exception("Code must not be empty.");
        if (!code.substring(0, 1).equals("B"))
            throw new Exception("Name of bank must start with 'B'.");
        this.code = code;
        this.name = name;
    }

    private String getCode() {
        return code;
    }

    private String getName() {
        return name;
    }

    /**
     * Method to return a high level string message.
     *
     * @return a newly formatted string e.g. 'B001,Confidence'
     */
    @Override
    public String toString() {
        return this.getCode() + "," + this.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = ( Bank ) o;
        return Objects.equals(code, bank.code) &&
               Objects.equals(name, bank.name);
    }

    /**
     * Banks are ordered by bank code.
     *
     * @param o object to be compared to.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to,
     * or greater than the specified object.
     */
    @Override
    public int compareTo(Bank o) {
        return this.code.compareTo(o.code);
    }
}
