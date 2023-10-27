import java.awt.Point;
import java.util.ArrayList;

public class Sensores {
    private Mapa mapa;
    private final int ID_AGENTE = 9;
    private final int ID_OBJETIVO = 5;

    private Point agent_pos;
    private Point obj_pos;

    public Sensores(Mapa mapa, int agent_f, int agent_c, int obj_f, int obj_c) {
        this.mapa = mapa;
        this.agent_pos = new Point(agent_f, agent_c);
        this.obj_pos = new Point(obj_f, obj_c);
        mapa.setValorCelda(agent_pos.x,agent_pos.y, ID_AGENTE);
        setObjetivo(obj_pos);
    }

    Sensores() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void actualizarPosicionAgente(int fila, int columna) {
        Point last = new Point(agent_pos);
        agent_pos.setLocation(new Point(fila, columna));
        System.out.println("\tAgente"+agent_pos.toString());
        System.out.println("\tLast"+last.toString());

        mapa.setValorCelda(agent_pos.x,agent_pos.y, ID_AGENTE);
        mapa.setValorCelda(last.x,last.y, 0);

    }

    public void setAgent(Point p){
        mapa.setValorCelda(p.x, p.y, ID_AGENTE);
    }

    public void setObjetivo(Point p){
        mapa.setValorCelda(p.x, p.y, ID_OBJETIVO);
    }
    public Point getObetivo(){
        return obj_pos;
    }
    public Point getAgentePos(){
        return agent_pos;
    }


    public Mapa getMapa() {
        return mapa;
    }


    public ArrayList<Integer> see() {
        int fila = agent_pos.x;
        int columna = agent_pos.y;

        ArrayList<Integer> submatriz = new ArrayList<>();
        int filas = mapa.getFilas();
        int columnas = mapa.getColumnas();

        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {
                if (i >= 0 && i < filas && j >= 0 && j < columnas) {
                    submatriz.add(mapa.getValorCelda(i,j));
                } else {
                    submatriz.add(null);  // Puedes usar otro valor especial si está fuera de la matriz
                }
            }
        }
        return submatriz;
    }

    public int verCeldaNorte() {

        return 0;

    }

    public int verCeldaSur() {
        // Obtener el valor de la celda al sur del agente
        return 0;
        // Obtener el valor de la celda al sur del agente
    }

    // Implementar otros métodos para ver celdas adyacentes
}

