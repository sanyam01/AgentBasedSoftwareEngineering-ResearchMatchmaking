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
	private int render = 0;

	// Adding Mingrui Code - 1 [Start]
	// .................................................................

//	String status;
//	String status1;
//	String status2;
//	BiddingManager bidBehaviour;

	protected void setup() {

		customerGUI = new CustomerGUI(this);
		providerListNew = new ProviderList();
		addBehaviour(new ShowGUICustomer(this, 10000));

		// Adding Mingrui Code -2 [Start]
		// .................................................................
		// this.status = "Not done yet";

	}

	// Adding Mingrui Code -3 [Start]
	// .................................................................
	// .................................................................
	// .................................................................

//	public void startBidding(String providerName, Double price) {
//		this.bidBehaviour = new BiddingManager(this, price, providerName);
//		addBehaviour(bidBehaviour);
//	}

//	private class BiddingManager extends SimpleBehaviour {
//		// private int providerID;
//		private float price;
//		private long initTime;
//		Agent parent;
//		private int compensationPrice;
//		boolean succ;
//		int step = 0;
//		double cnt;
//		String providerNameBid;

//		public BiddingManager(Agent parent, double price, String providerNameBid) {
//
//			this.succ = false;
//			this.parent = parent;
//			this.cnt = price;
//			this.providerNameBid = providerNameBid;
//
//		}
//
//		@Override
//		public void action() {
//			while (true) {
//				switch (step) {
//				// contract agent
//				case 0:
//					while (true) {
//
//						ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
//						String localname = "bidder";
//						AID receiver = new AID(providerNameBid, AID.ISLOCALNAME);
//						cfp.addReceiver(receiver);
//						cfp.setContent(String.valueOf(cnt + 10));
//						cnt = cnt + 10;
//					
//						myAgent.send(cfp);
//
//						ACLMessage recMsg = myAgent.blockingReceive();
//
//						if (recMsg != null) {
//
//							if (recMsg.getPerformative() == 0) {
//
//								System.out.println("[Sneder]Bidding is successful at price " + recMsg.getContent());
//								status = "Bidding is successful at price " + recMsg.getContent()
//										+ " and contract will be sending";
//								status1 = " The system will receive 30% of\n" + "any transaction";
//								customerGUI.showBidderStatus(status);
//								customerGUI.showBiddingResults(status1);
//								succ = true;
//								ACLMessage msg = new ACLMessage(ACLMessage.CFP);
//								step = 1;
//								break;
//
//							} else if (recMsg.getPerformative() == 15) {
//
//								System.out.println("[Sender]Bidding fails at price " + recMsg.getContent());
//								step = 0;
//
//							}
//						}
//						doWait(10000);
//
//					}
//					break;
//
//// Send the cfp to all sellers
//
//				case 1:
//					ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
////                  
//					AID receiver1 = new AID("reveiver", AID.ISLOCALNAME);
//					msg.addReceiver(receiver1);
//					msg.setContent("Accepted or refused. The system will receive 30% of\n" + "any transaction");
//					
//					myAgent.send(msg);
//					ACLMessage recMsg1 = myAgent.blockingReceive();
//					if (recMsg1 != null) {
//						System.out.println(recMsg1.getPerformative());
//						if (recMsg1.getPerformative() == 0) {
//							System.out.println("[Contract Sender]contract is successful at " + recMsg1.getContent());
//							this.succ = true;
//							step = 2;
//
//						} else if (recMsg1.getPerformative() == 15) {
//							System.out.println("[Contract Sender]contract fails at " + recMsg1.getContent());
//						}
//					}
//
//					System.out.println("System completed");
//					break;
//				case 2:
//					ACLMessage REQUEST = new ACLMessage(ACLMessage.REQUEST);
//					// String localname = "bidder";
//					AID receiver2 = new AID("reveiver", AID.ISLOCALNAME);
//					REQUEST.addReceiver(receiver2);
//					REQUEST.setContent("client wants to change project");
//					myAgent.send(REQUEST);
//
//					ACLMessage recMsg2 = myAgent.blockingReceive();
//					if (recMsg2 != null) {
//						System.out.println(recMsg2.getPerformative());
//						if (recMsg2.getPerformative() == 0) {
//							status2 = "the change is accepted by provider";
//							customerGUI.showChangeResults(status2);
//							System.out.println("[change Sender]provider can change this ? " + recMsg2.getContent());
//							step = 3;
//
//						} else if (recMsg2.getPerformative() == 15) {
//							status2 = "the change is rejected by provider";
//							customerGUI.showChangeResults(status2);
//							System.out.println("[change Sender]contract fails at " + recMsg2.getContent());
//						}
//					}
//					break;
//				case 3:
//					break;
//
//				}
//
//			}
//		}
////                this.cnt=+10;
//
//		@Override
//		public boolean done() {
//			return false;
//		}
//
//	}

	// Adding Mingrui Code -3 [Ends]
	// .................................................................
	// .................................................................
	// .................................................................

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

			String[] kewywordsSplitArray1 = keywords.split(",");
			ArrayList<String> keywordsSplit1 = new ArrayList<String>();

			for (String i : kewywordsSplitArray1) {
				keywordsSplit1.add(i);
			}

			for (Provider provider : providerListNew.getProviders()) {

				ArrayList<String> dataKeywords1 = provider.getKeywords();

				if (!(Collections.disjoint(dataKeywords1, keywordsSplit1)))
					sortedProviders.add(provider);
				else
					leftProviders.add(provider);

			}

			sortedProviders.addAll(leftProviders);

			displayProviders = providerListNew.getStringProviders(sortedProviders);

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
				providers = msgGetProvider.getContent();

				if (render == 0) {
					customerGUI.setTextContract(msgGetProvider.getContent());
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
				System.out.println("Checking out the providers");

				System.out.println(providerListNew.getStringProviders());

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
		}
	}
}
