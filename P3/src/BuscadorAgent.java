import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
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
        SequentialBehaviour seq = new SequentialBehaviour();
        seq.addSubBehaviour(new OneShotBehaviour(this) {
            public void action() {
                // Establecer canal de comunicación seguro con Rudolph
                establishSecureCommunication();
            }
        });
        // Cyclic
        seq.addSubBehaviour(new CyclicBehaviour(this) {
            public void action() {
                // Búsqueda de renos con Rudolph
                searchReindeers();
            }
        });
        //cy
        seq.addSubBehaviour(new CyclicBehaviour(this) {
            public void action() {
                // Comunicación con Santa Claus nuevamente
                communicateWithSanta();
            }
        });

        addBehaviour(seq);
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

    private void searchReindeers() {
        System.out.println("Buscando renos");
        // Preguntar a Rudolph por las coordenadas de los renos
        ACLMessage msg = new ACLMessage(ACLMessage.CFP);
        msg.addReceiver(getAID("Rudolph"));  // Ajusta esto según el nombre del agente Rudolph
        msg.setConversationId(secretCode);  // Usa el código secreto proporcionado por Santa Claus
        send(msg);

        ACLMessage reply = blockingReceive();
        if (reply.getPerformative() == ACLMessage.AGREE) {
            System.out.println(reply.getContent());
            String[] coords = reply.getContent().split(",");
            Point reno = new Point(Integer.parseInt(coords[0]),Integer.parseInt(coords[1]));
            System.out.println("Reno en la pos:" + reno);
            env.setObjetivo(reno);
            addBehaviour(new MostrarMapa(env));
            addBehaviour(new GetInformation(env));
            addBehaviour(new VerificarObjetivo(env));
            addBehaviour(new Movimiento(env));


        }



    }

    private void communicateWithSanta() {
        // Implementar la lógica de comunicación con Santa Claus
    }
}
