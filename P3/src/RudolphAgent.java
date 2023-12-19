import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.awt.*;
import java.util.ArrayList;

public class RudolphAgent extends Agent {
    private String secretCode = "CódigoSecreto123"; // Código secreto recibido de Santa
    private ArrayList<Point> coordenadasRenos;

    private boolean conectionEstablished = false;
    private int index = 0;
    protected void setup() {
        // Inicialización con argumentos personalizados
        Object[] args = getArguments();
        if (args != null && args.length == 1) {

            coordenadasRenos = (ArrayList<Point>) args[0];
            System.out.println("Argumentos correctos.");

        } else {
            // Manejo de error si los argumentos no son válidos
            System.out.println("Argumentos incorrectos.");
            doDelete(); // Eliminar el agente si los argumentos no son válidos
        }
        addBehaviour(new CyclicBehaviour(this) {    // Comportamiento genérico

            public void action() {
                ACLMessage msg = blockingReceive();

                if(!conectionEstablished){
                    // Request del setup de comms
                    if (msg.getPerformative() == ACLMessage.REQUEST) {
                        String receivedCode = msg.getContent();
                        ACLMessage reply = msg.createReply();
                        if (receivedCode.equals(secretCode)) {
                            reply.setPerformative(ACLMessage.AGREE);
                            conectionEstablished = true;
                        } else {
                            reply.setPerformative(ACLMessage.REFUSE);
                        }
                        send(reply);
                    }
                }else{
                    // request de ubi renos
                    if(msg.getPerformative() == ACLMessage.REQUEST) {
                        ACLMessage reply = msg.createReply();

                        reply.setPerformative(ACLMessage.INFORM);
                        String to_send;
                        // recibimos ubicaciones de renos hasta llegar al ultimo
                        if(index != coordenadasRenos.size()){
                            Point pos = getNextCoordenadaReno();
                            assert pos != null;
                            to_send = pos.x + "," + pos.y;
                        }else{
                            // una vez llegado al ultimo, recibiremos que el indice es igual al tamaño del array de renos
                            // por ello mandamos el "Finished"
                            to_send = "Finished";
                            doDelete();
                        }
                        reply.setContent(to_send);
                        send(reply);
                    }
                }
            }
        });
    }

    private Point getNextCoordenadaReno() {
        if (coordenadasRenos.size() == index) {
            // Devolver null si no hay coordenadas disponibles
            return null;
        }
        // Obtener las coordenadas del siguiente reno e incrementar el indice
        Point reno = coordenadasRenos.get(index);
        index++;
        return reno;
    }
}
