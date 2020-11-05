package Test;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import CalculeVariance.Main;

public class Variance {
	
	
	private String csvfile = "dt.csv";
	private Vector<String> vectorValues = new Vector<String>();
	private  String[] valuesFile = { "186","699","132","272","291","331","199","1890","788","1601" };
	private String elements = "10";
	private Double moyenne = 0.00;


	@Before 
    public void faireAvant() {
		for(int i = 0; i < valuesFile.length; i ++) {
			vectorValues.add(valuesFile[i]);
		}
	}
	

	@Test
	public void borneSuperieure() {	
		Double moyenne = Main.getMoyenne(elements, vectorValues);	
		assertTrue(Main.getVariance(elements, vectorValues,moyenne) < 391418);
	}
	
	@Test
	public void borneInferieure() {
		
		Double moyenne = Main.getMoyenne(elements, vectorValues);	
		assertTrue(Main.getVariance(elements, vectorValues,moyenne) > 391417.00 );
	}
	
	@Test
	public void valeurInvalide() {
		assertTrue((Main.getVariance(elements, vectorValues,moyenne)  > 0));
	}
	

}
