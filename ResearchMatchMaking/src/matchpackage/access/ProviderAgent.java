package matchpackage.access;

import javax.swing.SwingUtilities;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import matchpackage.application.EnhancedAgent;
import matchpackage.application.ProviderGUI;
import matchpackage.database.Provider;

public class ProviderAgent extends EnhancedAgent{
	
	ProviderGUI providerGUI;
	Provider provider;
	int check = 0;
	

	protected void setup() {
		providerGUI = new ProviderGUI();
		System.out.printf("Hello! My name is %s%n", getLocalName());
		addBehaviour(new ShowGUIProvider());
	}
	
	public void showGUI() {
		this.providerGUI.showGUI();
	}
	
    private class ShowGUIProvider extends Behaviour {

		

		@Override
		public void action() {

			switch (check) {

			case 0:
				ACLMessage msg = myAgent.blockingReceive();
				System.out.println(msg);
				
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						providerGUI.showGUI();
						

					}
				});

				check = check +1;
				break;

			case 1:
				System.out.println("I am in Provider 1");
				
				break;

			}
			// TODO Auto-generated method stub

		}

		@Override
		public boolean done() {
			// TODO Auto-generated method stub
			return false;
		}

	}
	

	
}
