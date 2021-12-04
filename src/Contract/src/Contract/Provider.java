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
        System.out.println("This is my name : " + getAID().getName());
        System.out.println("My global name is " + getAID().getLocalName());
        System.out.println("My addresses are :" + getAID().getAllAddresses());
        addBehaviour(new ReplyProposal(this));
    }
    private class ReplyProposal extends Behaviour {
        private Agent parent;
        private int step = 0;

        public ReplyProposal(Agent provider) {
//            super(provider, period);
            this.parent = provider;
        }

        @Override
        public void action() {
            while(true) {
                doWait(1000);
                switch (step) {
                    //contract agent
                    case 0:
                        ACLMessage reply;

                        System.out.println("[ContractReceiver]Trying to receive contract");

                        ACLMessage acl1 = parent.blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));

                        if (acl1 != null) {
                            System.out.println("[ContractReceiver] Received a contract");
                            System.out.println("[ContractReceiver] Accepted or Refused: " + acl1.getContent());
                            MessageTemplate template =
                                    MessageTemplate.MatchPerformative(ACLMessage.INFORM);
//                        ACLMessage msg = parent.receive(template);

                            reply = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);

//                msg.addReceiver(new AID("provider", AID.ISLOCALNAME));
                            reply.addReceiver(acl1.getSender());
                            reply.setContent("contract already created");
                            myAgent.send(reply);
                            step = 1;

                        } else {

                            System.out.println("[ContractReceiver] Did not receive a contract");
                            reply = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
                            block();
                            return;
                        }
                        System.out.println("Provider 0 completed");

                        step = 1;
                        //break;
                    //project agent
                    case 1:
                        ACLMessage replayProjectcreater;
                        System.out.println("[ProjectReceiver]Trying to receive projectCreator");
                        ACLMessage acl2 = parent.blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL));

                        System.out.println("[ProjectReceiver] Accepted or Refused: " + acl2.getContent());

                        replayProjectcreater = acl2.createReply();
                        try {
                            if (acl2 != null) {
                                // reply with a confirmation
                                replayProjectcreater.setContent("prejectCreater agent");
                                replayProjectcreater.setPerformative(ACLMessage.CONFIRM);
                            } else {
                                // reply with a disconfirmation
                                replayProjectcreater.setPerformative(ACLMessage.DISCONFIRM);
                            }
                            // if we weren't sent back a proper proposal
                        } catch (Exception e) {
                            // send a not understood reply we didn't get back a Proposal content
                            replayProjectcreater.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                        }
                        myAgent.send(replayProjectcreater);
                        System.out.println("Provider 1 completed");
                        step=2;
                        break;


                }
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

        }

    @Override
    public boolean done() {
        return false;
    }


}



}
