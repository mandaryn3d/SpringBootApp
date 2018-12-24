package pl.umcs.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.umcs.datatypes.Card;
import pl.umcs.services.CardService;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/cards")
public class CardController {

    private static final Logger log = LoggerFactory.getLogger(CardController.class);
    private CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/hello")
    public String getHello() {
        return "Hello. This is the Card controller speaking.";
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Card> getCards() {
        List<Card> cards = cardService.findAll();

        log.info("Retrieve objects {}", cards);
        return cards;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public Card save(@RequestBody Card card) {
        Card addedCard = cardService.save(card);

        log.info("Add Card {}", addedCard);
        return addedCard;
    }

    @GetMapping("/{cardId}")
    public Card find(@PathVariable Long cardId) {
        log.info("Searching for card: " + cardId);
        return cardService.find(cardId);
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity deleteCollection(@PathVariable Long cardId) {
        cardService.deleteCard(cardId);

        log.info("Deleting card: +" + cardId);
        return new ResponseEntity(NO_CONTENT);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public Card update(@RequestBody Card card) {
        Card updatedCard = cardService.update(card);

        log.info("Updated Card {}", updatedCard);
        return updatedCard;
    }
}
