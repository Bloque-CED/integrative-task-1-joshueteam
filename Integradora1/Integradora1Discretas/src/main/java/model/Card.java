package model;


public class Card {
    private Color color;
    private CardType type;
    private int number;

    public Card(int number, Color color, CardType type) {
        this.number = number;
        this.color = color;
        this.type = type;
    }
    public Card(Color color){
        this(0, color, null);
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Card other = (Card) obj;
        if (color != other.color)
            return false;
        if (number != other.number)
            return false;
        if (type != other.type)
            return false;
        return true;
    }

    public boolean isSpecial(){
        if(this.type != CardType.NORMAL){
            return true;
        }
        return false;
    }

    public boolean playable(Card topCard) {

        if (this.type == CardType.COLOR_CHANGER) {
            return true;
        }

        // Si la carta artificial insertada con su tipo y numero null, solo siendo color
        if (topCard.type == null && topCard.color == this.color) {
            return true;
        }

        if (this.type == CardType.NORMAL && (this.color == topCard.color || this.number == topCard.number)) {
            return true;
        }

        if (this.type != CardType.NORMAL && this.color == topCard.color) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder cardInfo = new StringBuilder(color.toString()); // Iniciamos con el color de la carta

        if (type == CardType.NORMAL) {
            cardInfo.append(" ").append(number);
        } else if (type == CardType.COLOR_CHANGER) {
            cardInfo.append(" Color changer");
        } else if (type == CardType.SKIP) {
            cardInfo.append(" Skip");
        } else if (type == CardType.REVERSE) {
            cardInfo.append(" Reverse");
        } else if (type == CardType.DRAW_TWO) {
            cardInfo.append(" Draw two");
        } else if(type == null){
            cardInfo.append(" Selected Color");
        }
        else {
            cardInfo.append(" Special");
        }

        return cardInfo.toString();
    }

    public CardType getType() {
        return type;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}




