public class StringReverser {
    // Time complexity: O(n)
    public static String reverse(String input) {
        Stack<Character> stack = new Stack<>(Math.max(1, input.length()));
        for (int i = 0; i < input.length(); i++) {
            stack.push(input.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        Character c;
        while ((c = stack.pop()) != null) {
            sb.append(c);
        }
        return sb.toString();
    }
}
