import jade.core.behaviours.TickerBehaviour;

class CicloComportamiento extends TickerBehaviour {
    private boolean decisionTomada = false;
    private Environment env;

    static private int num_steps = 0;

    public CicloComportamiento(Environment env) {
        super(null, 1000); // Establece la frecuencia de ejecución en milisegundos
        this.env = env;
    }

    protected void onTick() {
        myAgent.addBehaviour(new MostrarMapa(env));
        myAgent.addBehaviour(new GetInformation(env));
        VerificarObjetivo verificarObjetivo = new VerificarObjetivo(env, num_steps);
        myAgent.addBehaviour(verificarObjetivo);

        if (!decisionTomada) {
            myAgent.addBehaviour(new ComportamientoDecision(env));
        }
        num_steps++;

    }
}