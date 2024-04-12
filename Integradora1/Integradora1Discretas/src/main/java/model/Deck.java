package model;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 18 cartas azules del 1 al 9
 * 18 cartas rojas del 1 al 9
 * 18 cartas amarillas del 1 al 9
 * 18 cartas verdes del 1 al 9
 * 8 cartas coma 2, 2 de cada color
 * 8 cartas cambiar sentido, 2 de cada color
 * 8 cartas pierde turno, 2 de cada color
 * 8 cartas coma 1, dos de cada color
 * 4 cartas cambiar color, sin color ni numero
 */

import ownStructures.HashTable;
import ownStructures.IHashTable;
import ownStructures.IStack;
import ownStructures.Stack;


public class Deck {
    private IHashTable<Integer, Card> cardReferences;

    private IStack<Integer> cards;

    public Deck(){
        cardReferences = new HashTable<>();
        cards = new Stack<>();
        generateDeck();
    }

    public void generateDeck() {
        ArrayList<Integer> keys = new ArrayList<>();


        for (Color color : Color.values()) {
            if (color == Color.BLACK) continue;

            for (int number = 1; number <= 9; number++) {
                for (int i = 0; i < 2; i++) { // Dos cartas de cada número por color
                    Card card = new Card(number, color, CardType.NORMAL);
                    int key = card.hashCode();
                    cardReferences.put(key, card);
                    keys.add(key);
                }
            }


            keys.addAll(generateSpecialCards(color, CardType.DRAW_TWO, 2));
            keys.addAll(generateSpecialCards(color, CardType.REVERSE, 2));
            keys.addAll(generateSpecialCards(color, CardType.SKIP, 2));
            keys.addAll(generateSpecialCards(color, CardType.DRAW_ONE, 2));
        }


        for (int i = 0; i < 4; i++) {
            Card card = new Card(0, Color.BLACK, CardType.COLOR_CHANGER);
            int key = card.hashCode();
            cardReferences.put(key, card);
            keys.add(key);
        }


        Collections.shuffle(keys);


        for (int key : keys) {
            cards.push(key);
        }
    }

    private ArrayList<Integer> generateSpecialCards(Color color, CardType type, int quantity) {
        ArrayList<Integer> specialKeys = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            Card card = new Card(0, color, type); // El número es 0 para cartas especiales
            int key = card.hashCode();
            cardReferences.put(key, card);
            specialKeys.add(key);
        }
        return specialKeys;
    }

    public IStack<Integer> getCards(){
        return cards;
    }

    public Card getCardByKey(int key) {
        Card card = cardReferences.get(key);
        return card;
    }


    /**
    private Stack<Card> cards;

    public Deck() {
        cards = new Stack<>();
        initializeDeck();
    }

    private void initializeDeck(){
        List<Card.Color> colors = new ArrayList<>();
        Collections.addAll(colors, Card.Color.values());
        for(Card.Color color : colors){
            for(int i = 0; i<= 9 ; i++){
                cards.push(new Card(i, color, Card.CardType.NUMBER));
                if(i != 0){
                    cards.push(new Card(i, color, Card.CardType.NUMBER));
                }
            }
            for(int j = 0; j<2; j++){
                cards.push(new Card(-1, color, Card.CardType.SPECIAL));
                cards.push(new Card(-1, color, Card.CardType.SPECIAL));
                cards.push(new Card(-1, color, Card.CardType.SPECIAL));
                cards.push(new Card(-1, color, Card.CardType.SPECIAL));

            }

            Collections.shuffle(cards);
        }


    }

    public Card trump(){
        if(!cards.isEmpty()){
            return cards.pop();
        }
        return null;
    }

    public Stack<Card> getCards() {
        return cards;
    }

}
     */
}

