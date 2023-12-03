package com.sven.adventofcode.day_3;

import java.io.IOException;
import java.util.List;

import com.sven.adventofcode.utils.ChallengeHelper;

public class GearRatios {

    // 2d array string
    public static char[][] grid;
    public static int sum = 0;

    public static void convertFileIntoMultiArray() throws IOException {
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

    public static void calculateSymbolAdjacentSum() throws IOException {
        convertFileIntoMultiArray();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                char currentChar = grid[i][j];
                if (Character.isDigit(currentChar)) {
                    // Check if the digit is adjacent to a symbol
                    if (isAdjacentToSymbol(grid, i, j)) {
                        //char might be a 3 digit number, check if previous or next 2 chars are a digits, then jump to column of last digit+1
                        // 1 digit left and one digit right
                        if (Character.isDigit(grid[i][j - 1]) && Character.isDigit(grid[i][j + 1])) {
                            // 3 digit number
                            sum += Integer.parseInt(String.valueOf(grid[i][j - 1]) + String.valueOf(currentChar) + String.valueOf(grid[i][j + 1]));
                            j++;
                        }
                        // 1 or 2 digits right
                        else if (Character.isDigit(grid[i][j + 1])) {
                            if (Character.isDigit(grid[i][j + 2])) {
                                // 3 digit number
                                sum += Integer.parseInt(String.valueOf(currentChar) + String.valueOf(grid[i][j + 1]) + String.valueOf(grid[i][j + 2]));
                                j += 2;
                            } else {
                                // 2 digit number
                                sum += Integer.parseInt(String.valueOf(currentChar) + String.valueOf(grid[i][j + 1]));
                                j++;
                            }
                        } 
                        //1 or 2 digits left
                        else if (Character.isDigit(grid[i][j - 1])) {
                            if (Character.isDigit(grid[i][j - 2])) {
                                // 3 digit number
                                sum += Integer.parseInt(String.valueOf(grid[i][j - 2]) + String.valueOf(grid[i][j - 1]) + String.valueOf(currentChar));
                            } else {
                                // 2 digit number
                                sum += Integer.parseInt(String.valueOf(grid[i][j - 1]) + String.valueOf(currentChar));
                            }
                        }
                        else {
                            // 1 digit number
                            sum += Integer.parseInt(String.valueOf(currentChar));
                        }
                        
                    }
                }
            }
        }
    }

    //check if the current char of row and column is adjacent to a symbol. Above, below, left, right, diagonal.
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
            System.out.println("Sum: " + sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
