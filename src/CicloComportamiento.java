import jade.core.behaviours.TickerBehaviour;

class CicloComportamiento extends TickerBehaviour {
    private boolean decisionTomada = false;
    private Sensores sensores;
    static private int num_steps = 0;

    public CicloComportamiento(Sensores sensores) {
        super(null, 1000); // Establece la frecuencia de ejecución en milisegundos
        this.sensores = sensores;
    }

    protected void onTick() {
        myAgent.addBehaviour(new MostrarMapa(sensores));
        myAgent.addBehaviour(new GetInformation(sensores));
        VerificarObjetivo verificarObjetivo = new VerificarObjetivo(sensores);
        myAgent.addBehaviour(verificarObjetivo);

        if (!decisionTomada) {
            myAgent.addBehaviour(new ComportamientoDecision(sensores));
        }
        num_steps++;
    }

    public int getNum_steps(){
        return num_steps;
    }
}