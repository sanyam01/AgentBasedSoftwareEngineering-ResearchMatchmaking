package matchpackage.access;

import java.util.Set;

import javax.swing.SwingUtilities;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import matchpackage.application.CustomerGUI;
import matchpackage.application.EnhancedAgent;
import matchpackage.database.Customer;

public class CustomerAgent extends EnhancedAgent {

	CustomerGUI customerGUI;
	Set<AID> foundAgents;
	int check = 0;

	protected void setup() {
		customerGUI = new CustomerGUI();
		addBehaviour(new ShowGUICustomer(this, 3000));

	}

	public void showGUI() {

		this.customerGUI.showGUI();
	}

	private class ShowGUICustomer extends TickerBehaviour {

		public ShowGUICustomer(Agent a, long period) {
			super(a, period);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void onTick() {
			// TODO Auto-generated method stub
			switch (check) {

			case 0:
				ACLMessage msg = myAgent.blockingReceive();
				System.out.println(msg);

				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						customerGUI.showGUI();

					}
				});

				check = check + 1;
				break;

			case 1:
				System.out.println("I am in 1");
				foundAgents = searchForService("Web_services");
				System.out.println(foundAgents);
				for (AID j : foundAgents) {
					System.out.println("Name of the agents are");
				}

				ACLMessage msgRequest = new ACLMessage(ACLMessage.REQUEST);
				System.out.println("Am i here");
				msgRequest.addReceiver(new AID("match", AID.ISLOCALNAME));
				msgRequest.setContent("Get providers in Customer");
				send(msgRequest);

				ACLMessage msgGetProvider = myAgent.blockingReceive();
				System.out.println("This is in case 1 of customer agent");
				System.out.println(msgGetProvider.getContent());
				System.out.println("Yes I am updating the table");
				customerGUI.setContentListProvider(msgGetProvider.getContent());
				customerGUI.getProviderTable().repaint();
				
				
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						
						
						customerGUI.getProviderTable().repaint();

					}
				});
				
				break;

			}

		}

	}

}
