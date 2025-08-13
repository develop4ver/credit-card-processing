package com.example.creditcardprocessing.service;

import com.example.creditcardprocessing.Card;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Service layer responsible for managing cards and business logic such as
 * validating the card number using the Luhn algorithm, ensuring unique
 * account names, and initialising balances. Uses an in‑memory map keyed
 * by the account holder's name to store cards. New cards start with a
 * balance of zero regardless of validity.
 */
@Service
public class CardService {
    private final Map<String, Card> cards = new LinkedHashMap<>();

    /**
     * Add a new card to the system. Performs validation of the card
     * number via Luhn's algorithm and enforces uniqueness on the name.
     *
     * @param name       the account holder's name
     * @param cardNumber the credit card number as a string of digits
     * @param limit      the credit limit for the card
     * @return the created Card object
     * @throws IllegalArgumentException if a card with the same name exists
     */
    public Card addCard(String name, String cardNumber, BigDecimal limit) {
        String key = name.trim().toLowerCase();
        if (cards.containsKey(key)) {
            throw new IllegalArgumentException("A card with that name already exists");
        }
        boolean isValid = luhnCheck(cardNumber);
        Card card = new Card(name, cardNumber, limit, isValid);
        cards.put(key, card);
        return card;
    }

    /**
     * Retrieve all cards currently stored. Returns a new list to prevent
     * external modification of the internal map.
     *
     * @return an immutable list of all cards
     */
    public List<Card> getAllCards() {
        return Collections.unmodifiableList(new ArrayList<>(cards.values()));
    }

    /**
     * Implementation of the Luhn 10 algorithm to verify credit card numbers.
     * Accepts numeric strings of arbitrary length and returns true if the
     * checksum is valid. Numbers shorter than 13 or longer than 19 digits
     * automatically fail validation.
     *
     * @param value the card number as a numeric string
     * @return true if the number passes the Luhn check; otherwise false
     */
    public static boolean luhnCheck(String value) {
        if (value == null) return false;
        String sanitized = value.trim();
        if (sanitized.length() < 13 || sanitized.length() > 19) {
            return false;
        }
        int sum = 0;
        boolean shouldDouble = false;
        for (int i = sanitized.length() - 1; i >= 0; i--) {
            char c = sanitized.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }
            int digit = c - '0';
            if (shouldDouble) {
                digit *= 2;
                if (digit > 9) digit -= 9;
            }
            sum += digit;
            shouldDouble = !shouldDouble;
        }
        return sum % 10 == 0;
    }
}