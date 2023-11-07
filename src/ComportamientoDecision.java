import jade.core.behaviours.OneShotBehaviour;

class ComportamientoDecision extends OneShotBehaviour {
    private Sensores sensores;
    private boolean decision = false;
    public ComportamientoDecision(Sensores sensores) {
        this.sensores = sensores;
    }
    @Override
    public void action() {
        for(int i = 0; i < sensores.getPosiciones().size(); i++) {
            if (sensores.getAround(sensores.getPosiciones()).get(i) != null){
                if (sensores.getAround(sensores.getPosiciones()).get(i) == sensores.ID_OBJETIVO) {
                    myAgent.addBehaviour(new MovimientoSimple(sensores,sensores.getPosiciones().get(i)));
                    decision = true;
                }
            }
        }
        if(!decision){
            myAgent.addBehaviour(new MovimientoComplejo(sensores));
        }

    }
}