package matchpackage.Bidding;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import matchpackage.access.ProviderGUI;

public class Provider extends Agent {

    ACLMessage acl1;
    ProviderGUI gui;
    public void setup() {
        addBehaviour(new ReplyProposal(this));
        gui = new ProviderGUI(this);
    }
    public String getProviderPrice(){
        return "the bidder's bidding price: "+acl1.getContent();
    }



    private class ReplyProposal extends SimpleBehaviour {

        private Agent parent;
        int step=0;


        public ReplyProposal(Agent a) {

            parent = a;
        }


        @Override
        public void action() {
            while(true){
                switch (step) {
                    //contract agent
                    case 0:
                        System.out.println("[Receiver]Trying to receive bidding");
                        acl1 = parent.blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.CFP));

                        if (acl1 != null) {
                            System.out.println("[Receiver] Received a bidding");
                            System.out.println("[Receiver]the bidding is: " + acl1.getContent());
                        } else {
                            System.out.println("[Receiver]Did not receive a bid");
                            return;
                        }
                        ACLMessage reply;
                        while(Provider.this.gui.getAccepted().equals("Undecided")){
                            // wait for user decision
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        if(Provider.this.gui.getAccepted().equals("Accept")){
                            reply = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
                            reply.addReceiver(acl1.getSender());
                            reply.setContent(acl1.getContent());
                            parent.send(reply);

                            step=1;
                        } else {
                            reply = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
                            reply.addReceiver(acl1.getSender());
                            reply.setContent(acl1.getContent());
                            parent.send(reply);
                            step=0;
                        }
                        gui.setAccepted("Undecided");
                        break;
                    case 1:
                        ACLMessage reply1;
                        System.out.println("[ContractReceiver]Trying to receive contract");
                        ACLMessage acl11 = parent.blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
                        if (acl11 != null) {
                            System.out.println("[ContractReceiver] Received a contract");
                            System.out.println("[ContractReceiver] Accepted or Refused: " + acl11.getContent());
                            MessageTemplate template =
                                    MessageTemplate.MatchPerformative(ACLMessage.INFORM);
//                        ACLMessage msg = parent.receive(template);

                            reply1 = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);

//                msg.addReceiver(new AID("provider", AID.ISLOCALNAME));
                            reply1.addReceiver(acl11.getSender());
                            reply1.setContent("contract already created");
                            myAgent.send(reply1);
                            step = 2;

                        }
                        break;
                    case 2:
                        ACLMessage acl2 = parent.blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
                        if (acl2 != null) {
                            System.out.println("[ChangeReceiver] Received a change");
                            System.out.println("[ChangeReceiver] Accepted or Refused: " + acl2.getContent());
                        }  else {
                    System.out.println("[ChangeReceiver]Did not receive a change");
                    return;
                }
                        ACLMessage reply2;
                        while(Provider.this.gui.getChangeAccepted().equals("Undecided")){
                            // wait for user decision
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        if(Provider.this.gui.getChangeAccepted().equals("Accept")){
                            reply = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
                            reply.addReceiver(acl2.getSender());
                            reply.setContent("provider accept the changes");
                            parent.send(reply);

                            step=3;
                        } else {
                            reply = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
                            reply.addReceiver(acl1.getSender());
                            reply.setContent("contract already created");
                            parent.send(reply);
                            step=2;
                        }
                        gui.setChangeAccepted("Undecided");
                        break;

                    case 3:
                        break;


                }
            }




    }

        @Override
        public boolean done() {
            return false;
        }
    }}
