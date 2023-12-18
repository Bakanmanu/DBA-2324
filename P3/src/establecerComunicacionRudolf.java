import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Establece comunicacion con rudolph, mandando el cod. secreto,
 * si es el mismo que tiene rudolph hemos establecido la conexion segura
 * si no lo es, finalizamos el agente
 */
public class establecerComunicacionRudolf extends OneShotBehaviour {
    @Override
    public void action() {
        BuscadorAgent miAgente = (BuscadorAgent) myAgent;

        ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
        request.addReceiver(miAgente.getAID("Rudolph"));
        request.setContent(miAgente.getSecretCode());
        miAgente.send(request);

        ACLMessage reply = miAgente.blockingReceive();
        if (reply.getPerformative() == ACLMessage.AGREE) {
            System.out.println("COMUNICACION SEGURA CON RUDOLPH.  :)");
        } else {
            System.out.println("NO HAY COMUNICACION CON RUDOLPH!  :(");
            miAgente.doDelete();
        }
    }
}
