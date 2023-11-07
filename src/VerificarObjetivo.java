import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;

class VerificarObjetivo extends OneShotBehaviour {
    private Sensores sensores;

    public VerificarObjetivo(Sensores sensores) {
        this.sensores = sensores;
    }

    public void action() {
        if (sensores.getAgentePos().equals(sensores.getObjetivo())) {
            System.out.println("ENCONTRADO");
            myAgent.doDelete();
        }
    }
}