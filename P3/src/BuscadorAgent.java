import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;

import java.awt.*;
import java.util.Arrays;

public class BuscadorAgent extends Agent {
    private String secretCode;
    private Point agent_pos;
    private Environment env;


    protected void setup() {

        Object[] args = getArguments();
        if (args != null && args.length == 1) {

            env = (Environment) args[0];
            System.out.println("Argumentos correctos.");

        } else {
            // Manejo de error si los argumentos no son válidos
            System.out.println("Argumentos incorrectos.");
            doDelete(); // Eliminar el agente si los argumentos no son válidos
        }

        // Inicialización y presentación a Santa Claus
        secretCode = null;
        presentToSanta();

        // Lógica principal
        SequentialBehaviour seq = new SequentialBehaviour();
        seq.addSubBehaviour(new OneShotBehaviour(this) {
            public void action() {
                // Establecer canal de comunicación seguro con Rudolph
                establishSecureCommunication();
            }
        });

        seq.addSubBehaviour(new OneShotBehaviour(this) {
            public void action() {
                // Búsqueda de renos con Rudolph
                searchReindeers();
            }
        });

        seq.addSubBehaviour(new OneShotBehaviour(this) {
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
        } else {
            System.out.println("Santa Claus rejected the proposal. Mission aborted!");
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
            System.out.println("Secure communication with Rudolph established.");
        } else {
            System.out.println("Failed to establish secure communication with Rudolph. Mission aborted!");
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
            System.out.println(reno);
            env.setObjetivo(reno);
            addBehaviour(new MostrarMapa(env));
            addBehaviour(new GetInformation(env));
//        VerificarObjetivo verificarObjetivo = new VerificarObjetivo(env);
            addBehaviour(new VerificarObjetivo(env));
            addBehaviour(new Movimiento(env));
        }



    }

    private void communicateWithSanta() {
        // Implementar la lógica de comunicación con Santa Claus
    }
}
