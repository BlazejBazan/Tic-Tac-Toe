package tictactoe;

import static tictactoe.Game.*;

public class Grid {
    public enum GridStatus{
        GAME_NOT_FINISHED("Game not finished"),
        PLAYER_X_WINS(PLAYER_X + " wins"),
        PLAYER_O_WINS(PLAYER_O + " wins"),
        DRAW("Draw"),
        IMPOSSIBLE("Impossible");

        private final String gridStatus;

        public String  getStringGridStatus() {
            return gridStatus;
        }

        GridStatus(String gridStatus) {
            this.gridStatus = gridStatus;
        }
    }

    protected static void printGrid(String cells) {
        String result = "---------\n" +
                "| " + cells.charAt(0) + " " + cells.charAt(1) + " " + cells.charAt(2) + " |\n" +
                "| " + cells.charAt(3) + " " + cells.charAt(4) + " " + cells.charAt(5) + " |\n" +
                "| " + cells.charAt(6) + " " + cells.charAt(7) + " " + cells.charAt(8) + " |\n" +
                "---------\n";
        System.out.println(result);
    }

    protected static GridStatus getGridStatus(String cells) {
        String[] horizontalLines = getHorizontalLines(cells);
        String[] verticalLines = getVerticalLines(cells);
        String[] diagonalLines = getDiagonalLines(cells);

        boolean winnerIsO = winnerIsO(horizontalLines, verticalLines, diagonalLines);
        boolean winnerIsX = winnerIsX(horizontalLines, verticalLines, diagonalLines);

        long countX = cells.chars().filter(ch -> ch == PLAYER_X).count();
        long countO = cells.chars().filter(ch -> ch == PLAYER_O).count();
        long count_ = cells.chars().filter(ch -> ch == FREE_CELL).count();

        GridStatus gridStatus = GridStatus.IMPOSSIBLE;

        if (count_ != 0) gridStatus = GridStatus.GAME_NOT_FINISHED;
        if (count_ == 0) gridStatus = GridStatus.DRAW;
        if (winnerIsX) gridStatus = GridStatus.PLAYER_X_WINS;
        if (winnerIsO) gridStatus = GridStatus.PLAYER_O_WINS;
        if (Math.abs(countO - countX) > 1) gridStatus = GridStatus.IMPOSSIBLE;
        if (winnerIsO && winnerIsX) gridStatus = GridStatus.IMPOSSIBLE;

        return gridStatus;
    }

    private static String[] getDiagonalLines(String cells) {
        String[] dl = new String[2];
        dl[0] = cells.charAt(0) + String.valueOf(cells.charAt(4)) + cells.charAt(8);
        dl[1] = cells.charAt(2) + String.valueOf(cells.charAt(4)) + cells.charAt(6);
        return dl;
    }

    private static String[] getVerticalLines(String cells) {
        String[] vl = new String[3];
        vl[0] = cells.charAt(0) + String.valueOf(cells.charAt(3)) + cells.charAt(6);
        vl[1] = cells.charAt(1) + String.valueOf(cells.charAt(4)) + cells.charAt(7);
        vl[2] = cells.charAt(2) + String.valueOf(cells.charAt(5)) + cells.charAt(8);
        return vl;
    }

    private static String[] getHorizontalLines(String cells) {
        String[] hl = new String[3];
        hl[0] = cells.substring(0, 3);
        hl[1] = cells.substring(3, 6);
        hl[2] = cells.substring(6);
        return hl;
    }

    protected static boolean isCellFree(String cells, int[] coordinates) {
        int indexInCells = getIndexInCellsFromCoordinates(coordinates);
        return cells.charAt(indexInCells) == FREE_CELL;
    }

    protected static int getIndexInCellsFromCoordinates(int[] coordinates) {
        int indexInCells = coordinates[1] - 1;
        indexInCells += coordinates[0] == 2 ? 3 : 0;
        indexInCells += coordinates[0] == 3 ? 6 : 0;
        return indexInCells;
    }

    protected static boolean isCoordinatesInGrid(int[] coordinates) {
        boolean isLessOrEqualThree = coordinates[0] <= 3 && coordinates[1] <= 3;
        boolean isGraterOrEqualOne = coordinates[0] >= 1 && coordinates[1] >= 1;
        return isLessOrEqualThree && isGraterOrEqualOne;
    }
}
