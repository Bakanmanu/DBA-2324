import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.proto.states.ReplySender;
import jade.wrapper.AgentController;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BuscadorAgent extends Agent {
    private String secretCode;



    private Point agent_pos = new Point(0,0);
    private Environment env;
    private Mapa mapa;
    private Sensores sensor;

    private ACLMessage mensaje;     // Para evitar crear mensajes constantemente, utilizamos el mismo y lo vamos cambiando cada vez

    protected void setup() {

    Object[] args = getArguments();
    if (args != null && args.length == 1) {
          env = (Environment) args[0];
//        mapa = (Mapa) args[0];
//        sensor = (Sensores) args[1];

        System.out.println("Argumentos correctos.");

    } else {
        // Manejo de error si los argumentos no son válidos
        System.out.println("Argumentos incorrectos buscador.");
        doDelete(); // Eliminar el agente si los argumentos no son válidos
    }

        // Inicialización y presentación a Santa Claus ONESHOT
        secretCode = null;
        presentToSanta();

        // Lógica principal ONE SHOT

        addBehaviour(new OneShotBehaviour(this) {
            public void action() {
                // Establecer canal de comunicación seguro con Rudolph
                establishSecureCommunication();
            }
        });
        // Cyclic
        addBehaviour(new BusquedaReno()
//            new CyclicBehaviour(this) {
//            private ACLMessage msg;
//            public void action() {
//                // Búsqueda de renos con Rudolph
//                searchReindeers();
//            }}
        );
        //cyclic
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                System.out.println("Comunicacion con Santa.");
                // Comunicación con Santa Claus nuevamente
                //communicateWithSanta();
            }
        });
    }


    public String getSecretCode() {
        return secretCode;
    }

    public Environment getEnv() {
        return env;
    }

    private void presentToSanta() {
        ACLMessage proposal = new ACLMessage(ACLMessage.PROPOSE);
        proposal.addReceiver(getAID("SantaClaus"));
        send(proposal);

        ACLMessage reply = blockingReceive();
        if (reply.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {
            secretCode = reply.getContent();
            System.out.println("SANTA CLAUS NOS ACEPTA Y NOS DA EL CODIGO  :).");
        } else {
            System.out.println("SANTA CLAUS NOS HA RECHAZADO :( ");
            doDelete();
        }
    }

    /**
     * Establece comunicacion con rudolph, mandando el cod. secreto,
     * si es el mismo que tiene rudolph hemos establecido la conexion segura
     * si no lo es, finalizamos el agente
     */
    private void establishSecureCommunication() {
        ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
        request.addReceiver(getAID("Rudolph"));
        request.setContent(secretCode);
        send(request);

        ACLMessage reply = blockingReceive();
        if (reply.getPerformative() == ACLMessage.AGREE) {
            System.out.println("COMUNICACION SEGURA CON RUDOLPH.  :)");
        } else {
            System.out.println("NO HAY COMUNICACION CON RUDOLPH!  :(");
            doDelete();
        }
    }

    private void informRenoSanta(ACLMessage msg) {

    }

    private void askLocationSanta() {

    }

    private void informAllRenoSanta(){

    }
}
