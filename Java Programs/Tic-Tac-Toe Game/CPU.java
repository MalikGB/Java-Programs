import java.util.Random;

public class CPU {
	Random choice;
	int CPUX;
	int CPUY;
	public CPU() {
		choice = new Random();
	}
	/**
	 * Generates two random numbers using the Java.Random class
	 */
	public void CPUGenerator() {
		 CPUX = choice.nextInt(3);
		 CPUY = choice.nextInt(3);
		 
	}
	public int getCPUX() {
		return CPUX;
	}
	public int getCPUY() {
		return CPUY;
		
	}
}
