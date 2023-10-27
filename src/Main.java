import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.tools.introspector.Sensor;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import java.util.Arrays;



public class Main {
    public static void main(String[] args) {
        try {
            Mapa mapa = new Mapa(args[0]);
            Sensores sensor = new Sensores(mapa, 0, 0, 1,1);
            //mapa.mostrarMapa();
            Runtime rt = Runtime.instance();
            Profile profile = new ProfileImpl();
            profile.setParameter(Profile.MAIN_HOST, "localhost");
            profile.setParameter(Profile.CONTAINER_NAME, "dba_server");
            ContainerController container = rt.createMainContainer(profile);
            Object[] customArgs = new Object[] { mapa, sensor };
            AgentController agent = container.createNewAgent("ComportamientoAgente", ComportamientoAgente.class.getName(), new Object[] { mapa, sensor });
            agent.start();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
