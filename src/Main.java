import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

import java.awt.*;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(Arrays.toString(args));
            Mapa mapa = new Mapa(args[0]);
            Point agent_pos = new Point(0, 0);
            Point obj_pos = new Point(8, 5);

            Sensores sensor = new Sensores(mapa, agent_pos);
            Environment env = new Environment(mapa, sensor, agent_pos.x, agent_pos.y, obj_pos.x, obj_pos.y);
            Runtime rt = Runtime.instance();
            Profile profile = new ProfileImpl();
            profile.setParameter(Profile.MAIN_HOST, "localhost");
            profile.setParameter(Profile.CONTAINER_NAME, "dba_server");
            ContainerController container = rt.createMainContainer(profile);
            AgentController agent = container.createNewAgent("ComportamientoAgente", ComportamientoAgente.class.getName(), new Object[]{env});
            agent.start();


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
