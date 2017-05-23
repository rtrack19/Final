package rocketServer;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import exceptions.RateException;
import netgame.common.Hub;
import rocketBase.RateBLL;
import rocketData.LoanRequest;
import rocketDomain.RateDomainModel;
import util.HibernateUtil;


public class RocketHub extends Hub {

	private RateBLL _RateBLL = new RateBLL();
	
	public RocketHub(int port) throws IOException {
		super(port);
	}

	@Override
	protected void messageReceived(int ClientID, Object message) throws RateException {
		System.out.println("Message Received by Hub");
		
		if (message instanceof LoanRequest) {
			resetOutput();
			
			LoanRequest lq = (LoanRequest) message;
			
			lq.setdRate(RateBLL.getRate(ClientID));
			lq.setdPayment(RateBLL.getPayment(lq.getdRate(), lq.getiTerm(), lq.getiDownPayment(), lq.getdAmount(), true));
			sendToAll(lq);
		}
	}
}
