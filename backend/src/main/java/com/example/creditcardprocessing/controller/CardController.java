package com.example.creditcardprocessing.controller;

import com.example.creditcardprocessing.Card;
import com.example.creditcardprocessing.dto.CardRequest;
import com.example.creditcardprocessing.dto.CardResponse;
import com.example.creditcardprocessing.service.CardService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller exposing endpoints for creating and retrieving credit cards.
 * Implements two operations: adding a new card via POST and retrieving all
 * cards via GET. Validation annotations on the request body provide basic
 * input checking, with additional business rules enforced within the
 * controller/service.
 */
@RestController
@RequestMapping("/cards")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    /**
     * Create a new credit card account. Validates input and ensures the
     * card number passes the Luhn check. Duplicate names are rejected.
     *
     * @param request the card details from the client
     * @return a {@link ResponseEntity} containing either the created card
     *         or an error message
     */
    @PostMapping
    public ResponseEntity<?> addCard(@Valid @RequestBody CardRequest request) {
        // Perform the Luhn check; invalid numbers are rejected with 400
        if (!CardService.luhnCheck(request.getCardNumber())) {
            return ResponseEntity.badRequest().body("Invalid card number");
        }
        try {
            Card card = cardService.addCard(
                    request.getName(),
                    request.getCardNumber(),
                    request.getLimit()
            );
            CardResponse response = toResponse(card);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    /**
     * Retrieve all credit cards currently stored in the system. Cards are
     * returned in insertion order.
     *
     * @return list of {@link CardResponse}
     */
    @GetMapping
    public List<CardResponse> getAllCards() {
        return cardService.getAllCards().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private CardResponse toResponse(Card card) {
        return new CardResponse(
                card.getName(),
                card.getCardNumber(),
                card.getLimit(),
                card.getBalance(),
                card.isValid()
        );
    }
}