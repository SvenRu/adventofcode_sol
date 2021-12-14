package com.sven.adventofcode.challenges.day_7;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import com.sven.adventofcode.utils.ChallengeInputReader;

public class TheTreacheryofWhales {

    public static double totalFuel = 0;

    public static void main(String[] args) {
        
        try {
            String crapsPositions = ChallengeInputReader.readFileContentasList("crapHorizontalPosition_input.txt").get(0);
            List<String> horizontalPositions = Arrays.asList(crapsPositions.split(","));

            List<Double> crappHosPositions = horizontalPositions.stream().map(Double::parseDouble).collect(Collectors.toList());
            double median = getMedian(crappHosPositions); 

            for (Double hpos : crappHosPositions) {
                if(median > hpos){
                    totalFuel+=(median-hpos);
                } else {
                    totalFuel+=(hpos-median);
                }
            }

            System.out.println("Amount of fuel spend: " + totalFuel);

        } catch (IOException e) {
            e.printStackTrace();
        }  
    }  
    
    public static double getMedian(List<Double> horizontalPos){ //TODO:

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
