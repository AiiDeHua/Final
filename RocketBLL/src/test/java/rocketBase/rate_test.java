package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.RateException;

public class rate_test {

	//TODO - RocketBLL rate_test
	//		Check to see if a known credit score returns a known interest rate
	
	
	//TODO - RocketBLL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	@Test
	public void TestInterestRate() throws RateException {
		assertTrue(RateBLL.getRate(600) == 0.05);
		assertTrue(RateBLL.getRate(650) == 0.045);
		assertTrue(RateBLL.getRate(700) == 0.04);
		assertTrue(RateBLL.getRate(750) == 0.0375);
		assertTrue(RateBLL.getRate(800) == 0.035);
	}
	
	@Test(expected=RateException.class)
	public void TestInterestRateWithException() throws RateException{
		System.out.println(RateBLL.getRate(0));
	}
	

}
