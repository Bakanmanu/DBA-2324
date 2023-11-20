import jade.core.Agent;
//import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;

import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Map;
import java.util.List;
import java.util.*;


public class ComportamientoAgente extends Agent {
    private Sensores sensores;
    private  CicloComportamiento ciclo = null;

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
        CicloComportamiento ciclo = new CicloComportamiento(sensores);
        addBehaviour(ciclo);


    }
}