package com.sven.adventofcode.day_2;

import java.util.List;
import java.io.IOException;

import com.sven.adventofcode.utils.ChallengeInputReader;

public class CubeConundrum {

    // init new hashmap games int, arraylist of strings
    static int possibleGamesIdsSum = 0;
    static int totalPower = 0;
    static int maxRedCubes = 12;
    static int maxBlueCubes = 14;
    static int maxGreenCubes = 13;

    // read file day1_1.txt file to array
    public static void processInputFile_day2_1() throws IOException {
        // read file
        List<String> inputfile = ChallengeInputReader.readFileContentasList("day2_1.txt");
        // iterate
        int gameId = 1;
        for (String line : inputfile) {
            String[] lineArray = line.split(":");
            if(isValidGame(lineArray[1], gameId)){
                possibleGamesIdsSum += gameId;
            }
            gameId++;
        }
    }

    public static void processInputFile_day2_2() throws IOException {
        // read file
        List<String> inputfile = ChallengeInputReader.readFileContentasList("day2_2.txt");
        // iterate
        for (String line : inputfile) {
            String[] lineArray = line.split(":");
            totalPower += calcPower(lineArray[1]);
        }
    }

    private static int calcPower(String line) {
        String[] gameSets = line.split(";");

        int maxNumberOfRedCubes = 0;
        int maxNumberOfBlueCubes = 0;
        int maxNumberOfGreenCubes = 0;

        // for each game set
        for (String gameSet : gameSets) {
            // split by ,
            String[] gameSetArray = gameSet.split(",");
            // for each game set
            for (String gameSetElement : gameSetArray) {
                    String element = gameSetElement.trim();
                    // check if red
                    if (element.contains("red")) {
                        // get number and compare if it is bigger than maxNumberOfRedCubes
                        int numberOfRedCubes = Integer.parseInt(element.substring(0, element.length() - 4));
                        if(numberOfRedCubes > maxNumberOfRedCubes){
                            maxNumberOfRedCubes = numberOfRedCubes;
                        }
                    }
                    // check if blue
                    if (element.contains("blue")) {
                        // get number and compare if it is bigger than maxNumberOfBlueCubes
                        int numberOfBlueCubes = Integer.parseInt(element.substring(0, element.length() - 5));
                        if(numberOfBlueCubes > maxNumberOfBlueCubes){
                            maxNumberOfBlueCubes = numberOfBlueCubes;
                        }
                    }
                    // check if green
                    if (element.contains("green")) {
                        // get number and compare if it is bigger than maxNumberOfGreenCubes
                        int numberOfGreenCubes = Integer.parseInt(element.substring(0, element.length() - 6));
                        if(numberOfGreenCubes > maxNumberOfGreenCubes){
                            maxNumberOfGreenCubes = numberOfGreenCubes;
                        }
                    }
                }
        }
        return maxNumberOfRedCubes * maxNumberOfBlueCubes * maxNumberOfGreenCubes;
    }

    private static boolean isValidGame(String line, int gameId) {
        String[] gameSets = line.split(";");

        // for each game set
        for (String gameSet : gameSets) {
            int numberOfRedCubes = 0;
            int numberOfBlueCubes = 0;
            int numberOfGreenCubes = 0;
            // split by ,
            String[] gameSetArray = gameSet.split(",");
            // for each game set
            for (String gameSetElement : gameSetArray) {
                    String element = gameSetElement.trim();
                    // check if red
                    if (element.contains("red")) {
                        // increse numberOfRedCubes by number, which is the first char of gameSetElement
                        numberOfRedCubes += Integer.parseInt(element.substring(0, element.length() - 4));
                    }
                    // check if blue
                    if (element.contains("blue")) {
                        // increse numberOfBlueCubes by number, which is the first char of gameSetElement
                        numberOfBlueCubes += Integer.parseInt(element.substring(0, element.length() - 5));
                    }
                    // check if green
                    if (element.contains("green")) {
                        // increse numberOfGreenCubes by number, which is the first char of gameSetElement
                        numberOfGreenCubes += Integer.parseInt(element.substring(0, element.length() - 6));
                    }
                }
            if (numberOfRedCubes > maxRedCubes || numberOfBlueCubes > maxBlueCubes || numberOfGreenCubes > maxGreenCubes){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            processInputFile_day2_1();
            System.out.println("Possible games: " + possibleGamesIdsSum);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            processInputFile_day2_2();
            System.out.println("Total power: " + totalPower);
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }

}
