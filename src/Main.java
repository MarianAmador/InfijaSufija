import java.util.Stack;

public class Main {
    private static boolean Precedencia(char opera1, char opera2) {

        switch (opera1) {
            case '-':
            case '+':
                return (opera2 == '+' || opera2 == '-');

            case '/':
            case '*':
                return (opera2 == '*' || opera2 == '/');

            case '^':
                return true;
        }
        return false;
    }

    public static String convertir(String infija) {
        StringBuilder sufija = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < infija.length(); i++) {
            char token = infija.charAt(i);

            if (Character.isLetterOrDigit(token)) {
                sufija.append(token);
            } else if (token == '(') {
                stack.push(token);
            } else if (token == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sufija.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                }
            } else {
                while (!stack.isEmpty() && Precedencia(token, stack.peek())) {
                    sufija.append(stack.pop());
                }
                stack.push(token);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "Expresión infija no válida";
            }
            sufija.append(stack.pop());
        }

        return sufija.toString();
    }


    public static void main(String[] args) {
        String infija = "A + B * (C - D) / E ^ F - G * (H + I)";
        String sufija = convertir(infija);
        System.out.println("Expresión infija: " + infija);
        System.out.println("Expresión sufija: " + sufija);
    }
}
