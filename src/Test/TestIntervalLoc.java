package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import CalculeVariance.Main;

public class TestIntervalLoc {
	
	int nombreLocs = 1119;
	int nombreTestLocs = 900;
	Double sommeInterval = 1074925.6;
	Double moyenne = 382.8;
	Double varianceEtape2 = 34811.2539;
	Double ecartTypeEtape2 = 186.5777;
	Double racineInterval = 1.2665;
	Double intervalInf = 439.5453;
	Double intervalSup = 261.8366;
	Double extraireRacine = 16.00;
	Double racineCalculInterval = 1.2665;
	Double quatreVingtDixPourcent = 1.860;
    Double soixanteDixPourcent = 1.108;
    Double racineIntervalTestNeufCent = 0.00;
	
	@Before 
    public void faireAvant( ) {
		racineIntervalTestNeufCent = Main.calculerSqrt(
				Main.calculerRacineInterval( nombreTestLocs, sommeInterval, moyenne));
	}
	
	
	@Test
	public void testLimiteSup() {
		assertEquals( intervalSup,Main.calculerIntervalStudent(soixanteDixPourcent,
				Main.calculerSqrt(varianceEtape2),racineCalculInterval),0.05);
	}
	
	@Test
	public void testLocsNeufCentInf() {
		System.out.println(racineIntervalTestNeufCent);
		System.out.println(Main.calculerIntervalStudent(quatreVingtDixPourcent,
				Main.calculerSqrt(varianceEtape2),racineIntervalTestNeufCent));
		assertEquals( 387.8254, Main.calculerIntervalStudent(quatreVingtDixPourcent,
				Main.calculerSqrt(varianceEtape2),racineIntervalTestNeufCent),0.01);
	}
	
	@Test
	public void testLocsNeufCentSup() {
		System.out.println(racineIntervalTestNeufCent);
		System.out.println(Main.calculerIntervalStudent(soixanteDixPourcent,
				Main.calculerSqrt(varianceEtape2),racineIntervalTestNeufCent));
		assertEquals( 231.0271, Main.calculerIntervalStudent(soixanteDixPourcent,
				Main.calculerSqrt(varianceEtape2),racineIntervalTestNeufCent),0.01);
	}

}
