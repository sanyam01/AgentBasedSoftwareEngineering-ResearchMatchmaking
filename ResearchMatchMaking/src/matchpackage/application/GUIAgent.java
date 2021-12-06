package matchpackage.application;

import java.util.ArrayList;

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
//	public void callSearchKeywords(String keywords) {
//		// TODO Auto-generated method stub
//		System.out.println("I am reaching in method in GUI Agent 5");
//		addBehaviour(new getListProviders(keywords));
//
//	}

	public void createCustomerAgent(String name, String password) {
		Customer customer = new Customer(name, password);
		customerList.addCustomer(customer);

		AID customerAID = createAgentAID(name, "matchpackage.access.CustomerAgent");

	}

	public void createProviderAgent(String name, String password, String website, String logo, double compensation,
			ArrayList<String> keywords, String resume) {

		Provider provider = new Provider(name, password, website, logo, compensation, keywords, resume);
		providerList.addProvider(provider);
		AID providerAID = createAgentAID(name, "matchpackage.access.ProviderAgent");
		register("Web_services", providerAID);

	}

	public void showCustomerProviderGUI(String name) {

		addBehaviour(new ShowGUICustomerProvider(name));
		//this.step = 3;
		//addBehaviour(new getListProviders(""));
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
	
	public class SendListProvidersCustomer extends CyclicBehaviour{

		@Override
		public void action() {
			// TODO Auto-generated method stub
			ACLMessage msgGetProvider = myAgent.receive();
			if (msgGetProvider.getContent().contentEquals("Get providers in Customer")) {

				ACLMessage reply = msgGetProvider.createReply();
				reply.setContent(providerList.getStringProviders());
				send(reply);
				System.out.println("I am in case 3 GUI agent");

				System.out.println(msgGetProvider);
				
			}
			
		}
		
	}

	public class getListProviders extends Behaviour implements Runnable {

		String keywords = "";

//		private getListProviders(String words) {
//			this.keywords = words;
//
//		}

		@Override
		public void action() {

			switch (step) {

			case 2:
				
				keywords = appGUI.getGuestGUI().getStringKeyWords();
				ACLMessage msgSearch = new ACLMessage(ACLMessage.REQUEST);
				msgSearch.addReceiver(new AID("Search", AID.ISLOCALNAME));
				msgSearch.setLanguage("English");
				msgSearch.setContent(keywords);
				send(msgSearch);
				ACLMessage msgResponse = myAgent.blockingReceive();
				System.out.println(msgResponse);

				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {

						appGUI.getGuestGUI().setContentListProvider(msgResponse.getContent() + "\n");
						appGUI.getGuestGUI().setEnterKeywords("");

						System.out.println("1000");
					}
				});

				System.out.println("I am back in case2");
				step = 0;
				
				//bool = true;
				break;

			case 3:
				
				ACLMessage msgGetProvider = myAgent.blockingReceive();
				if (msgGetProvider.getContent().contentEquals("Get providers in Customer")) {

					ACLMessage reply = msgGetProvider.createReply();
					reply.setContent(providerList.getStringProviders());
					send(reply);
					System.out.println("I am in case 3 GUI agent");

					System.out.println(msgGetProvider);
					
					
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {

							appGUI.getGuestGUI().setContentListProvider(providerList.getStringProvidersGuest() + "\n");
							
						}
					});
					
					
					
					
					
				}
				
				setGuestProviders();
				
				break;

			}
					

		}

		@Override
		public boolean done() {
			// TODO Auto-generated method stub

			return bool;

		}

		@Override
		public void run() {
			// TODO Auto-generated method stub

		}

	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

}
