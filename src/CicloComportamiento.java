import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;

class CicloComportamiento extends OneShotBehaviour {
    private boolean decisionTomada = false;
    private Environment env;

    static private int num_steps = 0;

    public CicloComportamiento(Environment env) {
        this.env = env;
    }

    @Override
    public void action() {
        System.out.println("Inicializando ciclos");
        myAgent.addBehaviour(new MostrarMapa(env));
        myAgent.addBehaviour(new GetInformation(env));
        VerificarObjetivo verificarObjetivo = new VerificarObjetivo(env);
        myAgent.addBehaviour(verificarObjetivo);

        myAgent.addBehaviour(new ComportamientoDecision(env));
//        if (!decisionTomada) {
//        }
//        num_steps++;

    }
}