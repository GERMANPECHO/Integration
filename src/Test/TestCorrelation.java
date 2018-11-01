package Test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import CalculeCorrelation.Correlation;
import CalculeVariance.LireFichier;
import CalculeVariance.Main;

public class TestCorrelation {
	
	Vector<String> vectorValues = new Vector<String>();
	Vector<String> vectorValuesY = new Vector<String>();
	String values = "";
	Correlation  correlationTest;
	
	@Before 
    public void faireAvant() throws IOException {
	
		String csvfile = "dataCorrelation.csv";
		BufferedReader br = null;
		String line = "";
		String valuesNumber = "";
		
		
				
	try {
			
			LireFichier lecture = new LireFichier(csvfile);
			br = new BufferedReader(lecture.getReader());
			
			valuesNumber = br.readLine();
			
			String[] valuesNumberQt = valuesNumber.split(";");
			values = valuesNumberQt[0];
			
			while( (line = br.readLine()) != null ) {
				
				String[] data = line.split(";");
				vectorValues.add(data[0]);
				vectorValuesY.add(data[1]);
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
	correlationTest = new Correlation(vectorValuesY,vectorValues);
	
	}
	
	/*
	 * test pour vérifier si le coeff est inferieur a 1
	 */

	@Test
	public void borneSuperieure() {
		assertTrue(Main.calculerCorrelation(vectorValuesY, vectorValues) <= 1);
	}
	
	/*
	 * test pour vérifier si le coeff est inferieur a > 0
	 */
	
	@Test
	public void borneInferieure() {
		assertTrue(Main.calculerCorrelation(vectorValuesY, vectorValues) >= 0);
	}
	
	/*
	 * test pour vérifier si le coeff n'est pas superieur a 1 max value coeff
	 */
		
	@Test
	public void valeurInvalide() {
		assertTrue(!(Main.calculerCorrelation(vectorValuesY, vectorValues) > 1));
	}
	
	/*
	 * test pour vérifier si la quantité d'elements est la même
	 */
	
	@Test
	public void numeroDesElements() {
		assertTrue(Double.parseDouble(values) == correlationTest.calculerXcarre().size() &&
				Double.parseDouble(values) == correlationTest.calculerYcarre().size());
	}
	

}
