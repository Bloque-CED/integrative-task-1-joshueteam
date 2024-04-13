package model;

import java.util.ArrayList;

public class Player {
    private String id;
    private ArrayList<Integer> hand;

    public Player(String id) {
        this.id = id;
        this.hand = new ArrayList<>();
    }

    public boolean hasWon(){
        return hand.isEmpty();
    }
    public ArrayList<Integer> getHand() {
        return hand;
    }
    public String getId(){
        return id;
    }
}