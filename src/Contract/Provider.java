package Contract;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
public class Provider extends Agent {
    public void setup() {
        addBehaviour(new ReplyProposal(this));
    }
//    protected void takeDown() {
//        try {
//            DFService.deregister(this);
//        }
//        catch (FIPAException fe) {
//            fe.printStackTrace();
//        }
//// Dispose the GUI if it is there
////        if (myGui != null) {
//////            myGui.dispose();
////        }
//// Printout a dismissal message
//        System.out.println("Seller-agent "+getAID().getName()+" terminating.");
//    }
    private class ReplyProposal extends SimpleBehaviour {
        private Agent parent;

        public ReplyProposal(Agent provider) {
//            super(provider, period);
            this.parent = provider;
        }

        @Override
        public void action() {
            ACLMessage reply;
            System.out.println("[Receiver]Trying to receive contract");
            ACLMessage acl1 = parent.receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
            if (acl1 != null) {
                System.out.println("[Receiver] Received a contract");
                System.out.println("[Receiver] Accepted or Refused: " + acl1.getContent());
                MessageTemplate template =
                        MessageTemplate.MatchPerformative(ACLMessage.INFORM);
                ACLMessage msg = parent.receive(template);

                reply = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);

//                msg.addReceiver(new AID("provider", AID.ISLOCALNAME));
//                reply.addReceiver(acl1.getSender());
                reply.setContent(acl1.getContent());
            } else {

                System.out.println("[Receiver] Did not receive a contract");
                block();
                return;
            }





//            if (acl1.getContent() =="contract") {
//                reply = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
//                reply.addReceiver(acl1.getSender());
//                reply.setContent(acl1.getContent());
//            } else {
//                reply = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
//                reply.addReceiver(acl1.getSender());
//                reply.setContent(acl1.getContent());
//            }
            myAgent.send(reply);
        }

        @Override
        public boolean done() {
            return false;
        }
    }

    }
