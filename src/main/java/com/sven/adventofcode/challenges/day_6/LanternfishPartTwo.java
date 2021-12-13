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

            Map<Integer, BigInteger> fishPopulationMap = new HashMap<>();
            fishPopulationMap.put(8, new BigInteger("0"));
            fishPopulationMap.put(7, new BigInteger("0"));
            fishPopulationMap.put(6, new BigInteger("0"));
            fishPopulationMap.put(5, new BigInteger("0"));
            fishPopulationMap.put(4, new BigInteger("0"));
            fishPopulationMap.put(3, new BigInteger("0"));
            fishPopulationMap.put(2, new BigInteger("0"));
            fishPopulationMap.put(1, new BigInteger("0"));
            fishPopulationMap.put(0, new BigInteger("0"));

            for (String fish : fishesArray) {
                int daysleft = Integer.parseInt(fish);
                fishPopulationMap.put(daysleft, fishPopulationMap.get(daysleft).add(new BigInteger("1")));
            }

            int days = 256;
            totalAmountofFish = totalAmountofFish.add(calcTotalAmountOfFish(fishPopulationMap, days));

            System.out.println("Amount of fishes after " + days + " days: " + totalAmountofFish + " fish");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BigInteger calcTotalAmountOfFish(Map<Integer, BigInteger> dayBeforePopulation, int days) {

        for (int i = 1; i <= days; i++) {

            Map<Integer, BigInteger> nextDayPopulationMap = new HashMap<Integer, BigInteger>();
           
            nextDayPopulationMap.put(8, dayBeforePopulation.get(0));
            nextDayPopulationMap.put(7, dayBeforePopulation.get(8));
            nextDayPopulationMap.put(6, dayBeforePopulation.get(7).add(dayBeforePopulation.get(0)));
            nextDayPopulationMap.put(5, dayBeforePopulation.get(6));
            nextDayPopulationMap.put(4, dayBeforePopulation.get(5));
            nextDayPopulationMap.put(3, dayBeforePopulation.get(4));
            nextDayPopulationMap.put(2, dayBeforePopulation.get(3));
            nextDayPopulationMap.put(1, dayBeforePopulation.get(2));
            nextDayPopulationMap.put(0, dayBeforePopulation.get(1));

            dayBeforePopulation = nextDayPopulationMap;
        }

        BigInteger amountOfFish = new BigInteger("0");

        for (var entry : dayBeforePopulation.entrySet()) {
            amountOfFish = amountOfFish.add(entry.getValue());
        }

        return amountOfFish;
    }
}
