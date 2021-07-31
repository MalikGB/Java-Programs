
// Purpose of this program is to encode the binary search method to find a value in an array
public class BinarySearch {

	public static void main(String[] args) {
		
		int array[] = {1, 14, 15, 24, 55, 59, 73, 90, 94, 99};
		
		System.out.print("The value is at index: "+ search(array,0,array.length-1,73)  );
	}
	
	public static int search(int[] t, int min, int max, int value) {
		
		int leftB=min; 
		int rightB=max;
		System.out.println("Left Bound is at index: " + leftB + ", value = "+ t[leftB]);
		System.out.println("Right Bound is at index: "+rightB + ", value = "+t[rightB]);
		
		int mid = (rightB+leftB)/2;
		System.out.println("The middle value is: "+ t[mid]+"\n");
		// Need to incorporate a way to tell the program to break if the number doesn't exist in the list
		
		if(t[leftB]==value) {
			return leftB;
		}
		if(t[rightB]==value) {
			return rightB;
		}
		if(t[mid]<value) {
			return search(t, mid+1,rightB,value);
		}
		
		if (t[mid]>value) {
			return search(t, leftB,mid-1,value);
		}
		else {
			return mid;
		}
		
			}
	}