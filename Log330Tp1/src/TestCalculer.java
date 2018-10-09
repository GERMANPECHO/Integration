import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

/*
 * TESTS
 */
public class TestCalculer {
	
	private String csvfile = "dt.csv";
	private Vector<String> vectorValues = new Vector<String>();
	private  String[] valuesFile = { "186","699","132","272","291","331","199","1890","788","1601" };
	private int elements = 10;


	@Before 
    public void faireAvant() {
		for(int i = 0; i < valuesFile.length; i ++) {
			vectorValues.add(valuesFile[i]);
		}
	}

	@Test
	public void testConnection() throws FileNotFoundException {	
		LireFichier lecture = new LireFichier(csvfile);
		assertTrue(lecture!=null);
	}
	
	
	@Test
	public void testSomme() {
		double somme = 0;
		
		for(int i = 0; i<vectorValues.size(); i++){
			somme = somme + Double.parseDouble(vectorValues.get(i));
		}
		
		assertTrue(somme==6389.0);
	
	}

}
