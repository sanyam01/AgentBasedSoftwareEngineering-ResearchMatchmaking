package Bidding;
import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Bidder extends Agent {
    // The list of known provider agents

    protected void setup() {
        System.out.println("Client-agent " + getAID().getName() + " is ready.");
        // Get names of a seller agent as argument + add to providerAgent
//        Object[] args = getArguments();


        addBehaviour(new BiddingManager(this));


    }
    private class BiddingManager extends TickerBehaviour {
        //        private int providerID;
        private int price=1;
        private long initTime;
        Agent parent;

        //        protected BookBuyerGui myGui;
        public BiddingManager(Agent a) {
            super(a, 1000); // tick every minute
            this.parent = a;
            initTime = System.currentTimeMillis();
        }

        public void onTick() {
            long currentTime = System.currentTimeMillis();


// Compute the currently acceptable price and start a negotiation
            long elapsedTime = currentTime - initTime;
            int compensationPrice = (int) (price * (elapsedTime / (60 )));
            myAgent.addBehaviour(new BidNegotiator(compensationPrice, this));

        }

        private class BidNegotiator extends Behaviour {
            //            private int providerID;
            private int compensationPrice;
            private long initTime;
            private int step = 0;
            private MessageTemplate mt;
            boolean succ;

            public BidNegotiator(int compensationPrice, Bidding.Bidder.BiddingManager biddingManager) {
                this.compensationPrice = compensationPrice;
//                this.providerID=providerID;
                this.succ = false;


            }

            @Override
            public void action() {

// Send the cfp to all sellers
                ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
                String localname = "bidder";
                AID receiver = new AID("reveiver", AID.ISLOCALNAME);
                cfp.addReceiver(receiver);
                cfp.setContent(String.valueOf(compensationPrice));
//                        cfp.setConversationId("book-trade");
                cfp.setReplyWith("cfp" + System.currentTimeMillis()); // Unique value
                myAgent.send(cfp);

                ACLMessage recMsg = myAgent.blockingReceive();

                if (recMsg != null) {
                    System.out.println(recMsg.getPerformative());
                    if (recMsg.getPerformative() == 0) {
                        System.out.println("[Sneder]Bidding is successful at price " + recMsg.getContent());
                        this.succ = true;

                    } else if (recMsg.getPerformative() == 15) {
                        System.out.println("[Sender]Bidding fails at price " + recMsg.getContent());
                    }
                }
            }

            @Override
            public boolean done() {
                return this.succ;
            }
        }


    }
}
