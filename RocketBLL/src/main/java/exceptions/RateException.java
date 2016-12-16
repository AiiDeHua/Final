package exceptions;

public class RateException extends Exception {

	//	TODO - RocketBLL RateException - RateDomainModel should be an attribute of RateException
	//	* Add RateRomainModel as an attribute
	//	* Create a constructor, passing in RateDomainModel
	//	* Create a getter (no setter, set value only in Constructor)
	private int CreditScore;
	
	public RateException(int CreditScore){
		super();
		this.CreditScore = CreditScore;
	}
	public int getCreditScore(){
		return CreditScore;
	}
}
