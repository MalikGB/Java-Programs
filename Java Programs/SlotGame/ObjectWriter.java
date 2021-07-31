import java.io.File;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * This class is responsible for writing the information about the current objects into a external file
 * @author malik
 *
 */
public class ObjectWriter<T> {
	
	/**
	 * Creates the file the user saves the tiles to
	 * @param fname Name of the file
	 * @param objects ArrayList of the objects the user will save
	 * @return True if the is a supported file type and was written successfully, false if an exception or problem occured
	 */
	public boolean write (String fname, ArrayList<T> objects) {
		File f = new File(fname); // File object
		return write(f,objects);
	}
	/**
	 * Checks if the user entered a valid file type when saving the file
	 * @param f File the information is stored in
	 * @param objects Arraylist of the objects the user will save
	 * @return True if the information was written successfully to the desired file type, false if they entered an invalid file type or an exception occured
	 */
	public boolean write(File f, ArrayList<T> objects) {
		String fname = f.getName().toUpperCase(); // Converts the file's name to uppercase in order to compare its file type
		if(fname.endsWith(".TXT")) {
			return writeToText(f,objects);
		}
		if(fname.endsWith(".XML")) {
			return writeToXML(f,objects);
		}
		if(fname.endsWith(".BIN")) {
			return writeToBinary(f,objects);
		}
		return false;
	}
	/**
	 * Writes the information into a .txt file
	 * @param f File being created
	 * @param objects ArrayList of the objects the user wants to save
	 * @return true if the information is written sucessfully, false if an exception occurs
	 */
	public boolean writeToText(File f, ArrayList<T>objects) {
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f))); // pw allows data to be written to a text file
			for(int i =0; i<objects.size(); i++) {
				pw.println(objects.get(i));	
			}
			pw.close();
			return true;
		}catch(Exception ex) {
			return false; // Data was not written successfully 
		}
	}
	/**
	 * Writes the objects to a binary (.bin) file
	 * @param f File the information will be saved to
	 * @param objects ArrayList of the objects the user will save
	 * @return True if the information was written to the .bin file successfully, false if an exception occurred
	 */
	public boolean writeToBinary(File f, ArrayList<T> objects) {
		try {
			ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(f)); //oos is allows data to be written to a binary file 
			oos.writeObject(objects);
			oos.close();
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
	/**
	 * Writes the objects to a XML (.xml) file
	 * @param f File the information will be saved to
	 * @param objects ArrayList of the objects the user will save
	 * @return True if the information was written to the .xml file successfully, false if an exception occurred
	 */
	public boolean writeToXML(File f, ArrayList<T> objects) {
		try {
			XMLEncoder enc = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(f))); // enc allows the data to be written to a xml file
			enc.writeObject(objects);
			enc.close();
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
}
