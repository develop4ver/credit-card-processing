package com.example.creditcardprocessing;

import java.math.BigDecimal;

/**
 * Simple POJO representing a credit card. Cards are uniquely identified by
 * the account holder's name, and contain the original card number, the
 * account limit, current balance and a flag indicating whether the card
 * number passed Luhn validation.
 */
public class Card {
    private String name;
    private String cardNumber;
    private BigDecimal limit;
    private BigDecimal balance;
    private boolean valid;

    public Card() {
        // Default constructor
    }

    public Card(String name, String cardNumber, BigDecimal limit, boolean valid) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.limit = limit;
        this.balance = BigDecimal.ZERO;
        this.valid = valid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}