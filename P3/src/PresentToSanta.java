import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class PresentToSanta extends OneShotBehaviour {
    @Override
    public void action() {
        BuscadorAgent miAgente = (BuscadorAgent) myAgent;

        ACLMessage proposal = new ACLMessage(ACLMessage.PROPOSE);
        proposal.addReceiver(miAgente.getAID("SantaClaus"));
        miAgente.send(proposal);

        ACLMessage reply = miAgente.blockingReceive();
        if (reply.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {
            miAgente.setSecretCode(reply.getContent());
            System.out.println("SANTA CLAUS NOS ACEPTA Y NOS DA EL CODIGO  :).");
        } else {
            System.out.println("SANTA CLAUS NOS HA RECHAZADO :( ");
            miAgente.doDelete();
        }
    }
}
