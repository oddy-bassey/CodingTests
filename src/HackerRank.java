import java.util.*;
import java.util.stream.Collectors;

public class HackerRank {

    public static void closestNumbers(List<Integer> numbers) {
        // Write your code here
        if (numbers.size()<2) return;

        int minDifference = Integer.MAX_VALUE;
        List<Integer> numberData = new ArrayList<>();
        List<String> pairs = new ArrayList<>();

        //convert list to set
        Set<Integer> numberSet = new HashSet<>();
        numberSet.addAll(numbers);

        for (Integer integer : numberSet.stream().sorted().collect(Collectors.toList())) {
            numberData.add(integer);
            int size = numberData.size();

            if(size>1){
                int i = numberData.get(size-2);
                int j = numberData.get(size-1);
                int min = j-i;
                if(min<minDifference){
                    minDifference = min;
                    pairs.clear();
                    pairs.add(i+","+j);
                }else if(min == minDifference){
                    pairs.add(i+","+j);
                }
            }
        }

        for (String pair : pairs) {
            String[] data = pair.split(",");
            System.out.println(data[0]+" "+data[1]);
        }
    }

    public static String palindromeChecker(String s, List<Integer> startIndex, List<Integer> endIndex, List<Integer> subs) {
        // Write your code here
        StringBuilder result = new StringBuilder();

        for(int i=0; i<startIndex.size(); i++){
            String subString = s.substring(startIndex.get(i), endIndex.get(i));
            int substitutions = subs.get(i);

            if(isPalyndrone(subString, substitutions)){
                result.append("1");
            }else {
                result.append("0");
            }
        }

        return result.toString();
    }

    static boolean isPalyndrone(String s, int subs) {

        String original = s;
        // Finding the length of the string
        int length = s.length();

        int changes = 0;

        for (int i = 0; i < length / 2; i++) {

            // If the characters at location
            // i and length-i-1 are same then
            // no change is required
            if (s.charAt(i) == s.charAt(length - i - 1))
                continue;

            // Counting one change operation
            changes += 1;
            if (changes>subs) return false;

            // Changing the character with higher
            // ascii value with lower ascii value
            if (s.charAt(i) < s.charAt(length - i - 1))
                s = s.replace(s.charAt(length - i - 1), s.charAt(i));
            else
                s = s.replace(s.charAt(length - 1), s.charAt(length - i - 1));
        }
        if(s.equals(original)){
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args){
        //closestNumbers(Arrays.asList(4,4,-2,-1,3));

    }
}

