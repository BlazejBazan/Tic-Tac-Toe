package tictactoe;

import java.util.InputMismatchException;

import static tictactoe.Grid.*;
import static tictactoe.Main.READER;

public class Game {
    public static final int GRID_SIZE = 9;
    public static final char PLAYER_X = 'X';
    public static final char PLAYER_O = 'O';
    public static final char FREE_CELL = '_';

    public String cells;

    protected Game(){
        this.cells = "_________";
    }

    public Game(String cells) {
        if (cells.length() == GRID_SIZE) {
            this.cells = cells;
        } else {
            System.out.println("Wrong input!");
        }
    }

    public String startGame(){
        char player = PLAYER_X;
        while (getGridStatus(cells) == GridStatus.GAME_NOT_FINISHED) {
            printGrid(cells);
            cells = makeMove(cells, player);
            player = changePlayer(player);
        }
        printGrid(cells);
        return getGridStatus(cells).getStringGridStatus();
    }

    private char changePlayer(char player) {
        return player == PLAYER_X ? PLAYER_O : PLAYER_X;
    }

    protected static boolean winnerIsX(String[] horizontalLines, String[] verticalLines, String[] diagonalLines) {
        String winString = String.valueOf(PLAYER_X) + PLAYER_X + PLAYER_X;
        return checkWin(horizontalLines, verticalLines, diagonalLines, winString);
    }

    protected static boolean winnerIsO(String[] horizontalLines, String[] verticalLines, String[] diagonalLines) {
        String winString = String.valueOf(PLAYER_O) + PLAYER_O + PLAYER_O;
        return checkWin(horizontalLines, verticalLines, diagonalLines, winString);
    }

    private static boolean checkWin(String[] horizontalLines, String[] verticalLines, String[] diagonalLines, String winString) {
        boolean result = false;
        for (String diagonal : diagonalLines) {
            if (diagonal.equals(winString)) {
                result = true;
                break;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (horizontalLines[i].equals(winString) || verticalLines[i].equals(winString)) {
                result = true;
                break;
            }
        }
        return result;
    }

    protected static int[] getCoordinates() {
        int[] coordinates = new int[2];

        System.out.println("Enter the coordinates: ");
        try {
                coordinates[0] = READER.nextInt();
            if (READER.hasNextInt()) {
                coordinates[1] = READER.nextInt();
            } else throw new InputMismatchException();
        } catch (final InputMismatchException e) {
            READER.next();
            System.out.println("You should enter numbers!");
            coordinates = getCoordinates();
        }
        if (!isCoordinatesInGrid(coordinates)) {
            System.out.println("Coordinates should be from 1 to 3!");
            coordinates = getCoordinates();
        }

        return coordinates;
    }

    protected static String makeMove(String cells, char player) {
        int[] coordinates = getCoordinates();
        while (!isCellFree(cells, coordinates)) {
            System.out.println("This cell is occupied! Choose another one!");
            coordinates = getCoordinates();
        }

        int index = getIndexInCellsFromCoordinates(coordinates);

        return cells.substring(0, index) + player + cells.substring(index + 1);
    }
}
