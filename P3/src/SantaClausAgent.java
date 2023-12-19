import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

import java.awt.*;
import java.util.Random;

public class SantaClausAgent extends Agent {
    private Point  santaPos = new Point(3,3);
    private int cantRenos = 0;
    private boolean isAgentTrusted(String agentName) {
        Random random = new Random();
//        return random.nextDouble() <= 0.8;
        return true;
    }

    protected void setup() {
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
                System.out.println("\t\t\tEntro en renos < 8.");
                if(msg.getPerformative() == ACLMessage.INFORM){
                    System.out.println("\tLaponia - Santa: bomba O.o encontrño el reno nº: "+cantRenos);
                    cantRenos++;
                }

            }
            @Override
            public boolean done() { // cuando encuentra a todos los renos termina el comportamiento
                System.out.println("\t\t\t Done: la cantidad de renos que le hemos confirmado a santa es: "+ cantRenos);
                return cantRenos == 8;
            }
        });

        addBehaviour(new SimpleBehaviour() {   // Respuesta al request de la posicion de santa
            boolean terminado = false;
            @Override
            public void action() {

                if(cantRenos == 8){

                    System.out.println("Iniciado el Respuesta del Request de Buscador a Santa");
                    ACLMessage msg = blockingReceive();
                    System.out.println("Despues del bloqueo de Iniciado el Respuesta del Request de Buscador a Santa");

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
                if(cantRenos == 9){

                    System.out.println("\t\t\t Entro en renos > 8 .");
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
