package com.example.creditcardprocessing;

import com.example.creditcardprocessing.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link CardService}. Tests cover the Luhn
 * algorithm implementation, adding new cards and rejecting duplicate
 * account names.
 */
public class CardServiceTest {
    private CardService service;

    @BeforeEach
    void setUp() {
        service = new CardService();
    }

    @Test
    void testLuhnValidNumber() {
        assertTrue(CardService.luhnCheck("4111111111111111"));
        assertTrue(CardService.luhnCheck("5555555555554444"));
    }

    @Test
    void testLuhnInvalidNumber() {
        assertFalse(CardService.luhnCheck("1234567812345678"));
        assertFalse(CardService.luhnCheck("111122223333444")); // too short
        assertFalse(CardService.luhnCheck("41111111111111112222")); // too long
    }

    @Test
    void testAddValidCard() {
        Card card = service.addCard("Alice", "4111111111111111", BigDecimal.valueOf(1000));
        assertEquals("Alice", card.getName());
        assertTrue(card.isValid());
        assertEquals(BigDecimal.ZERO, card.getBalance());
        assertEquals(BigDecimal.valueOf(1000), card.getLimit());
        assertEquals(1, service.getAllCards().size());
    }

    @Test
    void testAddDuplicateNameThrows() {
        service.addCard("Bob", "4111111111111111", BigDecimal.valueOf(500));
        assertThrows(IllegalArgumentException.class, () ->
                service.addCard("Bob", "5555555555554444", BigDecimal.valueOf(800)));
    }
}