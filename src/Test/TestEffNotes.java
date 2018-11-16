package Test;

import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import CalculeCorrelation.Correlation;

public class TestEffNotes {
	
	private Vector<String> lineal = new Vector<String>();
	private Vector<String> notesLineal = new Vector<String>();
	private Vector<String> aleatoireLineal = new Vector<String>();
	private Vector<String> notesLinealAleatoire = new Vector<String>();
	private Vector<String> linealAleatoireHeures = new Vector<String>();
	private Vector<String> linealAleatoireNotes = new Vector<String>();
	private Vector<String> linealAleatoireMaxDispersion = new Vector<String>();
	private Vector<String> linealAleatoireMinDispersion = new Vector<String>();
	private Vector<String> fortelinearHeures = new Vector<String>();
	private Vector<String> fortelinearNotes = new Vector<String>();
	
	@Before 
    public void faireAvant() {
		
		//correlation lineal perfaite min heures 8 max 14
		double valminHeuresAccumule = 8.00;
		double valminNotesAccumule = 52.00;
		double valmaxHeuresAccumule = 14.00;
		double valmaxNotesAccumule = 100.00;
		double incrementLineal = ( 14.00 - 8.00 ) / 35 ;
		double incrementNotes = ( 100 - 52 ) / 35;
		double valueHeures = 0.00;
		double valueNotes = 0.00;
		double valuesHeuresAleatoire = 0.00;
		double valuesNotesAleatoire = 0.00;
		double values_Heures = 0.00;
		double values_Notes = 0.00;
		double values_Heures_maxDispersion = 0.00;
		double values_Notes_maxDispersion = 0.00;
		double valuesHeureNonLinear = 0.00;
		double valuesNotesNonLinear = 0.00;
		
		for( int i = 0; i < 35; i++ ) {
			
			valueHeures = valminHeuresAccumule + incrementLineal;
			valueNotes = valminNotesAccumule + incrementNotes;
			
            valuesHeureNonLinear = Math.random()*101.00 + 0.00;
			valuesNotesNonLinear = Math.log(valuesHeureNonLinear);
			
			
			/*
			 * valeurs limites entre le min et le max de la grille
			 */
			valuesNotesAleatoire =  Math.random()*101.00 + 52.00;	
			valuesHeuresAleatoire = Math.random()*15.00 + 8.00;
			
			/*
			 * valeurs limites min et max 4.93 heures chaque jour
			 */
			values_Heures = Math.random()*29.58 + 1.00;
			values_Notes = Math.random()*101.00 + 1.00;	
			
			values_Heures_maxDispersion = Math.random()*144.00 + 0.00;
			values_Notes_maxDispersion  = Math.random()*101.00 + 0.00;
			
			fortelinearHeures.add(String.valueOf(valuesHeureNonLinear));
			fortelinearNotes.add(String.valueOf(valuesNotesNonLinear));
			
			
			linealAleatoireMaxDispersion.add(String.valueOf(values_Heures_maxDispersion));
			linealAleatoireMinDispersion.add(String.valueOf(values_Notes_maxDispersion ));
			
			
			linealAleatoireHeures.add(String.valueOf(values_Heures));
			linealAleatoireNotes.add(String.valueOf(values_Notes));
			
			lineal.add(String.valueOf(valueHeures));
			aleatoireLineal.add(String.valueOf(valuesHeuresAleatoire));
			
			valminHeuresAccumule = valueHeures;
				
			notesLineal.add(String.valueOf(valueNotes));
			notesLinealAleatoire.add(String.valueOf(valuesNotesAleatoire));
			
			valminNotesAccumule = valueNotes;
			
			
			
		} 
			
	}

	@Test
	public void testCalculeCorrelationLinealPositive() {
		Correlation coefficient = new Correlation(notesLineal,lineal);
		double coefficientCorrelation = coefficient.calculerCoeffCorrelation();	
		double val = (double)Math.round(coefficientCorrelation * 1000d) / 1000d;
		System.out.println(coefficient.qualifierReponse(val));
		assertEquals( 1.00 , coefficientCorrelation , 0.01);
	}
	
	@Test
	public void testCalculeCorrelationLimites() {
		Correlation coefficient = new Correlation(notesLinealAleatoire,aleatoireLineal);
		double coefficientCorrelation = coefficient.calculerCoeffCorrelation();
		System.out.println(coefficient.qualifierReponse(coefficientCorrelation));
		assertTrue(coefficientCorrelation >= 0.00 && coefficientCorrelation <= 1.00);
	}
	
	@Test
	public void testCorrelationLinealNotNull() {
		Correlation coefficient = new Correlation(notesLineal,lineal);
		double coefficientCorrelation = coefficient.calculerCoeffCorrelation();
		assertTrue(coefficientCorrelation != 0);
	}
	
	@Test
	public void testCalculeCorrelationLimitesMaxMins() {
		Correlation coefficient = new Correlation(linealAleatoireNotes,linealAleatoireHeures);
		double coefficientCorrelation = coefficient.calculerCoeffCorrelation();
		System.out.println(coefficient.qualifierReponse(coefficientCorrelation));
		assertTrue(coefficientCorrelation >= 0.00 && coefficientCorrelation <= 1.00);
	}
	
	@Test
	public void testCalculeCorrelationMaxDispersion() {
		Correlation coefficient = new Correlation(linealAleatoireMaxDispersion,linealAleatoireMinDispersion);
		double coefficientCorrelation = coefficient.calculerCoeffCorrelation();
		assertTrue(coefficientCorrelation >= 0.00 && coefficientCorrelation <= 1.00);
	}
	
	@Test
	public void testFortLinearCoefficient() {
		Correlation coefficient = new Correlation(fortelinearNotes,fortelinearHeures);
		double coefficientCorrelation = coefficient.calculerCoeffCorrelation();
		System.out.println(coefficient.qualifierReponse(coefficientCorrelation));
		System.out.println("coeff "+ coefficientCorrelation);	
		assertTrue(coefficientCorrelation >= 0.00 && coefficientCorrelation <= 1.00);
	}
	
	

}
