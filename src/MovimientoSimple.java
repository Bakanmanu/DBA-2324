import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;


import java.awt.*;

class MovimientoSimple extends OneShotBehaviour {
    private Environment env;
    private POSICIONES mov;

    public MovimientoSimple(Environment env, POSICIONES m){
        this.env = env;
        this.mov = m;
    }
    @Override
    public void action() {
        System.out.println("------------------ MOVIMIENTO SIMPLE ------------------");
        System.out.println("Valor next cell" + env.getSensores().getAround(env.getPosiciones()));
        Point next_p = env.getNextPositon(mov);
        System.out.println("Siguiente posicion" + next_p.toString());
        Point last = env.actualizarPosicionAgente(env.getAgentePos().x + next_p.x, env.getAgentePos().y + next_p.y,1);
        env.getSensores().setVision(env.getSensores().see(env.getAgentePos(), env.getMemoria()));

    }
}