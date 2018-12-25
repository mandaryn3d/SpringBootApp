package pl.umcs;


import com.google.common.collect.Lists;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.umcs.datatypes.Card;
import pl.umcs.datatypes.Color;


import java.util.List;

@Component
public class CardCreator {

    private static final Logger log = LoggerFactory.getLogger(CardCreator.class);
    private RestTemplate restTemplate;
    @Autowired
    private EurekaClient eurekaClient;

    public CardCreator() {
        this.restTemplate = new RestTemplate();
    }


    @EventListener(ApplicationReadyEvent.class)
    public void sendList() {
        Application collectionApplication
                = eurekaClient.getApplication("collection");
        String url = getUrl(collectionApplication, "/cards");
        List<Card> cardList = createList();
        for( Card card: cardList) {
            restTemplate.postForEntity(url, card, Card.class);
            log.info("Sent: {}", card);
        }
        log.info("Finished sending cards");
    }


    private List<Card> createList() {
        List<Card> cardList = Lists.newArrayList();
        cardList.add(new Card("Dark Ritual","B", Color.Black, 1));
        cardList.add(new Card("Lightning Bolt","R", Color.Red, 1));
        cardList.add(new Card("Counterspell","UU", Color.Blue, 2));
        cardList.add(new Card("Dark Confidant","1B", Color.Black, 2));
        cardList.add(new Card("Eureka","3G", Color.Green, 4));
        cardList.add(new Card("Wrath of God","2WW", Color.White, 4));
        cardList.add(new Card("Smokestack","4", Color.Colorless, 4));
        cardList.add(new Card("Ethersworn Adjudicator","4U", Color.Blue, 5));
        cardList.add(new Card("Dark Petition","3BB", Color.Black, 5));
        cardList.add(new Card("Skullclamp","1", Color.Colorless, 1));
        cardList.add(new Card("Daretti, Scrap Servant","3R", Color.Red, 4));
        cardList.add(new Card("Prismatic Geoscope","5", Color.Colorless, 5));
        cardList.add(new Card("Path to Exile","W", Color.White, 1));
        cardList.add(new Card("Ichor Wellspring","2", Color.Colorless, 2));
        cardList.add(new Card("Hellkite Tyrant","4RR", Color.Red, 6));
        cardList.add(new Card("Thirst for Knowledge","2U", Color.Blue, 3));
        cardList.add(new Card("Snuff Out","3B", Color.Black, 4));
        cardList.add(new Card("Damnation","2BB", Color.Black, 4));
        cardList.add(new Card("Myr Battlesphere","7", Color.Colorless, 7));
        cardList.add(new Card("Cryptic Command","1UUU", Color.Blue, 4));
        cardList.add(new Card("Snapcaster Mage","1U", Color.Blue, 2));
        cardList.add(new Card("Sakura-Tribe Elder","1G", Color.Green, 2));
        cardList.add(new Card("Shatterstorm","2RR", Color.Red, 4));
        cardList.add(new Card("Brainstorm","U", Color.Blue, 1));
        cardList.add(new Card("Dispel","U", Color.Blue, 1));
        cardList.add(new Card("Cataclysm","2WW", Color.White, 4));

        return cardList;
    }

    private String getUrl(Application application, String path) {
        List<InstanceInfo> instances = application.getInstances();
        InstanceInfo instanceInfo = instances.iterator().next();
        String hostname = instanceInfo.getHostName();
        int port = instanceInfo.getPort();
        return "http://"+hostname+":"+port+path;
    }
}
