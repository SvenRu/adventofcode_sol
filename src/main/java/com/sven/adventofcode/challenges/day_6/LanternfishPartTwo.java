package com.sven.adventofcode.challenges.day_6;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;
import com.sven.adventofcode.utils.ChallengeInputReader;

public class LanternfishPartTwo {

    static int totalAmountofFish = 0;
    static HashMap<Integer, Integer> buffer = new HashMap<Integer, Integer>();
    
    public static void main(String[] args) {
        try {
            List<String> inputList = ChallengeInputReader.readFileContentasList("lanternfish2_input.txt");
            String[] fishesArray = inputList.get(0).split(",");

            List<Integer> fishesList =  new ArrayList<>();
            
            for (String fish : fishesArray){
                fishesList.add(Integer.parseInt(fish));
            }
            int days = 256;
            System.out.println("inital size: " + fishesList.size());
            //do first 128 days
            List<Integer> first_100_days_list = calcTotalAmountOfFish(fishesList, 128);
            
            //subset
            List<List<Integer>> subsets = Lists.partition(first_100_days_list, 10000);
            
            //do next 128 days
            System.out.println("subsets:" + subsets.size());
            for(List<Integer> subSet : subsets){
                totalAmountofFish+=calcTotalAmountOfFish(subSet, 128).size();
                System.out.println("subset done");
            }

            System.out.println("Amount of fishes after "+ days +" days: " + totalAmountofFish);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> calcTotalAmountOfFish(List<Integer> fishesList, int days){

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
        }

        return fishesList;
    }
}
