package com.sven.adventofcode.day_1;

import java.io.IOException;
import java.util.List;

import com.sven.adventofcode.utils.ChallengeHelper;

public class Trebuchet {

    static int result = 0;

    // read file day1_1.txt file to array
    public static void processInputFile_day1_1() throws IOException {
        // read file
        List<String> inputfile = ChallengeHelper.readFileContentasList("day1_1.txt");
        // iterate
        for (String line : inputfile) {
            processLine(line);
        }
    }

    // read file day1_1.txt file to array
    public static void processInputFile_day1_2() throws IOException {
        // read file
        List<String> inputfile = ChallengeHelper.readFileContentasList("day1_2.txt");
        // iterate
        for (String line : inputfile) {
            String cleanedLString = replaceCharactersByCorrespondingNumber(line);
            processLine(cleanedLString);
        }
    }

    private static String replaceCharactersByCorrespondingNumber(String line) {
        // line as char array
        String[] lineArray = line.split("");
        String resultString = "";
        String possibleNumberAsWordString = "";

        // iterate over every char of lineArray backwards (actually not necessary, we
        // don't have something like sixteen in the input file)
        for (int i = lineArray.length - 1; i >= 0; i--) {
            // check if numeric and add to array
            if (isNumeric(lineArray[i])) {
                resultString = lineArray[i] + resultString;
                possibleNumberAsWordString = "";
            } else {
                possibleNumberAsWordString = lineArray[i] + possibleNumberAsWordString;
                // check string could be a number from 20 to 1
                if (possibleNumberAsWordString.contains("ten")) {
                    resultString = "10" + resultString;
                    possibleNumberAsWordString = "";
                    i++; //numbers might overlap
                } else if (possibleNumberAsWordString.contains("nine")) {
                    resultString = "9" + resultString;
                    possibleNumberAsWordString = "";
                    i++;
                } else if (possibleNumberAsWordString.contains("eight")) {
                    resultString = "8" + resultString;
                    possibleNumberAsWordString = "";
                    i++;
                } else if (possibleNumberAsWordString.contains("seven")) {
                    resultString = "7" + resultString;
                    possibleNumberAsWordString = "";
                    i++;
                } else if (possibleNumberAsWordString.contains("six")) {
                    resultString = "6" + resultString;
                    possibleNumberAsWordString = "";
                    i++;
                } else if (possibleNumberAsWordString.contains("five")) {
                    resultString = "5" + resultString;
                    possibleNumberAsWordString = "";
                    i++;
                } else if (possibleNumberAsWordString.contains("four")) {
                    resultString = "4" + resultString;
                    possibleNumberAsWordString = "";
                    i++;
                } else if (possibleNumberAsWordString.contains("three")) {
                    resultString = "3" + resultString;
                    possibleNumberAsWordString = "";
                    i++;
                } else if (possibleNumberAsWordString.contains("two")) {
                    resultString = "2" + resultString;
                    possibleNumberAsWordString = "";
                    i++;
                } else if (possibleNumberAsWordString.contains("one")) {
                    resultString = "1" + resultString;
                    possibleNumberAsWordString = "";
                    i++;
                } else if (possibleNumberAsWordString.contains("zero")) {
                    resultString = "0" + resultString;
                    possibleNumberAsWordString = "";
                    i++;
                }
            }
        }

        return resultString;
    }

    // method to to process line
    public static void processLine(String line) {
        // line as char array
        String[] lineArray = line.split("");

        // number string
        String resultString = "";

        // iterate
        for (String lineElement : lineArray) {
            // check if numeric and add to array
            if (isNumeric(lineElement)) {
                // add to array
                resultString = resultString + lineElement;
            }
        }

        // get first and last char of resultString
        char firstChar = resultString.charAt(0);
        char lastChar = resultString.charAt(resultString.length() - 1);

        // add firstChar and LastChar to result as int
        result = result + Integer.parseInt(String.valueOf(firstChar) + String.valueOf(lastChar));
    }

    // check if lineElement is numeric using regex
    private static boolean isNumeric(String lineElement) {
        return lineElement.matches("-?\\d+(\\.\\d+)?");
    }

    public static void main(String[] args) {
        try {
            Trebuchet.processInputFile_day1_1();
            System.out.println(Trebuchet.result);
            Trebuchet.result = 0;
            Trebuchet.processInputFile_day1_2();
            System.out.println(Trebuchet.result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
