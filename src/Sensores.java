import java.awt.Point;

public class Sensores {
    private Mapa mapa;
    private Point agent_pos;
    private Point obj_pos;

    public Sensores(Mapa mapa, int agent_f, int agent_c, int obj_f, int obj_c) {
        this.mapa = mapa;
        this.agent_pos = new Point(agent_f, agent_c);
        this.obj_pos = new Point(obj_f, obj_c);
    }

    Sensores() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void actualizarPosicion(int fila, int columna) {
        agent_pos.setLocation(new Point(fila, columna));
    }

    public void setAgent(Point p){
        mapa.setValorCelda(p.x, p.y, 5);
    }

    public void setObjetivo(Point p){
        mapa.setValorCelda(p.x, p.y, 9);
    }
    public Point getObetivo(){
        return obj_pos;
    }

    public Mapa getMapa() {
        return mapa;
    }


    public int verCeldaNorte() {
        // Obtener el valor de la celda al norte del agente
        return 0;
        // Obtener el valor de la celda al norte del agente
    }

    public int verCeldaSur() {
        // Obtener el valor de la celda al sur del agente
        return 0;
        // Obtener el valor de la celda al sur del agente
    }

    // Implementar otros métodos para ver celdas adyacentes
}

