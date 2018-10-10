package Test;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import CalculeVariance.Main;

public class Moyenne {

	
	private String csvfile = "dt.csv";
	private Vector<String> vectorValues = new Vector<String>();
	private  String[] valuesFile = { "186","699","132","272","291","331","199","1890","788","1601" };
	private String elements = "10";


	@Before 
    public void faireAvant() {
		for(int i = 0; i < valuesFile.length; i ++) {
			vectorValues.add(valuesFile[i]);
		}
	}
		
	
	@Test
	public void borneSuperieure() {
		
		assertTrue(Main.getMoyenne(elements, vectorValues) < 640);
	}
	

	@Test
	public void borneInferieure() {
		
		assertTrue(Main.getMoyenne(elements, vectorValues) > 638);
		
	}
	
	@Test
	public void valeurInvalide() {
		
		assertTrue(!(Main.getMoyenne(elements, vectorValues) < 0));
	}
}
