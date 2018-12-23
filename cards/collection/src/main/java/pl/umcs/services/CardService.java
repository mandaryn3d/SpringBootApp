package pl.umcs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.umcs.datatypes.Card;
import pl.umcs.datatypes.Collection;

import java.util.List;

@Service
public class CardService {
    private static final Logger log = LoggerFactory.getLogger(CardService.class);

    private CollectionService collectionService;

    @Autowired
    public CardService(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    public Card save(Long idToAdd, Card card) {
        Collection collection = collectionService.find(idToAdd);
        if (null != collection) {
            collection.getCardList().add(card);
        }

        log.info("Successfull card: " + card.getName() + " added to collection: " + collection.getId());
        return card;
    }

    public List<Card> findAll(Long id) {
        Collection collection = collectionService.find(id);
        List<Card> cardList = null;
        if (null != collection) {
            cardList = collection.getCardList();
        }

        log.info("Returning all cards for collection: " +  id);
        return cardList;
    }

    public Card find(Long collectionId, Long cardId) {
        Collection collection = collectionService.find(collectionId);
        Card foundCard = null;
        if(null != collection) {
            for (Card card : collection.getCardList()) {
                if (cardId == card.getId()) {
                    foundCard = card;
                    break;
                }
            }
        }

        log.info("Returning card: " + cardId + " from collection: " + collectionId);
        return foundCard;
    }

    public Card update(Long id, Card card) {
        Collection collection = collectionService.find(id);
        Card foundCard = null;
        if (null != collection) {
            for (Card itr : collection.getCardList()) {
                if (card.getId() == itr.getId()) {
                    itr = card;
                    foundCard = itr;
                    break;
                }
            }
        }

        log.info("Successfull card Update: " + card.getId() + " for collection: " + id);
        return foundCard;
    }

    public void deleteCard(Long collectionId, Long cardId) {
        Collection collection = collectionService.find(collectionId);
        Card foundCard = null;
        if (null != collection) {
            for (Card card : collection.getCardList()) {
                if (cardId == card.getId()) {
                    collection.getCardList().remove(card);
                    break;
                }
            }
        }

        log.info("Deleting card: " + cardId + " from collection: " + collectionId);
    }
}
