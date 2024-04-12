package model;

import ownStructures.*;

import java.util.ArrayList;
import java.util.Comparator;



public class Controller {

    private int topCard;

    private Deck deck;

    private ArrayList<Player> players;

    private IStack<Integer> gameDeck;

    private IQueue<Integer> cardQueue;

    private IPriorityQueue<Player> playerQueue;

    public Controller(){
        deck = new Deck();
        players = new ArrayList<>();
        topCard = 0;
        gameDeck = new Stack<Integer>() ;
        cardQueue = new Queue<Integer>();
        useCards();


    }

    public Card getCard(int key){
        Card card = deck.getCardByKey(key);
        return card;
    }

    public void useCards(){
        gameDeck = deck.getCards();
    }

    public int getTopCard(){
        return topCard;
    }

    //to start the top card at the beggining of the game we need to make sure that the first card is not a special one.
    public void startTopCard(){
        if (topCard == 0){
            boolean noSpecial = false;
            while(!noSpecial){
            int numCard = gameDeck.pop();
            if(getCard(numCard).isSpecial()){
                gameDeck.push(numCard);
                noSpecial = false;
            }else{
                topCard = numCard;
                noSpecial = true;
            }
            }
        }
    }

    public int getCardFromPlayer(int num, int card){
        Player player = getPlayerByPriority(num);
        return player.getHand().get(card);
    }

    public boolean isPlayable(int cardInt){
        Card card = getCard(cardInt);
        return card.playable(getCard(topCard));
    }

    public void setTopCard(int topCard) {
        this.topCard = topCard;
    }

    public void drawCard(int num){
        cardQueue.enqueue(gameDeck.pop());
        Player player = getPlayerByPriority(num);
        player.getHand().add(cardQueue.dequeue());
    }

    public void createPlayersAndDealCards(int numPlayers) {
        playerQueue = new PriorityQueue<>();
        for (int i = 1; i <= numPlayers; i++) {
            Player player = new Player("p" + i, i);
            players.add(player);
        }

        for (int i = 0; i < 7 * players.size(); i++) {
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

        for(Player player : players){
            playerQueue.offer(player);
        }
    }

    public String printPlayersHand() {
        StringBuilder decksStringBuilder = new StringBuilder();
        int numPlayers = playerQueue.size();

        for (int i = 0; i < numPlayers; i++) {
            Player player = playerQueue.poll();
            if (player != null) {
                decksStringBuilder.append("Deck of player ").append(player.getId()).append(":\n");
                for (int cardKey : player.getHand()) {
                    Card card = getCard(cardKey);
                    decksStringBuilder.append(card.toString()).append("\n");
                }
                decksStringBuilder.append("\n");
            }
        }

        for(Player player : players){
            playerQueue.offer(player);
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
        Player returnPlayer = null;
        for(Player player : players){
            if(player.getPriority() == num){
                returnPlayer = player;
            }
        }
        return returnPlayer;

    }













    /**
     * 1. Generar o trear el mazo de las cartas
     */


/**
 *2.  Repartir a los N jugadores que haya las 7 cartas
 */

/**
 *3.  poner la primera carta en el mazo de descarte
 */

/**
 * bucle para el paso 4 y 5
 */


/**
 * 4. Hacer que el jugador p1 se le muestre el mazo y escoja su carta, y tambien la opcion comer(sacar de la pila del mazo del juego, pasarlo a la cola y luego ponerlo en la mano(arraylist) del jugador)
 */

/**
 *5. poner carta del usuario (quitarlo del arraylist) en la pila del (con push) mazo de descarte
 */

/**
 * 6. pasa a el siguiente jugador se le muestran las opciones
 */

/**
 *7.  validar cartas especiales:
 * - revertir:
 */


}
