import jade.core.Agent;
//import jade.core.behaviours.Behaviour;
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

        addBehaviour(new TickerBehaviour(null, 1000) {

            @Override
            protected void onTick() {
                System.out.println();
                sensores.getMapa().mostrarMapa();
                System.out.println("Posicion del agente:" + sensores.getAgentePos());
                System.out.println("Posicion del objetivo:" + sensores.getObjetivo());
                POSICIONES p = sensores.determinarDireccion();
                System.out.println("El objetivo se encuentra al: "+p);
                if(sensores.getAgentePos().equals(sensores.getObjetivo())){
                    System.out.println("ENCONTRADO");
                    stop();
                    doDelete();
                } else if (sensores.getAround(p) >= 0) {
                    System.out.println("Valor next cell"+sensores.getAround(p));
                    Point next_p = sensores.getNextPositon(p);
                    System.out.println("Siguiente posicion"+next_p.toString());
                    sensores.actualizarPosicionAgente(sensores.getAgentePos().x + next_p.x, sensores.getAgentePos().y+next_p.y);
                }
            }

        });

    }
}

