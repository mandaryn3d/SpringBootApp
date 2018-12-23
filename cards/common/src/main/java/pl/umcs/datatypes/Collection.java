package pl.umcs.datatypes;

import com.google.common.collect.Lists;

import java.util.List;


public class Collection {
    //@Id
    //@GeneratedValue(strategy = AUTO)
    private Long id;
    private List<Card> cardList = Lists.newArrayList();

    public Long getId() {
        return id;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }
}
