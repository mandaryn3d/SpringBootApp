package pl.umcs.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.umcs.datatypes.Card;
import pl.umcs.services.SearcherService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/search")
public class SearcherController {
    private static final Logger log = LoggerFactory.getLogger(SearcherController.class);

    private SearcherService searcherService;

    @Autowired
    public SearcherController(SearcherService searcherService) {
        this.searcherService = searcherService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public List<Card> search(@RequestBody Card card) {
        List<Card> cardList = searcherService.search(card);

        log.info("Found cards {}", cardList);
        return cardList;
    }
}
