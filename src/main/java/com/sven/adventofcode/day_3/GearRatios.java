package com.sven.adventofcode.day_3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sven.adventofcode.utils.ChallengeHelper;

public class GearRatios {

    // 2d array string
    public static char[][] grid;
    public static int sum = 0;

    public static int number1 = 0;
    public static int number2 = 0;

    public static int digitsToLeft = 0;
    public static int digitsToRight = 0;

    public static void convertFileIntoMultiArrayDay3_1() throws IOException {
        // Read file
        List<String> input = ChallengeHelper.readFileContentasList("day3_1.txt");
        // Create 2d array
        grid = new char[input.size()][input.get(0).length()];

        // Fill 2d array from input
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                grid[i][j] = input.get(i).charAt(j);
            }
        }
    }

    // day3_2
    public static void convertFileIntoMultiArrayDay3_2() throws IOException {
        // Read file
        List<String> input = ChallengeHelper.readFileContentasList("day3_2.txt");
        // Create 2d array
        grid = new char[input.size()][input.get(0).length()];

        // Fill 2d array from input
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                grid[i][j] = input.get(i).charAt(j);
            }
        }
    }

    public static void calculateGearRatioSum() throws IOException {
        convertFileIntoMultiArrayDay3_2();
        // loop through grid line by line
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                char currentChar = grid[i][j];
                // check if current char is a symbol
                if (currentChar == '*') {
                    // check if symbol is adjacent to two numbers
                    if (isAdjacentToTwoNumbers(grid, i, j)) {
                        // multiply the two numbers and add to sum
                        sum += (number1 * number2);
                    }
                }
            }
        }
    }

    // check if symbol is adjacent to two numbers
    private static boolean isAdjacentToTwoNumbers(char[][] grid2, int i, int j) {
        List<Integer> numbers = new ArrayList<>();
        boolean topLeft = false;
        boolean top = false;
        boolean bottomRight = false;
        boolean bottom = false;
        // check above area then go in circle, if first number is found set n1, if
        // second number is found set n2
        // check above left diagonal
        if (i - 1 >= 0 && j - 1 >= 0) {
            if (Character.isDigit(grid[i - 1][j - 1])) {
                numbers.add(determineNumber(grid2, i - 1, j - 1));
                topLeft = true;
            }
        }
        // check above
        if (i - 1 >= 0) {// nothing found above left
            if (Character.isDigit(grid[i - 1][j])) {
                if (!topLeft) {
                    numbers.add(determineNumber(grid2, i - 1, j));
                    top = true;
                }
            }
        }
        // check above right diagonal
        if (i - 1 >= 0 && j + 1 < grid[i].length) {// nothing found above
            if (Character.isDigit(grid[i - 1][j + 1])) {
                if (!top && digitsToRight == 0) {
                    numbers.add(determineNumber(grid2, i - 1, j + 1));
                }
            }
        }
        digitsToLeft = 0;
        digitsToRight = 0;
        // check below right diagonal
        if (i + 1 < grid.length && j + 1 < grid[i].length) {
            if (Character.isDigit(grid[i + 1][j + 1])) {
                numbers.add(determineNumber(grid2, i + 1, j + 1));
                bottomRight = true;
            }
        }
        // check below
        if (i + 1 < grid.length) {
            if (Character.isDigit(grid[i + 1][j])) {
                if (!bottomRight) {
                    numbers.add(determineNumber(grid2, i + 1, j));
                    bottom = true;
                }
            }
        }
        // check below left diagonal
        if (i + 1 < grid.length && j - 1 >= 0) {
            if (Character.isDigit(grid[i + 1][j - 1])) {
                if (!bottom && digitsToLeft == 0) {
                    numbers.add(determineNumber(grid2, i + 1, j - 1));
                }
            }
        }
        // check left
        if (j - 1 >= 0) {
            if (Character.isDigit(grid[i][j - 1])) {
                numbers.add(determineNumber(grid2, i, j - 1));
            }
        }
        // check right
        if (j + 1 < grid[i].length) {
            if (Character.isDigit(grid[i][j + 1])) {
                numbers.add(determineNumber(grid2, i, j + 1));
            }
        }
        digitsToLeft = 0;
        digitsToRight = 0;
        return twoNumbersFound(numbers);
    }

    // determine Number
    public static int determineNumber(char[][] grid, int i, int j) {
        // char might be a 3 digit number, check if previous or next 2 chars are a
        // digits, then jump to column of last digit+1
        // 1 digit left and one digit right
        if (Character.isDigit(grid[i][j - 1]) && Character.isDigit(grid[i][j + 1])) {
            // 3 digit number
            digitsToLeft = 1;
            digitsToRight = 1;
            return Integer.parseInt(
                    String.valueOf(grid[i][j - 1]) + String.valueOf(grid[i][j]) + String.valueOf(grid[i][j + 1]));
        }
        // 1 or 2 digits right
        else if (Character.isDigit(grid[i][j + 1])) {
            if (Character.isDigit(grid[i][j + 2])) {
                // 3 digit number
                digitsToLeft = 0;
                digitsToRight = 2;
                return Integer.parseInt(
                        String.valueOf(grid[i][j]) + String.valueOf(grid[i][j + 1]) + String.valueOf(grid[i][j + 2]));
            } else {
                // 2 digit number
                digitsToLeft = 0;
                digitsToRight = 1;
                return Integer.parseInt(String.valueOf(grid[i][j]) + String.valueOf(grid[i][j + 1]));
            }
        }
        // 1 or 2 digits left
        else if (Character.isDigit(grid[i][j - 1])) {
            if (Character.isDigit(grid[i][j - 2])) {
                // 3 digit number
                digitsToLeft = 2;
                digitsToRight = 0;
                return Integer.parseInt(
                        String.valueOf(grid[i][j - 2]) + String.valueOf(grid[i][j - 1]) + String.valueOf(grid[i][j]));
            } else {
                // 2 digit number
                digitsToLeft = 1;
                digitsToRight = 0;
                return Integer.parseInt(String.valueOf(grid[i][j - 1]) + String.valueOf(grid[i][j]));
            }
        } else {
            // 1 digit number
            digitsToLeft = 0;
            digitsToRight = 0;
            return Integer.parseInt(String.valueOf(grid[i][j]));
        }
    }

    public static boolean twoNumbersFound(List<Integer> numbers) {
        if (numbers.size() == 2) {
            number1 = numbers.get(0);
            number2 = numbers.get(1);
            return true;
        }
        return false;
    }

    public static void calculateSymbolAdjacentSum() throws IOException {
        convertFileIntoMultiArrayDay3_1();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                char currentChar = grid[i][j];
                if (Character.isDigit(currentChar)) {
                    // Check if the digit is adjacent to a symbol
                    if (isAdjacentToSymbol(grid, i, j)) {
                        // char might be a 3 digit number, check if previous or next 2 chars are a
                        // digits, then jump to column of last digit+1
                        // 1 digit left and one digit right
                        if (Character.isDigit(grid[i][j - 1]) && Character.isDigit(grid[i][j + 1])) {
                            // 3 digit number
                            sum += Integer.parseInt(String.valueOf(grid[i][j - 1]) + String.valueOf(currentChar)
                                    + String.valueOf(grid[i][j + 1]));
                            j++;
                        }
                        // 1 or 2 digits right
                        else if (Character.isDigit(grid[i][j + 1])) {
                            if (Character.isDigit(grid[i][j + 2])) {
                                // 3 digit number
                                sum += Integer.parseInt(String.valueOf(currentChar) + String.valueOf(grid[i][j + 1])
                                        + String.valueOf(grid[i][j + 2]));
                                j += 2;
                            } else {
                                // 2 digit number
                                sum += Integer.parseInt(String.valueOf(currentChar) + String.valueOf(grid[i][j + 1]));
                                j++;
                            }
                        }
                        // 1 or 2 digits left
                        else if (Character.isDigit(grid[i][j - 1])) {
                            if (Character.isDigit(grid[i][j - 2])) {
                                // 3 digit number
                                sum += Integer.parseInt(String.valueOf(grid[i][j - 2]) + String.valueOf(grid[i][j - 1])
                                        + String.valueOf(currentChar));
                            } else {
                                // 2 digit number
                                sum += Integer.parseInt(String.valueOf(grid[i][j - 1]) + String.valueOf(currentChar));
                            }
                        } else {
                            // 1 digit number
                            sum += Integer.parseInt(String.valueOf(currentChar));
                        }

                    }
                }
            }
        }
    }

    // check if the current char of row and column is adjacent to a symbol. Above,
    // below, left, right, diagonal.
    public static boolean isAdjacentToSymbol(char[][] grid, int row, int col) {
        // Check above
        if (row - 1 >= 0) {
            if (isSymbol(grid[row - 1][col])) {
                return true;
            }
        }
        // Check below
        if (row + 1 < grid.length) {
            if (isSymbol(grid[row + 1][col])) {
                return true;
            }
        }
        // Check left
        if (col - 1 >= 0) {
            if (isSymbol(grid[row][col - 1])) {
                return true;
            }
        }
        // Check right
        if (col + 1 < grid[row].length) {
            if (isSymbol(grid[row][col + 1])) {
                return true;
            }
        }
        // Check diagonal up left
        if (row - 1 >= 0 && col - 1 >= 0) {
            if (isSymbol(grid[row - 1][col - 1])) {
                return true;
            }
        }
        // Check diagonal up right
        if (row - 1 >= 0 && col + 1 < grid[row].length) {
            if (isSymbol(grid[row - 1][col + 1])) {
                return true;
            }
        }
        // Check diagonal down left
        if (row + 1 < grid.length && col - 1 >= 0) {
            if (isSymbol(grid[row + 1][col - 1])) {
                return true;
            }
        }
        // Check diagonal down right
        if (row + 1 < grid.length && col + 1 < grid[row].length) {
            if (isSymbol(grid[row + 1][col + 1])) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSymbol(char currentChar) {
        // use regex to check if string is a symbol: * - $ % # @ = / # & +
        return String.valueOf(currentChar).matches("[*\\-\\$%#@=/#&\\+]");
    }

    public static void main(String[] args) {
        try {
            GearRatios.calculateSymbolAdjacentSum();
            System.out.println("Adjacent Sum: " + sum);
            sum = 0;
            GearRatios.calculateGearRatioSum();
            System.out.println("GearRatio Sum: " + sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
