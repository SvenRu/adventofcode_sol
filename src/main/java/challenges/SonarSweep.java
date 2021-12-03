package challenges;

import java.io.IOException;
import java.util.List;

import utils.ChallengeInputReader;

public class SonarSweep {

    static int increasedCount = 0;
    static int decreasedCount = 0;

    public static void main(String[] args) {

        try {
            List<String> resultList = ChallengeInputReader.readFileConentasList("sonarsweep_input.txt");
            SonarSweep.solve(resultList);

        } catch (IOException e) {
            e.printStackTrace();
        }   

    }

    public static void solve(List<String> measurements){

        int lastValue = -1;
        for(String s : measurements){
            int currentValue = Integer.parseInt(s);

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

        System.out.println("Increased count: "+ SonarSweep.increasedCount);
        System.out.println("Decreased count: "+ SonarSweep.decreasedCount);   

    }
}
