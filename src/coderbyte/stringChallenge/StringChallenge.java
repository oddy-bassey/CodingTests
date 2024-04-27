package coderbyte.stringChallenge;

import java.util.HashMap;
import java.util.Map;

public class StringChallenge {
    public static String StringChallenge(String str) {
        // create variables to hold the result and a map of token characters to be removed from the result
        StringBuilder result = new StringBuilder();
        final String challengeToken = "j2p75643f";
        Map<Character, Character> tokenMap = new HashMap<>();

        // fill the map with the token characters
        for(char c : challengeToken.toCharArray()) {
            tokenMap.put(c, c);
        }

        // reverse the string and also remove characters appearing in the token map
        for(int i=str.length()-1; i>=0; i--) {
            char currentChar = str.charAt(i);
            // check if the character is in the map, if yes then dont add else add it to the result
            if (tokenMap.get(currentChar) == null) {
                result.append(currentChar);
            }
        }

        return result.toString().isEmpty()? "EMPTY": result.toString();
    }

    public static void main (String[] args) {
        System.out.println(StringChallenge("coderbyte"));
        System.out.println(StringChallenge("I Love Code"));
    }
}
