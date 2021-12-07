package matchpackage.Bidding;
import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import matchpackage.access.CustomerGUI;

public class Bidder extends Agent {
    String status;
    String status1;
    String status2;
    private CustomerGUI gui;
    BiddingManager bidBehaviour;


    protected void setup() {
        System.out.println("Client-agent " + getAID().getName() + " is ready.");
        this.status = "Not done yet";
        this.gui= new CustomerGUI(this);

    }
    public void startBidding(float price){
        this.bidBehaviour = new BiddingManager(this, price);
        addBehaviour(bidBehaviour);
    }

    public void eat(){
        System.out.println("Eating");
        if(full()){
            shit();
        }else{
            //
        }
    }

    public boolean full(){
        return false;
    }

    public void shit(){
        System.out.println("shitting");
    }




    private class BiddingManager extends SimpleBehaviour {
        //        private int providerID;
        private float price;
        private long initTime;
        Agent parent;
        private int compensationPrice;
        boolean succ;
        int step=0;
        float cnt;


        public BiddingManager( Agent parent,float price) {
//            this.compensationPrice = compensationPrice;
//                this.providerID=providerID;
            this.succ = false;
            this.parent=parent;
            this.cnt =price;


        }


        @Override
        public void action() {
            while(true){
                switch (step) {
                    //contract agent
                    case 0:
                        while(true){

                            ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
                            String localname = "bidder";
                            AID receiver = new AID("reveiver", AID.ISLOCALNAME);
                            cfp.addReceiver(receiver);
                            cfp.setContent(String.valueOf(cnt+10));
                            cnt=cnt+10;
                            //                        cfp.setConversationId("book-trade");
//                        cfp.setReplyWith("cfp" + System.currentTimeMillis()); // Unique value
                            myAgent.send(cfp);

                            ACLMessage recMsg = myAgent.blockingReceive();

                            if (recMsg != null) {

                                if (recMsg.getPerformative() == 0) {

                                    System.out.println("[Sneder]Bidding is successful at price " + recMsg.getContent());
                                    Bidder.this.status = "Bidding is successful at price " + recMsg.getContent()+" and contract will be sending";
                                    Bidder.this.status1 = " The system will receive 30% of\n" +
                                            "any transaction";
                                    gui.showBidderStatus(Bidder.this.status);
                                    gui.showBiddingResults(Bidder.this.status1);
                                    this.succ = true;
                                    ACLMessage msg = new ACLMessage(ACLMessage.CFP);
                                    step=1;
                                    break;

                                } else if (recMsg.getPerformative() == 15) {

                                    System.out.println("[Sender]Bidding fails at price " + recMsg.getContent());
                                    step=0;

                                }
                            }
                            doWait(10000);



                        }
                        break;




// Send the cfp to all sellers

                    case 1:
                        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
//                        String localname = "contractClient";
                        AID receiver1 = new AID("reveiver", AID.ISLOCALNAME);
                        msg.addReceiver(receiver1);
                        msg.setContent("Accepted or refused. The system will receive 30% of\n" +
                                "any transaction");
                        //                        cfp.setConversationId("book-trade");
                        //            cfp.setReplyWith("cfp" + System.currentTimeMillis()); // Unique value
                        myAgent.send(msg);
                        ACLMessage recMsg1 = myAgent.blockingReceive();
                        if (recMsg1 != null) {
                            System.out.println(recMsg1.getPerformative());
                            if (recMsg1.getPerformative() == 0) {
                                System.out.println("[Contract Sender]contract is successful at " + recMsg1.getContent());
                                this.succ = true;
                                step=0;


                            } else if (recMsg1.getPerformative() == 15) {
                                System.out.println("[Contract Sender]contract fails at " + recMsg1.getContent());
                            }
                        }

                        System.out.println("System0 completed");
                        break;
                    case 2:
                        ACLMessage REQUEST = new ACLMessage(ACLMessage.REQUEST);
                        //            String localname = "bidder";
                        AID receiver2 = new AID("reveiver", AID.ISLOCALNAME);
                        REQUEST.addReceiver(receiver2);
                        REQUEST.setContent("client wants to change project");
                        myAgent.send(REQUEST);

                        ACLMessage recMsg2 = myAgent.blockingReceive();
                        if (recMsg2 != null) {
                            System.out.println(recMsg2.getPerformative());
                            if (recMsg2.getPerformative() == 0) {
                                Bidder.this.status2 ="You can change the project";
                                gui.showChangeResults(Bidder.this.status2);
                                System.out.println("[change Sender]provider can change this ? " + recMsg2.getContent());
                                step = 3;


                            } else if (recMsg2.getPerformative() == 15) {
                                System.out.println("[change Sender]contract fails at " + recMsg2.getContent());
                            }
                        }
                        break;
                    case 3:
                        break;

                }

            }
            }
//                this.cnt=+10;





        @Override
        public boolean done() {
            return false;
        }


        }}

