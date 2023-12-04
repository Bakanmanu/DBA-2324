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
            coords.add(new Point(0,0));
            coords.add(new Point(1,1));
            coords.add(new Point(2,2));


            // Lanzar agentes
            AgentController santaClausController = container.createNewAgent("SantaClaus", SantaClausAgent.class.getName(), null);
            santaClausController.start();

            AgentController rudolphController = container.createNewAgent("Rudolph", RudolphAgent.class.getName(), new Object[]{coords});
            rudolphController.start();

            AgentController buscadorController = container.createNewAgent("Buscador", BuscadorAgent.class.getName(), null);
            buscadorController.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
