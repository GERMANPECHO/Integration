package CalculeCorrelation;

import java.util.Vector;

public class Correlation {
	
	private Vector<String> vectValuesY;
	private Vector<String> vectValuesX;
	private Vector<Double> vectorXcarre;
	private Vector<Double> vectorYcarre;
	private Vector<Double> vectorXY;
	private int correlationNumberOfValues;
 	
	public Correlation(Vector<String> vectValues, Vector<String> vectVal) {
		
		vectValuesY = vectValues;
		vectValuesX = vectVal;
		
		correlationNumberOfValues = vectValuesY.size();		
	}
	
	public void afficherValues() {
		
		for( int i = 0; i<correlationNumberOfValues; i++) {
			System.out.println(" valeurs de x " + 
		 vectValuesX.get(i) + "   valeurs de y " + vectValuesY.get(i));
		}
			
	}
	
	
	
	public double calculerSommeX() {
		
		double totalSommeX = 0;
		
		for( int i = 0; i<correlationNumberOfValues; i++) {
		    	totalSommeX = totalSommeX + Double.parseDouble(vectValuesX.get(i));
		}
		
		return totalSommeX;
	}
	
public double calculerSommeY() {
		
		double totalSommeY = 0;
		
	
		for( int i = 0; i<correlationNumberOfValues; i++) {
			    String temp = vectValuesY.get(i).replace(',','.');
		    	totalSommeY = totalSommeY + Double.parseDouble(temp);
		}
		
		return totalSommeY;
	}

public Vector<Double> calculerXcarre() {
	
	double valueXcarre = 0;
	
	vectorXcarre = new Vector<Double>();
	
	
	for( int i = 0; i<correlationNumberOfValues; i++) {
		
	       valueXcarre = Double.parseDouble(vectValuesX.get(i));
	       valueXcarre = Math.pow(valueXcarre, 2);
	       vectorXcarre.add(valueXcarre);
	}
	
	return vectorXcarre;
}

public Vector<Double> calculerYcarre() {
	
	double valueYcarre = 0;
	
	vectorYcarre = new Vector<Double>();
	
	
	for( int i = 0; i<correlationNumberOfValues; i++) {
	   	 String temp = vectValuesY.get(i).replace(',','.');
	       valueYcarre = Double.parseDouble(temp);
	       valueYcarre = Math.pow(valueYcarre, 2);
	       vectorYcarre.add(valueYcarre);
	}
	
	return vectorXcarre;
}


public Vector<Double> calculerXY() {
	
	double valueXY = 0;
	
	vectorXY = new Vector<Double>();
	
	
	for( int i = 0; i<correlationNumberOfValues; i++) {
		 String temp = vectValuesY.get(i).replace(',','.');
	     
	       valueXY = Double.parseDouble(vectValuesX.get(i))*Double.parseDouble(temp);
	       vectorXY.add(valueXY);
	}
	
	return vectorXY;
}

public double calculerCoeffCorrelation() {
	
	double sumMultXY = 0;
	double sumMultXYN = 0;
	double sumXsumY = 0;
	double racineXY = 0;
	double sommeXcarre = 0;
	double sommeYcarre = 0;
	double coefficient = 0;
	
	calculerXcarre();
	calculerYcarre();
	
	for ( int j = 0; j < correlationNumberOfValues; j++) {
		sumMultXY = sumMultXY + calculerXY().get(j);
		sommeXcarre = sommeXcarre + vectorXcarre.get(j);
		sommeYcarre = sommeYcarre + vectorYcarre.get(j);
	}
	
    sumMultXYN = sumMultXY*correlationNumberOfValues;
    sumXsumY = calculerSommeX()*calculerSommeY();
    racineXY = (correlationNumberOfValues*sommeXcarre - Math.pow(calculerSommeX(), 2))*
    		(correlationNumberOfValues*sommeYcarre - Math.pow(calculerSommeY(), 2)); 
    
    
    coefficient = Math.abs((sumMultXYN - sumXsumY)/Math.sqrt((racineXY)));
	
	return coefficient;
}



}
