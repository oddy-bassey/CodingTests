package mobilise.tempertaure;

import java.io.PrintStream;
import java.util.Scanner;

public class LowestTemperature {

    public static int computeClosestToZero(int[] ts) {
        // Write your code here
        // To debug: System.err.println("Debug messages...");

        if(ts.length == 0) return 0;

        boolean isNegative = false;
        int lowestTemp = Integer.MAX_VALUE;
        int lowestValue = Integer.MAX_VALUE;

        for(Integer temp : ts) {
            int value = temp < 0? (-1*temp) : temp;

            if(value < lowestValue){
                lowestTemp = temp;
                isNegative = temp < 0;
                lowestValue = value;
            }
        }

        return lowestValue;
    }

    /* Ignore and do not change the code below */
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ts = new int[n];
        for (int i = 0; i < n; i++) {
            ts[i] = in.nextInt();
        }
        PrintStream outStream = System.out;
        System.setOut(System.err);
        int solution = computeClosestToZero(ts);
        System.setOut(outStream);
        System.out.println(solution);
    }
}
