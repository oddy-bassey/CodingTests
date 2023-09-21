package hackerrank.minMaxSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'miniMaxSum' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void miniMaxSum(List<Integer> arr) {
        // Write your code here
        int minSUm = 0;
        int maxSum = 0;
        int sumCount = 0;

        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());

        minQueue.addAll(arr);
        maxQueue.addAll(arr);

        while (!minQueue.isEmpty()) {
            if(sumCount == 4) break;

            minSUm += minQueue.poll();
            sumCount++;
        }

        sumCount = 0;
        while (!maxQueue.isEmpty()) {
            if(sumCount == 4) break;

            maxSum += maxQueue.poll();
            sumCount++;
        }

        System.out.println(minSUm + "  " + maxSum);
    }

}

public class MinMaxSum {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        Result.miniMaxSum(arr);

        bufferedReader.close();
    }
}
