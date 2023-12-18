import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BuscadorAgent extends Agent {
    private String secretCode=null;

    private boolean renoEncontrado = true;  // Ajuste para el comportamiento de ObtenerPosReno

    private boolean decisionMov = false; // Decision del movimiento tomada

    public int getIdReno() {
        return idReno;
    }

    public void setIdReno(int idReno) {
        this.idReno = idReno;
    }

    private int idReno = -1;      // id del reno encontrado
    private ArrayList<String> nameRenos = new ArrayList<>(Arrays.asList("Dasher", "Dancer", "Prancer", "Vixen","Comet", "Cupid", "Donner", "Blitzen"));
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

        // Inicio Comms
        addBehaviour(new PresentToSanta());
        addBehaviour(new establecerComunicacionRudolf());

    // Comienza ciclo busqueda

        // Obtencion posicion reno
        addBehaviour(new ObtenerPosReno());

        // Ciclo de busqueda cada reno
        addBehaviour(new MostrarMapa(getEnv()));
        addBehaviour(new GetInformation(getEnv()));
        addBehaviour(new Movimiento(getEnv()));
        addBehaviour(new VerificarObjetivo(getEnv()));


        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                if(renoEncontrado){
                    System.out.println("¡Santa, encontré a "+ nameRenos.get(idReno)+"!");
                    // Comunicación con Santa Claus nuevamente
                    //communicateWithSanta();
                }
            }
        });
    }


    public String getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }

    public Environment getEnv() {
        return env;
    }

    public boolean isRenoEncontrado() {
        return renoEncontrado;
    }

    public void setRenoEncontrado(boolean renoEncontrado) {
        this.renoEncontrado = renoEncontrado;
    }

    public boolean isDecisionMov() {
        return decisionMov;
    }

    public void setDecisionMov(boolean decisionMov) {
        this.decisionMov = decisionMov;
    }


    private void informRenoSanta(ACLMessage msg) {

    }

    private void askLocationSanta() {

    }

    private void requestAllRenoSanta(){

    }
}
