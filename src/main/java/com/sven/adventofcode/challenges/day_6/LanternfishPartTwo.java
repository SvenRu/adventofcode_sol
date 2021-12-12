package com.sven.adventofcode.challenges.day_6;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sven.adventofcode.utils.ChallengeInputReader;

public class LanternfishPartTwo {

    static int totalAmountofFish = 0;

    public static void main(String[] args) {
        try {
            List<String> inputList = ChallengeInputReader.readFileContentasList("lanternfish_input.txt");
            String[] fishesArray = inputList.get(0).split(",");

            Map<Integer, Integer> fishPopulationMap = new HashMap<>();
            fishPopulationMap.put(8, 0);
            fishPopulationMap.put(7, 0);
            fishPopulationMap.put(6, 0);
            fishPopulationMap.put(5, 0);
            fishPopulationMap.put(4, 0);
            fishPopulationMap.put(3, 0);
            fishPopulationMap.put(2, 0);
            fishPopulationMap.put(1, 0);
            fishPopulationMap.put(0, 0);

            for (String fish : fishesArray) {
                int daysleft = Integer.parseInt(fish);
                fishPopulationMap.put(daysleft, fishPopulationMap.get(daysleft)+1);
            }

            int days = 80;
            totalAmountofFish += calcTotalAmountOfFish(fishPopulationMap, days);

            System.out.println("Amount of fishes after " + days + " days: " + totalAmountofFish + " fish");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int calcTotalAmountOfFish(Map<Integer, Integer> dayBeforefishPopulation, int days) {

        for (int i = 1; i <= days; i++) {

            Map<Integer, Integer> nextDayPopulationMap = new HashMap<Integer, Integer>();
           
            nextDayPopulationMap.put(8, dayBeforefishPopulation.get(0));
            nextDayPopulationMap.put(7, dayBeforefishPopulation.get(8));
            nextDayPopulationMap.put(6, dayBeforefishPopulation.get(7) + dayBeforefishPopulation.get(0));
            nextDayPopulationMap.put(5, dayBeforefishPopulation.get(6));
            nextDayPopulationMap.put(4, dayBeforefishPopulation.get(5));
            nextDayPopulationMap.put(3, dayBeforefishPopulation.get(4));
            nextDayPopulationMap.put(2, dayBeforefishPopulation.get(3));
            nextDayPopulationMap.put(1, dayBeforefishPopulation.get(2));
            nextDayPopulationMap.put(0, dayBeforefishPopulation.get(1));

            dayBeforefishPopulation = nextDayPopulationMap;
        }

        int amountOfFish = 0;

        for (var entry : dayBeforefishPopulation.entrySet()) {
            amountOfFish = amountOfFish+entry.getValue();
        }

        return amountOfFish;
    }
}
