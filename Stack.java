


// This is the custom Stack class.
public class Stack {
	
	// Private members to hold the stack elements and top index
	private int[] myArray; // Array to store stack elements
	private int stackTop;  // Index of the top element in the stack
	
	// Constructor to initialize the stack
	public Stack() {
		this.myArray = new int[1]; // Initialize the array with size 1
		stackTop = -1; // Initialize stack top to -1 (empty stack)
	}
	
	// Method to push an element onto the stack
	public void push(int element) {
		if (stackTop == myArray.length - 1) { // If the stack is full
			int[] newArray = new int[myArray.length * 2]; // Create a new array with double the size
			
			// Copy elements from the old array to the new array
			for (int i = 0; i < myArray.length; i++) {
				newArray[i] = myArray[i];
			}
			myArray = newArray; // Update the reference to the new array
		}
		stackTop++; // Increment stack top
		myArray[stackTop] = element; // Add the new element to the stack
	}
	
	// Method to pop an element from the stack
	public int pop() {
		if (isEmpty()) { // If the stack is empty
		    throw new IllegalStateException("Cannot pop from an empty stack"); // Throw exception
		}
		int element = myArray[stackTop]; // Get the top element of the stack
		stackTop--; // Decrement stack top
		return element; // Return the popped element
	}
	
	// Method to check if the stack is empty
	public boolean isEmpty() {
		return stackTop == -1; // Stack is empty if stack top is -1
	}
	
	// Method to peek at the top element of the stack without removing it
	public int peek() {
		return myArray[stackTop]; // Return the top element of the stack
	}
}
