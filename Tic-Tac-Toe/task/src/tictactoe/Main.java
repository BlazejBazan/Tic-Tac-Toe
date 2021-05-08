package tictactoe;

import java.util.Scanner;

public class Main {
    public static final Scanner READER = new Scanner(System.in);

    public static void main(String[] args) {
        Game game = new Game();

//        String cells = READER.nextLine();
//        String cells = "XXX___OO_";
//        Game game = new Game(cells);

        System.out.println(game.startGame());

        READER.close();
    }
}
