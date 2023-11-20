import jade.core.behaviours.OneShotBehaviour;

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
            int step_value = env.distanciaManhattan(env.getAgentePos()) -  env.getMemoria().getValorCelda(env.getAgentePos().x, env.getAgentePos().y);
            System.out.println("Siguiente Paso: " + step_value);
            myAgent.addBehaviour(new MovimientoComplejo(env, 1));
        }

    }
}