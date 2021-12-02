package matchpackage.application;

import javax.swing.SwingUtilities;

import jade.core.AID;
import jade.core.Agent;
import jade.core.AgentContainer;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
//import jade.wrapper.ContainerController;
//import jade.wrapper.AgentContainer;
import jade.core.AgentContainer;
import matchpackage.application.AppGUI;
import jade.core.behaviours.*;

public class GUIAgent extends Agent {

	AppGUI appGUI;
	private int step = 0;
	private boolean bool = false;

	public boolean isBool() {
		return bool;
	}

	public void setBool(boolean bool) {
		this.bool = bool;
	}

	protected void setup() {

		System.out.println("I am an agent");
		System.out.println("This is my name : " + getAID().getName());
		System.out.println("My global name is " + getAID().getLocalName());
		System.out.println("My addresses are :" + getAID().getAllAddresses());

		appGUI = new AppGUI(this);
		createAgent("Access", "matchpackage.access.AccessAgent");
		createAgent("Search", "matchpackage.search.SearchAgent");
		addBehaviour(new getListProviders(""));
	}

	public void callSearchKeywords(String keywords) {
		// TODO Auto-generated method stub
		System.out.println("I am reaching in method in GUI Agent 5");
		addBehaviour(new getListProviders(keywords));
		
		
	}

	private void createAgent(String name, String className) {
		AID agentID = new AID(name, AID.ISLOCALNAME);
		ContainerController controller = getContainerController();

		try {
			AgentController agent = controller.createNewAgent(name, className, null);
			agent.start();
			System.out.println("+++ Created: " + agentID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class getListProviders extends Behaviour implements Runnable {

		String keywords;

		private getListProviders(String words) {
			this.keywords = words;

		}

		@Override
		public void action() {

			switch (step) {

			case 1:
				ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
				msg.addReceiver(new AID("Access", AID.ISLOCALNAME));
				msg.setLanguage("English");
				msg.setContent("Get providers");
				send(msg);
				ACLMessage msgGet = myAgent.blockingReceive();
				System.out.println(msgGet);

				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {

						appGUI.getGuestGUI().setContentListProvider(msgGet.getContent() + "\n");
						
					}
				});

				System.out.println("I am back in GUIAgent");
				bool = true;
				break;

			case 2:
				
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
				bool = true;
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
