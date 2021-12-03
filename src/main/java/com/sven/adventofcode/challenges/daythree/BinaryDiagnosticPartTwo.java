package com.sven.adventofcode.challenges.daythree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sven.adventofcode.utils.ChallengeInputReader;

public class BinaryDiagnosticPartTwo {

    static int onesCount = 0;
    static int zerosCount = 0;

    public static void main(String[] args) {
        try {
            List<String> report = ChallengeInputReader.readFileContentasList("binarydiagnostic2_input.txt");

            String oxygenGeneratorRating = getOxygenGeneratorRating(report);
            String co2ScrubberRating = getCo2ScrubberRating(report);

            System.out.println("oxygenGeneratorRating: " + (Integer.parseInt(oxygenGeneratorRating.toString(), 2)));
            System.out.println("co2ScrubberRating: " + (Integer.parseInt(co2ScrubberRating.toString(), 2)));

            int lifeSupportRating = Integer.parseInt(oxygenGeneratorRating.toString(), 2)
                    * Integer.parseInt(co2ScrubberRating.toString(), 2);
            System.out.println("lifeSupportRating: " + lifeSupportRating);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getOxygenGeneratorRating(List<String> report) {

        int maxPositions = report.get(0).length();
        List<String> reportList = report;

        for (int pos = 0; pos < maxPositions; pos++) {

            if (reportList.size() == 1) {
                return reportList.get(0);
            }

            getACount(reportList, pos);

            if (onesCount > zerosCount || zerosCount == onesCount) {
                reportList = cleanUpReport(1, reportList, pos);
            } else if (zerosCount > onesCount) {
                reportList = cleanUpReport(0, reportList, pos);
            }
        }

        return reportList.get(0);
    }

    private static String getCo2ScrubberRating(List<String> report) {

        int maxPositions = report.get(0).length();
        List<String> reportList = report;

        for (int pos = 0; pos < maxPositions; pos++) {

            if (reportList.size() == 1) {
                return reportList.get(0);
            }

            getACount(reportList, pos);

            if (onesCount > zerosCount || zerosCount == onesCount) {
                reportList = cleanUpReport(0, reportList, pos);
            } else if (zerosCount > onesCount) {
                reportList = cleanUpReport(1, reportList, pos);
            }

        }

        return reportList.get(0);
    }

    public static void getACount(List<String> reportList, int pos) {
        for (String line : reportList) {
            char number = line.charAt(pos);
            int input = Character.getNumericValue(number);

            if (1 == input) {
                onesCount++;
            } else {
                zerosCount++;
            }
        }
    }

    public static List<String> cleanUpReport(int numberToKeep, List<String> report, int pos) {

        List<String> cleanedReportList = new ArrayList<>();

        for (String n : report) {
            int currentNumber = Character.getNumericValue(n.charAt(pos));
            if (currentNumber == numberToKeep) {
                cleanedReportList.add(n);
            }
        }

        onesCount = 0;
        zerosCount = 0;

        return cleanedReportList;
    }

}
