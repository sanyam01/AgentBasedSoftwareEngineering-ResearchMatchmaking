package matchpackage.search;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import matchpackage.database.ProviderList;
import matchpackage.database.Provider;
import matchpackage.search.SortProvidersList;

public class SearchAgent extends Agent {

	protected void setup() {

		System.out.println("I am a Search Agent");
		System.out.println("This is my name : " + getAID().getName());
		System.out.println("My global name is " + getAID().getLocalName());
		System.out.println("My addresses are :" + getAID().getAllAddresses());
		addBehaviour(new SortProviders());

	}

	private class SortProviders extends CyclicBehaviour {
		

		private int step = 0;

		@Override
		public void action() {
			// TODO Auto-generated method stub

			ACLMessage msg = myAgent.blockingReceive();
			System.out.println(msg);
			
			
			ACLMessage reply = msg.createReply();
			

			reply.setContent(new SortProvidersList(msg.getContent()).sortProvidersList());
			send(reply);

		}

	}

}
