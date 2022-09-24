import java.util.HashMap;
import java.util.Map;

public class SideroTest {
    public static void main(String [] args) {
        // you can write to stdout for debugging purposes, e.g.
        System.out.println(getMaxSum(new String[] {"51", "71", "17", "42"}));
    }

    public static int getMaxSum(String A[]){
        int maxSum = Integer.MIN_VALUE;
        boolean hasPair = false;
        Map<Integer, Integer> sumMap = new HashMap<>();

        if(A.length<2) return -1;

        for (String s : A) {
            char[] figures = s.toCharArray();
            int value = Integer.parseInt(s);
            int key = (int) figures[0] + (int) figures[1];

            if(!sumMap.containsKey(key)){
                sumMap.put(key, value);
            }else{
                hasPair = true;
                maxSum = Math.max(maxSum, sumMap.get(key)+value);
                sumMap.put(key, value);
            }
        }
        if(hasPair) return maxSum;

        return -1;
    }
}