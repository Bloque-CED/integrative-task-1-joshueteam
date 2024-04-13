package ui;

import model.Controller;
import model.Player;
import ownStructures.Heap.NodePQ;


import java.util.Scanner;

public class Executable {
    private Scanner reader;
    private static Controller controller;

    public static void main(String[] args) {
        Executable exe = new Executable();
        controller.startTopCard();
        exe.startGame();
    }

    public Executable() {
        controller = new Controller();
        reader = new Scanner(System.in);
    }

    public void startGame() {
        System.out.println("Welcome to Uno card game");
        System.out.println("Please tell us the number of players (2-4)");
        int numPlayers = reader.nextInt();
        while (numPlayers < 2 || numPlayers > 4) {
            System.out.println("Invalid number of players. Please enter a number between 2 and 4.");
            numPlayers = reader.nextInt();
        }
        controller.createPlayersAndDealCards(numPlayers);
        String playersHand = controller.printPlayersHand();
        System.out.println(playersHand);

        while (true) {
            // Obtener jugador actual
            NodePQ<Player> currentPlayer = controller.getCurrentPlayer();

            // Mostrar información del turno
            System.out.println("Turn of player: " + currentPlayer.getData().getId());
            System.out.println("Top card at discard deck: " + controller.getCard(controller.getTopCard()).toString());
            System.out.println(controller.printPlayerHand(currentPlayer.getPriority()));
            System.out.println("Choose an option:");
            System.out.println("1. Play a card");
            System.out.println("2. Draw a card");
            int option = reader.nextInt();
            reader.nextLine(); // Consumir el carácter de nueva línea restante

            switch (option) {
                case 1:
                    playCard(currentPlayer);
                    break;
                case 2:
                    drawCard(currentPlayer);
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }

            // Verificar si hay un ganador
            if (currentPlayer.getData().getHand().isEmpty()) {
                System.out.println("Game Over");
                System.out.println("¡Player " + currentPlayer.getData().getId() + " has won!");
                break;
            }
        }
    }

    private void playCard(NodePQ<Player> currentPlayer) {
        System.out.println("Type the number of the card you want to play:");
        int selectedCardIndex = reader.nextInt();
        reader.nextLine(); // Consumir el carácter de nueva línea restante
        int selectedCard = currentPlayer.getData().getHand().get(selectedCardIndex);
        String color = "";

        // Validar si la carta es jugable
        if (controller.isPlayable(selectedCard)) {
            if(controller.isColorChanger(selectedCard)){
                System.out.println("Type the color you want to change:");
                color = reader.nextLine();
            }
            controller.playCard(selectedCard, color);
        } else {
            System.out.println("Selected card is not playable. Try again");
        }
    }

    private void drawCard(NodePQ<Player> currentPlayer) {
        int drawnCard = controller.drawCard(currentPlayer.getPriority());

        if (drawnCard != -1) {
            System.out.println("You drawed this card: " + controller.getCard(drawnCard).toString());
        } else {
            System.out.println("There aren´t more cards in the game deck");
        }

        if (controller.isPlayable(drawnCard)) {
            playCard(currentPlayer);
        } else {
            System.out.println("The drawed card is not playable. Next player.");
            controller.nextPlayer();
        }
    }
}
