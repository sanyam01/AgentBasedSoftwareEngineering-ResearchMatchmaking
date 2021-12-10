package matchpackage.contract;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import matchpackage.database.ProjectList;

public class BiddingAgent extends Agent {

	long time = 1000;
	String providerName = "";
	String customerName = "";
	int check = 0;
	String customerContractDecision = "PENDING";
	String providerContractDecision = "PENDING";
	ProjectList projectList;

	protected void setup() {

		projectList = new ProjectList();
		addBehaviour(new AcceptContractRequest(this, time));

	}

	private class AcceptContractRequest extends TickerBehaviour {

		AcceptContractRequest(Agent myAgent, long time) {
			super(myAgent, time);
		}

		@Override
		protected void onTick() {

			switch (check) {

			case 0:

				ACLMessage messageAcceptProvider = myAgent.blockingReceive();
				if (messageAcceptProvider.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {

					providerName = messageAcceptProvider.getSender().getLocalName();
					customerName = messageAcceptProvider.getContent();

				}

				check = 1;

				break;

			case 1:

				ACLMessage sendContractMsg = new ACLMessage(ACLMessage.PROPOSE);
				sendContractMsg.setContent("Please accept the contract");
				sendContractMsg.addReceiver(new AID(customerName, AID.ISLOCALNAME));
				sendContractMsg.addReceiver(new AID(providerName, AID.ISLOCALNAME));
				send(sendContractMsg);
				check = 2;
				break;

			case 2:

				ACLMessage msgContractFirst = myAgent.blockingReceive();
				if (msgContractFirst.getPerformative() == ACLMessage.INFORM) {
					if (msgContractFirst.getSender().getLocalName().contentEquals(customerName))
						customerContractDecision = msgContractFirst.getContent();
					else
						providerContractDecision = msgContractFirst.getContent();
					check = 3;
					System.out.println("I am exiting case 2 of bididng agent");
				}

				break;

			case 3:

				ACLMessage msgContractSecond = myAgent.blockingReceive();
				if (msgContractSecond.getPerformative() == ACLMessage.INFORM) {
					if (msgContractSecond.getSender().getLocalName().contentEquals(providerName))
						providerContractDecision = msgContractSecond.getContent();
					else
						customerContractDecision = msgContractSecond.getContent();

				}
				check = 4;
				System.out.println("I am exiting case 3 of bidding agent");
				break;
			case 4:

				System.out.println("I am running");
				System.out.println("Value of customer contract decision is" + customerContractDecision);
				System.out.println("Value of provider contract decision is" + providerContractDecision);
				if (customerContractDecision.contentEquals("ACCEPT")
						&& providerContractDecision.contentEquals("ACCEPT")) {
					System.out.println("Contracts have been accepted by both the users");
					ACLMessage msgTrackerGUI = new ACLMessage(ACLMessage.REQUEST_WHENEVER);
					msgTrackerGUI.addReceiver(new AID(providerName, AID.ISLOCALNAME));
					msgTrackerGUI.addReceiver(new AID(customerName, AID.ISLOCALNAME));
					send(msgTrackerGUI);
					check = 5;

				}

				break;

			case 5:

				ACLMessage paymentMessage = blockingReceive();
				System.out.println("I am in case 5 of bidding agent");
				if (paymentMessage.getPerformative() == ACLMessage.PROPAGATE) {
					ACLMessage paymentSendMsg = new ACLMessage(ACLMessage.REQUEST);
					paymentSendMsg.addReceiver(new AID(customerName, AID.ISLOCALNAME));
					paymentSendMsg.setContent("Please pay the 2500 Dollars");
					send(paymentSendMsg);
					check = 6;
				}

				
				break;
				
			case 6:
				ACLMessage paymentMessageReceived = blockingReceive();
				if(paymentMessageReceived.getPerformative() == ACLMessage.AGREE) {
					ACLMessage paymentConfirmMsg = new ACLMessage(ACLMessage.CONFIRM);
					paymentConfirmMsg.setContent("Payment is confirmed");
					paymentConfirmMsg.addReceiver(new AID(providerName, AID.ISLOCALNAME));
					send(paymentConfirmMsg);
					
					check = 1;
				}
				
				break;
				
				
				

			}

		}
	}

}
