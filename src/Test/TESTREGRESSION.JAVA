package Test;

import static org.junit.Assert.*;

import java.util.Arrays;aSDdsSDs
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import CalculeCorrelation.Regression;

public class TestRegression {
	
	double valuesXcarre[] = new double[10];
	double valuesXY[] = new double[10];
	double moyenneX = 0;
	double moyenneY = 0;
	double sumXcarre = 0;
	double sumXY = 0;
	Regression regression;
	/*
	 * mocktest 10 values
	 */
	@Before 
    public void faireAvant() {
		
		double valuesx[] = {130,650,99,150,128,302,95,945,368,961};
		double valuesy[] = {186,699,132,272,291,331,199,1890,788,1601};
		
		for(int i = 0; i < 10; i++) {
			
			moyenneX = moyenneX + valuesx[i];
			moyenneY = moyenneY + valuesy[i];
			sumXY = sumXY + valuesx[i]*valuesy[i];
			sumXcarre = sumXcarre + Math.pow(valuesx[i],2);
		}
		
		moyenneX = moyenneX/10;
		moyenneY = moyenneY/10;
		
		regression = new Regression(10,sumXY,moyenneX,moyenneY,sumXcarre);
	}

	@Test
	public void mockTestRegressionB1() {
		
		assertEquals(1.72,regression.getCoeffB1(),0.01);
	}
	
	@Test
	public void mockTestRegressionB0() {
		assertEquals(-22.55,regression.getCoeffB0(),0.01);
	}
	
	@Test
	public void testErreurPenteB1() {
		assertTrue(regression.getCoeffB1() != 0);
	}
	
	@Test
	public void testErreurB0() {
		assertTrue(regression.getCoeffB0() != 0);
	}
	
	

}
