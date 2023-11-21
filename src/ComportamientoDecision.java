import jade.core.behaviours.OneShotBehaviour;


@SuppressWarnings("FieldMayBeFinal")

/**
 * Clase que toma la decicion sobre cual es el mejor movimiento que realizar, podemos buscar a nuestro alrededor y si encuentra
 * el objetivo, entonces realizare el movimiento simple hacia esa posicion, en caso contrario, tendre que pensar cual sera el mejor movimiento
 */
class ComportamientoDecision extends OneShotBehaviour {
    private Environment env;
    private boolean decision = false;
    public ComportamientoDecision(Environment env) {
        this.env = env;
    }

    @Override
    public void action() {
        for(int i = 0; i < env.getPosiciones().size(); i++) {
            if (env.getSensores().getAround(env.getPosiciones()).get(i) != null){
                if (env.getSensores().getAround(env.getPosiciones()).get(i) == env.ID_OBJETIVO) {
                    myAgent.addBehaviour(new MovimientoSimple(env,env.getPosiciones().get(i)));
                    decision = true;
                }
            }
        }
        if(!decision){
            myAgent.addBehaviour(new MovimientoComplejo(env));
        }

    }
}