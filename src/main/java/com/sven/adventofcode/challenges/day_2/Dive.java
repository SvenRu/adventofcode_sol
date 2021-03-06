package com.sven.adventofcode.challenges.day_2;

import java.io.IOException;
import java.util.List;

import com.sven.adventofcode.utils.ChallengeInputReader;

public class Dive {

    static int horizontalPos = 0;
    static int depthPos = 0; 

    public static void main(String[] args) {
        
        try {
            List<String> instructionsList = ChallengeInputReader.readFileContentasList("dive_input.txt");
            solve(instructionsList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void solve(List<String> instructions){

        for(String instruction : instructions){
            if (instruction.contains("forward")) {
                horizontalPos+=getNav(instruction);
            }
            if (instruction.contains("down")) {
                depthPos+=getNav(instruction);
            }
            if (instruction.contains("up")) {
                depthPos-=getNav(instruction);
            }
        }

        int result = horizontalPos * depthPos;
        System.out.println("Final depth: " + result);
    }

    public static int getNav(String instruction){
        return Integer.parseInt(instruction.replaceAll("\\D+",""));
    }
    
}
