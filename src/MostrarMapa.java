import jade.core.behaviours.OneShotBehaviour;

class MostrarMapa extends OneShotBehaviour {
    private Sensores sensores;

    public MostrarMapa(Sensores sensores) {
        this.sensores = sensores;
    }

    public void action() {
        System.out.println("Mostrando mapa:");
        sensores.getMapa().mostrarMapa();
        System.out.println("---------------------------------------------");
        sensores.getMemoria().mostrarMapa();
        System.out.println("---------------------------------------------");
    }
}