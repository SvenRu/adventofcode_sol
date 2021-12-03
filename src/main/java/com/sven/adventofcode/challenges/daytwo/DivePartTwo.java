package com.sven.adventofcode.challenges.daytwo;

import java.io.IOException;
import java.util.List;

import com.sven.adventofcode.utils.ChallengeInputReader;

public class DivePartTwo {
    
    static int horizontalPos = 0;
    static int depthPos = 0; 

    static int aim = 0;

    public static void main(String[] args) {
        
        try {
            List<String> instructionsList = ChallengeInputReader.readFileContentasList("dive2_input.txt");
            solve(instructionsList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void solve(List<String> instructions){

        for(String instruction : instructions){
            if (instruction.contains("forward")) {
                horizontalPos+=getNav(instruction);
                depthPos = depthPos + aim * getNav(instruction);
            }
            if (instruction.contains("down")) {
                aim+=getNav(instruction);
            }
            if (instruction.contains("up")) {
                aim-=getNav(instruction);
            }
        }

        int result = horizontalPos * depthPos;
        System.out.println("Final depth: " + result);
    }

    public static int getNav(String instruction){
        return Integer.parseInt(instruction.replaceAll("\\D+",""));
    }
    


}
