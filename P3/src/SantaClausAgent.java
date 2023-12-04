import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Random;

public class SantaClausAgent extends Agent {
    private boolean isAgentTrusted(String agentName) {
        // Lógica para determinar si un agente es confiable
        Random random = new Random();
        return random.nextDouble() <= 0.8; // 80% de confiabilidad
    }

    protected void setup() {
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage msg = blockingReceive();
                if (msg.getPerformative() == ACLMessage.PROPOSE) {
                    String agentName = msg.getSender().getLocalName();
                    if (isAgentTrusted(agentName)) {
                        ACLMessage reply = msg.createReply();
                        reply.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                        reply.setContent("CódigoSecreto123"); // Código secreto para la comunicación con Rudolph
                        send(reply);
                    } else {
                        ACLMessage reply = msg.createReply();
                        reply.setPerformative(ACLMessage.REJECT_PROPOSAL);
                        send(reply);
                    }
                }
            }
        });
    }
}
