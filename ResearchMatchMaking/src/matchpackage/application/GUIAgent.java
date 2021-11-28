package matchpackage.application;

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
	
	
	protected void setup() {

		System.out.println("I am an agent");
		System.out.println("This is my name : " + getAID().getName());
		System.out.println("My global name is " + getAID().getLocalName());
		System.out.println("My addresses are :" + getAID().getAllAddresses());

		
		appGUI = new AppGUI(this);
		createAgent("Access", "matchpackage.access.AccessAgent");
		addBehaviour(new getListProviders());
	}
	
	private void createAgent(String name, String className) {
      	AID agentID = new AID( name, AID.ISLOCALNAME );
      	ContainerController controller = getContainerController();
     
      	
      	try {
      		AgentController agent = controller.createNewAgent( name, className, null );
      		agent.start();
      		System.out.println("+++ Created: " + agentID);
      	}
      	catch (Exception e){ e.printStackTrace(); }
  }


	
	public class getListProviders extends OneShotBehaviour{

		@Override
		public void action() {
			// TODO Auto-generated method stub
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			msg.addReceiver(new AID("Access", AID.ISLOCALNAME));
			msg.setLanguage("English");
			msg.setContent("Get providers");
			send(msg);
			
			
			
		}
		
	
	

}
}
