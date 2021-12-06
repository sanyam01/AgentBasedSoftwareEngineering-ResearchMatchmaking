package matchpackage.access;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import matchpackage.application.AppGUI;
import matchpackage.application.EnhancedAgent;
import matchpackage.application.GuestGUI;
import matchpackage.database.ProviderList;

public class AccessAgent extends EnhancedAgent {

	private ProviderList providerList;

	protected void setup() {

		providerList = new ProviderList();
		System.out.println("I am an Access Agent");
		addBehaviour(new CallForProvidersList());
		createAllProviderAgent(providerList);

	}

	public class CallForProvidersList extends OneShotBehaviour {

		@Override
		public void action() {

			ACLMessage msg = myAgent.blockingReceive();
			System.out.println(msg);
			ACLMessage reply = msg.createReply();
			reply.setContent(providerList.getStringProvidersGuest());
			send(reply);

		}
	}
	
	protected void takeDown() {
		
		try {
			DFService.deregister(this);
		}
		catch (FIPAException fe) {
			fe.printStackTrace();
		}
	}

}
