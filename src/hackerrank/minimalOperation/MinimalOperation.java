package hackerrank.minimalOperation;

import java.io.IOException;
import java.util.*;

public class MinimalOperation {

    /*
     * Complete the 'minimalOperations' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts STRING_ARRAY words as parameter.
     */

    public static List<Integer> minimalOperations(List<String> words) {
        int minimalOperation = 0;
        List<Integer> minimalOperations = new ArrayList<>();
        Queue<Character> characters = new LinkedList<>();

        for(String word : words) {
            if (!characters.isEmpty()) characters.clear();
            minimalOperation = 0;

            for (char c : word.toCharArray()) {
                characters.add(c);
            }

            Character previousCharacter = ' ';
            for (Character character : characters) {
                if(character.equals(previousCharacter)) {
                    minimalOperation++;
                    previousCharacter = ' ';
                }else {
                    previousCharacter = character;
                }
            }
            minimalOperations.add(minimalOperation);
        }

        return minimalOperations;
    }
    public static void main(String[] args) throws IOException {
        // do nothing
        minimalOperations(Arrays.asList("ab", "aab", "abb", "abab", "abaaaba")).forEach(System.out::println);
    }
}
