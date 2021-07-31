import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;


public class Plot {

	public static void main(String[] args) {
		// Note: need to import jars in notes for the program to work
		
		//configure arrays of double numbers for x and y values
		
		double[] x = new double[21];
		double[] y = new double [21];
		
		// fill the data for a parabola
		for(int i =-10; i<=10; i++) {
			x[i+10] = i;
			y[i+10]= i*i;
		}
		
		Plot2dPanel plot = new Plot2dPanel();
		plot.addLinePlot("Parabola", x,y); // Name of plot, x coordinates, y coordinates
		plot.setAxisLabels("x", "y"); //Lets us name the x and y axis (x axis, y axis)
		plot.addLegend("SOUTH"); // Creates a legend for the plot on the southern segment of the frame
		plot.setFixedBounds(1, 0, 100); // Limits the y axis to exist from y=0 to y =100
		
		BaseLabel title = new BaseLabel ("Parabola", Color.RED, 0.5,1.1);
		plot.addPlotable(title);
		
		//Create a frame to house the plot
		JFrame frm = new JFrame();
		frm.setTitle("Plot of a parabola");
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setBounds(100,100,500,500);
		Container c = frm.getContentPane();
		c.setLayout(new BorderLayout());
		c.add((plot, BorderLayout.CENTER)); // Prints the plot in the center segment
		frm.setVisible(true);
	}

}
