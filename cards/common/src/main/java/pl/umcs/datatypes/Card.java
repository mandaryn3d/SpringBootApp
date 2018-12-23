package pl.umcs.datatypes;


public class Card {
    //@Id
    //@GeneratedValue(strategy = AUTO)
    private Long id;

    private String name;
    private String cmc;
    private Color color;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCmc() {
        return cmc;
    }

    public void setCmc(String cmc) {
        this.cmc = cmc;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
