package matchpackage.application;

import jade.core.Agent;
import matchpackage.application.AppGUI;

public class GUIAgent extends Agent {

	AppGUI appGUI;

	protected void setup() {

		System.out.println("I am an agent");
		System.out.println("This is my name : " + getAID().getName());
		System.out.println("My global name is " + getAID().getLocalName());
		System.out.println("My addresses are :" + getAID().getAllAddresses());

		
		appGUI = new AppGUI();

	}

}
