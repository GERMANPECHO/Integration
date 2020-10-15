import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/*
 * NOM:PECHO SANCHEZ
 * PRENOM:GERMAN GABRIEL
 * LOG330 - ASSURANCE DE LA QUALITÉ DES LOGICIELS
 * CLASS : MAIN
 */

public class Main {
	

	public static void main(String[] args) throws IOException {
		
		String csvfile = "dt.csv";
		BufferedReader br = null;
		String line = "";
		double moyenne = 0;
		double variance =  0;
		double calcVariance;
		String values = "";
		
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
		
			
		moyenne = getSomme(values,vectorValues)/Integer.parseInt(values);
		System.out.println(" moyenne 10 values :"+  moyenne);
		
		for( int i=0; i< Integer.parseInt(values); i++ ) {
			variance = variance + Math.pow((Double.parseDouble(vectorValues.get(i))-moyenne),2);
		}
		
		System.out.println(" variance 10 values : " + (1.0/9.0)*variance);
		System.out.println("l'écart type est :" + Math.sqrt((1.0/9.0)*variance));
		
		// TODO Auto-generated method stub

	}
	
	public static double getSomme(String values,Vector<String> vectorValues) {
		
		double somme = 0;
		
		for( int i=0; i< Integer.parseInt(values); i++ ) {
			somme = somme + Double.parseDouble(vectorValues.get(i));
		}
		
		return somme;
	}
	
	
	public static double getSomme(String values,Vector<String> vectorValues) {
		
		double sommeil = 0;
		double local = 3;
		
		for( int i=0; i< Integer.parseInt(values); i++ ) {
			sambler = somme + Double.parseDouble(vectorValues.get(i));
		}
		
		return somme;
	}
	public static double getSomme(String values,Vector<String> vectorValues) {
		
		double somme = 0;
		
		for( int i=0; i< Integer.parseInt(values); i++ ) {
			somme = somme + Double.parseDouble(vectorValues.get(i));
		}
		
		return somme;
	}
   public void void
   pjblci
   kkkk
   southampton
}
