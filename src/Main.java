import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(Arrays.toString(args));
            Mapa mapa = new Mapa(args[0]);
            Sensores sensor = new Sensores(mapa, 0,0,8, 4);
            Runtime rt = Runtime.instance();
            Profile profile = new ProfileImpl();
            profile.setParameter(Profile.MAIN_HOST, "localhost");
            profile.setParameter(Profile.CONTAINER_NAME, "dba_server");
            ContainerController container = rt.createMainContainer(profile);
            AgentController agent = container.createNewAgent("ComportamientoAgente", ComportamientoAgente.class.getName(), new Object[] {sensor});
            agent.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
