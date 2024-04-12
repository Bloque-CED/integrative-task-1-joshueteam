package ui;

import model.Controller;

import java.util.Scanner;

public class Executable {
    private Scanner reader;

    private Controller controller;

    public static void main(String[] args) {
        Executable exe = new Executable();

        exe.mainMenu();


    }

    public Executable(){
        controller = new Controller();
        reader = new Scanner(System.in);

    }

    public void mainMenu(){
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
        startGame();
    }

    private void startGame() {
        boolean winner = false;






    }
}
