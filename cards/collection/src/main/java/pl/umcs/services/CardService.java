package pl.umcs.services;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.umcs.datatypes.Card;
import pl.umcs.repositories.CardRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    private static final Logger log = LoggerFactory.getLogger(CardService.class);

    private CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }




    public Card save(Card card) {
        Card savedCard = cardRepository.save(card);
        log.info("Successfull card: " + card);
        return savedCard;
    }

    public List<Card> findAll() {
        Iterable<Card> cardIterable = cardRepository.findAll();
        List<Card> cards = Lists.newArrayList(cardIterable);

        log.info("Returning all cards");
        return cards;
    }

    public Card find(Long id) {
        Optional<Card> collection = cardRepository.findById(id);
        log.info("Returning collection: " + id);
        return collection.orElse(null);
    }

    public Card update(Card card) {
        Card updatedCard = cardRepository.save(card);
        log.info("Successfull card Update: " + updatedCard.getId());

        return updatedCard;
    }

    public void deleteCard(Long id) {
        cardRepository.deleteById(id);

        log.info("Successfull card Delete: " +  id);
    }
}
