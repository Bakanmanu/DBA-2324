import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.awt.*;
import java.util.ArrayList;

public class RudolphAgent extends Agent {
    private String secretCode = "CódigoSecreto123"; // Código secreto recibido de Santa
    private ArrayList<Point> coordenadasRenos;
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
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage msg = blockingReceive();
                if (msg.getPerformative() == ACLMessage.REQUEST) {
                    String receivedCode = msg.getContent();
                    ACLMessage reply = msg.createReply();
                    if (receivedCode.equals(secretCode)) {
                        reply.setPerformative(ACLMessage.AGREE);
                    } else {
                        reply.setPerformative(ACLMessage.REFUSE);
                    }
                    send(reply);
                }

                if(msg.getPerformative() == ACLMessage.CFP){
                    System.out.println("Recive");
                    String receivedCode = msg.getConversationId();
                    ACLMessage reply = msg.createReply();
                    if (receivedCode.equals(secretCode)) {
                        reply.setPerformative(ACLMessage.AGREE);
                        Point pos = getNextCoordenadaReno();
                        assert pos != null;
                        String to_send = pos.x + "," + pos.y;
                        reply.setContent(to_send);
                    } else {
                        reply.setPerformative(ACLMessage.REFUSE);
                    }
                    send(reply);
                }
            }
        });
    }

    private Point getNextCoordenadaReno() {
        if (coordenadasRenos.isEmpty()) {
            // Devolver null si no hay coordenadas disponibles
            return null;
        }

        // Obtener las coordenadas del siguiente reno
        Point reno = coordenadasRenos.get(index);

        // Incrementar el índice y volver al inicio si llegamos al final
        index = (index + 1) % coordenadasRenos.size();

        return reno;
    }

}
