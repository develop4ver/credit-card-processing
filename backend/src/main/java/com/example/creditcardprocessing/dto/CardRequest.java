package com.example.creditcardprocessing.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * DTO representing the payload for adding a new credit card. Uses Jakarta Bean
 * Validation annotations to enforce basic constraints: non‑blank name, numeric
 * card numbers up to 19 digits and a positive limit. More detailed validation
 * such as the Luhn check is performed in the service layer.
 */
public class CardRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Card number is required")
    @Pattern(regexp = "^[0-9]+$", message = "Card number must be numeric")
    @Size(min = 13, max = 19, message = "Card number must be between 13 and 19 digits")
    private String cardNumber;

    @jakarta.validation.constraints.NotNull(message = "Limit is required")
    @jakarta.validation.constraints.Positive(message = "Limit must be positive")
    private BigDecimal limit;

    public CardRequest() {}

    public CardRequest(String name, String cardNumber, BigDecimal limit) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.limit = limit;
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
}