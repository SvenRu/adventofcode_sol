package com.sven.adventofcode.challenges.day_5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sven.adventofcode.utils.ChallengeInputReader;

public class HydrothermalVenture {

    static int countOverlappingLines = 0;

    static int x_max = 0;
    static int y_max = 0;

    static List<Integer> x1List = new ArrayList<>();
    static List<Integer> y1List = new ArrayList<>();

    static List<Integer> x2List = new ArrayList<>();
    static List<Integer> y2List = new ArrayList<>();
    static Integer[][] diagram = null;

    public static void main(String[] args) {

        try {
            List<String> inputList = ChallengeInputReader.readFileContentasList("hydrothermalventure_input.txt");
            initCoordinates(inputList);
            fillDiagram();

            for (int i = 0; i < diagram.length; i++) {
                for (int j = 0; j < diagram[i].length; j++) {
                    if(diagram[i][j]>1){
                        countOverlappingLines++;
                    }
                }
            }

            System.out.println("Overlapping lines: "+countOverlappingLines);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fillDiagram() {

        diagram = new Integer[y_max + 1][x_max + 1];// row, column
        Arrays.stream(diagram).forEach(a -> Arrays.fill(a, 0));

        for (int i = 0; i < x1List.size(); i++) {
            int x1 = x1List.get(i);
            int y1 = y1List.get(i);

            int x2 = x2List.get(i);
            int y2 = y2List.get(i);

            if (x1 - x2 == 0) {
                if (y1 < y2) {
                    for (int j = y1; j < y2 + 1; j++) {
                        diagram[j][x1]++;
                    }
                } else {
                    for (int j = y2; j < y1 + 1; j++) {
                        diagram[j][x1]++;
                    }
                }
            }
            if (y1 - y2 == 0) {
                if (x1 < x2) {
                    for (int j = x1; j < x2 +1;  j++) {
                        diagram[y1][j]++;
                    }
                } else {
                    for (int j = x2; j < x1+1; j++) {
                        diagram[y1][j]++;
                    }
                }
            }
        }
    }

    public static void initCoordinates(List<String> inputList) {
        for (int i = 0; i < inputList.size(); i++) {

            String line = inputList.get(i);
            String[] parts = line.split(" -> ");

            String[] firstCoordinates = parts[0].trim().split(",");
            String[] secondCoordonates = parts[1].trim().split(",");

            int x1 = Integer.parseInt(firstCoordinates[0]);
            int y1 = Integer.parseInt(firstCoordinates[1]);
            x1List.add(x1);
            y1List.add(y1);

            int x2 = Integer.parseInt(secondCoordonates[0]);
            int y2 = Integer.parseInt(secondCoordonates[1]);
            x2List.add(x2);
            y2List.add(y2);

            if (x1 > x_max) {
                x_max = x1;
            }
            if (x2 > x_max) {
                x_max = x2;
            }
            if (y1 > y_max) {
                y_max = y1;
            }
            if (y2 > y_max) {
                y_max = y2;
            }
        }
    }

}
