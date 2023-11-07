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

        addBehaviour( new CicloComportamiento(sensores));

//        addBehaviour(new TickerBehaviour(null, 1000) {
//
//            @Override
//            protected void onTick() {
//                System.out.println();
//                sensores.getMapa().mostrarMapa();
//                System.out.println("---------------------------------------------");
//                sensores.getMemoria().mostrarMapa();
//                System.out.println("---------------------------------------------");
//
//                System.out.println("Posicion del agente:" + sensores.getAgentePos());
//                System.out.println("Posicion del objetivo:" + sensores.getObjetivo());
//                sensores.setPosiciones(sensores.determinarDireccion());
//                System.out.println("El objetivo se encuentra al: "+sensores.getPosiciones());
//                System.out.println("Get around: "+sensores.getAround(sensores.getPosiciones()));
//                boolean decision = false;
//                // Si estoy encima del objetiVo paro
//                if(sensores.getAgentePos().equals(sensores.getObjetivo())) {
//                    System.out.println("ENCONTRADO");
//                    decision = true;
//                    stop();
//                    doDelete();
//                } else {
//                    for(int i = 0; i < sensores.getPosiciones().size(); i++) {
//                        if (sensores.getAround(sensores.getPosiciones()).get(i) != null){
//                            if (sensores.getAround(sensores.getPosiciones()).get(i) == sensores.ID_OBJETIVO) {
//                                System.out.println("Valor next cell" + sensores.getAround(sensores.getPosiciones()));
//                                Point next_p = sensores.getNextPositon(sensores.getPosiciones().get(i));
//                                System.out.println("Siguiente posicion" + next_p.toString());
//                                Point last = sensores.actualizarPosicionAgente(sensores.getAgentePos().x + next_p.x, sensores.getAgentePos().y + next_p.y);
//                                //                    sensores.getMapa().setValorCelda(last.x,last.y, sensores.getMapa().getValorCelda(last.x, last.y)+1);
//                                sensores.setVision(sensores.see());
//                                decision = true;
//                            }
//                        }
//                    }
//                }
//
//                if(!decision){
//                    Point next_p = null;
//                    List<Map.Entry<POSICIONES,Integer>> mejores= new ArrayList<>();
//
//
//                    for (POSICIONES pos : sensores.getPosiciones()) {
//                        mejores.add(new AbstractMap.SimpleEntry<>(pos, sensores.getSimpleAround(pos)));
//                    }
//
//                    List<Map.Entry<POSICIONES, Integer>> nuevosMejores = new ArrayList<>();
//
//                    for (Iterator<Map.Entry<POSICIONES, Integer>> iterator = mejores.iterator(); iterator.hasNext();) {
//                        Map.Entry<POSICIONES, Integer> p = iterator.next();
//                        nuevosMejores.add(new AbstractMap.SimpleEntry<>(sensores.opposite(p.getKey()), sensores.getSimpleAround(sensores.opposite(p.getKey()))));
//                    }
//
//                    mejores.addAll(nuevosMejores);
//
////                    Collections.sort(nuevosMejores, Comparator.comparing(Map.Entry::getValue));
//                    Collections.sort(mejores, Comparator.comparing(Map.Entry::getValue, Comparator.nullsLast(Comparator.naturalOrder())));
//
////                        entryList.sort(Comparator.comparing(entry -> entry.getValue(), Comparator.nullsLast(Comparator.naturalOrder())));
//
//                    for (Map.Entry<POSICIONES,Integer> p : mejores) {
//                        System.out.println("Opciones: " + p.getKey() + " : " + p.getValue());
//                    }
//
//                    for (Map.Entry<POSICIONES, Integer> entry : mejores) {
//                        POSICIONES key = entry.getKey();
//                        Integer value = entry.getValue();
//                        if (value >= 0 /*&& key != sensores.opposite(posiciones)*/) {
//                            System.out.println("Escogida :"+ key + " : " + value);
//                            next_p = sensores.getNextPositon(key);
//                            System.out.println("Siguiente posicion2: " + key);
//                            break;
//                        }
//
//                    }
//
//                    Point last = sensores.actualizarPosicionAgente(sensores.getAgentePos().x + next_p.x, sensores.getAgentePos().y + next_p.y);
//                    System.out.println("\tActualizando el valor de la celda: " + last);
//                    System.out.println("\tValor de la celda: " + sensores.getMapa().getValorCelda(last.x, last.y));
//
////                    sensores.getMapa().setValorCelda(last.x, last.y, sensores.getMapa().getValorCelda(last.x, last.y) + 1);
//                    sensores.setVision(sensores.see());
//
//                }
//            }
//
//        });

    }


//    static class MostrarMapa extends OneShotBehaviour {
//        private Sensores sensores;
//
//        public MostrarMapa(Sensores sensores) {
//            this.sensores = sensores;
//        }
//
//        public void action() {
//            System.out.println("Mostrando mapa:");
//            sensores.getMapa().mostrarMapa();
//            System.out.println("---------------------------------------------");
//            sensores.getMemoria().mostrarMapa();
//            System.out.println("---------------------------------------------");
//        }
//    }
//
//    static class GetInformation extends OneShotBehaviour{
//        private Sensores sensores;
//
//        public GetInformation(Sensores sensores){
//            this.sensores = sensores;
//        }
//        @Override
//        public void action() {
//            System.out.println("Posicion del agente:" + sensores.getAgentePos());
//            System.out.println("Posicion del objetivo:" + sensores.getObjetivo());
//            sensores.setPosiciones( sensores.determinarDireccion());
//            System.out.println("El objetivo se encuentra al: "+sensores.getPosiciones());
//            System.out.println("Get around: "+sensores.getAround(sensores.getPosiciones()));
//        }
//    }
//
//    static class VerificarObjetivo extends OneShotBehaviour {
//        private Sensores sensores;
//        private boolean enObjetivo = false;
//
//        public VerificarObjetivo(Sensores sensores) {
//            this.sensores = sensores;
//        }
//
//        public void action() {
//            if (sensores.getAgentePos().equals(sensores.getObjetivo())) {
//                System.out.println("ENCONTRADO");
//                // Realiza las acciones necesarias cuando el agente encuentra el objetivo
//                enObjetivo = true;
//            }
//        }
//        public boolean getenObjetivo(){
//            return enObjetivo;
//        }
////        public int onEnd() {
////            if (!enObjetivo) {
////                myAgent.addBehaviour(new MovimientoSimple(sensores));
////            }
////            return super.onEnd();
////        }
//    }
//
//    static class MovimientoSimple extends OneShotBehaviour{
//        private Sensores sensores;
//        private POSICIONES mov;
//        public MovimientoSimple(Sensores sensores, POSICIONES m){
//            this.sensores = sensores;
//            this.mov = m;
//        }
//        @Override
//        public void action() {
//            System.out.println("------------------ MOVIMIENTO SIMPLE ------------------");
//            System.out.println("Valor next cell" + sensores.getAround(sensores.getPosiciones()));
//            Point next_p = sensores.getNextPositon(mov);
//            System.out.println("Siguiente posicion" + next_p.toString());
//            Point last = sensores.actualizarPosicionAgente(sensores.getAgentePos().x + next_p.x, sensores.getAgentePos().y + next_p.y);
//            sensores.setVision(sensores.see());
//
//        }
//    }
//
//    static class MovimientoComplejo extends OneShotBehaviour{
//        private Sensores sensores;
//        public MovimientoComplejo(Sensores sensores){
//            this.sensores = sensores;
//        }
//        @Override
//        public void action() {
//            System.out.println("------------------ MOVIMIENTO Complejo ------------------");
//
//            Point next_p = null;
//            List<Map.Entry<POSICIONES,Integer>> mejores= new ArrayList<>();
//
//
//            for (POSICIONES pos : sensores.getPosiciones()) {
//                mejores.add(new AbstractMap.SimpleEntry<>(pos, sensores.getSimpleAround(pos)));
//            }
//
//            List<Map.Entry<POSICIONES, Integer>> nuevosMejores = new ArrayList<>();
//
//            for (Iterator<Map.Entry<POSICIONES, Integer>> iterator = mejores.iterator(); iterator.hasNext();) {
//                Map.Entry<POSICIONES, Integer> p = iterator.next();
//                nuevosMejores.add(new AbstractMap.SimpleEntry<>(sensores.opposite(p.getKey()), sensores.getSimpleAround(sensores.opposite(p.getKey()))));
//            }
//
//            mejores.addAll(nuevosMejores);
//
////                    Collections.sort(nuevosMejores, Comparator.comparing(Map.Entry::getValue));
//            Collections.sort(mejores, Comparator.comparing(Map.Entry::getValue, Comparator.nullsLast(Comparator.naturalOrder())));
//
////                        entryList.sort(Comparator.comparing(entry -> entry.getValue(), Comparator.nullsLast(Comparator.naturalOrder())));
//
//            for (Map.Entry<POSICIONES,Integer> p : mejores) {
//                System.out.println("Opciones: " + p.getKey() + " : " + p.getValue());
//            }
//
//            for (Map.Entry<POSICIONES, Integer> entry : mejores) {
//                POSICIONES key = entry.getKey();
//                Integer value = entry.getValue();
//                if (value >= 0 /*&& key != sensores.opposite(posiciones)*/) {
//                    System.out.println("Escogida :"+ key + " : " + value);
//                    next_p = sensores.getNextPositon(key);
//                    System.out.println("Siguiente posicion2: " + key);
//                    break;
//                }
//
//            }
//
//            Point last = sensores.actualizarPosicionAgente(sensores.getAgentePos().x + next_p.x, sensores.getAgentePos().y + next_p.y);
//            System.out.println("\tActualizando el valor de la celda: " + last);
//            System.out.println("\tValor de la celda: " + sensores.getMapa().getValorCelda(last.x, last.y));
//
////                    sensores.getMapa().setValorCelda(last.x, last.y, sensores.getMapa().getValorCelda(last.x, last.y) + 1);
//            sensores.setVision(sensores.see());
//
//        }
//    }
//
//    static class ComportamientoDecision extends OneShotBehaviour{
//        private Sensores sensores;
//        private boolean decision = false;
//        public ComportamientoDecision(Sensores sensores) {
//            this.sensores = sensores;
//        }
//        @Override
//        public void action() {
//            for(int i = 0; i < sensores.getPosiciones().size(); i++) {
//                if (sensores.getAround(sensores.getPosiciones()).get(i) != null){
//                    if (sensores.getAround(sensores.getPosiciones()).get(i) == sensores.ID_OBJETIVO) {
//                        addBehaviour(new MovimientoSimple(sensores,sensores.getPosiciones().get(i)));
//                        decision = true;
//                    }
//                }
//            }
//            if(!decision){
//                addBehaviour(new MovimientoComplejo(sensores));
//            }
//
//        }
//    }
//
//    class CicloComportamiento extends TickerBehaviour {
//        private boolean objetivoEncontrado = false;
//        private boolean decisionTomada = false;
//        private Sensores sensores;
//
//        public CicloComportamiento(Sensores sensores) {
//            super(null, 1000); // Establece la frecuencia de ejecución en milisegundos
//            this.sensores = sensores;
//        }
//
//        protected void onTick() {
//            addBehaviour(new MostrarMapa(sensores));
//            addBehaviour(new GetInformation(sensores));
//            VerificarObjetivo verificarObjetivo = new VerificarObjetivo(sensores);
//            addBehaviour(verificarObjetivo);
//            objetivoEncontrado = verificarObjetivo.getenObjetivo();
//            decisionTomada = objetivoEncontrado;
//            System.out.println("OBJ ENCONTRADO" + objetivoEncontrado);
//            if(objetivoEncontrado) {
//                stop();
//                doDelete();
//                return;
//            }
//
//            if (!decisionTomada) {
//                // Ejecutar el comportamiento de tomar decisiones
//                addBehaviour(new ComportamientoDecision(sensores));
//            }
//
//            // Luego, ejecutar el comportamiento de actualizar posición
////            addBehaviour(new ComportamientoActualizarPosicion(sensores));
//
//            // Luego, vuelva a ejecutar este comportamiento para continuar el ciclo
//        }
//    }




}