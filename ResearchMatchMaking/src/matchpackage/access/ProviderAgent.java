package matchpackage.access;

import javax.swing.SwingUtilities;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import matchpackage.application.EnhancedAgent;
import matchpackage.application.ProviderGUI;
import matchpackage.database.Provider;

public class ProviderAgent extends EnhancedAgent {

	ProviderGUI providerGUI;
	Provider provider;
	int check = 0;
	String customerName = "";
	String bidValue;
	String decision = "";

	protected void setup() {
		providerGUI = new ProviderGUI(this);
		System.out.printf("Hello! My name is %s%n", getLocalName());
		addBehaviour(new ShowGUIProvider(this, 1000));
	}

	public void showGUI() {

		this.providerGUI.showGUI();
	}

	public void afterBidClick(String text, Agent myAgent) {

		decision = text;
		System.out.println("hereeeeeeeee1111");
		System.out.println("hereeeeeeeeeeeee");
		addBehaviour(new SendBidDecision());
		//addBehaviour(new SendBidDecisions(myAgent, Long.valueOf(1000)));
	}
	
//	private class SendBidDecisions extends TickerBehaviour {
//
//		
//
//		SendBidDecisions(Agent agent, Long time ){
//			super(agent, time);
//		}
//		@Override
//		protected void onTick() {
//			// TODO Auto-generated method stub
//			System.out.println("sonaliiiiiiiiiii");
//
//			System.out.println("I am reaching in action of ProviderAgent");
//			if (decision.contentEquals("Accept")) {
//
//			}
//
//			if (decision.contentEquals("Reject")) {
//				System.out.println("I am in here as well");
//				ACLMessage bidMsgReply = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
//				bidMsgReply.addReceiver(new AID(customerName, AID.ISLOCALNAME));
//				bidMsgReply.setContent("Bid is rejected");
//				send(bidMsgReply);
//			}
//			
//		}
//
//	}

	

	

	private class SendBidDecision extends OneShotBehaviour {

		private static final long serialVersionUID = 1L;


		@Override
		public void action() {

			System.out.println("sonaliiiiiiiiiii");

			System.out.println("I am reaching in action of ProviderAgent");
			if (decision.contentEquals("Accept")) {

			}

			if (decision.contentEquals("Reject")) {
				System.out.println("I am in here as well");
				ACLMessage bidMsgReply = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
				bidMsgReply.addReceiver(new AID(customerName, AID.ISLOCALNAME));
				bidMsgReply.setContent("Bid is rejected");
				send(bidMsgReply);
			}

		}

	}

	private class ShowGUIProvider extends TickerBehaviour {

		ShowGUIProvider(Agent a, long period) {
			super(a, period);
		}

		@Override
		protected void onTick() {
			// TODO Auto-generated method stub
			ACLMessage msg = myAgent.blockingReceive();

			System.out.println("I am in Provider agent and want to di");
			if (msg.getContent().contentEquals("Open GUI")) {
				System.out.println("Am i reaching here");
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						providerGUI.showGUI();

					}
				});
			}

			if (msg.getPerformative() == ACLMessage.PROPOSE) {

				customerName = msg.getSender().getLocalName();

				System.out.println("I have recieved the proposal");

				bidValue = msg.getContent();
				double price = Double.parseDouble(bidValue);
				String text = customerName + bidValue;
				providerGUI.setBidText(text);

				System.out.println("I am giving it the bid value");
				System.out.println("***************************************************************************");

			}
		}

	}

}
