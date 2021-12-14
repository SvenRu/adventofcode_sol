package com.sven.adventofcode.challenges.day_7;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import com.sven.adventofcode.utils.ChallengeInputReader;

public class TheTreacheryofWhalesPartTwo {
    public static int totalFuel = 0;

    public static void main(String[] args) {
        
        try {
            String crapsPositions = ChallengeInputReader.readFileContentasList("crapHorizontalPosition_input.txt").get(0);
            List<String> horizontalPositions = Arrays.asList(crapsPositions.split(","));

            List<Double> crappHosPositions = horizontalPositions.stream().map(Double::parseDouble).collect(Collectors.toList());
            double median = getMean(crappHosPositions); 
            System.out.println("Mean: " + median);
            for (Double hpos : crappHosPositions) {

                int hops = 0;

                if(median < hpos) {
                    hops = (int) (hpos - median);
                } else {
                    hops = (int) (median - hpos);
                }

                totalFuel+=burnedFuel(hops, median);
            }

            System.out.println("Amount of fuel spend: " + totalFuel);

        } catch (IOException e) {
            e.printStackTrace();
        }  
    }  

    public static double burnedFuel(int hops, double median){

        int fuel = 0;
        int fuelToAdd = 1;

        for (int i = 0; i < hops; i++) {
            fuel+=fuelToAdd;
            fuelToAdd++;
        }
        return fuel;
    }
    
    public static double getMean(List<Double> horizontalPos){

        Collections.sort(horizontalPos);
        double sum = 0;
        for (Double pos : horizontalPos) {
            sum+=pos;
        }

        return sum/horizontalPos.size();
    }
}
