package matchpackage;

import jade.core.Agent;
public class TestClass extends Agent {

	protected void setup() {
		
		System.out.println("I am an agent");
		System.out.println("This is my name : " + getAID().getName());
		System.out.println("My global name is " + getAID().getLocalName());
		System.out.println("My addresses are :" + getAID().getAllAddresses());

		Object[] args = getArguments();
		if (args != null) {
			for (int i = 0; i < args.length; ++i) {
				System.out.println(args[i]);
			}
		}

	}
	
}
