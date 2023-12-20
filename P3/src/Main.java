import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;

import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            // Configuración de JADE
            Runtime rt = Runtime.instance();
            Profile profile = new ProfileImpl();
            AgentContainer container = rt.createMainContainer(profile);
            ArrayList<Point> coords = new ArrayList<>();
            // Coordenadas para 10x10
            /*coords.add(new Point(8,5));
            coords.add(new Point(1,1));
            coords.add(new Point(2,4));
            coords.add(new Point(2,3));
            coords.add(new Point(2,2));
            coords.add(new Point(2,1));
            coords.add(new Point(1,5));
            coords.add(new Point(1,9));*/
            // Coordenadas para 40x40
            coords.add(new Point(8,5));
            coords.add(new Point(31,18));
            coords.add(new Point(22,22));
            coords.add(new Point(0,0));
            coords.add(new Point(39,39));
            coords.add(new Point(15,23));
            coords.add(new Point(26,35));
            coords.add(new Point(18,5));

            Mapa mapa = new Mapa(args[0]);
            Point agent_pos = new Point(0, 0);
            Point obj_pos = new Point(7, 7);

            Sensores sensor = new Sensores(mapa, agent_pos);
            Environment env = new Environment(mapa, sensor, agent_pos.x, agent_pos.y);

            int numRenos = coords.size();
            // Lanzar agentes
            AgentController santaClausController = container.createNewAgent("SantaClaus", SantaClausAgent.class.getName(), new Object[]{numRenos});
            AgentController rudolphController = container.createNewAgent("Rudolph", RudolphAgent.class.getName(), new Object[]{coords});
            AgentController buscadorController = container.createNewAgent("Buscador", BuscadorAgent.class.getName(), new Object[]{env});


            santaClausController.start();
            rudolphController.start();
            buscadorController.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
