package CalculeVariance;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Vector;

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
	
	
	public static void main(String[] args) throws IOException {
		
		String csvfile = "dt.csv";
		BufferedReader br = null;
		String line = "";
		double calcVariance;
		String values = "";
		
		DecimalFormat df = new DecimalFormat("#.####");
		DecimalFormat de = new DecimalFormat("#.##");
		
		 df.setRoundingMode(RoundingMode.CEILING);
		 de.setRoundingMode(RoundingMode.CEILING);
		
		Vector<String> vectorValues = new Vector<String>();
		
		try {
			
			LireFichier lecture = new LireFichier(csvfile);
			br = new BufferedReader(lecture.getReader());
			
			values = br.readLine();
			
			while( (line = br.readLine()) != null ) {
				
				String data = line;
				System.out.println(data);
				vectorValues.add(data);
				
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
		System.out.println("l'écart type est :" + ecart);
		
		// TODO Auto-generated method stub

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
