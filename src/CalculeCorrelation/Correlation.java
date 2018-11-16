package CalculeCorrelation;

import java.util.Vector;

/*
 * Class Correlation
 *  
 */

    public class Correlation {
	
	private Vector<String> vectValuesY;
	private Vector<String> vectValuesX;
	private Vector<Double> vectorXcarre;
	private Vector<Double> vectorYcarre;
	private Vector<Double> vectorXY;
	private int correlationNumberOfValues;
	private double sumXY;
	private double sumXcarre;
 	
	public Correlation(Vector<String> vectValues, Vector<String> vectVal) {
		
		vectValuesY = vectValues;
		vectValuesX = vectVal;
		
		correlationNumberOfValues = vectValuesY.size();		
	}
	
	public String qualifierReponse(Double coefficient) {
		
		String qualification = "";
		
	    if ( 0.9<= coefficient && coefficient <= 1.00 ) {
	    	qualification = "Très forte à parfaite";
	    } else if (  0.7<= coefficient && coefficient < 0.9 ) {
	    	qualification = "Forte à très forte";
	    } else if ( 0.4<= coefficient && coefficient < 0.7 ) {
	    	qualification = "Moyenne à forte";
	    } else if (  0.2<= coefficient && coefficient < 0.4 ) {
	    	qualification = "Faible à moyenne";
	    } else if ( 0.0<= coefficient && coefficient < 0.2 ) {
	    	qualification = "Nulle à faible";
	    } else {
	    	qualification = "impossible value";
	    }
		
		return qualification;
	}
	
	public void afficherValues() {
		
		for( int i = 0; i<correlationNumberOfValues; i++) {
			System.out.println(" valeurs de x " + 
		 vectValuesX.get(i) + "   valeurs de y " + vectValuesY.get(i));
		}
			
	}
	
	/*
	 *  fonction CalculerSommeX
	 *  addition de tous les valeurs x
	 *  precon: vectorValuesX
	 *  post: double
	 */
	
	public double calculerSommeX() {
		
		double totalSommeX = 0;
		
		for( int i = 0; i<correlationNumberOfValues; i++) {
		    	totalSommeX = totalSommeX + Double.parseDouble(vectValuesX.get(i));
		}
		
		return totalSommeX;
	}
	
	/*
	 * function calculerMoyenneX
	 */
	
	public double calculerMoyenneX() {
		
		return calculerSommeX()/correlationNumberOfValues;
		
	}
	
	/*
	 *  fonction calculerSommeY
	 *  addition de tous les valeurs Y
	 *  precon: vectorValuesY
	 *  post: double
	 */
	
    public double calculerSommeY() {
		
		double totalSommeY = 0;
		
	
		for( int i = 0; i<correlationNumberOfValues; i++) {
			    String temp = vectValuesY.get(i).replace(',','.');
		    	totalSommeY = totalSommeY + Double.parseDouble(temp);
		}
		
		return totalSommeY;
	}
    
    /*
     * function calculerMoyenneY
     */
    
	public double calculerMoyenneY() {
		
		return calculerSommeY()/correlationNumberOfValues;
		
	}
    
    
    /*
	 *  fonction Calculercarre de X
	 *  precon: vectorValuesX
	 *  post: vector<Double>
	 */

   public Vector<Double> calculerXcarre() {
	
	double valueXcarre = 0;
	double sum = 0;
	
	vectorXcarre = new Vector<Double>();
	
	
	for( int i = 0; i<correlationNumberOfValues; i++) {
		
	       valueXcarre = Double.parseDouble(vectValuesX.get(i));
	       valueXcarre = Math.pow(valueXcarre, 2);
	       sum = sum + valueXcarre;
	       vectorXcarre.add(valueXcarre);
	}
	
	sumXcarre = sum;
	
	return vectorXcarre;
}
   
   public double getSommeXcarre() {
	   return sumXcarre;
   }
   
   /*
	 *  fonction Calculercarre de Y
	 *  precon: vectorValuesY
	 *  post: vector<Double>
	 */

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

   /*
	 *  fonction CalculerXY
	 *  precon: vectorValuesY et vectorValuesX
	 *  post: vector<Double>
	 */
   

   public Vector<Double> calculerXY() {
	
	double valueXY = 0;
	double sum = 0;
	
	vectorXY = new Vector<Double>();
	
	
	for( int i = 0; i<correlationNumberOfValues; i++) {
		 String temp = vectValuesY.get(i).replace(',','.');
	     
	       valueXY = Double.parseDouble(vectValuesX.get(i))*Double.parseDouble(temp);
	       sum = sum + valueXY;
	       vectorXY.add(valueXY);
	}
	
	sumXY = sum;
	
	return vectorXY;
}
   
   
   public double getSumXY() {
	   
	  return sumXY;
   }
   /*
    * fonction calculerCoeffCorrelation
    * return double
    */

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
