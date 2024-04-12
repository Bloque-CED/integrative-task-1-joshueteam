package model;

import java.util.ArrayList;


public class Player implements Comparable<Player> {
    private String id;

    private ArrayList<Integer> hand;

    private int priority;

    public Player(String id, int priority) {
        this.id = id;
        this.priority = priority;
        this.hand = new ArrayList<>();
    }

    public int compareTo(Player other) {
        return Integer.compare(this.priority, other.priority);
    }

    public boolean hasWon(){
        return hand.isEmpty();
    }


    public int getPriority(){
        return priority;
    }

    public ArrayList<Integer> getHand() {
        return hand;
    }

    public String getId(){
        return id;
    }
}
