package Contract;

import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

public class SystemClient extends Agent {
    protected void setup() {
        System.out.println("This is my name : " + getAID().getName());
        System.out.println("My global name is " + getAID().getLocalName());
        System.out.println("My addresses are :" + getAID().getAllAddresses());
        System.out.println("Client-agent " + getAID().getName() + " is ready.");
        String contract="contract";

        addBehaviour(new ContractManager(this,contract));
    }
    private class ContractManager extends Behaviour {

        Agent parent;
        boolean succ;
        String contract;
        private int step = 0;
        public ContractManager(SystemClient contractManager, String contract) {
            this.succ = false;
            this.contract=contract;



        }

        @Override
        public void action() {
            switch (step) {
                case 0:
                    ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                    String localname = "contractClient";
                    AID receiver = new AID("projectCreator", AID.ISLOCALNAME);
                    msg.addReceiver(receiver);
                    msg.setContent("Accepted or refused. The system will receive 30% of\n" +
                            "any transaction");

                    myAgent.send(msg);
                    ACLMessage recMsg = myAgent.blockingReceive();
                    if (recMsg != null) {
                        System.out.println(recMsg.getPerformative());
                        if (recMsg.getPerformative() == 0) {
                            System.out.println("[Contract Sender]contract is successful at " + recMsg.getContent());
                            this.succ = true;
                            step=1;
                        } else if (recMsg.getPerformative() == 15) {
                            System.out.println("[Contract Sender]contract fails at " + recMsg.getContent());
                        }
                    }
                    System.out.println("System0 completed");
                case 1:
                    System.out.println("Enter into step 2: creating project " );
                    ACLMessage projectCreater = new
                            ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
                    AID projectReceiver = new AID("projectCreator", AID.ISLOCALNAME);
                    projectCreater.addReceiver(projectReceiver);
                    projectCreater.setContent("wheather the project create or not?");
                    myAgent.send(projectCreater);
                    ACLMessage recproject = myAgent.blockingReceive();
                    if (recproject != null) {
                        System.out.println(recproject.getPerformative());
                        if (recproject.getPerformative() == 4) {
                            System.out.println("[Project Sender]project is successful at " + recproject.getContent());
                            this.succ = true;
                        } else if (recproject.getPerformative() == 5) {
                            System.out.println("[Project Sender]project fails at " + recproject.getContent());
                        }
                    }
                    System.out.println("System 1 completed");
                    step=2;
        }
    }
        @Override
        public boolean done() {
            return false;
        }

    }}
