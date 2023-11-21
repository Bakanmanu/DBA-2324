import jade.core.behaviours.OneShotBehaviour;

import java.awt.*;
import java.util.*;
import java.util.List;

class MovimientoComplejo extends OneShotBehaviour {
    private final Environment env;

    public MovimientoComplejo(Environment env){
        this.env = env;
    }
    @Override
    public void action() {
        System.out.println("------------------ MOVIMIENTO Complejo ------------------");

        Point next_p = null;
        java.util.List<Map.Entry<POSICIONES,Integer>> mejores= new ArrayList<>();

        // 2 mejores movimientos, y los meto en mejores
        for (POSICIONES pos : env.getPosiciones()) {
            mejores.add(new AbstractMap.SimpleEntry<>(pos, env.getSensores().getSimpleAround(pos)));
        }

        List<Map.Entry<POSICIONES, Integer>> nuevosMejores = new ArrayList<>();

        // Ahora meto los otros movimientos posibles
        for (Map.Entry<POSICIONES, Integer> p : mejores) {
            nuevosMejores.add(new AbstractMap.SimpleEntry<>(env.opposite(p.getKey()), env.getSensores().getSimpleAround(env.opposite(p.getKey()))));
        }

        mejores.addAll(nuevosMejores);
        // Los ordeno de menor a mayor
        mejores.sort(Map.Entry.comparingByValue(Comparator.nullsLast(Comparator.naturalOrder())));

        for (Map.Entry<POSICIONES,Integer> p : mejores) {
            System.out.println("Opciones: " + p.getKey() + " : " + p.getValue());
        }

        // Escoger la mejor opción y que sea válida
        for (Map.Entry<POSICIONES, Integer> entry : mejores) {
            POSICIONES key = entry.getKey();
            Integer value = entry.getValue();

            if (value >= 0 /*&& key != env.opposite(posiciones)*/) {
                System.out.println("Escogida :"+ key + " : " + value);
                next_p = env.getNextPositon(key);
                System.out.println("Siguiente posicion2: " + key);
                break;
            }

        }

        // Me muevo hacia alli
        assert next_p != null;
        Point last = env.actualizarPosicionAgente(env.getAgentePos().x + next_p.x, env.getAgentePos().y + next_p.y, 1);
        System.out.println("\tActualizando el valor de la celda: " + last);
        System.out.println("\tValor de la celda: " + env.getMapa().getValorCelda(last.x, last.y));
        env.getSensores().setVision(env.getSensores().see(env.getAgentePos(), env.getMemoria()));

    }
}