import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

public class InformRenoSanta extends SimpleBehaviour{
    @Override
    public void action() {
        BuscadorAgent miAgente = (BuscadorAgent) myAgent;
        if(miAgente.isRenoEncontrado()){
            ACLMessage mensaje = new ACLMessage(ACLMessage.INFORM);
            String contenido = "¡Santa, encontré a "+ miAgente.getNameRenos().get(miAgente.getIdReno())+"!";
            mensaje.setContent(contenido);

            mensaje.addReceiver(miAgente.getAID("SantaClaus"));

            miAgente.send(mensaje);
            System.out.println(contenido);
        }
    }
    @Override
    public boolean done() {
        BuscadorAgent miAgente = (BuscadorAgent) myAgent;
//        System.out.println("Mi trabajo aqui ha finalizado - InformRenoSanta");
        return miAgente.getIdReno() == miAgente.getNameRenos().size()-1;
    }
}
