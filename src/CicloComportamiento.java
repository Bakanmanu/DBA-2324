import jade.core.behaviours.TickerBehaviour;

/**
 * Clase que representara el comportamiento ciclico del agente y todos los pasos que realizara
 */
class CicloComportamiento extends TickerBehaviour {
    private final Environment env;
    private int num_steps = 0;

    public CicloComportamiento(Environment env) {
        super(null, 1000);
        this.env = env;
    }

    protected void onTick() {
        myAgent.addBehaviour(new MostrarMapa(env));
        myAgent.addBehaviour(new GetInformation(env));
        VerificarObjetivo verificarObjetivo = new VerificarObjetivo(env, num_steps);
        myAgent.addBehaviour(verificarObjetivo);

        boolean decisionTomada = false;
        if (!decisionTomada) {
            myAgent.addBehaviour(new ComportamientoDecision(env));
        }
        num_steps++;
    }
}