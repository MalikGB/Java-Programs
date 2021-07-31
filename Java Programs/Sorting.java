// Note: since the method is static, we are doing a pass by reference
        // Changing x changes the values in unsort so the sort array is not needed
        // Just made the sort method to get a point across
public class Sorting {
        
        public static <E> Object arraySorting(E[]x) {
               E temp;
               E a;
               E b;
               // Went with an insertion sort method
               // Reads 1 value then compares it to all other values in the array, then moves to the next value
               for(int i = 0; i<x.length; i++) {
                       for(int j=i+1; j<x.length;j++) {
                               if((x[i].toString()).compareTo(x[j].toString())>0) {
                                      System.out.println(x[i]);
                                      System.out.println(x[j]);
                                      temp = x[j];
                                      x[j]=x[i];
                                      x[i]=temp;
                                      int c = 1+2;
                               }
                       }
               }
               return x;
        }
 
        public static void main(String[] args) {
               //String [] unsorted = {"Ma", "Mal", "A","Bal","Malik","Ben","Abraham","Burger"};
               Integer[] unsorted = {5,42,3};
               System.out.print("Unsorted array: ");
               for(int i =0; i<unsorted.length;i++) {
                       System.out.print(unsorted[i]+ " ");
               }
               
               arraySorting(unsorted);
               System.out.print("\nSorted array: ");
               for(int i =0; i<unsorted.length;i++) {
                       System.out.print(unsorted[i] + " ");
               }
        }
 
}