import jade.core.behaviours.CyclicBehaviour;

class MostrarMapa extends CyclicBehaviour {
    private Environment env;

    public MostrarMapa(Environment env) {
        this.env = env;
    }

    public void action() {
        System.out.println("Mostrando mapa:");
        env.getMapa().mostrarMapa();
        System.out.println("---------------------------------------------");
        env.getMemoria().mostrarMapa();
        System.out.println("---------------------------------------------");
    }
}