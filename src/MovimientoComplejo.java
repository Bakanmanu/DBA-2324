import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.core.behaviours.OneShotBehaviour;

import java.awt.*;
import java.util.*;
import java.util.List;

import static java.lang.Thread.sleep;

class MovimientoComplejo extends CyclicBehaviour {
    private Environment env;
    private int step_value;
    boolean decision = false;
    public MovimientoComplejo(Environment env, int step){
        this.env = env;
        this.step_value = step;

    }
    @Override
    public void action() {
        for(int i = 0; i < env.getPosiciones().size(); i++) {
            if (env.getSensores().getAround(env.getPosiciones()).get(i) != null){
                if (env.getSensores().getAround(env.getPosiciones()).get(i) == env.ID_OBJETIVO) {
                    System.out.println("------------------ MOVIMIENTO SIMPLE ------------------");
                    System.out.println("Valor next cell" + env.getSensores().getAround(env.getPosiciones()));

                    Point next_p = env.getNextPositon(env.getPosiciones().get(i));

                    System.out.println("Siguiente posicion" + next_p.toString());

                    Point last = env.actualizarPosicionAgente(env.getAgentePos().x + next_p.x, env.getAgentePos().y + next_p.y,1);
                    env.getSensores().setVision(env.getSensores().see(env.getAgentePos(), env.getMemoria()));
                    decision = true;
                }
            }
        }

        if (!decision) {
            System.out.println("------------------ MOVIMIENTO Complejo ------------------");

            Point next_p = null;
            java.util.List<Map.Entry<POSICIONES, Integer>> mejores = new ArrayList<>();

            // 2 mejores movimientos, y los meto en mejores
            for (POSICIONES pos : env.getPosiciones()) {
                mejores.add(new AbstractMap.SimpleEntry<>(pos, env.getSensores().getSimpleAround(pos)));
            }

            List<Map.Entry<POSICIONES, Integer>> nuevosMejores = new ArrayList<>();

            // Ahora meto los otros movimientos posibles
            for (Iterator<Map.Entry<POSICIONES, Integer>> iterator = mejores.iterator(); iterator.hasNext(); ) {
                Map.Entry<POSICIONES, Integer> p = iterator.next();
                nuevosMejores.add(new AbstractMap.SimpleEntry<>(env.opposite(p.getKey()), env.getSensores().getSimpleAround(env.opposite(p.getKey()))));
            }

            mejores.addAll(nuevosMejores);
            // Los ordeno de menor a mayor
            Collections.sort(mejores, Comparator.comparing(Map.Entry::getValue, Comparator.nullsLast(Comparator.naturalOrder())));

            for (Map.Entry<POSICIONES, Integer> p : mejores) {
                System.out.println("Opciones: " + p.getKey() + " : " + p.getValue());
            }

            // Escogo la mejor opcion y que sea valida
            for (Map.Entry<POSICIONES, Integer> entry : mejores) {
                POSICIONES key = entry.getKey();
                Integer value = entry.getValue();

                if (value >= 0 /*&& key != env.opposite(posiciones)*/) {
                    System.out.println("Escogida :" + key + " : " + value);
                    next_p = env.getNextPositon(key);
                    System.out.println("Siguiente posicion2: " + key);
                    break;
                }

            }

            // Me muevo hacia alli
            assert next_p != null;

            Point last = env.actualizarPosicionAgente(env.getAgentePos().x + next_p.x, env.getAgentePos().y + next_p.y, step_value);
            System.out.println("\tActualizando el valor de la celda: " + last);
            System.out.println("\tValor de la celda: " + env.getMapa().getValorCelda(last.x, last.y));
            env.getSensores().setVision(env.getSensores().see(env.getAgentePos(), env.getMemoria()));


        }
    }

//    @Override
//    public boolean done() {
//        return true;
//    }
}