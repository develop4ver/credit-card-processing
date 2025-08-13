package com.example.creditcardprocessing.dto;

import java.math.BigDecimal;

/**
 * DTO representing the response for card data. This is returned to the
 * client when retrieving or creating cards. Exposes the relevant
 * information without leaking any internal representations.
 */
public class CardResponse {
    private String name;
    private String cardNumber;
    private BigDecimal limit;
    private BigDecimal balance;
    private boolean valid;

    public CardResponse() {}

    public CardResponse(String name, String cardNumber, BigDecimal limit, BigDecimal balance, boolean valid) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.limit = limit;
        this.balance = balance;
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