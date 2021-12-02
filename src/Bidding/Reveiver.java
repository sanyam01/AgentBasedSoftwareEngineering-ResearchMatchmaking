package Bidding;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Reveiver extends Agent {

    public void setup() {
        addBehaviour(new ReplyProposal(this, 1000));
    }

    private class ReplyProposal extends TickerBehaviour {

        private Agent parent;

        public ReplyProposal(Agent a, long period) {
            super(a, period);
            parent = a;
        }

        @Override
        protected void onTick() {
            System.out.println("[Receiver]Trying to receive bidding");
            ACLMessage acl1 = parent.receive(MessageTemplate.MatchPerformative(ACLMessage.CFP));
            if (acl1 != null) {
                System.out.println("[Receiver] Received a bidding");
                System.out.println("[Receiver]the bidding is: " + acl1.getContent());
            } else {
                System.out.println("[Receiver]Did not receive a bid");
                return;
            }
            ACLMessage reply;
            if (Integer.parseInt(acl1.getContent()) > 100) {
                reply = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
                reply.addReceiver(acl1.getSender());
                reply.setContent(acl1.getContent());
            } else {
                reply = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
                reply.addReceiver(acl1.getSender());
                reply.setContent(acl1.getContent());
            }
            parent.send(reply);
        }
    }
}
