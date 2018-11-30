package CalculeCorrelation;

import java.util.Vector;

public class Regression {
	
	private double coeffB1;
	private double coeffB0;
	private int quantiteLogs;
	private Vector<String> y_Bo_B1xi = new Vector<String>();;
	
	public Regression(int nombreElements,double sumXY,double xMoyenne,double yMoyenne,double sumXcarre) {
		
		double valueNum = (sumXY - nombreElements*(xMoyenne*yMoyenne));
		double valueDen = sumXcarre - nombreElements*calculePuissance(xMoyenne, 2);
			
		coeffB1 = valueNum / valueDen;
		
		coeffB0 = yMoyenne - coeffB1*xMoyenne;
		
		quantiteLogs = nombreElements;
	}
	
	public double getCoeffB1() {
		return coeffB1;
	}
	
	public double getCoeffB0() {
		return coeffB0;
	}
	
	public double getValueX(int valueY) {
		double valueX = (valueY - coeffB0) / coeffB1;
		return valueX;
	}
	
	public double getValueY(int valueX) {
		double valueY = coeffB1*valueX + coeffB0;
		return valueY;
	}
	
	public Vector<String> getY_BoB1Xi(Vector<String> valY,Vector<String> valX) {
		
		for ( int i = 0; i<quantiteLogs; i++) {
			double calculerLinealY = Double.parseDouble(valY.get(i)) - coeffB0 - coeffB1*(Double.parseDouble(valX.get(i)));
			y_Bo_B1xi.add(String.valueOf((calculePuissance(calculerLinealY,2))));
		}	
		return y_Bo_B1xi;
	}
	
    public  double calculePuissance( double value, int puissance ) {
		
		double multValue = 1;
		
		for( int i = 0; i < puissance; i++ ) {
			multValue = multValue*value;
		}
		
		return multValue;
		
	}

}
