package matchpackage.access;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import javax.swing.SwingUtilities;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import matchpackage.application.CustomerGUI;
import matchpackage.application.EnhancedAgent;
import matchpackage.contract.ClientChatGUI;
import matchpackage.contract.ClientFeedbackGUI;
import matchpackage.contract.ClientProjectGUI;
import matchpackage.contract.ProviderChatGUI;
import matchpackage.contract.ProviderProjectGUI;
import matchpackage.database.Customer;
import matchpackage.database.Provider;
import matchpackage.database.ProviderList;

public class CustomerAgent extends EnhancedAgent {

	CustomerGUI customerGUI;
	Set<AID> foundAgents;
	int check = 0;
	String keywords = "";
	String providers = "";
	ProviderList providerListNew;
	private ArrayList<Provider> sortedProviders;
	private ArrayList<Provider> leftProviders;
	private ArrayList<Provider> premiumProviders;
	private ClientProjectGUI clientProjectGUI;
	private ClientChatGUI clientChatGUI;
	private int render = 0;
	String changeRequestText = "PENDING";
	String providerName = "";
	ClientFeedbackGUI clientFeedbackGUI;

	protected void setup() {

		customerGUI = new CustomerGUI(this);
		providerListNew = new ProviderList();
		addBehaviour(new ShowGUICustomer(this, 10000));

	}

	public void closeFeedbackWindow()

	{
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				clientFeedbackGUI.setVisible(false);

			}
		});

	}

	public void changeRequest(String text) {
		changeRequestText = text;
		System.out.println("I am inside changeRequest fucntion");
		System.out.println("Valeu of changeRequestText is " + changeRequestText);
	}

	public void sendPaymentConfirmation() {

		addBehaviour(new SendPaymentConfirmationBehaviour());

	}

	private class SendPaymentConfirmationBehaviour extends OneShotBehaviour {

		@Override
		public void action() {
			// TODO Auto-generated method stub
			ACLMessage sendMessageConfirm = new ACLMessage(ACLMessage.AGREE);
			sendMessageConfirm.setContent("Payment done");
			sendMessageConfirm.addReceiver(new AID("BIDDING", AID.ISLOCALNAME));
			send(sendMessageConfirm);

		}

	}

	public void afterAcceptingContract(String text) {

		System.out.println("I am in One shot behaviour customer after accepting contract");
		addBehaviour(new SendContractMessage(text, this));

	}

	private class SendContractMessage extends OneShotBehaviour {

		String contractDec;
		CustomerAgent myAgent;

		SendContractMessage(String dec, CustomerAgent a) {
			this.myAgent = a;
			this.contractDec = dec;
		}

		@Override
		public void action() {
			// TODO Auto-generated method stub

			System.out.println("Am i going in the send contract customer agent");

			ACLMessage msgContractDec = new ACLMessage(ACLMessage.INFORM);
			msgContractDec.addReceiver(new AID("Bidding", AID.ISLOCALNAME));
			msgContractDec.setContent(contractDec);
			send(msgContractDec);

			System.out.println("Project is on too in customer");
			ACLMessage startMsgClient = blockingReceive();
			if (startMsgClient.getPerformative() == ACLMessage.REQUEST_WHENEVER) {

				clientProjectGUI = new ClientProjectGUI(myAgent);
				clientChatGUI = new ClientChatGUI();
			}

			ACLMessage messageContent = blockingReceive();
			if (messageContent.getPerformative() == ACLMessage.INFORM) {
				providerName = messageContent.getSender().toString();
				String msgContent = messageContent.getContent();
				String[] dataContent = msgContent.split("\\*");
				clientProjectGUI.setDeadlineArea(dataContent[0]);
				clientProjectGUI.setProgressArea(dataContent[1]);
				clientProjectGUI.setTimeArea(dataContent[2]);

			}
//			int a = 0;
//			while (changeRequestText.contentEquals("PENDING")) {
//				if (a == 10000) {
//					System.out.println("I am inside while");
//					a = 0;
//				}
//					
//				a = a+1;
//			}
//			System.out.println("I am out of while loop");
//			System.out.println("The provider is " + providerName);
//			
//
//			ACLMessage messageChangeText = new ACLMessage(ACLMessage.PROPOSE);
//			messageChangeText.setContent(changeRequestText);
//			messageChangeText.addReceiver(new AID(providerName, AID.ISLOCALNAME));
//			send(messageChangeText);
//			System.out.println("I have sent the message");
//
//			ACLMessage messageDecisionText = blockingReceive();
//			if (messageDecisionText.getPerformative() == ACLMessage.INFORM) {
//				clientProjectGUI.setTextArea(messageDecisionText.getContent());
//			}

			ACLMessage messageContentEnd = blockingReceive();
			if (messageContentEnd.getPerformative() == ACLMessage.CANCEL) {
				String messageContentText = messageContentEnd.getContent();

				clientProjectGUI.setDeadlineArea("");
				clientProjectGUI.setProgressArea("");
				clientProjectGUI.setTimeArea("");
				clientProjectGUI.setStatusArea(messageContentText);

			}

			clientFeedbackGUI = new ClientFeedbackGUI(myAgent);

			System.out.println("I have reached here");
			ACLMessage messagePayment = blockingReceive();
			if (messagePayment.getPerformative() == ACLMessage.REQUEST) {

				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						System.out.println("I am reaching here in though");
						clientFeedbackGUI.setPaymentArea(messagePayment.getContent());

					}
				});

			}
		}

	}

	public void setKeywords(String words) {

		this.keywords = words;
		addBehaviour(new DisplaySortProviders(this, 10000));
		render = 1;

	}

	private class DisplaySortProviders extends TickerBehaviour {

		String displayProviders = "";

		DisplaySortProviders(Agent a, long period) {

			super(a, period);

		}

		@Override
		protected void onTick() {

			sortedProviders = new ArrayList<Provider>();
			leftProviders = new ArrayList<Provider>();
			premiumProviders = new ArrayList<Provider>();

			String[] kewywordsSplitArray1 = keywords.split(",");
			ArrayList<String> keywordsSplit1 = new ArrayList<String>();

			for (String i : kewywordsSplitArray1) {
				keywordsSplit1.add(i);
			}

			for (Provider provider : providerListNew.getProviders()) {

				ArrayList<String> dataKeywords1 = provider.getKeywords();
//
//				if (!(Collections.disjoint(dataKeywords1, keywordsSplit1)))
//					sortedProviders.add(provider);
//				else
//					leftProviders.add(provider);
				
				if(provider.getPlan().contentEquals("Premium")) {
					premiumProviders.add(provider);
				}
				else {
					if(!(Collections.disjoint(dataKeywords1, keywordsSplit1)) )
						sortedProviders.add(provider);
					else
						leftProviders.add(provider);
				}

			}
			
			premiumProviders.addAll(sortedProviders);
			premiumProviders.addAll(leftProviders);

			//sortedProviders.addAll(leftProviders);

			displayProviders = providerListNew.getStringProviders(premiumProviders);

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					// customerGUI.setTextContract(displayProviders);
					customerGUI.setContentListProvider(displayProviders);

					customerGUI.getProviderTable().repaint();

				}
			});

		}
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
				check = 1;

				break;

			case 1:
				// System.out.println("I am in 1");
				foundAgents = searchForService("Web_services");
				// System.out.println(foundAgents);
//				for (AID j : foundAgents) {
//					System.out.println("Name of the agents are");
//				}

				ACLMessage msgRequest = new ACLMessage(ACLMessage.REQUEST);
				System.out.println("Am i here");
				msgRequest.addReceiver(new AID("match", AID.ISLOCALNAME));
				msgRequest.setContent("Get providers in Customer");
				send(msgRequest);

				ACLMessage msgGetProvider = myAgent.blockingReceive();
//				System.out.println("This is in case 1 of customer agent");
//				System.out.println(msgGetProvider.getContent());
//				System.out.println("Yes I am updating the table");
				providers = msgGetProvider.getContent();

				if (render == 0) {
					// customerGUI.setTextContract(msgGetProvider.getContent());
					customerGUI.setContentListProvider(msgGetProvider.getContent());
					customerGUI.getProviderTable().repaint();

					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							// customerGUI.setTextContract(msgGetProvider.getContent());
							customerGUI.setContentListProvider(msgGetProvider.getContent());

							customerGUI.getProviderTable().repaint();

						}
					});
				}

				String allProviders = msgGetProvider.getContent();
				providerListNew = new ProviderList();
				ArrayList<Provider> newUpdatedProviders = new ArrayList<Provider>();

				String[] rowsDummy = allProviders.split("\n");
				int rowNum = 0;
				for (String i : rowsDummy) {
					rowNum = rowNum + 1;
					String[] column = i.split("\\*");
					Double comp = Double.parseDouble(column[5]);
					String listKeywordsProviderDummy = column[3];
					listKeywordsProviderDummy = listKeywordsProviderDummy.substring(1,
							listKeywordsProviderDummy.length() - 1);
					String[] finalKeywordProvider = listKeywordsProviderDummy.split(",");
					ArrayList<String> listKeywordsProvidersDummy = new ArrayList<String>();
					for (String j : finalKeywordProvider)
						listKeywordsProvidersDummy.add(j);
					Provider provider = new Provider(column[0], "dummmy", column[1], column[2], comp,
							listKeywordsProvidersDummy, column[4]);
					newUpdatedProviders.add(provider);

				}

				providerListNew.setProviders(newUpdatedProviders);
//				System.out.println("Checking out the providers");

//				System.out.println(providerListNew.getStringProviders());

				break;

			}

		}

	}

	public void placeBid(String providerName, Double bidValues) {

		addBehaviour(new SendBidData(providerName, bidValues));

	}

	private class SendBidData extends OneShotBehaviour {

		String providerBidName;
		double providerBidValue;

		SendBidData(String value, double bidValue) {

			this.providerBidName = value;
			this.providerBidValue = bidValue;
		}

		@Override
		public void action() {
			// TODO Auto-generated method stub

			ACLMessage bidMsg = new ACLMessage(ACLMessage.PROPOSE);
			bidMsg.addReceiver(new AID(providerBidName, AID.ISLOCALNAME));
			bidMsg.setContent(Double.toString(providerBidValue));
			send(bidMsg);

			ACLMessage recMsg = blockingReceive();
			System.out.println(recMsg.getPerformative());
			if (recMsg.getPerformative() == ACLMessage.REJECT_PROPOSAL) {
				customerGUI.showBidderStatus(recMsg.getContent());
				customerGUI.setBidValueArea("");

			}

			if (recMsg.getPerformative() == ACLMessage.PROPOSE) {
				customerGUI.showBidderStatus("Bid has been accepted");
				customerGUI.setTextContract(recMsg.getContent());
				customerGUI.setBidValueArea("");
			}
		}
	}

}
