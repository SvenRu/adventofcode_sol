package com.sven.adventofcode.challenges.dayfour;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sven.adventofcode.utils.ChallengeInputReader;

public class GiantSquidPartTwo {
    static List<String> drawnNumbersList = new ArrayList<>();

    static int numbersDrawnWinner = 1;
    static String[][] winnerBoard = null;
    static int finalScore = 1;
    private static int numberCalled = 0;

    public static void main(String[] args) {
        try {

            List<String> inputList = ChallengeInputReader.readFileContentasList("giantsquid_input.txt");
            List<String[][]> bingoBoardsList = fillBingoBoards(inputList);

            for (String[][] bingoBoard : bingoBoardsList) {
                checkBingoBoard(bingoBoard);
            }

            System.out.println("Worst Board: ");
            System.out.println(Arrays.deepToString(winnerBoard)
                    .replace("],", "\n").replace(",", "\t ")
                    .replaceAll("[\\[\\]]", " "));

            System.out.println("Numbers drawn: " + numbersDrawnWinner);
            System.out.println("Number called: " + numberCalled);
            System.out.println("Final Score: " + finalScore);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[][] checkBingoBoard(String[][] currentBingoBoard) {

        int numbersDrawn = 0;
        for (String drawnNumber : drawnNumbersList) {
            for (int row = 0; row < currentBingoBoard.length; row++) {
                for (int col = 0; col < currentBingoBoard[row].length; col++) {
                    String bingoNumber = currentBingoBoard[row][col];
                    if (drawnNumber.equals(bingoNumber)) {
                        currentBingoBoard[row][col] = "x";
                        if (isAWinner(currentBingoBoard, row, col)) {
                            if (numbersDrawn > numbersDrawnWinner) {
                                numbersDrawnWinner = numbersDrawn;
                                winnerBoard = currentBingoBoard;
                                calcFinalScore(currentBingoBoard, drawnNumber);
                            }
                            return currentBingoBoard;
                        }
                    }
                }
            }
            numbersDrawn++;
        }
        return currentBingoBoard;
    }

    private static void calcFinalScore(String[][] currentBingoBoard, String drawnNumber) {
        int sumUnmarked = 0;
        numberCalled = Integer.parseInt(drawnNumber);

        for (int row = 0; row < currentBingoBoard.length; row++) {
            for (int col = 0; col < currentBingoBoard[row].length; col++) {
                String bingoNumber = currentBingoBoard[row][col];
                if (!(bingoNumber.equals("x"))) {
                    int bingoInt = Integer.parseInt(bingoNumber);
                    sumUnmarked += bingoInt;
                }
            }
        }
        finalScore = sumUnmarked * numberCalled;
    }

    public static Boolean isAWinner(String[][] bingoBoard, int row, int col) {

        Boolean isRowBingo = true;
        Boolean isColumnBingo = true;

        for (int r = 0; r < bingoBoard.length; r++) {
            if (!(bingoBoard[r][col].equals("x"))) {
                isRowBingo = false;
                break;
            }
        }

        for (int c = 0; c < bingoBoard[row].length; c++) {
            if (!(bingoBoard[row][c].equals("x"))) {
                isColumnBingo = false;
                break;
            }
        }

        if (isRowBingo || isColumnBingo) {
            return true;
        } else {
            return false;
        }
    }

    public static List<String[][]> fillBingoBoards(List<String> inputList) {

        drawnNumbersList = Arrays.asList(inputList.get(0).split(","));

        inputList.remove(0);// remove drawn numbers from list
        inputList.remove(0); // emtpy line

        String[][] bingo_board = new String[5][5]; // row, column
        List<String[][]> bingoBoardList = new ArrayList<>();

        int row = 0;

        for (String line : inputList) {

            if (line.isBlank()) {
                continue;
            }

            String[] lineArray = line.replaceAll("( +)", " ").trim().split(" ");

            for (int column = 0; column < lineArray.length; column++) {
                bingo_board[row][column] = lineArray[column];
            }
            row++;

            if (row == 5) {// next bingoboard
                bingoBoardList.add(bingo_board);
                row = 0;
                bingo_board = new String[5][5];
            }

        }
        return bingoBoardList;
    }

}
