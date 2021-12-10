package matchpackage.application;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.SwingUtilities;

import jade.core.AID;
import jade.core.Agent;
import jade.core.AgentContainer;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
//import jade.wrapper.ContainerController;
//import jade.wrapper.AgentContainer;
import jade.core.AgentContainer;
import matchpackage.application.AppGUI;
import matchpackage.database.Customer;
import matchpackage.database.CustomerList;
import matchpackage.database.Provider;
import matchpackage.database.ProviderList;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class GUIAgent extends EnhancedAgent {

	AppGUI appGUI;
	private int step = 0;
	private boolean bool = false;
	private SignUpGUI signUpGUI;
	private LoginGUI loginGUI;
	private AID[] providers;
	private CustomerList customerList;
	private ProviderList providerList;
	private ArrayList<Provider> sortedProviders1;
	private ArrayList<Provider> leftProviders1;
	private ArrayList<Provider> premiumProviders1;

	public boolean isBool() {
		return bool;
	}

	public void setBool(boolean bool) {
		this.bool = bool;
	}

	protected void setup() {

		System.out.println("I am in GUIAgent");
		System.out.println(".............................................");
		System.out.println("*********************************************");
		System.out.println(getAID());
		System.out.println(getAID().getLocalName());

		appGUI = new AppGUI(this);

		createAgent("Access", "matchpackage.access.AccessAgent");
		createAgent("Search", "matchpackage.search.SearchAgent");

		customerList = new CustomerList();
		providerList = new ProviderList();

		addBehaviour(new getListProviders());

	}

	public void runAfterLogin() {
		addBehaviour(new SendListProvidersCustomer());

	}

	public void createCustomerAgent(String name, String password) {
		Customer customer = new Customer(name, password);
		customerList.addCustomer(customer);

		AID customerAID = createAgentAID(name, "matchpackage.access.CustomerAgent");

	}

	public void createProviderAgent(String name, String password, String website, String logo, double compensation,
			ArrayList<String> keywords, String resume, String plan) {

		Provider provider = new Provider(name, password, website, logo, compensation, keywords, resume);
		provider.setPlan(plan);
		providerList.addProvider(provider);
		AID providerAID = createAgentAID(name, "matchpackage.access.ProviderAgent");
		register("Web_services", providerAID);
		setGuestProviders();

	}

	public void showCustomerProviderGUI(String name) {

		addBehaviour(new ShowGUICustomerProvider(name));

	}

	public void setGuestProviders() {

		appGUI.getGuestGUI().setContentListProvider(providerList.getStringProvidersGuest() + "\n");

	}

	public class ShowGUICustomerProvider extends OneShotBehaviour {

		String name;

		public ShowGUICustomerProvider(String name) {
			this.name = name;
		}

		@Override
		public void action() {

			System.out.println("class ShowGUICustomerProvider extends OneShotBehaviour");
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			msg.addReceiver(new AID(name, AID.ISLOCALNAME));
			msg.setContent("Open GUI");
			send(msg);
			step = 3;

		}

	}

	public class SendListProvidersCustomer extends CyclicBehaviour {

		@Override
		public void action() {
			// TODO Auto-generated method stub
			ACLMessage msgGetProvider = myAgent.receive();
			if (msgGetProvider.getContent().contentEquals("Get providers in Customer")) {

				ACLMessage reply = msgGetProvider.createReply();
				reply.setContent(providerList.getStringProviders());
				send(reply);
				System.out.println("I am in case 3 GUI agent");

				// System.out.println(msgGetProvider);

			}

		}

	}

	public class getListProviders extends Behaviour {

		String keywords = "";

		@Override
		public void action() {

			switch (step) {

			case 2:
				//...................................
				sortedProviders1 = new ArrayList<Provider>();
				leftProviders1 = new ArrayList<Provider>();
				premiumProviders1 = new ArrayList<Provider>();
				//...................................
				keywords = appGUI.getGuestGUI().getStringKeyWords();
				ACLMessage msgSearch = new ACLMessage(ACLMessage.REQUEST);
				msgSearch.addReceiver(new AID("Search", AID.ISLOCALNAME));
				msgSearch.setLanguage("English");
				msgSearch.setContent(keywords);
				send(msgSearch);
				ACLMessage msgResponse = myAgent.blockingReceive();
				//System.out.println(msgResponse);

//				SwingUtilities.invokeLater(new Runnable() {
//					@Override
//					public void run() {
//
//						//appGUI.getGuestGUI().setContentListProvider(msgResponse.getContent() + "\n");
//						appGUI.getGuestGUI().setEnterKeywords("");
//
//						System.out.println("1000");
//					}
//				});
				
				//.............................................................................
				//.............................................................................
				

				String[] kewywordsSplitArray1 = keywords.split(",");
				ArrayList<String> keywordsSplit1 = new ArrayList<String>();
				
				
				
				for (String i : kewywordsSplitArray1) {
					keywordsSplit1.add(i);
				}
				
				for (Provider provider : providerList.getProviders()) {
					
					ArrayList<String> dataKeywords1 = provider.getKeywords();
					
					if(provider.getPlan().contentEquals("Premium")) {
						premiumProviders1.add(provider);
					}
					else {
						if(!(Collections.disjoint(dataKeywords1, keywordsSplit1)) )
							sortedProviders1.add(provider);
						else
							leftProviders1.add(provider);
					}
					
					
					
					
				}
				premiumProviders1.addAll(sortedProviders1);
				premiumProviders1.addAll(leftProviders1);
				
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {

						//appGUI.getGuestGUI().setContentListProvider(msgResponse.getContent() + "\n");
						appGUI.getGuestGUI().setContentListProvider(providerList.getStringProvidersGuest(premiumProviders1) + "\n");
						appGUI.getGuestGUI().setEnterKeywords("");

						System.out.println("1000");
					}
				});
				
				//appGUI.getGuestGUI().setContentListProvider(providerList.getStringProvidersGuest(sortedProviders1) + "\n");
				

				System.out.println("I am back in case2");
				step = 0;

				// bool = true;
				break;

			case 3:

				ACLMessage msgGetProvider = myAgent.blockingReceive();
				if (msgGetProvider.getContent().contentEquals("Get providers in Customer")) {

					ACLMessage reply = msgGetProvider.createReply();
					reply.setContent(providerList.getStringProviders());
					send(reply);
					System.out.println("I am in case 3 GUI agent");

					//System.out.println(msgGetProvider);

				}

				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {

						appGUI.getGuestGUI().setContentListProvider(providerList.getStringProvidersGuest() + "\n");

					}
				});
				
				

				break;

			}

		}

		@Override
		public boolean done() {
			// TODO Auto-generated method stub

			return bool;

		}

	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

}
