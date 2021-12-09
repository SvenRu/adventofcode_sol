package com.sven.adventofcode.challenges.day_6;

import java.io.IOException;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

import com.sven.adventofcode.utils.ChallengeInputReader;

public class Lanternfish {

    public static void main(String[] args) {
        try {
            List<String> inputList = ChallengeInputReader.readFileContentasList("lanternfish_input.txt");
            
            String[] fishesArray = inputList.get(0).split(",");

            List<Integer> fishesList =  new ArrayList<>();
            
            for (String fish : fishesArray){
                fishesList.add(Integer.parseInt(fish));
            }

            int days = 200;

            System.out.println("Amount of fishes after "+ days +" days: " + calcReceration(fishesList, days));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int calcReceration(List<Integer> fishesList, int days){

        //System.out.println("Inital state: " + Arrays.toString(fishesList.toArray()));

        for(int i = 0; i < days; i++){
            for(int j = 0; j < fishesList.size(); j++){
                
                int daysleft = fishesList.get(j);

                if(daysleft == 0){
                    daysleft = 6;
                    fishesList.add(9);
                } else {
                    daysleft--;
                }
                
                fishesList.set(j, daysleft);
            }

            //System.out.println("After day " + i + ": " + Arrays.toString(fishesList.toArray()));
        }

        return fishesList.size();
    }
    
}
