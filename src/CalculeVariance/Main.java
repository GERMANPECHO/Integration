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
 * LOG330 - ASSURANCE DE LA QUALIT� DES LOGICIELS
 * CLASS : MAIN
 */

public class Main {
	
	static double variance =  0;
	static double moyenne = 0;
	static String ecart;
	static String varianceCalcule;
	static 	Correlation coefficient;
	
	
	public static void main(String[] args) throws IOException {
		
		String csvfile = "dataCorrelation.csv";
		BufferedReader br = null;
		String line = "";
		double calcVariance;
		String valuesNumber = "";
		String values = "";
	
		
		DecimalFormat df = new DecimalFormat("#.####");
		DecimalFormat de = new DecimalFormat("#.##");
		
		 df.setRoundingMode(RoundingMode.CEILING);
		 de.setRoundingMode(RoundingMode.CEILING);
		
		Vector<String> vectorValues = new Vector<String>();
		Vector<String> vectorValuesY = new Vector<String>();
		
		try {
			
			LireFichier lecture = new LireFichier(csvfile);
			br = new BufferedReader(lecture.getReader());
			
			valuesNumber = br.readLine();
			String[] valuesNumberQt = valuesNumber.split(";");
			values = valuesNumberQt[0];
			
			while( (line = br.readLine()) != null ) {
				
				String[] data = line.split(";");
		//		System.out.println( " x value " + data[0] + " y value " + data[1]);
				vectorValues.add(data[0]);
				vectorValuesY.add(data[1]);
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
			
		
		moyenne = getMoyenne(values,vectorValues);	
		variance = getVariance(values,vectorValues,moyenne);
		
		varianceCalcule = df.format(variance);
		ecart = de.format(getEcart(variance));
		
		System.out.println(" moyenne 10 values :"+  moyenne);
		System.out.println(" variance 10 values : " + varianceCalcule);
		System.out.println("l'�cart type est :" + ecart);
		
		// TODO Auto-generated method stub
		
		System.out.println("resultat coeff correlation X et Y  " + calculerCorrelation(vectorValuesY,vectorValues));
        
        Regression regression = new Regression(Integer.parseInt(values),coefficient.getSumXY(),
        		coefficient.calculerMoyenneX(),coefficient.calculerMoyenneY(),coefficient.getSommeXcarre());
        
        System.out.println(regression.getCoeffB0());
        System.out.println(regression.getCoeffB1());
        
/*        Scanner inScanner = new Scanner(System.in);
        
        System.out.println("Donnez-moi un valeur x pour calculer y");
        String valueInX = inScanner.nextLine();
        
        System.out.println(" resultat Y lineal " +
        regression.getValueY(Integer.parseInt(valueInX)));
        
        System.out.println("Donnez-moi un valeur y pour calculer x");
        String valueInY = inScanner.nextLine();
        
        System.out.println(" resultat X lineal " +
        regression.getValueX(Integer.parseInt(valueInY)));*/
        
       
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
			variance = variance + Math.pow((Double.parseDouble(vectorValues.get(i))-moyenne),2);
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

}
