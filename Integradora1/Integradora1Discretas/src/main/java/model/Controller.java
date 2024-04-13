package model;

import ownStructures.Heap.NodePQ;
import ownStructures.Heap.PriorityQueueH;
import ownStructures.Queue.IQueue;
import ownStructures.Queue.Queue;
import ownStructures.Stack.IStack;
import ownStructures.Stack.Stack;

import java.util.ArrayList;

public class Controller {
    private int topCard;
    private Deck deck;
    private ArrayList<Player> players;
    private IStack<Integer> gameDeck;
    private IQueue<Integer> cardQueue;
    private PriorityQueueH<Player> playerQueue;

    public Controller(){
        deck = new Deck();
        players = new ArrayList<>();
        topCard = 0;
        gameDeck = new Stack<>() ;
        cardQueue = new Queue<>();
        useCards();
    }

    public Card getCard(int key){
        return deck.getCardByKey(key);
    }

    public void useCards(){
        gameDeck = deck.getCards();
    }

    public int getTopCard(){
        return topCard;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public NodePQ<Player> getCurrentPlayer(){
        return playerQueue.peek();
    }

    public void startTopCard(){
        if (topCard == 0){
            boolean noSpecial = false;
            while(!noSpecial){
                int numCard = gameDeck.pop();
                if(getCard(numCard).isSpecial()){
                    gameDeck.push(numCard);
                }else{
                    topCard = numCard;
                    noSpecial = true;
                }
            }
        }
    }

    public boolean isPlayable(int cardInt){
        Card card = getCard(cardInt);
        return card.playable(getCard(topCard));
    }

    public void playCard(int selectedCard, String color) {
        NodePQ<Player> currentPlayer = getCurrentPlayer();
        currentPlayer.getData().getHand().remove(Integer.valueOf(selectedCard));
        setTopCard(selectedCard);
        CardType type = identifyCardType(selectedCard);
        switch (type){
            case SKIP:
                nextPlayer();
                nextPlayer();
                break;
            case REVERSE:
                reverse();
                break;
            case COLOR_CHANGER:
                changeColor(selectedCard, selectColor(color));
                nextPlayer();
                break;
            case DRAW_TWO:
                nextPlayer();
                drawTWO(getCurrentPlayer().getData());
                break;
            default:
                nextPlayer();
                break;
        }
    }

    public void setTopCard(int topCard) {
        this.topCard = topCard;
    }

    public Color selectColor(String color){
        Color newColor = null;
        switch (color.toUpperCase()){
            case "GREEN":
                newColor = Color.GREEN;
                break;
            case "BLUE":
                newColor = Color.BLUE;
                break;
            case "RED":
                newColor = Color.RED;
                break;
            case "YELLOW":
                newColor = Color.YELLOW;
                break;
            default:
                System.out.println("invalid color, current color will be keept");
                break;
        }

        return newColor;
    }

    public int drawCard(int num){
        if (!gameDeck.isEmpty()) {
            int card = gameDeck.pop();
            Player player = getPlayerByPriority(num);
            player.getHand().add(card);
            return card;
        }
        return -1;
    }

    public boolean isColorChanger(int cardIndex) {
        Card card = getCard(cardIndex);
        return card.getType() == CardType.COLOR_CHANGER;
    }

    public void createPlayersAndDealCards(int numPlayers) {
        playerQueue = new PriorityQueueH<>(true);
        for (int i = 1; i <= numPlayers; i++) {
            Player player = new Player("P" + i);
            players.add(player);
        }

        for (int i = 0; i < 7 * numPlayers; i++) {
            if (!gameDeck.isEmpty()) {
                cardQueue.enqueue(gameDeck.pop());
            }
        }

        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                if (!cardQueue.isEmpty()) {
                    player.getHand().add(cardQueue.dequeue());
                }
            }
        }

        for(int i = 1; i <= players.size(); i++){
            playerQueue.enqueue(new NodePQ<>(players.get(i-1), i));
        }
    }

    public String printPlayersHand() {
        StringBuilder decksStringBuilder = new StringBuilder();
        int numPlayers = playerQueue.size();

        for (int i = 0; i < numPlayers; i++) {
            Player player = playerQueue.dequeue().getData();
            if (player != null) {
                decksStringBuilder.append("Deck of player ").append(player.getId()).append(":\n");
                for (int cardKey : player.getHand()) {
                    Card card = getCard(cardKey);
                    decksStringBuilder.append(card.toString()).append("\n");
                }
                decksStringBuilder.append("\n");
            }
        }

        for(int i = 1; i <= players.size(); i++){
            playerQueue.enqueue(new NodePQ<>(players.get(i-1), i));
        }

        return decksStringBuilder.toString();
    }

    public String printPlayerHand(int num){
        String msg = "";
        Player player = getPlayerByPriority(num);
        int i = 0;

        for(int cardKey : player.getHand()){
            Card card = getCard(cardKey);
            msg += (i) +". " + card.toString() + "\n";
            i++;
        }
        return msg;
    }

    private Player getPlayerByPriority(int num) {
        NodePQ<Player> returnPlayer = null;
        for(NodePQ<Player> node : playerQueue.getHeap()){
            if(node.getPriority() == num){
                returnPlayer = node;
                break;
            }
        }
        return returnPlayer.getData();
    }

    public void nextPlayer() {
        playerQueue.assignPriority();
        playerQueue.enqueue(playerQueue.dequeue());
    }

    public CardType identifyCardType(int cardIndex) {
        if (cardIndex >= 0 && cardIndex < 109) {
            return deck.getCardByKey(cardIndex).getType();
        } else {
            return null;
        }
    }

    public void drawTWO(Player nextPlayer) {
        int drawnCards = 2;
        for (int i = 0; i < drawnCards; i++) {
            nextPlayer.getHand().add(gameDeck.pop());
        }
        nextPlayer();
    }

    public void changeColor(int currentCard, Color newColor) {
        if (newColor != null) {
            Card lastPlayedCard = getCard(currentCard);
            lastPlayedCard.setColor(newColor);
        }
    }

    public void reverse(){
        PriorityQueueH<Player> reversedQueue = new PriorityQueueH<>(true);
        while (!playerQueue.isEmpty()) {
            NodePQ<Player> playerNode = playerQueue.dequeue();
            playerNode.setPriority(-playerNode.getPriority());
            reversedQueue.enqueue(playerNode);
        }
        playerQueue = reversedQueue;
    }

    public void printFullDeck() {
        IStack<Integer> tempStack = new Stack<>();
        while (!gameDeck.isEmpty()) {
            int cardKey = gameDeck.pop();
            tempStack.push(cardKey);
            Card card = deck.getCardByKey(cardKey);
            System.out.println(card.toString());
        }
        while (!tempStack.isEmpty()) {
            gameDeck.push(tempStack.pop());
        }
    }

    public Deck getDeck() {
        return deck;
    }
}