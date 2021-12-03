package challenges;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.ChallengeInputReader;

public class SonarSweepPartTwo {
    
    static int increasedCount = 0;
    static int decreasedCount = 0;

    public static void main(String[] args) {

        try {
            SonarSweepPartTwo.solve(SonarSweepPartTwo.getCleanedData());
        } catch (IOException e) {
            e.printStackTrace();
        }   

    }

    public static List<Integer> getCleanedData() throws IOException{
        
        List<Integer> resultList = new ArrayList<>();

        List<String> sonarDataList = ChallengeInputReader.readFileConentasList("sonarsweep_input.txt");

        for(int i = 0; i < sonarDataList.size()-2; i++){

            int firstValue = Integer.parseInt(sonarDataList.get(i));
            int secondValue = Integer.parseInt(sonarDataList.get(i + 1));
            int thirdValue = Integer.parseInt(sonarDataList.get(i + 2));

            int result = firstValue + secondValue + thirdValue;
            resultList.add(result);
        }
        return resultList;
    }

    public static void solve(List<Integer> measurements){

        int lastValue = -1;
        for(Integer currentValue : measurements){
            if(lastValue == -1){
                lastValue = currentValue;
            } else if (currentValue > lastValue) {
                increasedCount++;
                lastValue = currentValue;
            } else if (currentValue < lastValue) {
                decreasedCount++;
                lastValue = currentValue;
            }
        }

        System.out.println("Increased count: "+ SonarSweepPartTwo.increasedCount);
        System.out.println("Decreased count: "+ SonarSweepPartTwo.decreasedCount);   

    }


}