import jade.core.Agent;
//import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;

import java.awt.Point;

public class ComportamientoAgente extends Agent {
    private Sensores sensores;
    private int incremento = 0;


    public ComportamientoAgente(){
        System.out.println("Inicializando agente");
    }

    @Override
    public void setup() {
        // Inicialización con argumentos personalizados
        Object[] args = getArguments();
        if (args != null && args.length == 1) {

            sensores = (Sensores) args[0];
            System.out.println("Argumentos correctos.");

        } else {
            // Manejo de error si los argumentos no son válidos
            System.out.println("Argumentos incorrectos.");
            doDelete(); // Eliminar el agente si los argumentos no son válidos
        }

        addBehaviour(new TickerBehaviour(null, 2000) {

            @Override
            protected void onTick() {
                System.out.println();
                sensores.getMapa().mostrarMapa();
                System.out.println("Lo que veo");
                System.out.println(sensores.see());
                System.out.println("Posicion del agente:");
                System.out.println(sensores.getAgentePos());
                sensores.actualizarPosicionAgente(sensores.getAgentePos().x, sensores.getAgentePos().y+1);
                incremento++;
                if(incremento == 5){
                    stop();
                }
            }

        });

    }
}

