package com.monocept.sudoku.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Board {

    int[][] grid = new int[9][9];
    boolean[][] fixed = new boolean[9][9];
    Random random = new Random();

    private final int[][] baseSolvedBoard = {
    	//This is base board and we are shuffling this board to get a new board every time a 
    	// new game is played. From this board we can make around 5.67 billion different boards.
        {5,3,4,6,7,8,9,1,2},
        {6,7,2,1,9,5,3,4,8},
        {1,9,8,3,4,2,5,6,7},
        {8,5,9,7,6,1,4,2,3},
        {4,2,6,8,5,3,7,9,1},
        {7,1,3,9,2,4,8,5,6},
        {9,6,1,5,3,7,2,8,4},
        {2,8,7,4,1,9,6,3,5},
        {3,4,5,2,8,6,1,7,9}
        
    };

    public void loadBoard(Difficulty level) {
        resetBoard();

        int[][] puzzle = copyBoard(baseSolvedBoard);

        shuffleRowsWithinBands(puzzle);
        shuffleColsWithinStacks(puzzle);
        remapDigits(puzzle);

        int blanks = getBlanksByDifficulty(level);
        removeCells(puzzle, blanks);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = puzzle[i][j];
                fixed[i][j] = puzzle[i][j] != 0;
            }
        }
    }

    private void resetBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = 0;
                fixed[i][j] = false;
            }
        }
    }

    private int[][] copyBoard(int[][] source) {
        int[][] copy = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(source[i], 0, copy[i], 0, 9);
        }
        return copy;
    }

    private int getBlanksByDifficulty(Difficulty level) {
        switch (level) {
            case EASY:
                return 15;
            case MEDIUM:
                return 30;
            case HARD:
                return 50;
            default:
                return 5;
        }
    }

    private void removeCells(int[][] board, int blanks) {
        int removed = 0;
        while (removed < blanks) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);

            if (board[row][col] != 0) {
                board[row][col] = 0;
                removed++;
            }
        }
    }

    private void shuffleRowsWithinBands(int[][] board) {
        for (int band = 0; band < 3; band++) {
            int start = band * 3;
            for (int i = 0; i < 3; i++) {
                int row1 = start + random.nextInt(3);
                int row2 = start + random.nextInt(3);
                swapRows(board, row1, row2);
            }
        }
    }

    private void shuffleColsWithinStacks(int[][] board) {
        for (int stack = 0; stack < 3; stack++) {
            int start = stack * 3;
            for (int i = 0; i < 3; i++) {
                int col1 = start + random.nextInt(3);
                int col2 = start + random.nextInt(3);
                swapCols(board, col1, col2);
            }
        }
    }

    private void swapRows(int[][] board, int row1, int row2) {
        int[] temp = board[row1];
        board[row1] = board[row2];
        board[row2] = temp;
    }

    private void swapCols(int[][] board, int col1, int col2) {
        for (int i = 0; i < 9; i++) {
            int temp = board[i][col1];
            board[i][col1] = board[i][col2];
            board[i][col2] = temp;
        }
    }

    private void remapDigits(int[][] board) {
        List<Integer> digits = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            digits.add(i);
        }

        Collections.shuffle(digits);

        int[] map = new int[10];
        for (int i = 1; i <= 9; i++) {
            map[i] = digits.get(i - 1);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = map[board[i][j]];
            }
        }
    }

    public void printBoard() {
        System.out.println("+-------+-------+-------+");

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) {
                    System.out.print("| ");
                }

                if (grid[i][j] == 0) {
                    System.out.print(". ");
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }

            System.out.println("|");

            if ((i + 1) % 3 == 0) {
                System.out.println("+-------+-------+-------+");
            }
        }
    }

    public boolean isFixedCell(int row, int col) {
        return fixed[row][col];
    }

    public void setValue(int row, int col, int num) {
        grid[row][col] = num;
    }

    public boolean isComplete() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}