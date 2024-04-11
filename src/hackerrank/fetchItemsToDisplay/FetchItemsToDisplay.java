package hackerrank.fetchItemsToDisplay;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FetchItemsToDisplay {


    /*
     * Complete the 'fetchItemsToDisplay' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. 2D_STRING_ARRAY items
     *  2. INTEGER sortParameter
     *  3. INTEGER sortOrder
     *  4. INTEGER X
     */

    public static List<String> fetchItemsToDisplay(List<List<String>> items, int sortParameter, int sortOrder, int X) {
        List<String> result = new ArrayList<>();

        if(items.isEmpty()) return result;
        if(items.get(0).get(sortParameter) == null) return result;

        items.stream()
                .map(item -> item.get(sortParameter))
                .sorted(sortOrder == 0? Comparator.naturalOrder() : Comparator.reverseOrder())
                .collect(Collectors.toList()).forEach(item -> {
                    if(result.size() < X) {
                        result.add(item);
                    }
                });

        return result;
    }

    public static int palindrome(String s) {
        char[] characters = s.toCharArray();
        int numberOfPalins = 0;
        Map<String, String> charSet = new HashMap();

        for (int i = 0; i < characters.length; i++) {
            StringBuilder word = new StringBuilder(characters[i]);

            if(charSet.get(word.toString()) == null) {
                if(isPalindrome(word.toString())) numberOfPalins++;
                charSet.put(word.toString(), word.toString());
            }

            for (int j = i + 1; j < characters.length; j++) {
                word.append(characters[j]);
                if (isPalindrome(word.toString())) numberOfPalins++;
            }
        }

        return numberOfPalins;
    }

    public static boolean isPalindrome(String str)
    {
        String rev = "";
        boolean ans = false;

        for (int i = str.length() - 1; i >= 0; i--) {
            rev = rev + str.charAt(i);
        }

        if (str.equals(rev)) {
            ans = true;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
       // minimalOperations().forEach(System.out::println);
        System.out.println(palindrome("mokkori"));
    }
}
