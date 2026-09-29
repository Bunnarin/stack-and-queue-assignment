public class DelimiterChecker {
    // Time complexity: O(n)
    public static boolean check(String input) {
        if (input == null) {
            return false;
        }
        Stack<Character> stack = new Stack<>(Math.max(1, input.length()));
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                Character open = stack.pop();
                if (open == null || !matches(open, c)) {
                    return false;
                }
            }
        }
        return stack.peek() == null; // true only if no unmatched openers remain
    }

    // Time complexity: O(1)
    private static boolean matches(char open, char close) {
        return (open == '(' && close == ')')
            || (open == '[' && close == ']')
            || (open == '{' && close == '}');
    }
}
