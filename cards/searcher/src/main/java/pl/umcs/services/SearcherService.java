package pl.umcs.services;

import com.google.common.collect.Lists;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.umcs.datatypes.Card;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearcherService {

    @Autowired
    private EurekaClient eurekaClient;
    @Autowired
    private RestTemplate restTemplate;

    public List<Card> search (Card matcher) {
        Application collectionApplication
                = eurekaClient.getApplication("collection");
        ResponseEntity<Card[]> listResponseEntity = restTemplate.getForEntity(getUrl(collectionApplication, "/cards"), Card[].class);
        Card[] cardList = listResponseEntity.getBody();

        List<Card> matchingCards = Lists.newArrayList();
        for (Card card : cardList) {
            if (allMatch(card, matcher)) {
                matchingCards.add(card);
            }
        }

        return matchingCards;
    }

    private boolean anyMatch (Card card, Card matcher) {
        return matchName(card, matcher) ||
                matchCost(card, matcher) ||
                matchColor(card, matcher) ||
                matchCmc(card, matcher);
    }
    private boolean allMatch(Card card, Card matcher) {
        boolean b = true;
        if(null != matcher.getName()) {
            b &=  matchName(card, matcher);
        }
        if(null != matcher.getCost()) {
            b &=  matchCost(card, matcher);
        }
        if(null != matcher.getColor()) {
            b &=  matchColor(card, matcher);
        }
        if(null != matcher.getCmc()) {
            b &=  matchCmc(card, matcher);
        }
        return b;
    }
    private boolean matchName (@NotNull Card card, @NotNull Card matcher) {
        return card.getName().equals(matcher.getName());
    }
    private boolean matchCost (@NotNull Card card, @NotNull Card matcher) {
        return card.getCost().equals(matcher.getCost());
    }
    private boolean matchColor (@NotNull Card card, @NotNull Card matcher) {
        return card.getColor().equals(matcher.getColor());
    }
    private boolean matchCmc (@NotNull Card card, @NotNull Card matcher) {
        return card.getCmc().equals(matcher.getCmc());
    }
    private String getUrl(Application application, String path) {
        List<InstanceInfo> instances = application.getInstances();
        InstanceInfo instanceInfo = instances.iterator().next();
        String hostname = instanceInfo.getHostName();
        int port = instanceInfo.getPort();
        return "http://"+hostname+":"+port+path;
    }
}
