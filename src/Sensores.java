import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;


public class Sensores {

    private Mapa mapa;
    private Mapa memoria;
    final int ID_AGENTE = -9;
    final int ID_OBJETIVO = -5;

    private Point agent_pos;
    private Point obj_pos;

    private ArrayList<Integer> vision;

    public Sensores(Mapa mapa, int agent_f, int agent_c, int obj_f, int obj_c) {
        this.mapa = mapa.clone();
        this.memoria = mapa.clone();
        this.agent_pos = new Point(agent_f, agent_c);
        this.obj_pos = new Point(obj_f, obj_c);
        setAgent(agent_pos);
        setObjetivo(obj_pos);
        memoria.setValorCelda(agent_pos.x, agent_pos.y, 1);
        vision = see();
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

    public Integer getAround(POSICIONES p) {
//        System.out.println(Arrays.toString(vision.toArray()));
        return switch (p) {
            case ARRIBA -> this.vision.get(1);
            case ABAJO -> this.vision.get(7);
            case DERECHA -> this.vision.get(5);
            case IZQUIERDA -> this.vision.get(3);
            default -> null;
        };
    }

    public Point getNextPositon(POSICIONES p) {
        return switch (p) {
            case ARRIBA -> new Point(-1,0);
            case ABAJO -> new Point(1,0);
            case DERECHA -> new Point(0,1);
            case IZQUIERDA -> new Point(0,-1);
            default -> null;
        };
    }

    public int distanciaManhattan() {
        return Math.abs(agent_pos.x - obj_pos.x) + Math.abs(agent_pos.y - obj_pos.y);
    }

    public POSICIONES determinarDireccion() {
        int distanciaHorizontal = Math.abs(agent_pos.x - obj_pos.x);
        int distanciaVertical = Math.abs(agent_pos.y - obj_pos.y);

        if (distanciaHorizontal > distanciaVertical) {
            if (agent_pos.x < obj_pos.x) {
                return POSICIONES.ABAJO;
            } else {
                return POSICIONES.ARRIBA;
            }
        } else {
            if (agent_pos.y < obj_pos.y) {
                return POSICIONES.DERECHA;
            } else {
                return POSICIONES.IZQUIERDA;
            }
        }
        //CUANDO SON IGUALES METER ALGO PARA K NO SE QUEDE PILLAO
    }

    public POSICIONES opposite(POSICIONES p){
        if(p == POSICIONES.ABAJO) return POSICIONES.ARRIBA;
        else if(p == POSICIONES.ARRIBA) return POSICIONES.ABAJO;
        else if(p == POSICIONES.DERECHA) return POSICIONES.IZQUIERDA;
        else if(p == POSICIONES.IZQUIERDA) return POSICIONES.DERECHA;
        return p;
    }

}