import jade.core.Agent;
//import jade.core.behaviours.Behaviour;
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
    private Queue<Point> queue;

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
                System.out.println("Get around: "+sensores.getAround(p));

                if(sensores.getAgentePos().equals(sensores.getObjetivo())) {
                    System.out.println("ENCONTRADO");
                    stop();
                    doDelete();
                } else if (sensores.getAround(p) >= 0 || sensores.getAround(p) == sensores.ID_OBJETIVO) {
                    System.out.println("Valor next cell"+sensores.getAround(p));
                    Point next_p = sensores.getNextPositon(p);
                    System.out.println("Siguiente posicion"+next_p.toString());
                    Point last = sensores.actualizarPosicionAgente(sensores.getAgentePos().x + next_p.x, sensores.getAgentePos().y+next_p.y);
                    sensores.getMapa().setValorCelda(last.x,last.y, sensores.getMapa().getValorCelda(last.x, last.y)+1);
                    sensores.setVision(sensores.see());


                } else {
                    Map<POSICIONES,Integer> mejores = new HashMap<>();
                    for (POSICIONES pos : POSICIONES.values()){
                        mejores.put(pos,sensores.getAround(pos));
                    }
                    List<Map.Entry<POSICIONES, Integer>> entryList = new ArrayList<>(mejores.entrySet());

                    // Ordenar la lista de entradas por los valores en orden ascendente
//                    entryList.sort(Comparator.comparing(Map.Entry::getValue/*,Comparator.reverseOrder()*/));
//                    entryList.sort(Comparator.comparing(entry -> entry.getValue() == null ? null : entry.getValue()));
                    entryList.sort(Comparator.comparing(entry -> entry.getValue(), Comparator.nullsLast(Comparator.naturalOrder())));

                    Point next_p = null;
                    for (Map.Entry<POSICIONES, Integer> entry : entryList) {
                        POSICIONES key = entry.getKey();
                        Integer value = entry.getValue();
                        if(value >= 0) {
                            next_p = sensores.getNextPositon(key);
                            System.out.println("Siguiente posicion2: " + key);
                            break;
                        }
                    }
                    Point last = sensores.actualizarPosicionAgente(sensores.getAgentePos().x + next_p.x, sensores.getAgentePos().y + next_p.y);
                    sensores.getMapa().setValorCelda(last.x, last.y, sensores.getMapa().getValorCelda(last.x, last.y) + 1);
                    sensores.setVision(sensores.see());


                }
            }

        });

    }


}

