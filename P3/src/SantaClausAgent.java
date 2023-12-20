import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

import java.awt.*;
import java.util.Random;

public class SantaClausAgent extends Agent {
    private Point  santaPos = new Point(33,33);
    private int cantRenos = 0;
    private int totalRenos = 0;
    private boolean isAgentTrusted(String agentName) {
        Random random = new Random();
//        return random.nextDouble() <= 0.8;
        return true;
    }

    protected void setup() {

        Object[] args = getArguments();
        if (args != null && args.length == 1) {
            totalRenos = (int) args[0];
            System.out.println("Argumentos correctos.");

        } else {
            // Manejo de error si los argumentos no son válidos
            System.out.println("Argumentos incorrectos buscador.");
            doDelete(); // Eliminar el agente si los argumentos no son válidos
        }

        addBehaviour(new OneShotBehaviour(this) {   // Reply de Propose inicial
            public void action() {
                ACLMessage msg = blockingReceive();

                if (msg.getPerformative() == ACLMessage.PROPOSE) {
                    String agentName = msg.getSender().getLocalName();
                    ACLMessage reply = msg.createReply();
                    if (isAgentTrusted(agentName)) {
                        reply.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                        reply.setContent("CódigoSecreto123"); // Código secreto para la comunicación con Rudolph
                        send(reply);
                    } else {
                        reply.setPerformative(ACLMessage.REJECT_PROPOSAL);
                        send(reply);
//                        doDelete();
                    }
                }
            }
        });

        addBehaviour(new SimpleBehaviour() {    // Recibir INFORM de renos
            @Override
            public void action() {

                ACLMessage msg = blockingReceive();
                if(msg.getPerformative() == ACLMessage.INFORM){
                    System.out.println("\tLaponia - Santa: bomba O.o encontro el reno nº: "+cantRenos);
                    cantRenos++;
                }

            }
            @Override
            public boolean done() { // cuando encuentra a todos los renos termina el comportamiento
                return cantRenos == totalRenos;
            }
        });

        addBehaviour(new SimpleBehaviour() {   // Respuesta al request de la posicion de santa
            boolean terminado = false;
            @Override
            public void action() {
                if(cantRenos == totalRenos){
                    ACLMessage msg = blockingReceive();
                    if (msg.getPerformative() == ACLMessage.REQUEST) {
                        ACLMessage reply = msg.createReply();

                        reply.setPerformative(ACLMessage.INFORM);
                        String sendPos;

                        sendPos = santaPos.x + "," + santaPos.y;
                        reply.setContent(sendPos);
                        send(reply);
                        cantRenos++;
                        terminado = true;
                    }
                }
            }
            @Override
            public boolean done() {
                return terminado;
            }
        });

        addBehaviour(new SimpleBehaviour() {   // Respuesta al inform de obtener todos los renos
            boolean terminado = false;
            @Override
            public void action() {
                if(cantRenos == totalRenos+1){
                    ACLMessage msg = blockingReceive();
                    if (msg.getPerformative() == ACLMessage.INFORM) {
                        ACLMessage reply = msg.createReply();
                        reply.setPerformative(ACLMessage.INFORM);

                        String mensaje = "HoHoHo! Feliz Navidad!";
                        reply.setContent(mensaje);
                        send(reply);
                        System.out.println(mensaje);
                        terminado = true;
                        doDelete();
                    }
                }
            }

            @Override
            public boolean done() {
                return terminado;
            }
        });
    }
}
