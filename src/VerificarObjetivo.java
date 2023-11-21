import jade.core.behaviours.OneShotBehaviour;

@SuppressWarnings("FieldMayBeFinal")
class VerificarObjetivo extends OneShotBehaviour {
    private Sensores sensores;
    private Environment env;
    private int numSteps;

    public VerificarObjetivo(Environment env, int numSteps) {
        this.env = env;
        this.numSteps = numSteps;
    }

    public void action() {
        if (env.getAgentePos().equals(env.getObjetivo())) {
            System.out.println("ENCONTRADO en el paso: " + numSteps);
            myAgent.doDelete();
        }
    }
}