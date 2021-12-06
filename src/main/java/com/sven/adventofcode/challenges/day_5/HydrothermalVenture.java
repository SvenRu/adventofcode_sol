package com.sven.adventofcode.challenges.day_5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sven.adventofcode.utils.ChallengeInputReader;

public class HydrothermalVenture {

    static int countOverlappingLines = 0;

    static List<String> firstCoordinates = new ArrayList<String>();
    static List<String> secondCoordinates = new ArrayList<String>();

    public static void main(String[] args) {

        try {
            List<String> inputList = ChallengeInputReader.readFileContentasList("hydrothermalventure_input.txt");
            setCoordinates(inputList);
            HashMap<String, Integer> coordinates = getAllCoordinates();
            coordinates.forEach(
                (k, v) -> {
                    if (v > 1){
                        countOverlappingLines++;
                    }
                }
            );
            System.out.println("Overlapping lines count: " + countOverlappingLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public static HashMap<String, Integer> getAllCoordinates(){
            HashMap<String, Integer> coordinates = new HashMap<String, Integer>();

            for(int i = 0; i < firstCoordinates.size(); i++){

                String startCoordinate = firstCoordinates.get(i);
                String endCoordinate = secondCoordinates.get(i);

                if(coordinates.containsKey(startCoordinate)){
                    coordinates.put(startCoordinate, coordinates.get(startCoordinate) + 1);
                } else {
                    coordinates.put(startCoordinate, 1);
                }
                if(coordinates.containsKey(endCoordinate)){
                    coordinates.put(endCoordinate, coordinates.get(endCoordinate) + 1);
                } else {
                    coordinates.put(endCoordinate, 1);
                }
                calcConnectingCoordinates(startCoordinate, endCoordinate);
            }
            return coordinates;
    }

    private static void calcConnectingCoordinates(String startCoordinate, String endCoordinate) {
        
        List<String> connectingCoordinates = new ArrayList<String>();

        String[] startCoordinates = startCoordinate.split(",");
        String[] endCoordinates = endCoordinate.split(",");

        String x1 = startCoordinates[0];
        String y1 = startCoordinates[1];

        String x2 = endCoordinates[0];
        String y2 = endCoordinates[1];

        

        // find all the coordinates and add to coordinates

    
    }

    public static void setCoordinates(List<String> inputList){
        for(int i = 0; i < inputList.size(); i++){
        
        String line = inputList.get(i);
        String[] parts = line.split(" -> ");
        
        String firstCoordinate = parts[0].trim();
        String secondCoordonate = parts[1].trim();

        firstCoordinates.add(firstCoordinate);
        secondCoordinates.add(secondCoordonate);

        }
    }
    
}
