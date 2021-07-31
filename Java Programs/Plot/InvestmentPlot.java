import java.util.Scanner;
import java.io.File;
import java.util.LinkedHashMap;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Container;
import org.math.plot.Plot2dPanel;
import org.math.plot.plotObjects.BaseLabel;

public class InvestmentPlot {

	public static LinkedHashMap<String, double[]> readDtata(Scanner fsc){
		LinkedHashMap <String, double[]> result = new LinkedHashMap<String, double[]>();
		String line = fsc.nextLine();
		String[] parts = line.split("\t");
		int dayCount = parts.length-1;
		String name;
		double[] InvestmentValues;
		while (fsc.hasNextLine()) {
			line = fsc.nextLine();
			parts = line.split("\t");
			name = parts [0];
			InvestmentValues = new double[dayCount];
			for(int i=1; i<parts.length; i++) {
				InvestmentValues[i-1] = Double.parseDouble(parts[i]);
			}
			result.put(name, InvestmentValues);
		}
		return result;
	}
	public static void main(String[] args) {
		
		LinkedHashMap<String, double[]> accounts;
		
		try {
			Scanner fsc = new Scanner(new File("investment.txt"));
		}catch( Exception ex) {
			System.out.println(" I couldn't open the file");
		}
	

	}

}
