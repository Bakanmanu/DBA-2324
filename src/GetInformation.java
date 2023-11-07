import jade.core.behaviours.OneShotBehaviour;

class GetInformation extends OneShotBehaviour {
    private Sensores sensores;

    public GetInformation(Sensores sensores){
        this.sensores = sensores;
    }
    @Override
    public void action() {
        System.out.println("Posicion del agente:" + sensores.getAgentePos());
        System.out.println("Posicion del objetivo:" + sensores.getObjetivo());
        sensores.setPosiciones( sensores.determinarDireccion());
        System.out.println("El objetivo se encuentra al: "+sensores.getPosiciones());
        System.out.println("Get around: "+sensores.getAround(sensores.getPosiciones()));
    }
}