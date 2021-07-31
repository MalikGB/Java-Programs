
public class MouseArrayStack {
	
	int stackX[]; // Defining the array stack for the row
	int stackY[]; // Defining the array stack for the column
	int topX; // Defining the "top" of the stack (
	int topY;
	
	// Constructor method that instantiates the size of the stack and definies the top value
	public MouseArrayStack() {
   
		stackX = new int[25];
		stackY = new int[25];
		topX = 0;
		topY=0;
	}
	
	// Method that creates a new Array / Stack
	void makeNewArray() {
		int[] newStackX = new int[stackX.length*2]; // This new array is double the size of the old array
		int[] newStackY = new int[stackY.length*2];
		
		// For loop that moves in all of the values from the previous array into the new array
		for( int i =0; i<stackX.length; i++) {
			newStackX[i]= stackX[i];
			newStackY[i]= stackY[i];
			
		stackX= newStackX; // Redefines the reference of "Stack" to be that of the new stack that was just created
		stackY = newStackY;
		}
	}
	
	// Method that pushes values into the stack
	void push(int x, int y) {
		
		// If the array is full, program will create a new array
		if(topX==stackX.length) {
			makeNewArray();
		}
		// puts the pushed value into the array and increments top by one
		stackX[topX]=x;
		stackY[topY]=y;
		topX++;
		topY++;
	}
	
	// Method that "Removes" variables from the stack
	int popX() {

		stackX[topX]=0; // value at the top of the stack becomes 0
		topX-=1;// decrements top
		
		return stackX[topX];// returns the new top value
	
	}
	int popY() {

		stackY[topY]=0;
		topY-=1;
		
		return stackY[topY];// returns the new top value
	
	}
	
}
