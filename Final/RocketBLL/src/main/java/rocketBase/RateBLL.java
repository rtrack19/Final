package rocketBase;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.*;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	public static double getRate(int GivenCreditScore) throws RateException 
	{
		double dInterestRate = 0;
		
		try{
			ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
			for(RateDomainModel r:rates){
				if(r.getiMinCreditScore() > GivenCreditScore){
					rates.remove(r);
				}
				
			}
			catch(RateException e){
				new RateException();
				System.out.println("Credit Score is too low");
			}
			finally{
				dInterestRate=Math.max(Math.max(rates.get(0).getdInterestRate(), rates.get(1).getdInterestRate()),rates.get(3).getdInterestRate());
			}
			return dInterestRate;
		}
	}
	
	public static double getPayment(double r, double n, double p, double f, boolean t)
	{		
		return FinanceLib.pmt(r, n, p, f, t);
	}
}
