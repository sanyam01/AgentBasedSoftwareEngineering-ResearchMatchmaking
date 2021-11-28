package matchpackage.access;

import jade.core.Agent;
import matchpackage.application.AppGUI;
import matchpackage.application.GuestGUI;

public class AccessAgent extends Agent {

	protected void setup() {

		System.out.println("I am an Access Agent");
		System.out.println("This is my name : " + getAID().getName());
		System.out.println("My global name is " + getAID().getLocalName());
		System.out.println("My addresses are :" + getAID().getAllAddresses());

	}

}
