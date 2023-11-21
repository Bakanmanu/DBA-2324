import jade.core.behaviours.OneShotBehaviour;

@SuppressWarnings("FieldMayBeFinal")
class GetInformation extends OneShotBehaviour {
    private Environment env;

    public GetInformation(Environment env){
        this.env = env;
    }
    @Override
    public void action() {
        System.out.println("Posicion del agente:" + env.getAgentePos());
        System.out.println("Posicion del objetivo:" + env.getObjetivo());
        env.setPosiciones( env.determinarDireccion());
        System.out.println("El objetivo se encuentra al: "+env.getPosiciones());
        System.out.println("Get around: "+env.getSensores().getAround(env.getPosiciones()));
    }
}