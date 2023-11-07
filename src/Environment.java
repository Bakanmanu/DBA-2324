import java.awt.Point;
/*
public class Environment {
    private Sensores sensores;
    private Point agent_pos;
    private Point obj_pos;
    private Mapa memoria;
    Sensores getSensores(Sensores sensores);
    void setSensores(int fil ,int col);
    void acutalizarPos(int fil ,int colint);
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


    // Dada una posicion a la que deberia desplazarma "p" ver que es lo que se encuentra en esa direccion (vision)
    // Puede devolver MUR0 = -1
    //                No explorado = 0
    //                Ya visitado > 1
    //                null
    // Devuelve la posicion de la celda de al lado del agente en la direccion especificada
    // Mas que posicion es la direccion, fiajte en los Points, es una suma de la
    public Point getNextPositon(POSICIONES p) {
        return switch (p) {
            case ARRIBA -> new Point(-1,0);
            case ABAJO -> new Point(1,0);
            case DERECHA -> new Point(0,1);
            case IZQUIERDA -> new Point(0,-1);
            default -> null;
        }

    public int distanciaManhattan() {
        return Math.abs(agent_pos.x - obj_pos.x) + Math.abs(agent_pos.y - obj_pos.y);
    }
    // CUIDADO CON ESTA, PORQUE ES UN POCO XD
    // Devuelve la dirección hacia donde está el objetivo
    // "x" son las filas  "y" son las columnas tipo matriz
    // No me gusta mucho esta funcion ehhh
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
    public ArrayList<Integer> getAround(ArrayList<POSICIONES> p) {
        ArrayList<Integer> direcciones = new ArrayList<>();
        for(POSICIONES pos : p){
            direcciones.add( switch (p) {
                case ARRIBA -> this.vision.get(1);
                case ABAJO -> this.vision.get(7);
                case DERECHA -> this.vision.get(5);
                case IZQUIERDA -> this.vision.get(3);
                default -> null;
            });
        }
        switch (p) {
            case ARRIBA -> this.vision.get(1);
            case ABAJO -> this.vision.get(7);
            case DERECHA -> this.vision.get(5);
            case IZQUIERDA -> this.vision.get(3);
            default -> null;
        };
        return direcciones;
}
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

    }
}
*/

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