package rocketBase;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.*;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	public static double getRate(int GivenCreditScore) throws RateException
	{
		//TODO - RocketBLL RateBLL.getRate - make sure you throw any exception
		double interestrate = 0;
		ArrayList<RateDomainModel> AllRates = RateDAL.getAllRates();
		if(GivenCreditScore<600){
			throw new RateException(GivenCreditScore);
		}
			for(RateDomainModel r: AllRates){
				if(GivenCreditScore >= r.getiMinCreditScore()){
					interestrate = r.getdInterestRate()/100;
				}
			}

		//		Call RateDAL.getAllRates... this returns an array of rates
		//		write the code that will search the rates to determine the 
		//		interest rate for the given credit score
		//		hints:  you have to sort the rates...  you can do this by using
		//			a comparator... or by using an OrderBy statement in the HQL
		
		
		//TODO - RocketBLL RateBLL.getRate
		//			obviously this should be changed to return the determined rate
		return interestrate;
		
	}
	
	
	//TODO - RocketBLL RateBLL.getPayment 
	//		how to use:
	//		https://poi.apache.org/apidocs/org/apache/poi/ss/formula/functions/FinanceLib.html
	
	public static double getPayment(double r, double n, double p, double f, boolean t)
	{		
		return FinanceLib.pmt(r, n, p, f, t);
	}
	
	public static double PITICalculation(double monthlyincome,double otherloanpayment){
		double PITI;
		double PITI1 = monthlyincome * 0.28;
		if(PITI1 > monthlyincome*0.36 - otherloanpayment){
			PITI = monthlyincome*0.36 - otherloanpayment;
		}else{
			PITI = PITI1;
		}
		return PITI;
	}
}
