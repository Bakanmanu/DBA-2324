import java.awt.Point;
import java.util.ArrayList;

public class Sensores {
    private ArrayList<Integer> vision;

    public Sensores(Mapa memoria, Point agent_pos) {
        see(agent_pos, memoria);
    }

    public ArrayList<Integer> getVision(){
        return vision;
    }
    public void setVision(ArrayList<Integer> v){
        vision = v;
    }

    // Funcion que dada la posicion del agente mira a su alrededer y nos devuelve las casiilas adyacentes
    public ArrayList<Integer> see(Point agent_pos, Mapa memoria) {
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
                    submatriz.add(null);
                }
            }
        }
        vision = submatriz;
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
        };
    }
}