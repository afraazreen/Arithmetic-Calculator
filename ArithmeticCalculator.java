import java.io.*;

public class ArithmeticCalculator {
    
    // Main method to read from input file, calculate, and write results to output file
    public static void main(String[] args) {
        String inFilePath = "input.txt"; // Input file path
        String outFilePath = "output.txt"; // Output file path

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inFilePath));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outFilePath))) {

            String line;
            // Read lines from input file
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim(); // Trim leading and trailing whitespace
                // If the line is not empty
                if (!line.isEmpty()) {
                    int result = calculate(line); // Calculate the expression
                    // Write the expression and result to the output file
                    bufferedWriter.write(line + " = ");
                    bufferedWriter.write(Integer.toString(result));
                    bufferedWriter.newLine(); // Move to the next line
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred"); // Display error message if file operation fails
        }
    }
    
    // Method to calculate the arithmetic expression
    public static int calculate(String expression) {
        // Stacks for numbers and operators
        Stack numberStack = new Stack();
        Stack operatorStack = new Stack();
        
        // Priority array for operator precedence
        int[] priority = new int[Character.MAX_VALUE + 1];
        setPriority(priority);
    
        // Loop through each character in the expression
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            
            // If character is a digit, parse the number
            if (Character.isDigit(ch)) {
                int number = 0;
                
                // Parsing multi-digit numbers
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    number = number * 10 + (expression.charAt(i) - '0'); 
                    i++;
                }
                
                i--; // Adjusting the index after parsing the number
                numberStack.push(number);
            } 
            // If character is an opening parenthesis
            else if (ch == '(') {
                operatorStack.push(ch);
            } 
            // If character is a closing parenthesis
            else if (ch == ')') {
                // Pop operators and apply operations until an opening parenthesis is found
                while (!operatorStack.isEmpty() && (char) operatorStack.peek() != '(') {
                    int num2 = numberStack.pop();
                    int num1 = numberStack.pop();
                    char op = (char) operatorStack.pop();
                    int res = applyOperation(num1, num2, op);
                    numberStack.push(res);
                }
                
                operatorStack.pop(); // Remove the opening parenthesis
            } 
            // If character is an operator
            else if (isOp(ch)) {
                // Pop operators with higher or equal precedence and apply operations
                while (!operatorStack.isEmpty() && priority[ch] <= priority[operatorStack.peek()]) {
                    int num2 = numberStack.pop();
                    int num1 = numberStack.pop();
                    char op = (char) operatorStack.pop();
                    int res = applyOperation(num1, num2, op);
                    numberStack.push(res);
                }
                operatorStack.push(ch); // Push the current operator
            }
        }
        
        // After processing all characters, apply remaining operations
        while (!operatorStack.isEmpty()) {
            if (!numberStack.isEmpty()) {
                int num2 = numberStack.pop();
                int num1 = numberStack.pop();
                char op = (char) operatorStack.pop();
                int res = applyOperation(num1, num2, op);
                numberStack.push(res);
            }
        }
        return numberStack.pop();    // Return the final result
    }
    
    // Method to apply arithmetic operations
    private static int applyOperation(int num1, int num2, char op) {
        int res = 0;
        switch (op) {
            case '^':
                res = pow(num1, num2);
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num1 / num2;
                break;
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num1 - num2;
                break;
            case '>':
                res = num1 > num2 ? 1 : 0; // For the next couple of operations, compare and assign 1 if true, 0 if false
                break;
            case '<':
                res = num1 < num2 ? 1 : 0;
                break;
            case '≥':
                res = num1 >= num2 ? 1 : 0;
                break;
            case '≤':
                res = num1 <= num2 ? 1 : 0;
                break;
            case '=':
                res = num1 == num2 ? 1 : 0;
                break;
            case '!': //it's supposed to be != but was not able to implement that so using only character ! instead.
                res = num1 != num2 ? 1 : 0;
                break;
        }
        return res;
    }
    
    // Method to calculate power
    private static int pow(int base, int exp) {
        int res = 1;
        for (int i = 0; i < exp; i++) {
            res *= base;
        }
        return res;
    }

    // Method to set operator priorities
    public static void setPriority(int[] priority) {
        priority['('] = priority[')'] = 1;
        priority['^'] = 4; 
        priority['*'] = priority['/'] = 3;
        priority['+'] = priority['-'] = 2; 
        priority['>'] = priority['<'] = priority['≥'] = priority['≤'] = 5;
        priority['='] = priority['!'] = 6;
    }

    // Method to check if a character is an operator
    private static boolean isOp(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^' || ch == '>' || ch == '<' || ch == '=' || ch == '!' || ch == '≥' || ch == '≤';
    }
}
