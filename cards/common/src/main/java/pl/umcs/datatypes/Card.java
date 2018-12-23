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

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost='" + cost + '\'' +
                ", color=" + color +
                '}';
    }
}
