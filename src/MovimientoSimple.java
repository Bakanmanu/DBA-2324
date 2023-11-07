import jade.core.behaviours.OneShotBehaviour;

import java.awt.*;

class MovimientoSimple extends OneShotBehaviour {
    private Sensores sensores;
    private POSICIONES mov;
    public MovimientoSimple(Sensores sensores, POSICIONES m){
        this.sensores = sensores;
        this.mov = m;
    }
    @Override
    public void action() {
        System.out.println("------------------ MOVIMIENTO SIMPLE ------------------");
        System.out.println("Valor next cell" + sensores.getAround(sensores.getPosiciones()));
        Point next_p = sensores.getNextPositon(mov);
        System.out.println("Siguiente posicion" + next_p.toString());
        Point last = sensores.actualizarPosicionAgente(sensores.getAgentePos().x + next_p.x, sensores.getAgentePos().y + next_p.y);
        sensores.setVision(sensores.see());

    }
}