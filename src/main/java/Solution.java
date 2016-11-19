import java.util.Scanner;
import java.util.Stack;

public class Solution {

    public static final Integer MAX_INT_MINE = new Double(Math.pow(2, 20) - 1).intValue();
    public static final Integer MIN_INT_MINE = 0;
    public static final Integer MAX_COMMANDS = 10;

    public int solution(String S) {
        Scanner scanner = new Scanner(S);
        Stack<Integer> stack = new Stack<Integer>();
        int numTokens = 0;

        try {
            while (scanner.hasNext()) {
                String cmdToken = scanner.next();

                numTokens++;
                if (numTokens > MAX_COMMANDS) {
                    throw new RuntimeException("The command line is too long");
                }

                if ("DUP".equals(cmdToken)) {
                    duplicate(stack);
                } else if ("POP".equals(cmdToken)) {
                    pop(stack);
                } else if ("+".equals(cmdToken)) {
                    sum(stack);
                } else if ("-".equals(cmdToken)) {
                    diff(stack);
                } else {
                    push(stack, cmdToken);
                }
            }
        } catch (Exception e) {
            scanner.close();
            System.out.println(e.getMessage());
            return -1;
        }

        scanner.close();

        if (isStackEmpty(stack)) {
//            throw new RuntimeException("The stack doesn't have the necessary elements.");
            return -1;
        }
        return stack.pop();
    }

    private void push(Stack<Integer> stack, String cmdToken) {
        Integer value;
        try {
            value = Integer.valueOf(cmdToken);
        } catch (NumberFormatException nfe) {
            throw new RuntimeException("Invalid data on word machine");
        }

        if (value < MIN_INT_MINE) {
            throw new RuntimeException("Underflow error.");
        }

        if (value > MAX_INT_MINE) {
            throw new RuntimeException("Overflow error.");
        }

        stack.push(value);
    }

    private void diff(Stack<Integer> stack) {
        if (hasEnoughElements(stack)) {
            throw new RuntimeException("The stack doesn't have the necessary elements.");
        }

        int a = stack.pop();
        int b = stack.pop();
        int c = a - b;

        if (c < MIN_INT_MINE) {
            throw new RuntimeException("Underflow error. Operation exceeded limits.");
        }
        stack.push(c);
    }

    private void sum(Stack<Integer> stack) {
        if (hasEnoughElements(stack)) {
            throw new RuntimeException("The stack doesn't have the necessary elements.");
        }

        int a = stack.pop();
        int b = stack.pop();
        int c = a + b;

        if (c > MAX_INT_MINE) {
            throw new RuntimeException("Overflow error. Operation exceeded limits.");
        }
        stack.push(c);
    }

    private void pop(Stack<Integer> stack) {
        if (isStackEmpty(stack)) {
            throw new RuntimeException("The stack is empty.");
        }

        stack.pop();
    }

    private void duplicate(Stack<Integer> stack) {
        if (isStackEmpty(stack)) {
            throw new RuntimeException("The stack is empty. Can't duplicate.");
        }

        stack.push(stack.peek());
    }

    private boolean isStackEmpty(Stack<Integer> stack) {
        return stack.size() < 1;
    }

    private boolean hasEnoughElements(Stack<Integer> stack) {
        return stack.size() < 2;
    }

}
