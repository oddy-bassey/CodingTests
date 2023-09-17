package amazon;

import java.util.*;

public class RunningMedian {


    public static void main(String[] args) {
        runningMedian(Arrays.asList(10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        for (Double aDouble : runningMedian(Arrays.asList(7, 3, 5, 2))) {
            System.out.print(aDouble+" ");
        }
    }

    public static List<Double> runningMedian(List<Integer> a) {
        // Write your code here
        List<Double> medains = new ArrayList<>();

        PriorityQueue<Integer> lowerNumbers = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return -1 * a.compareTo(b);
            }
        });
        PriorityQueue<Integer> higherNumbers = new PriorityQueue<>();

        for (Integer value : a) {
            addInteger(value, lowerNumbers, higherNumbers);
            balanceQueue(lowerNumbers, higherNumbers);
            medains.add(getMedian(lowerNumbers, higherNumbers));
        }

        return medains;
    }

    private static void addInteger(Integer value, PriorityQueue<Integer> lowerNumbers, PriorityQueue<Integer> higherNumbers) {
        if(lowerNumbers.isEmpty() || value < lowerNumbers.peek()) {
            lowerNumbers.add(value);
        } else {
            higherNumbers.add(value);
        }
    }

    private static void balanceQueue(PriorityQueue<Integer> lowerNumbers, PriorityQueue<Integer> higherNumbers) {
        PriorityQueue<Integer> largerQueue = lowerNumbers.size() > higherNumbers.size()? lowerNumbers : higherNumbers;
        PriorityQueue<Integer> smallerQueue = lowerNumbers.size() > higherNumbers.size()? higherNumbers : lowerNumbers;

        if(largerQueue.size() - smallerQueue.size() >= 2){
            smallerQueue.add(largerQueue.poll());
        }
    }

    private static double getMedian(PriorityQueue<Integer> lowerNumbers, PriorityQueue<Integer> higherNumbers) {
        PriorityQueue<Integer> largerQueue = lowerNumbers.size() > higherNumbers.size()? lowerNumbers : higherNumbers;
        PriorityQueue<Integer> smallerQueue = lowerNumbers.size() > higherNumbers.size()? higherNumbers : lowerNumbers;

        if(largerQueue.size() == smallerQueue.size()) {
            return ((double) largerQueue.peek() + smallerQueue.peek()) / 2;
        } else {
            return largerQueue.peek();
        }
    }
}
