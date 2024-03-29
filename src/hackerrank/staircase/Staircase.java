package hackerrank.staircase;

import java.io.*;

import static java.util.stream.Collectors.joining;

class Result {
    /*
     * Complete the 'staircase' function below.
     *
     * The function accepts INTEGER n as parameter.
     */

    public static void staircase(int n) {
        // Write your code here
        getStairs(n, 1, "");
    }

    static void getStairs(int n, int stepCount, String step) {
        if (stepCount > n) return;

        step = "";
        for(int i=1; i<=n-stepCount; i++) {
            step += " ";
        }
        for(int i=1; i<=stepCount; i++) {
            step += "#";
        }
        System.out.println(step);
        stepCount++;

        getStairs(n, stepCount, step);
    }

}

public class Staircase {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        Result.staircase(n);

        bufferedReader.close();
    }
}
