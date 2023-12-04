package com.sven.adventofcode.day_4;

import java.io.IOException;
import java.util.List;

import com.sven.adventofcode.utils.ChallengeHelper;

public class Scratchcards {

    public static int points = 0;

    public static void main(String[] args) {
        try {
            process();
            System.out.println("Points: " + points);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void process() throws IOException {

        List<String> input = ChallengeHelper.readFileContentasList("day4_1.txt");

        for (String line : input) {

            String[] split = line.split(":");
            String[] game = split[1].split("\\|");

            String[] winningNumbers = game[0].split(" ");
            String[] numbersIhave = game[1].split(" ");

            processNumbers(winningNumbers, numbersIhave);
        }

    }


    private static void processNumbers(String[] winningNumbers, String[] numbersIhave) {

        int valueOfCard = 0;

        for(String winningNumber : winningNumbers) {
            //check if winningNumber is empty
            if(winningNumber.isEmpty()) {
                continue;
            }
            for(String numberIhave : numbersIhave) {
                if(winningNumber.equals(numberIhave)) {
                    //doulbe value of card
                    if(valueOfCard == 0){
                        valueOfCard++;
                    } else {
                        valueOfCard *= 2;
                    }
                }
            }
        }
        points += valueOfCard;
    }

    
}
