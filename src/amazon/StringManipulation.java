package amazon;

import java.util.Stack;

public class StringManipulation {

    public static void main(String[] args) {
        System.out.println(isBalanced("{{[[(())]]}}"));
    }
    public static String isBalanced(String s) {
        // Write your code here
        Stack<Character> stack = new Stack<>();

        for(char c: s.toCharArray()) {
            if (c == '{' || c == '(' || c == '[') {
                stack.add(c);
            } else {
                if(stack.isEmpty()) return "NO";

                char openBracket = stack.pop();
                if (openBracket == '{' && c != '}' || openBracket == '(' && c!= ')' ||
                        openBracket == '[' && c != ']') {
                    return "NO";
                }
            }
        }

        if(stack.isEmpty()) return "YES";

        return "NO";
    }
}
