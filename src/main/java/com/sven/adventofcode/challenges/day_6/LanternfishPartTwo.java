package com.sven.adventofcode.challenges.day_6;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sven.adventofcode.utils.ChallengeInputReader;

public class LanternfishPartTwo {

    static BigInteger totalAmountofFish = new BigInteger("0");

    public static void main(String[] args) {
        try {
            List<String> inputList = ChallengeInputReader.readFileContentasList("lanternfish_input.txt");
            String[] fishesArray = inputList.get(0).split(",");

            Map<Integer, BigInteger> fishPopulation = new HashMap<>();
            fishPopulation.put(8, new BigInteger("0"));
            fishPopulation.put(7, new BigInteger("0"));
            fishPopulation.put(6, new BigInteger("0"));
            fishPopulation.put(5, new BigInteger("0"));
            fishPopulation.put(4, new BigInteger("0"));
            fishPopulation.put(3, new BigInteger("0"));
            fishPopulation.put(2, new BigInteger("0"));
            fishPopulation.put(1, new BigInteger("0"));
            fishPopulation.put(0, new BigInteger("0"));

            for (String fish : fishesArray) {
                int daysleft = Integer.parseInt(fish);
                fishPopulation.put(daysleft, fishPopulation.get(daysleft).add(new BigInteger("1")));
            }

            int days = 256;
            totalAmountofFish = totalAmountofFish.add(calcTotalAmountOfFish(fishPopulation, days));

            System.out.println("Amount of fishes after " + days + " days: " + totalAmountofFish + " fish");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BigInteger calcTotalAmountOfFish(Map<Integer, BigInteger> dayBeforePopulation, int days) {

        for (int i = 1; i <= days; i++) {

            Map<Integer, BigInteger> nextDayPopulation = new HashMap<Integer, BigInteger>();
           
            nextDayPopulation.put(8, dayBeforePopulation.get(0));
            nextDayPopulation.put(7, dayBeforePopulation.get(8));
            nextDayPopulation.put(6, dayBeforePopulation.get(7).add(dayBeforePopulation.get(0)));
            nextDayPopulation.put(5, dayBeforePopulation.get(6));
            nextDayPopulation.put(4, dayBeforePopulation.get(5));
            nextDayPopulation.put(3, dayBeforePopulation.get(4));
            nextDayPopulation.put(2, dayBeforePopulation.get(3));
            nextDayPopulation.put(1, dayBeforePopulation.get(2));
            nextDayPopulation.put(0, dayBeforePopulation.get(1));

            dayBeforePopulation = nextDayPopulation;
        }

        BigInteger amountOfFish = new BigInteger("0");

        for (var entry : dayBeforePopulation.entrySet()) {
            amountOfFish = amountOfFish.add(entry.getValue());
        }

        return amountOfFish;
    }
}
