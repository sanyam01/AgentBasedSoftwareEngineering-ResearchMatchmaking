package matchpackage.access;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import matchpackage.application.AppGUI;
import matchpackage.application.GuestGUI;

public class AccessAgent extends Agent {
	
	private ProviderList providerList;

	protected void setup() {

		providerList = new ProviderList();
		System.out.println("I am an Access Agent");
		System.out.println("This is my name : " + getAID().getName());
		System.out.println("My global name is " + getAID().getLocalName());
		System.out.println("My addresses are :" + getAID().getAllAddresses());
		addBehaviour(new CallForProvidersList());

	}
	
	public class CallForProvidersList extends OneShotBehaviour {
		

	@Override
	public void action() {

		ACLMessage msg =   myAgent.blockingReceive();
		System.out.println(msg);
		ACLMessage reply= msg.createReply();
		reply.setContent(providerList.getStringProviders());
		send(reply);
		
		
		
	}
	}
	
	

}
