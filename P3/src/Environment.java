import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @class Environment
 * @brief Clase que modela el entorno del agente, incluyendo el mapa, la posición del agente y el objetivo, y métodos relacionados.
 */
@SuppressWarnings("FieldMayBeFinal")
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

    /**
     * @brief Constructor de la clase Environment.
     * @param mapa Mapa inicial del entorno.
     * @param sensores Sensores utilizados por el agente.
     * @param agent_f Fila inicial del agente.
     * @param agent_c Columna inicial del agente.
     * @param obj_f Fila del objetivo.
     * @param obj_c Columna del objetivo.
     */
    public Environment(Mapa mapa,Sensores sensores, int agent_f, int agent_c) {
        this.mapa = mapa.clone();
        this.sensores = sensores;
        this.memoria = mapa.clone();
        this.agent_pos = new Point(agent_f, agent_c);
        // this.obj_pos = new Point(obj_f, obj_c);
        setAgent(agent_pos);
        // setObjetivo(obj_pos);
// memoria.setValorCelda(obj_pos.x, obj_pos.y, ID_OBJETIVO);
        sensores.see(agent_pos, memoria);
        //
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

    /**
     * Actualizo la posicion de agente y modificando los valores tanto en el mapa y en la memoria, ademas volvemos a calcular la distancia manh y volvemos a observar
     * @param fila Siguiente fila a la que me muevo
     * @param columna Siguiente col a la que me muevo
     * @param step Valor que pongo sobre la celda que ya he pasado
     * @return La anterior posicion antes de realizar el movimiento
     */
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
        obj_pos = p;
        mapa.setValorCelda(p.x, p.y, ID_OBJETIVO);

        this.memoria = mapa.clone();
        memoria.setValorCelda(obj_pos.x, obj_pos.y, ID_OBJETIVO);
        posiciones = determinarDireccion();
        compute_man();
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

    /**
     *Determina las direcciones hacia las que el agente debe moverse para alcanzar un objetivo.
     *
     * Esta función calcula la distancia horizontal y vertical entre la posición del agente y la posición del objetivo.
     * Luego, determina las direcciones hacia las cuales el agente debe moverse para acercarse al objetivo, y las devuelve en una lista.
     * La dirección horizontal tiene prioridad sobre la vertical, y se invierten las direcciones si la distancia horizontal es mayor que la vertical.
     *
     * @return Lista de direcciones hacia las cuales el agente debe moverse para alcanzar el objetivo.
     */
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
        if (distanciaHorizontal < distanciaVertical){
            Collections.reverse(direcciones);
        }
        return direcciones;
    }

    public Mapa getMemoria() {
        return memoria;
    }


    /**
     * Obtiene la posición opuesta a la dada.
     *
     * Esta función toma una posición y devuelve la posición opuesta a la misma.
     * Si la posición dada es ABAJO, devuelve ARRIBA. Si es ARRIBA, devuelve ABAJO.
     * Si es DERECHA, devuelve IZQUIERDA, y si es IZQUIERDA, devuelve DERECHA.
     * Si la posición dada no es ninguna de las anteriores, se devuelve la misma posición.
     *
     * @param p Posición para la cual se debe obtener la posición opuesta.
     * @return Posición opuesta a la dada.
     */
    public POSICIONES opposite(POSICIONES p){
        if(p == POSICIONES.ABAJO) return POSICIONES.ARRIBA;
        else if(p == POSICIONES.ARRIBA) return POSICIONES.ABAJO;
        else if(p == POSICIONES.DERECHA) return POSICIONES.IZQUIERDA;
        else if(p == POSICIONES.IZQUIERDA) return POSICIONES.DERECHA;
        return p;
    }

    /**
     * Obtiene la siguiente posición basada en una dirección dada.
     * Esta función toma una posición y devuelve la siguiente posición basada en la dirección proporcionada.
     * Utiliza una expresión switch para determinar la nueva posición en función de la dirección dada.
     *
     * @param p Dirección que indica hacia dónde debe moverse la posición actual.
     * @return Nueva posición después de moverse en la dirección especificada.
     */
    public Point getNextPositon(POSICIONES p) {
        return switch (p) {
            case ARRIBA -> new Point(-1, 0);
            case ABAJO -> new Point(1, 0);
            case DERECHA -> new Point(0, 1);
            case IZQUIERDA -> new Point(0, -1);
        };
    }

    /**
     * Calcula la distancia de Manhattan entre dos puntos.
     * Esta función toma un punto y calcula la distancia de Manhattan entre ese punto y el objeto
     * especificado por `obj_pos`. La distancia de Manhattan es la suma de las diferencias absolutas
     * de las coordenadas x e y entre los dos puntos.
     *
     * @param pos Punto para el cual se calculará la distancia de Manhattan al objeto.
     * @return Distancia de Manhattan entre el punto dado y el objeto.
     */
    public int distanciaManhattan(Point pos) {
        return Math.abs(pos.x - obj_pos.x) + Math.abs(pos.y - obj_pos.y);
    }

    /**
     * Calcula y actualiza los valores de la memoria de acuerdo con la distancia de Manhattan.
     *
     * Esta función calcula la distancia de Manhattan entre la posición actual del agente y varias posiciones
     * circundantes. Luego, actualiza los valores de la memoria según la distancia calculada, siempre y cuando
     * la celda no haya sido visitada previamente.
     */
    public void compute_man() {
        // Lista de puntos que representan posiciones circundantes
        ArrayList<Point> puntos = new ArrayList<>();

        // Añade el punto inicial (0, 0)
        puntos.add(new Point(0, 0));

        // Añade los puntos circundantes basados en las direcciones posibles
        for(POSICIONES p : POSICIONES.values()){
            puntos.add(getNextPositon(p));
        }

        // Itera sobre cada punto y calcula la distancia de Manhattan
        for(Point p : puntos){
            // Calcula la posición actual sumando el punto ala posición del agente
            Point curr = new Point(getAgentePos().x + p.x, getAgentePos().y + p.y);

            // Calcula la distancia de Manhattan desde la posición actual hasta el objeto
            int dist = distanciaManhattan(curr);

            // Obtiene el valor actual de la celda en la memoria
            Integer cell_value = memoria.getValorCelda(curr.x, curr.y);

            if(cell_value == null || cell_value == 0){
                memoria.setValorCelda(curr.x, curr.y, dist);
            }
        }
    }

}
