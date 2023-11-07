import jade.core.behaviours.OneShotBehaviour;

class VerificarObjetivo extends OneShotBehaviour {
    private Sensores sensores;
    private boolean enObjetivo = false;

    public VerificarObjetivo(Sensores sensores) {
        this.sensores = sensores;
    }

    public void action() {
        if (sensores.getAgentePos().equals(sensores.getObjetivo())) {
            System.out.println("ENCONTRADO");
            // Realiza las acciones necesarias cuando el agente encuentra el objetivo
            enObjetivo = true;
        }
    }
    public boolean getenObjetivo(){
        return enObjetivo;
    }
//        public int onEnd() {
//            if (!enObjetivo) {
//                myAgent.addBehaviour(new MovimientoSimple(sensores));
//            }
//            return super.onEnd();
//        }
}