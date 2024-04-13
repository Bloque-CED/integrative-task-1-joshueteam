package model;
import ownStructures.HashTable.HashTable;
import ownStructures.HashTable.IHashTable;
import ownStructures.Stack.IStack;
import ownStructures.Stack.Stack;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private IHashTable<Integer, Card> cardReferences;
    private IStack<Integer> cards;
    private List<Integer> cardsList;

    public Deck(){
        cardReferences = new HashTable<>();
        cards = new Stack<>();
        cardsList = new ArrayList<>();
        generateDeck();
    }

    public void generateDeck() {
        int index = 0;
        int indexUpdater = 0;

        while (!cards.isEmpty()) {
            cardsList.add(cards.pop());
        }

        for (Color color : Color.values()) {
            if (color != Color.BLACK){
                for (int number = 1; number <= 9; number++) {
                    for (int i = 0; i < 2; i++) {
                        Card card = new Card(number, color, CardType.NORMAL);
                        cardReferences.put(index, card);
                        cardsList.add(index);
                        index++;
                    }
                }
                indexUpdater = index;
                indexUpdater = generateAndAddSpecialCards(indexUpdater, color, CardType.DRAW_TWO, 2);
                indexUpdater = generateAndAddSpecialCards(indexUpdater, color, CardType.REVERSE, 2);
                indexUpdater = generateAndAddSpecialCards(indexUpdater, color, CardType.SKIP, 2);
                index = indexUpdater;
            }
        }

        generateAndAddSpecialCards(index, Color.BLACK, CardType.COLOR_CHANGER, 12);

        Collections.shuffle(cardsList);

        for (Integer cardIndex : cardsList) {
            cards.push(cardIndex);
        }
    }

    private int generateAndAddSpecialCards(int startIndex, Color color, CardType type, int quantity) {
        for (int i = 0; i < quantity; i++) {
            Card card = new Card(0, color, type);
            cardReferences.put(startIndex, card);
            cardsList.add(startIndex);
            startIndex++;
        }
        return startIndex;
    }

    public IStack<Integer> getCards(){
        return cards;
    }

    public Card getCardByKey(int key) {
        return cardReferences.get(key);
    }

    public int size(){
        return cards.size();
    }
}
