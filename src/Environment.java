import java.awt.Point;
import java.util.ArrayList;

public class Environment {

    final int ID_AGENTE = -9;
    final int ID_OBJETIVO = -5;

    private Sensores sensores;
    private Point agent_pos;
    private Point obj_pos;
    private Mapa mapa;
    private Mapa memoria;
    private ArrayList<POSICIONES> posiciones; //2 Posiciones a donde me tengo que mover porque esta el objetivo



    Environment() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Environment(Mapa mapa,Sensores sensores, int agent_f, int agent_c, int obj_f, int obj_c) {
        this.mapa = mapa.clone();
        this.sensores = sensores;
        this.memoria = mapa.clone();
        this.agent_pos = new Point(agent_f, agent_c);
        this.obj_pos = new Point(obj_f, obj_c);
        setAgent(agent_pos);
        setObjetivo(obj_pos);
//        memoria.setValorCelda(agent_pos.x, agent_pos.y, 1); // Nada mas aparezco pongo un 1 en la memoria
        memoria.setValorCelda(obj_pos.x, obj_pos.y, ID_OBJETIVO);
        sensores.see(agent_pos, memoria);
        posiciones = determinarDireccion();
        compute_man();
    }

    public ArrayList<POSICIONES> getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(ArrayList<POSICIONES> posiciones) {
        this.posiciones = posiciones;
    }

    public Sensores getSensores(){
        return this.sensores;
    }

    public void setSensores(Sensores s){
        this.sensores = s;
    }

    public Point actualizarPosicionAgente(int fila, int columna, int step) {
        Point last = new Point(agent_pos);
        agent_pos.setLocation(new Point(fila, columna));
        setAgent(agent_pos);
        mapa.setValorCelda(last.x, last.y, 0);
        compute_man();
        memoria.setValorCelda(agent_pos.x, agent_pos.y, memoria.getValorCelda(agent_pos.x, agent_pos.y)+step);
        sensores.see(agent_pos, memoria);

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

    public Mapa getMapa() {
        return mapa;
    }

    public Mapa getMemoria() {
        return memoria;
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
    public int distanciaManhattan(Point pos) {
        return Math.abs(pos.x - obj_pos.x) + Math.abs(pos.y - obj_pos.y);
    }

    public void compute_man(){
        ArrayList<Point> puntos = new ArrayList<>();
        puntos.add(new Point(0,0));
        for(POSICIONES p : POSICIONES.values()){
            puntos.add(getNextPositon(p));
        }

        for(Point p : puntos){
            Point curr = new Point(getAgentePos().x + p.x, getAgentePos().y + p.y);
            int dist = distanciaManhattan(curr);
            Integer cell_value = memoria.getValorCelda(curr.x,curr.y);
            if(cell_value == 0 && cell_value != null){
                memoria.setValorCelda(curr.x,curr.y, dist);
            }

        }

    }
}



/*
Clase Planteamiento:Sensores qombre de es que asi usamos siglas y es mas  Ncomodo)?eAbajo S

Clase Enviroment:
    - :MAPA              ¿Mapa origina  ?
    - MEMORIA          ¿mapa del cami   n- POS_AGENTE   int fil int col //renta hacer una clase/struct posicion que guarde in fil int col
    - POS_OBJ       int fil int col
    - GetNexPosition()
        - Si

Clase Agente:
    -()()()Comportamiento Iniciar
-Comportamiento buscar objetivo
-Comportamiento ObjetivoAlcanzado
*/