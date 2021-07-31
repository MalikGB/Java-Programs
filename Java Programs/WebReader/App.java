import java.net.URL;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		try {
			// URL link for the specified website
			URL link = new URL("http://www.cs.lewisu.edu/mathcs/");
			Scanner sc = new Scanner (link.openStream()); // Allows link to start reciving data from the website, and the scanner can read that data
			while(sc.hasNextLine()) {
				System.out.println(sc.nextLine());
			}
;		}catch(Exception ex) {
	System.out.println("Could not reach that site.");
			
		}

	}

}
