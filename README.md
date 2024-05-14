# Arithmetic-Calculator
An arithmetic calculator which enforces order of operations via BEDMAS, made using a custom Stack class. I did this in order to consolidate my understanding of the stack data structure and how to design, implement and utilize it efficiently in programming contexts. This project does not use any of the built-in types provided by Java, in order to deepen my understanding of how the stack data structure works.

## Stack Class
This class essentially acts as a skeleton, mimicing the pre-existing Java Stack class. Here, we use dynamically resizable arrays in order to mimic a stack, however, this is done without any built in types, such as ArrayLists. Here, I use an array, and simulate the logic of the push(), pop() and peek() methods. Note: there is no FullStackException, we use the doubling strategy in order to generate a new stack with the previous elements. 

## Arithmetic Calculator 
This contains the code for ensuring. The expression will include particular set of binary operators as well as parentheses pairs (possibly nested ones). In particular, the arithmetic calculators must read lines of text from a text file, where each line contains a syntactically correct arithmetic expression. The output will be directed to
a file, where the input line (the expression) will be recorded and print the computed result on the next line. Your calculators must support the following operators on integers and observe the standard operator precedence as shown below (1 to 6, where 1 is the highest and 6 is the lowest. Same precedence operators are evaluated from left to right). 

### The order of precedence:
1. Parentheses (possibly nested ones): ( , )

Binary operators:
 2. power function: x^y.
 3. operators: *, /
 4. operators: +, -

Redistribution or publication of this document or its text, by any means, is strictly prohibited.
 5. operators: >, ≥, ≤, <
 6. operators: ==, != 
