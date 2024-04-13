import model.Card;
import model.CardType;
import model.Controller;
import model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ownStructures.Heap.NodePQ;


import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    private Controller controller;

    @BeforeEach
    public void setUp() {
        controller = new Controller();
        controller.createPlayersAndDealCards(4);
    }

    @Test
    public void topCardProof(){
        Card card = controller.getCard(controller.getTopCard());
        CardType type = card.getType();
        assertEquals(CardType.NORMAL, type);

    }

    @Test
    public void testPlayCard() {
        Player currentPlayer = controller.getCurrentPlayer().getData();
        int initialHandSize = currentPlayer.getHand().size();
        int initialTopCard = controller.getTopCard();

        int selectedCard = -1;
        for (int cardKey : currentPlayer.getHand()) {
            if (controller.isPlayable(cardKey)) {
                selectedCard = cardKey;
                break;
            }
        }

        if (selectedCard != -1) {
            controller.playCard(selectedCard, "GREEN");
            assertEquals(initialHandSize - 1, currentPlayer.getHand().size());
            assertNotEquals(initialTopCard, controller.getTopCard());
        }
    }

    @Test
    public void testDrawCard() {
        int initialDeckSize = controller.getDeck().size();
        int cardDrawn = controller.drawCard(1);
        assertEquals(true, cardDrawn >= 0);
        assertEquals(initialDeckSize - 1, controller.getDeck().size());
    }

    @Test
    public void testCreatePlayersAndDealCards() {
        assertEquals(4, controller.getPlayers().size());
        for (Player player : controller.getPlayers()) {
            assertEquals(7, player.getHand().size());
        }
    }

    @Test
    public void testNextPlayer() {
        NodePQ<Player> currentPlayer = controller.getCurrentPlayer();
        controller.nextPlayer();
        assertNotEquals(currentPlayer, controller.getCurrentPlayer());
    }
}
