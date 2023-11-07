import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;


public class Sensores {

    private Mapa mapa;          // Mapa
    private Mapa memoria;       // Memoria por donde voy pasando
    final int ID_AGENTE = -9;
    final int ID_OBJETIVO = -5;

    private Point agent_pos;
    private Point obj_pos;

    private ArrayList<Integer> vision;
    private ArrayList<POSICIONES> posiciones; //Posiciones a donde me tengo que mover porque esta el objetivo

    public Sensores(Mapa mapa, int agent_f, int agent_c, int obj_f, int obj_c) {
        this.mapa = mapa.clone();
        this.memoria = mapa.clone();
        this.agent_pos = new Point(agent_f, agent_c);
        this.obj_pos = new Point(obj_f, obj_c);
        setAgent(agent_pos);
        setObjetivo(obj_pos);
        memoria.setValorCelda(agent_pos.x, agent_pos.y, 1); // Nada mas aparezco pongo un 1 en la memoria
        memoria.setValorCelda(obj_pos.x, obj_pos.y, ID_OBJETIVO); // Nada mas aparezco pongo un 1 en la memoria

        vision = see();
        posiciones = determinarDireccion();
    }

    Sensores() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Point actualizarPosicionAgente(int fila, int columna) {
        Point last = new Point(agent_pos);
        agent_pos.setLocation(new Point(fila, columna));
        setAgent(agent_pos);
        mapa.setValorCelda(last.x, last.y, 0);
        memoria.setValorCelda(agent_pos.x, agent_pos.y, memoria.getValorCelda(agent_pos.x, agent_pos.y)+1);

        vision = see();
        return last;
    }

    public void setAgent(Point p) {
        mapa.setValorCelda(p.x, p.y, ID_AGENTE);
    }

    public void setObjetivo(Point p) {
        mapa.setValorCelda(p.x, p.y, ID_OBJETIVO);
    }

    public Point getObjetivo() {
        return obj_pos;
    }

    public Point getAgentePos() {
        return agent_pos;
    }

    public ArrayList<Integer> getVision(){
        return vision;
    }

    public void setVision(ArrayList<Integer> v){
        vision = v;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public Mapa getMemoria() {
        return memoria;
    }


    // Funcion que dada la posicion del agente mira a su alrededer y nos devuelve las casiilas adyacentes
    public ArrayList<Integer> see() {
        int fila = agent_pos.x;
        int columna = agent_pos.y;

        ArrayList<Integer> submatriz = new ArrayList<>();
        int filas = memoria.getFilas();
        int columnas = memoria.getColumnas();

        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {
                if (i >= 0 && i < filas && j >= 0 && j < columnas) {
                    submatriz.add(memoria.getValorCelda(i, j));
                } else {
                    submatriz.add(null);  // Puedes usar otro valor especial si está fuera de la matriz
                }
            }
        }
        return submatriz;
    }



    public ArrayList<Integer> getAround(ArrayList<POSICIONES> p) {
        ArrayList<Integer> direcciones = new ArrayList<>();
        for(POSICIONES pos : p){
            direcciones.add( switch (pos) {
                case ARRIBA -> this.vision.get(1);
                case ABAJO -> this.vision.get(7);
                case DERECHA -> this.vision.get(5);
                case IZQUIERDA -> this.vision.get(3);
                default -> null;
            });


        }

        return direcciones;
}
    public Integer getSimpleAround(POSICIONES p) {
        return switch (p) {
            case ARRIBA -> this.vision.get(1);
            case ABAJO -> this.vision.get(7);
            case DERECHA -> this.vision.get(5);
            case IZQUIERDA -> this.vision.get(3);
            default -> null;
        };
    }
    // Devuelve la dirección hacia donde está el objetivo
    // "x" son las filas  "y" son las columnas tipo matriz
    public ArrayList<POSICIONES> determinarDireccion() {
        int distanciaHorizontal = Math.abs(agent_pos.x - obj_pos.x);
        int distanciaVertical = Math.abs(agent_pos.y - obj_pos.y);
        ArrayList<POSICIONES> direcciones = new ArrayList<>();

        if (agent_pos.x < obj_pos.x) {
            direcciones.add(POSICIONES.ABAJO);
        } else {
            direcciones.add(POSICIONES.ARRIBA);
        }
        if (agent_pos.y < obj_pos.y) {
            direcciones.add(POSICIONES.DERECHA);

        } else {
            direcciones.add(POSICIONES.IZQUIERDA);
        }
        return direcciones;
    }

            // Devuelve la dirección opuesta a 'p'
            // Es que esta no funciona muy bien cuando el objetivo esta arriba y hay que ir hacia abajo
            public POSICIONES opposite(POSICIONES p){
                if(p == POSICIONES.ABAJO) return POSICIONES.ARRIBA;
                else if(p == POSICIONES.ARRIBA) return POSICIONES.ABAJO;
                else if(p == POSICIONES.DERECHA) return POSICIONES.IZQUIERDA;
                else if(p == POSICIONES.IZQUIERDA) return POSICIONES.DERECHA;
                return p;
            }

            // Dada una posicion a la que deberia desplazarma "p" ver que es lo que se encuentra en esa direccion (vision)
        // Puede devolver MUR0 = -1
        //                No explorado = 0
        //                Ya visitado > 1
        //                null
        // Devuelve la posicion de la celda de al lado del agente en la direccion especificada
        // Mas que posicion es la direccion, fiajte en los Points, es una suma de la
        public Point getNextPositon(POSICIONES p) {
            return switch (p) {
                case ARRIBA -> new Point(-1, 0);
                case ABAJO -> new Point(1, 0);
                case DERECHA -> new Point(0, 1);
                case IZQUIERDA -> new Point(0, -1);
                default -> null;
            };
        }
        public int distanciaManhattan() {
                return Math.abs(agent_pos.x - obj_pos.x) + Math.abs(agent_pos.y - obj_pos.y);
        }

    public ArrayList<POSICIONES> getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(ArrayList<POSICIONES> posiciones) {
        this.posiciones = posiciones;
    }
}