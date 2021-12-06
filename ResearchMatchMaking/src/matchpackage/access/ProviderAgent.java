package matchpackage.access;



import jade.core.Agent;
import matchpackage.application.EnhancedAgent;
import matchpackage.application.ProviderGUI;
import matchpackage.database.Provider;

public class ProviderAgent extends EnhancedAgent{
	
	ProviderGUI providerGUI;
	Provider provider;
	

	protected void setup() {
		providerGUI = new ProviderGUI();
		System.out.printf("Hello! My name is %s%n", getLocalName());
	}
	

	
}
