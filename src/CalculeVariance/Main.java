package CalculeVariance;
import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Vector;

import CalculeCorrelation.Correlation;
import CalculeCorrelation.Regression;

/*
 * NOM:PECHO SANCHEZ
 * PRENOM:GERMAN GABRIEL
 * LOG330 - ASSURANCE DE LA QUALITÉ DES LOGICIELS
 * CLASS : MAIN
 */

public class Main {
	
	static double variance =  0;
	static double moyenne = 0;
	static String ecart;
	static String varianceCalcule;
	static 	Correlation coefficient;
	static  Double sommeXinterval = 0.00;
	static Double quatreVingtDixPourcent = 1.860;
	static Double soixanteDixPourcent = 1.108;
	

	public static void main(String[] args) throws IOException {
		
		String csvfile = "dataCorrelation.csv";
		String csvfileEffortNote = "effnote.csv"; 
		
		BufferedReader br = null;
		BufferedReader breffnote = null;
		
		String line = "";
		String lineNotes = "";
		double calcVariance;
		String valuesNumber = "";
		String values = "";
		String valuesEffNote = "";
	
		
		DecimalFormat df = new DecimalFormat("#.####");
		DecimalFormat de = new DecimalFormat("#.##");
		
		 df.setRoundingMode(RoundingMode.CEILING);
		 de.setRoundingMode(RoundingMode.CEILING);
		
		Vector<String> vectorValues = new Vector<String>();
		Vector<String> vectorValuesY = new Vector<String>();
		Vector<String> vectorValuesHeures = new Vector<String>();
		Vector<String> vectorValuesNotes = new Vector<String>();
		
		
		try {
			
			LireFichier lecture = new LireFichier(csvfile);
			LireFichier lectureEffortNote = new LireFichier(csvfileEffortNote);
			
			br = new BufferedReader(lecture.getReader());
			breffnote = new BufferedReader(lectureEffortNote.getReader());
			
			valuesNumber = br.readLine();
			
			valuesEffNote = breffnote.readLine();
			valuesEffNote = breffnote.readLine();
			
			String[] valuesNumberQt = valuesNumber.split(";");
			String[] valueseffNote = valuesEffNote.split(";");
			
			values = valuesNumberQt[0];
			
	   while( (line = br.readLine()) != null ) {			
				
				String[] data = line.split(";");
				
				vectorValues.add(data[0]);
				vectorValuesY.add(data[1]);
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while( (lineNotes = breffnote.readLine()) != null ) {			
			
			String[] data = lineNotes.split(";");
			double sum = 0;
						
			for( int i = 1; i < 7 ; i++ ) {
				sum = sum + Double.parseDouble(data[i].replace(',','.'));
			}
			
			vectorValuesHeures.add(Double.toString(sum));
			vectorValuesNotes.add((data[7].replace(',','.')));
					
		}
	
		
		moyenne = getMoyenne(values,vectorValues);	
		variance = getVariance(values,vectorValues,moyenne);
		
		varianceCalcule = df.format(variance);
		ecart = de.format(getEcart(variance));
		
		System.out.println(" moyenne 10 values :"+  moyenne);
		System.out.println(" variance 10 values : " + varianceCalcule);
		System.out.println("l'écart type est :" + ecart);
		
		// TODO Auto-generated method stub
		
		System.out.println("resultat coeff correlation X et Y  " + calculerCorrelation(vectorValuesY,vectorValues));
        
        Regression regression = new Regression(Integer.parseInt(values),coefficient.getSumXY(),
        		coefficient.calculerMoyenneX(),coefficient.calculerMoyenneY(),coefficient.getSommeXcarre());
        
        System.out.println(regression.getCoeffB0());
        System.out.println(regression.getCoeffB1());
        
        Double value = calculerCorrelation(vectorValuesNotes,vectorValuesHeures);
        
        
        System.out.println("resultat coeff correlation heures vs note " + 
         value + " interpretation : " +  coefficient.qualifierReponse(value));
        
        Double sommeEtape2_Tstudent = getSomme(values,regression.getY_BoB1Xi(vectorValuesY,vectorValues));
              
        Double variance_etape2 =(1/(Double.parseDouble(values)-1))*sommeEtape2_Tstudent;
        
        System.out.println( "Somme Etape2 " + sommeEtape2_Tstudent + " variance " + 
        variance_etape2 + " ecart-type2 " + calculerSqrt(variance_etape2));
        
        Double sommeXinterval = calculerSommeXinterval(vectorValues,getMoyenne(values,vectorValues));
        
        System.out.println( "Somme Etape3 " + sommeXinterval );
        
        int tailleLoc = 1119;
      
        Double moyenneX = getMoyenne(values,vectorValues);
        
        Double racineInterval = calculerSqrt(calculerRacineInterval(tailleLoc,sommeXinterval,moyenneX));
        
        System.out.println("racineInterval  " + tailleLoc + "   " + sommeXinterval + "  " + moyenneX);
        System.out.println("racineInterval  " + racineInterval );
        
        Double intervalQuatreVingtDix = calculerIntervalStudent(quatreVingtDixPourcent,calculerSqrt(variance_etape2),racineInterval);
        Double intervalSoixanteDix = calculerIntervalStudent(soixanteDixPourcent,calculerSqrt(variance_etape2),racineInterval);
        
        System.out.println(intervalQuatreVingtDix);
        System.out.println(intervalSoixanteDix);
        
   //    Scanner inScanner = new Scanner(System.in);
        
  /*      System.out.println("Donnez-moi un valeur x pour calculer y");
        String valueInX = inScanner.nextLine();
        
        System.out.println(" resultat Y lineal " +
        regression.getValueY(Integer.parseInt(valueInX)));
        
        System.out.println("Donnez-moi un valeur y pour calculer x");
        String valueInY = inScanner.nextLine();
        
        System.out.println(" resultat X lineal " +
        regression.getValueX(Integer.parseInt(valueInY)));*/
        
       
	}
	
	public static double calculerIntervalStudent( Double coeffStudent, Double ecartType , Double racine ) {
		
		Double value = coeffStudent*ecartType*racine;
		
		return value;
	}
	
	public static double calculerRacineInterval(int tailleLOC, Double somme, Double moyenne) {
		
		Double racine =  (1 + (1/10) + (calculePuissance((tailleLOC - moyenne),2)/somme));
		
		return racine;
	}
	
	public static double calculerSommeXinterval( Vector<String> vectorX, Double moyenne) {
	
		Double val = 0.00;
		
		for( int i = 0; i < vectorX.size(); i++) {
			val = calculePuissance(Double.parseDouble(vectorX.get(i))-moyenne,2);
			sommeXinterval = sommeXinterval + val;
		}
		
		return sommeXinterval;
	}
	
	public static double calculerCorrelation(Vector<String> vectorY, Vector<String> vectorX) {
		
		coefficient = new Correlation(vectorY,vectorX);	
		return coefficient.calculerCoeffCorrelation();
	}
	
	public static Double getEcart(Double variance) {
		
		double ecartCalcule = 0;
		ecartCalcule = Math.sqrt(variance);
		
		return ecartCalcule;
	}
	
	public static Double getVariance(String values, Vector<String> vectorValues,double moyenne) {
		
		for( int i=0; i< Integer.parseInt(values); i++ ) {
			
			variance = variance + calculePuissance((Double.parseDouble(vectorValues.get(i))-moyenne),2);
		}
		
		return (variance*(1.0/9.0));
	}
	
	public static double getMoyenne(String values,Vector<String> vectorValues) {
		
		double calculMoyenne = getSomme(values,vectorValues)/Integer.parseInt(values);
		
		return calculMoyenne;
	}
	
	
	public static double getSomme(String values,Vector<String> vectorValues) {
		
		double somme = 0;
		
		for( int i=0; i< Integer.parseInt(values); i++ ) {
			
			somme = somme + Double.parseDouble(vectorValues.get(i));
		}
		
		return somme;
	}
	
	public static double calculePuissance( double value, int puissance ) {
		
		double multValue = 1;
		
		for( int i = 0; i < puissance; i++ ) {
			
			multValue = multValue*value;
		}
		
		return multValue;
		
	}
	
	 public static double calculerSqrt (double n) {
         
		    double racine = 1.00;
		    double num = n;
		     
		    for (int i = 0; i < n; i++) {
		         
		        racine = 0.5 * (racine + num/racine);
		    } 
		    
		    return racine;
	 }

}
