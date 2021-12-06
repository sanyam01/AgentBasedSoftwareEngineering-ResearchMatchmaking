package matchpackage.application;

import jade.domain.FIPAException;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

import java.util.Set;
import java.util.HashSet;
import jade.core.Agent;
import jade.core.AID;

public class EnhancedAgent extends Agent {
	protected Set<AID> searchForService(String serviceName) {
		Set<AID> foundAgents = new HashSet<>();
		DFAgentDescription dfd = new DFAgentDescription();
		ServiceDescription sd = new ServiceDescription();
		sd.setType(serviceName.toLowerCase());
		dfd.addServices(sd);

		SearchConstraints sc = new SearchConstraints();
		sc.setMaxResults(Long.valueOf(-1));

		try {
			DFAgentDescription[] results = DFService.search(this, dfd, sc);
			for (DFAgentDescription result : results) {
				foundAgents.add(result.getName());
			
			}
			System.out.println("I am  in DFAgent Desription");
			return foundAgents;
		} catch (FIPAException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	protected void takeDown() {
		try {
			DFService.deregister(this);
		} catch (Exception ex) {
		}
	}

	protected void register(String serviceName, AID accessAID) {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(accessAID);
		ServiceDescription sd = new ServiceDescription();
		sd.setName(getLocalName());
		sd.setType(serviceName.toLowerCase());
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		} catch (FIPAException ex) {
			ex.printStackTrace();
		}
	}
	
	protected AID createAgentAID(String name, String className) {
		AID agentID = new AID(name, AID.ISLOCALNAME);
		ContainerController controller = getContainerController();

		try {
			AgentController agent = controller.createNewAgent(name, className, null);
			agent.start();
			System.out.println("+++ Created: " + agentID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return agentID;

	}
	
	protected void createAgent(String name, String className) {
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
	

}
