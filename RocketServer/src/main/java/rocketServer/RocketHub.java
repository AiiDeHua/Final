package rocketServer;

import java.io.IOException;

import netgame.common.Hub;
import rocketBase.RateBLL;
import rocketData.LoanRequest;


public class RocketHub extends Hub {

	private RateBLL _RateBLL = new RateBLL();
	
	public RocketHub(int port) throws IOException {
		super(port);
	}

	@Override
	protected void messageReceived(int ClientID, Object message) {
		System.out.println("Message Received by Hub");
		
		if (message instanceof LoanRequest) {
			resetOutput();
			
			LoanRequest lq = (LoanRequest) message;
			
			//	TODO - RocketHub.messageReceived
			try{
				if(RateBLL.getRate(lq.getiCreditScore()) == 0){
				throw new Exception();
				}
				lq.setdRate(RateBLL.getRate(lq.getiCreditScore()));
				lq.setdPayment(RateBLL.getPayment(lq.getdRate()/12, lq.getiTerm(), lq.getdAmount(), 0, false));
				lq.setPITI(RateBLL.PITICalculation(lq.getIncome(), lq.getExpenses()+lq.getiDownPayment()));
				}catch(Exception e){
					System.out.println("Something is wrong!");
				
			}
			//	You will have to:
			//	Determine the rate with the given credit score (call RateBLL.getRate)
			//		If exception, show error message, stop processing
			//		If no exception, continue
			//	Determine if payment, call RateBLL.getPayment
			//	
			//	you should update lq, and then send lq back to the caller(s)
			
			sendToAll(lq);
		}
	}
}
