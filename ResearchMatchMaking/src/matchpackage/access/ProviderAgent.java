package matchpackage.access;

import javax.swing.SwingUtilities;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
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
		addBehaviour(new ShowGUIProvider(this, 4000));
	}
	
	public void showGUI() {
		
		
		this.providerGUI.showGUI();
	}
	
    private class ShowGUIProvider extends TickerBehaviour {
    	
    	ShowGUIProvider(Agent a, long period){
    		super(a,period);
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
						providerGUI.showGUI();
						

					}
				});

				check = check +1;
				break;

			case 1:
				System.out.println("I am in Provider 1");
				
				ACLMessage recieve = myAgent.blockingReceive();
				if (recieve != null) {
					if (recieve.getPerformative() == ACLMessage.PROPOSE) {
						double price = Double.parseDouble(recieve.getContent());
						System.out.println("I have recived the proposal");
					}
				}
				
				break;

			}
			// TODO Auto-generated method stub

			
		}

	}
	

	
}
