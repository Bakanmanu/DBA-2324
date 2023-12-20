import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;

import jade.lang.acl.ACLMessage;

import java.awt.*;


public class ObtenerPosReno extends SimpleBehaviour {
boolean busquedaTerminada = false;
    @Override
    public void action() {
        BuscadorAgent miAgente = (BuscadorAgent) myAgent;

        if (miAgente.isRenoEncontrado()) {
//            System.out.println("Buscando renos");
            // Preguntar a Rudolph por las coordenadas de los renos
            ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);

            msg.addReceiver(miAgente.getAID("Rudolph"));  // Ajusta esto según el nombre del agente Rudolph
            msg.setConversationId(miAgente.getSecretCode());  // Usa el código secreto proporcionado por Santa Claus
            miAgente.send(msg);

            ACLMessage reply = miAgente.blockingReceive();
            if (reply.getPerformative() == ACLMessage.INFORM) {
                // si recibe el Finished termina la busqeuda de renos y añade el bhave de comms con santa
                if (reply.getContent().equals("Finished")) {
                    System.out.println("Busqueda terminada.");
                    busquedaTerminada = true;
                    // Añadimos el comportamiento de pedir la posicion una vez terminemos de pedir posiciones de renos
                    miAgente.addBehaviour(new RequestLocationSanta());
                } else {
                    System.out.println(reply.getContent());
                    String[] coords = reply.getContent().split(",");

                    Point reno = new Point(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
                    System.out.println("Reno en la pos:" + reno);

                    miAgente.getEnv().setObjetivo(reno);
                }
            }
            miAgente.setRenoEncontrado(false);
        }
    }

    @Override
    public boolean done() {
        return busquedaTerminada;
    }
}
