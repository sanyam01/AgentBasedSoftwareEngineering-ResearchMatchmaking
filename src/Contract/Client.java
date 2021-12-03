package Contract;

import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
public class Client extends Agent {
    protected void setup() {
        System.out.println("Client-agent " + getAID().getName() + " is ready.");
        String contract="contract";

        addBehaviour(new ContractManager(this,contract));
    }
    private class ContractManager extends SimpleBehaviour {

        Agent parent;
        boolean succ;
        String contract;
        public ContractManager(Client contractManager,String contract) {
            this.succ = false;
            this.contract=contract;



        }

        @Override
        public void action() {
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            String localname = "client";
            AID receiver = new AID("provider", AID.ISLOCALNAME);
            msg.addReceiver(receiver);
            msg.setContent("Accepted or refused. The system will receive 30% of\n" +
                    "any transaction");
//                        cfp.setConversationId("book-trade");
//            cfp.setReplyWith("cfp" + System.currentTimeMillis()); // Unique value
            myAgent.send(msg);
            ACLMessage recMsg = myAgent.blockingReceive();
            if (recMsg != null) {
                System.out.println(recMsg.getPerformative());
                if (recMsg.getPerformative() == 0) {
                    System.out.println("[Sneder]contract is successful at " + recMsg.getContent());
                    this.succ = true;

                } else if (recMsg.getPerformative() == 15) {
                    System.out.println("[Sender]contract fails at " + recMsg.getContent());
                }
            }
        }

        @Override
        public boolean done() {
            return false;
        }
    }

}
