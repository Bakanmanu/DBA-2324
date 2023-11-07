import jade.core.behaviours.TickerBehaviour;

class CicloComportamiento extends TickerBehaviour {
    private boolean objetivoEncontrado = false;
    private boolean decisionTomada = false;
    private Sensores sensores;

    public CicloComportamiento(Sensores sensores) {
        super(null, 1000); // Establece la frecuencia de ejecución en milisegundos
        this.sensores = sensores;
    }

    protected void onTick() {
        myAgent.addBehaviour(new MostrarMapa(sensores));
        myAgent.addBehaviour(new GetInformation(sensores));
        VerificarObjetivo verificarObjetivo = new VerificarObjetivo(sensores);
        myAgent.addBehaviour(verificarObjetivo);
        objetivoEncontrado =
        decisionTomada = objetivoEncontrado;
        System.out.println("OBJ ENCONTRADO" + objetivoEncontrado);
        if(objetivoEncontrado) {
            stop();
            myAgent.doDelete();
            return;
        }

        if (!decisionTomada) {
            // Ejecutar el comportamiento de tomar decisiones
            myAgent.addBehaviour(new ComportamientoDecision(sensores));
        }

        // Luego, ejecutar el comportamiento de actualizar posición
//            addBehaviour(new ComportamientoActualizarPosicion(sensores));

        // Luego, vuelva a ejecutar este comportamiento para continuar el ciclo
    }
}