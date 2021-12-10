package com.sven.adventofcode.challenges.day_6;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sven.adventofcode.utils.ChallengeInputReader;

public class LanternfishPartTwo {

    static BigInteger totalAmountofFish = new BigInteger("0");
    
    public static void main(String[] args) {
        try {
            List<String> inputList = ChallengeInputReader.readFileContentasList("lanternfish2_input.txt");
            String[] fishesArray = inputList.get(0).split(",");

            List<Integer> day_1_List = new ArrayList<>();
            List<Integer> day_2_List = new ArrayList<>();
            List<Integer> day_3_List = new ArrayList<>();
            List<Integer> day_4_List = new ArrayList<>();
            List<Integer> day_5_List = new ArrayList<>();

            for (String fish : fishesArray){
                int daysleft = Integer.parseInt(fish);

                if(daysleft == 1){
                    day_1_List.add(1);
                }
                if(daysleft == 2){
                    day_2_List.add(2);
                }
                if(daysleft == 3){
                    day_3_List.add(3);
                }
                if(daysleft == 4){
                    day_4_List.add(4);
                }
                if(daysleft == 5){
                    day_5_List.add(5);
                }
            }
            int days = 80; //5934

            totalAmountofFish = totalAmountofFish.add(calcTotalAmountOfFish(day_1_List, 1, days));
            totalAmountofFish = totalAmountofFish.add(calcTotalAmountOfFish(day_2_List, 2, days));
            totalAmountofFish = totalAmountofFish.add(calcTotalAmountOfFish(day_3_List, 3, days));
            totalAmountofFish = totalAmountofFish.add(calcTotalAmountOfFish(day_4_List, 4, days));
            totalAmountofFish = totalAmountofFish.add(calcTotalAmountOfFish(day_5_List, 5, days));

            System.out.println("Amount of fishes after "+ days +" days: " + totalAmountofFish + " fish");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BigInteger calcTotalAmountOfFish(List<Integer> day_List, int days_left, int days_end){
        
        if(day_List.isEmpty()){
            return new BigInteger("0");
        }

        int count_normal_fish = day_List.size();
        

        Map<Integer, BigInteger> days_fish_map = new HashMap<Integer, BigInteger>();
        days_fish_map.put(days_left, new BigInteger(""+count_normal_fish));

        for (int i = 0; i < days_end; i++) {

            Map<Integer, BigInteger> temp_map = new HashMap<Integer, BigInteger>();

            for (var entry : days_fish_map.entrySet()) {

                int days = entry.getKey();
                BigInteger amount_fish = entry.getValue();
                days--;

                if(days < 0){
                    temp_map.put(8, amount_fish);
                    if(temp_map.containsKey(6)){
                        temp_map.put(6, temp_map.get(6).add(amount_fish));
                    } else {
                        temp_map.put(6, amount_fish);
                    }
                } else {
                    BigInteger fish_to_add = new BigInteger(""+0);
                    if(days_fish_map.containsKey(days)){

                        fish_to_add = days_fish_map.get(days).add(amount_fish);
                    } else  {
                        fish_to_add = amount_fish;
                    }
                    if(temp_map.containsKey(days)){

                        temp_map.put(days, temp_map.get(days).add(fish_to_add));
                    } else {
                        temp_map.put(days, fish_to_add);
                    }
                }  
            }

            days_fish_map = temp_map;
        }

        BigInteger total_count_fish = new BigInteger(""+0);

        for (var entry : days_fish_map.entrySet()) {
            BigInteger v = entry.getValue();
            total_count_fish = total_count_fish.add(v);
        }

        return total_count_fish;
    }
}
