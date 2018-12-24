package pl.umcs.datatypes;


import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String name;
    private String cost;
    @Enumerated(EnumType.STRING)
    private Color color;
    private Integer cmc;


    public Card() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Integer getCmc() {
        return cmc;
    }

    public void setCmc(Integer cmc) {
        this.cmc = cmc;
    }

    public Card(String name, String cost, Color color, Integer cmc) {
        this.name = name;
        this.cost = cost;
        this.color = color;
        this.cmc = cmc;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost='" + cost + '\'' +
                ", color=" + color +
                ", cmc=" + cmc +
                '}';
    }
}
