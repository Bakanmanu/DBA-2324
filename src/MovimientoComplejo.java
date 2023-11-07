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


        for (POSICIONES pos : sensores.getPosiciones()) {
            mejores.add(new AbstractMap.SimpleEntry<>(pos, sensores.getSimpleAround(pos)));
        }

        List<Map.Entry<POSICIONES, Integer>> nuevosMejores = new ArrayList<>();

        for (Iterator<Map.Entry<POSICIONES, Integer>> iterator = mejores.iterator(); iterator.hasNext();) {
            Map.Entry<POSICIONES, Integer> p = iterator.next();
            nuevosMejores.add(new AbstractMap.SimpleEntry<>(sensores.opposite(p.getKey()), sensores.getSimpleAround(sensores.opposite(p.getKey()))));
        }

        mejores.addAll(nuevosMejores);

//                    Collections.sort(nuevosMejores, Comparator.comparing(Map.Entry::getValue));
        Collections.sort(mejores, Comparator.comparing(Map.Entry::getValue, Comparator.nullsLast(Comparator.naturalOrder())));

//                        entryList.sort(Comparator.comparing(entry -> entry.getValue(), Comparator.nullsLast(Comparator.naturalOrder())));

        for (Map.Entry<POSICIONES,Integer> p : mejores) {
            System.out.println("Opciones: " + p.getKey() + " : " + p.getValue());
        }

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

        Point last = sensores.actualizarPosicionAgente(sensores.getAgentePos().x + next_p.x, sensores.getAgentePos().y + next_p.y);
        System.out.println("\tActualizando el valor de la celda: " + last);
        System.out.println("\tValor de la celda: " + sensores.getMapa().getValorCelda(last.x, last.y));

//                    sensores.getMapa().setValorCelda(last.x, last.y, sensores.getMapa().getValorCelda(last.x, last.y) + 1);
        sensores.setVision(sensores.see());

    }
}