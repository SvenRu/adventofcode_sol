package com.sven.adventofcode.challenges.day_7;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.sven.adventofcode.utils.ChallengeInputReader;

public class TheTreacheryofWhalesPartTwo {

    public static void main(String[] args) {
        
        try {
            String crapsPositions = ChallengeInputReader.readFileContentasList("crapHorizontalPosition_input.txt").get(0);
            List<String> horizontalPositions = Arrays.asList(crapsPositions.split(","));

            List<Double> crappHosPositions = horizontalPositions.stream().map(Double::parseDouble).collect(Collectors.toList());
            
            double median = getMedian(crappHosPositions); 
            
            int perfectMinFuel = 100000000;

            for(double i = median; i < 800; i++){//super ugly solution
                
                int fuel = getFuelConsumption(crappHosPositions, i);

                if(fuel < perfectMinFuel){
                    perfectMinFuel = fuel;
                }
            }

            System.out.println("Amount of fuel spend: " + perfectMinFuel);

        } catch (IOException e) {
            e.printStackTrace();
        }  
    } 


    public static int getFuelConsumption(List<Double> crappHosPositions, double median){

        int totalFuel = 0;

        for (Double hpos : crappHosPositions) {

            int hops = 0;

            if(median < hpos) {
                hops = (int) (hpos - median);
            } else {
                hops = (int) (median - hpos);
            }

            totalFuel+=burnedFuel(hops);
        }

        return totalFuel;
    }

    public static double burnedFuel(int hops){
        
        int fuel = 0;
        int fuelToAdd = 1;

        for (int i = 0; i < hops; i++) {
            fuel+=fuelToAdd;
            fuelToAdd++;
        }
        return fuel;
    }
    
    public static double getMedian(List<Double> horizontalPos){

        Collections.sort(horizontalPos);

        if (horizontalPos.size() % 2 == 1){
        return horizontalPos.get((horizontalPos.size() + 1) / 2 - 1);
        } else {
        
        double m1 = horizontalPos.get(horizontalPos.size() / 2 - 1);
        double m2 = horizontalPos.get(horizontalPos.size() / 2);

        return (m1 + m2) / 2.0;
        }
    }
}
