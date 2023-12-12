import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;

import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

import java.awt.*;
import java.util.ArrayList;

public class Main2 {

    public static void main(String[] args) {
        try {
            // Configuración de JADE
            Runtime rt = Runtime.instance();
            Profile profile = new ProfileImpl();
            AgentContainer container = rt.createMainContainer(profile);
            ArrayList<Point> coords = new ArrayList<>();
            coords.add(new Point(8,5));
            coords.add(new Point(1,1));
            coords.add(new Point(2,2));

            Mapa mapa = new Mapa(args[0]);
            Point agent_pos = new Point(0, 0);
            Point obj_pos = new Point(7, 7);

            Sensores sensor = new Sensores(mapa, agent_pos);
            Environment env = new Environment(mapa, sensor, agent_pos.x, agent_pos.y, obj_pos.x, obj_pos.y);

            // Lanzar agentes
            AgentController santaClausController = container.createNewAgent("SantaClaus", SantaClausAgent.class.getName(), null);
            santaClausController.start();

            AgentController rudolphController = container.createNewAgent("Rudolph", RudolphAgent.class.getName(), new Object[]{coords});
            rudolphController.start();

            AgentController buscadorController = container.createNewAgent("Buscador", BuscadorAgent.class.getName(), new Object[]{env});
            buscadorController.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
