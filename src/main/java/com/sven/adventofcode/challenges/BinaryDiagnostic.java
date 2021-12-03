package com.sven.adventofcode.challenges;

import java.io.IOException;
import java.util.List;

import com.sven.adventofcode.utils.ChallengeInputReader;

public class BinaryDiagnostic {

    public static void main(String[] args) { 
        try {
            List<String> report = ChallengeInputReader.readFileContentasList("binarydiagnostic_input.txt");
            solve(report);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void solve(List<String> report) {

        int maxPositions = report.get(0).length();
        StringBuilder gammaRate = new StringBuilder();
        StringBuilder epsilonRate = new StringBuilder();

        for(int bit = 0; bit < maxPositions; bit++){

            int onesCount = 0;
            int zerosCount = 0;

            for(String line : report){
                char number = line.charAt(bit);
                int input = Character.getNumericValue(number);

                if(1 == input){
                    onesCount++;
                } else {
                    zerosCount++;
                }
            }

            if(onesCount > zerosCount){
                gammaRate.append("1");
                epsilonRate.append("0");
            } else if (zerosCount > onesCount) {
                gammaRate.append("0");
                epsilonRate.append("1");
            }
            onesCount = 0;
            zerosCount = 0;
        }
        System.out.println("GammaRate: " + (Integer.parseInt(gammaRate.toString(),2)));
        System.out.println("EpsilonRate: " + (Integer.parseInt(epsilonRate.toString(),2)));

        int result = Integer.parseInt(gammaRate.toString(),2) * Integer.parseInt(epsilonRate.toString(),2);
        System.out.println("Solution: " + result);
    }

}
