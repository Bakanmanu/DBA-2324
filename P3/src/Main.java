import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import java.awt.*;
import java.util.Arrays;

import static java.lang.Thread.sleep;

/**
* Función main que ejecuta todos los mapas inicializando el container
* */
public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(Arrays.toString(args));

            Runtime rt = Runtime.instance();
            Profile profile = new ProfileImpl();
            profile.setParameter(Profile.MAIN_HOST, "localhost");
            profile.setParameter(Profile.CONTAINER_NAME, "dba_server");
            ContainerController container = rt.createMainContainer(profile);

            run(container);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * Funcion para ejecutar todos los mapas
     *
     * */
    public static void run(ContainerController container) {
        try {

            String[] mapFiles = {
//                    "maps/mapaJodio.txt",
//                    "maps/mapWithComplexObstacle1.txt",
//                    "maps/mapWithComplexObstacle2.txt",
//                    "maps/mapWithComplexObstacle3.txt",
//                    "maps/mapWithDiagonalWall.txt"
//                    "maps/mapWithHorizontalWall.txt",
//                    "maps/mapWithoutObstacleP3.txt",
//                    "maps/mapWithVerticalWall.txt"
                    "maps/mapComplex.txt"
            };

            for (String mapFile : mapFiles) {
                Mapa mapa = new Mapa(mapFile);
                Point agent_pos = new Point(29, 15);
                Point obj_pos = new Point(15, 15);

                Sensores sensor = new Sensores(mapa, agent_pos);
                Environment env = new Environment(mapa, sensor, agent_pos.x, agent_pos.y);
                AgentController agent = container.createNewAgent("ComportamientoAgente", ComportamientoAgente.class.getName(), new Object[]{env});
                agent.start();

                sleep(2000);
            }

        } catch (Error | StaleProxyException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
