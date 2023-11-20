import jade.core.behaviours.OneShotBehaviour;

import java.awt.*;
import java.util.*;
import java.util.List;

class MovimientoComplejo extends OneShotBehaviour {
    private Sensores sensores;
    public MovimientoComplejo(Sensores sensores){
        this.sensores = sensores;
    }
    @Override
    public void action() {
        System.out.println("------------------ MOVIMIENTO Complejo ------------------");

        Point next_p = null;
        java.util.List<Map.Entry<POSICIONES,Integer>> mejores= new ArrayList<>();

        // 2 mejores movimientos, y los meto en mejores
        for (POSICIONES pos : sensores.getPosiciones()) {
            mejores.add(new AbstractMap.SimpleEntry<>(pos, sensores.getSimpleAround(pos)));
        }

        List<Map.Entry<POSICIONES, Integer>> nuevosMejores = new ArrayList<>();

        // Ahora meto los otros movimientos posibles
        for (Iterator<Map.Entry<POSICIONES, Integer>> iterator = mejores.iterator(); iterator.hasNext();) {
            Map.Entry<POSICIONES, Integer> p = iterator.next();
            nuevosMejores.add(new AbstractMap.SimpleEntry<>(sensores.opposite(p.getKey()), sensores.getSimpleAround(sensores.opposite(p.getKey()))));
        }

        mejores.addAll(nuevosMejores);
        // Los ordeno de menor a mayor
        Collections.sort(mejores, Comparator.comparing(Map.Entry::getValue, Comparator.nullsLast(Comparator.naturalOrder())));

        for (Map.Entry<POSICIONES,Integer> p : mejores) {
            System.out.println("Opciones: " + p.getKey() + " : " + p.getValue());
        }

        // Escogo la mejor opcion y que sea valida
        for (Map.Entry<POSICIONES, Integer> entry : mejores) {
            POSICIONES key = entry.getKey();
            Integer value = entry.getValue();
            if (value >= 0 /*&& key != sensores.opposite(posiciones)*/) {
                System.out.println("Escogida :"+ key + " : " + value);
                next_p = sensores.getNextPositon(key);
                System.out.println("Siguiente posicion2: " + key);
                break;
            }

        }

        // Me muevo hacia alli
        assert next_p != null;
        Point last = sensores.actualizarPosicionAgente(sensores.getAgentePos().x + next_p.x, sensores.getAgentePos().y + next_p.y);
        System.out.println("\tActualizando el valor de la celda: " + last);
        System.out.println("\tValor de la celda: " + sensores.getMapa().getValorCelda(last.x, last.y));
        sensores.setVision(sensores.see());

    }
}