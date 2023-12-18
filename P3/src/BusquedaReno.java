import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.SimpleBehaviour;

import jade.lang.acl.ACLMessage;

import java.awt.*;


public class BusquedaReno extends SimpleBehaviour {
boolean busquedaTerminada = false;
    @Override
    public void action() {
        BuscadorAgent miAgente = (BuscadorAgent) myAgent;

        System.out.println("Buscando renos");
        // Preguntar a Rudolph por las coordenadas de los renos
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.addReceiver(miAgente.getAID("Rudolph"));  // Ajusta esto según el nombre del agente Rudolph
        msg.setConversationId(miAgente.getSecretCode());  // Usa el código secreto proporcionado por Santa Claus
        miAgente.send(msg);

        ACLMessage reply = miAgente.blockingReceive();
        if (reply.getPerformative() == ACLMessage.INFORM) {

            if(reply.getContent().equals("Finished")){
                System.out.println("Busqueda terminada.");
                miAgente.removeBehaviour(this);
                busquedaTerminada = true;
            }else {

                System.out.println(reply.getContent());
                String[] coords = reply.getContent().split(",");

                Point reno = new Point(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
                System.out.println("Reno en la pos:" + reno);

                miAgente.getEnv().setObjetivo(reno);

                SequentialBehaviour seq = new SequentialBehaviour();

                seq.addSubBehaviour(new MostrarMapa(miAgente.getEnv()));
                seq.addSubBehaviour(new GetInformation(miAgente.getEnv()));
                seq.addSubBehaviour(new VerificarObjetivo(miAgente.getEnv()));
                seq.addSubBehaviour(new Movimiento(miAgente.getEnv()));

                miAgente.addBehaviour(seq);
            }
        }
    }

    @Override
    public boolean done() {
        return busquedaTerminada;
    }
}
