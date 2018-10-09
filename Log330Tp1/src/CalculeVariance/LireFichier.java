package CalculeVariance;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class LireFichier {
	
	FileReader myFile;
	
	public LireFichier(String myfile) throws FileNotFoundException {
		
		myFile = new FileReader(myfile);
		
	}
	
	public FileReader getReader() {
		return myFile;
	}

}
