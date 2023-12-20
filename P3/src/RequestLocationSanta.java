import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

import java.awt.*;

public class RequestLocationSanta extends SimpleBehaviour {
    boolean terminado = false;
    boolean primeraVez = true;
    @Override
    public void action() {
        BuscadorAgent miAgente = (BuscadorAgent) myAgent;
        if(primeraVez){
            ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
            msg.addReceiver(miAgente.getAID("SantaClaus"));
            miAgente.send(msg);
            ACLMessage reply = miAgente.blockingReceive();

            if(reply.getPerformative() == ACLMessage.INFORM){
                String[] coords = reply.getContent().split(",");
                Point posSanta = new Point(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));

                miAgente.getEnv().setObjetivo(posSanta);
                miAgente.setRenoEncontrado(false);
            }
            primeraVez = false;
        }else{
            if(miAgente.isRenoEncontrado()){
                System.out.println("He llegado a Santa");
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                String contenido = "He conseguido todos los Renos.";
                System.out.println(contenido);
                msg.addReceiver(miAgente.getAID("SantaClaus"));
                msg.setContent(contenido);
                miAgente.send(msg);
                terminado = true;
                miAgente.doDelete();
            }
        }
    }

    @Override
    public boolean done() {
        return terminado;
    }
}
