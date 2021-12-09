package matchpackage.access;

import javax.swing.SwingUtilities;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import matchpackage.application.EnhancedAgent;
import matchpackage.application.ProviderGUI;
import matchpackage.contract.ProviderChatGUI;
import matchpackage.contract.ProviderProjectGUI;
import matchpackage.database.Provider;

public class ProviderAgent extends EnhancedAgent {

	ProviderGUI providerGUI;
	Provider provider;
	int check = 0;
	String customerName = "";
	String bidValue;
	String decision = "PENDING";
	// SendBidDecision madeBehaviour;
	int step = 0;
	int caseVal = 0;
	String contractDecision = "PENDING";
	private ProviderProjectGUI providerProjectGUI;
	private ProviderChatGUI providerChatGUI;

	protected void setup() {
		createAgent("Bidding", "matchpackage.contract.BiddingAgent");
		providerGUI = new ProviderGUI(this);
		System.out.printf("Hello! My name is %s%n", getLocalName());
		//addBehaviour(new ProjectTracker(this, 2000));
		addBehaviour(new ShowGUIProvider(this, 2000));
		// addBehaviour(new SendBidDecisions());

		// addBehaviour(new chatMessenger());

	}

//	private class ProjectTracker extends TickerBehaviour {
//
//		public ProjectTracker(Agent a, long period) {
//			super(a, period);
//			// TODO Auto-generated constructor stub
//		}
//
//		@Override
//		protected void onTick() {
//			// TODO Auto-generated method stub
//			System.out.println("Project is on too");
//			ACLMessage startMsg = blockingReceive();
//			if (startMsg.getPerformative() == ACLMessage.REQUEST_WHENEVER) {
//
//				providerProjectGUI = new ProviderProjectGUI();
//			}
//
//		}
//
//	}

	// private class ChatMessenger extends TicketBehaviour{

	public void showGUI() {

		this.providerGUI.showGUI();
	}

	public void afterBidClick(String text) {

		decision = text;
//		System.out.println("hereeeeeeeee1111");
//		System.out.println("hereeeeeeeeeeeee");
//		check = 1;
//		System.out.println("Value of check is  " + check);
//		System.out.println("Value of decision is  " + decision);

		// this.madeBehaviour = new SendBidDecision();
		// check = 1;
		// addBehaviour(new SendBidDecision());
		// this.addBehaviour(madeBehaviour);

		// addBehaviour(new SendBidDecisions(myAgent, Long.valueOf(1000)));
	}

	public void afterContractClick(String text) {
		contractDecision = text;
	}

	private class ShowGUIProvider extends TickerBehaviour {

		ShowGUIProvider(Agent a, long period) {
			super(a, period);
		}

		@Override
		protected void onTick() {

			switch (caseVal) {

			case 0:

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

				caseVal = 1;
				break;

			case 1:

				ACLMessage msg1 = myAgent.blockingReceive();

				if (msg1.getPerformative() == ACLMessage.PROPOSE) {

					customerName = msg1.getSender().getLocalName();

					System.out.println("I have recieved the proposal");

					bidValue = msg1.getContent();
					double price = Double.parseDouble(bidValue);
					String text = customerName + bidValue;
					providerGUI.setBidText(text);

					System.out.println("I am giving it the bid value");
					System.out.println("***************************************************************************");

				}

				caseVal = 2;
				break;

			case 2:

				if (!(decision.contentEquals("PENDING"))) {

					System.out.println("I am reaching in action of ProviderAgent");
					if (decision.contentEquals("Accept")) {
						ACLMessage msgAccept = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
						msgAccept.addReceiver(new AID("Bidding", AID.ISLOCALNAME));
						// String sendContent = getAID().getLocalName() + "*" + customerName;
						msgAccept.setContent(customerName);
						send(msgAccept);
						caseVal = 3;
					}

					if (decision.contentEquals("Reject")) {
						System.out.println("I am in here as well");
						ACLMessage bidMsgReply = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
						bidMsgReply.addReceiver(new AID(customerName, AID.ISLOCALNAME));
						bidMsgReply.setContent("Bid is rejected");
						send(bidMsgReply);
						caseVal = 1;
						decision = "PENDING";
					}

				}
				break;

			case 3:

				ACLMessage msgContract = myAgent.blockingReceive();
				if (msgContract != null) {
					if (msgContract.getPerformative() == ACLMessage.PROPOSE) {
						providerGUI.setContract(msgContract.getContent());
					}
				}

				caseVal = 4;

				break;

			case 4:

				if (!(contractDecision.contentEquals("PENDING"))) {

					System.out.println("I am reaching in contract action of ProviderAgent");
					if (decision.contentEquals("Accept")) {
						System.out.println("I have played case 4 for provider agent");
						ACLMessage msgAcceptContract = new ACLMessage(ACLMessage.INFORM);
						msgAcceptContract.addReceiver(new AID("Bidding", AID.ISLOCALNAME));
						msgAcceptContract.setContent("ACCEPT");
						send(msgAcceptContract);
						contractDecision = "PENDING";
						caseVal = 1;
						decision = "PENDING";
					}

					if (decision.contentEquals("Reject")) {
						System.out.println("I am in here as well");
						ACLMessage msgRejectContract = new ACLMessage(ACLMessage.INFORM);
						msgRejectContract.addReceiver(new AID("Bidding", AID.ISLOCALNAME));
						msgRejectContract.setContent("REJECT");
						send(msgRejectContract);
						caseVal = 5;
						decision = "PENDING";
						contractDecision = "PENDING";
					}
					
					caseVal = 5;

				}

				break;

			case 5:

				System.out.println("Project is on too");
				ACLMessage startMsg = blockingReceive();
				if (startMsg.getPerformative() == ACLMessage.REQUEST_WHENEVER) {

					providerProjectGUI = new ProviderProjectGUI();
					providerChatGUI = new ProviderChatGUI();
				}

				caseVal = 1;
				break;
			}

		}
	}
}

//private class SendBidDecision extends OneShotBehaviour {
//
//
//
//private static final long serialVersionUID = 1L;
//
//
//@Override
//public void action() {
//
//	System.out.println("sonaliiiiiiiiiii");
//
//	System.out.println("I am reaching in action of ProviderAgent");
//	if (decision.contentEquals("Accept")) {
//
//	}
//
//	if (decision.contentEquals("Reject")) {
//		System.out.println("I am in here as well");
//		ACLMessage bidMsgReply = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
//		bidMsgReply.addReceiver(new AID(customerName, AID.ISLOCALNAME));
//		bidMsgReply.setContent("Bid is rejected");
//		send(bidMsgReply);
//	}
//
//}
//
//}
//
//private class SendBidDecisions extends CyclicBehaviour {
//
//@Override
//public void action() {
//
//	if (!(decision.contentEquals("PENDING"))) {
//
//		System.out.println("I am reaching in action of ProviderAgent");
//		if (decision.contentEquals("Accept")) {
//
//		}
//
//		if (decision.contentEquals("Reject")) {
//			System.out.println("I am in here as well");
//			ACLMessage bidMsgReply = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
//			bidMsgReply.addReceiver(new AID(customerName, AID.ISLOCALNAME));
//			bidMsgReply.setContent("Bid is rejected");
//			send(bidMsgReply);
//		}
//
//		check = 0;
//	}
//
//}
//
//}
