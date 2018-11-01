package CalculeCorrelation;

public class Regression {
	
	private double coeffB1;
	private double coeffB0;
	
	public Regression(int nombreElements,double sumXY,double xMoyenne,double yMoyenne,double sumXcarre) {
		
		double valueNum = (sumXY - nombreElements*(xMoyenne*yMoyenne));
		double valueDen = sumXcarre - nombreElements*Math.pow(xMoyenne, 2);
		
		coeffB1 = valueNum / valueDen;
		
		coeffB0 = yMoyenne - coeffB1*xMoyenne;
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

}
