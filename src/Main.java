import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import java.awt.*;
import java.beans.Encoder;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        try {
//            System.out.println(Arrays.toString(args));
//            Mapa mapa = new Mapa(args[0]);
//            Point agent_pos = new Point(0, 0);
//            Point obj_pos = new Point(7, 7);
//
//            Sensores sensor = new Sensores(mapa, agent_pos);
//            Environment env = new Environment(mapa, sensor, agent_pos.x, agent_pos.y, obj_pos.x, obj_pos.y);
//            Runtime rt = Runtime.instance();
//            Profile profile = new ProfileImpl();
//            profile.setParameter(Profile.MAIN_HOST, "localhost");
//            profile.setParameter(Profile.CONTAINER_NAME, "dba_server");
//            ContainerController container = rt.createMainContainer(profile);
//            AgentController agent = container.createNewAgent("ComportamientoAgente", ComportamientoAgente.class.getName(), new Object[]{env});
//            agent.start();
            run();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void run() {
        try {
            String[] mapFiles = {
                    "maps/mapa.txt",
                    "maps/mapaJodio.txt",
                    "maps/mapWithComplexObstacle1.txt",
                    "maps/mapWithComplexObstacle2.txt",
                    "maps/mapWithComplexObstacle3.txt",
                    "maps/mapWithDiagonalWall.txt",
                    "maps/mapWithHorizontalWall.txt",
                    "maps/mapWithoutObstacle.txt",
                    "maps/mapWithVerticalWall.txt"
            };

            Runtime rt = Runtime.instance();
            Profile profile = new ProfileImpl();
            profile.setParameter(Profile.MAIN_HOST, "localhost");
            profile.setParameter(Profile.CONTAINER_NAME, "dba_server");
            ContainerController container = rt.createMainContainer(profile);

            for (String mapFile : mapFiles) {
                runAgent( mapFile);
            }


        } catch (Error e) {
            throw new RuntimeException(e);
        }

    }

    private static void runAgent(String mapFile) {
        try {
            Runtime rt = Runtime.instance();
            Profile profile = new ProfileImpl();
            profile.setParameter(Profile.MAIN_HOST, "localhost");
            profile.setParameter(Profile.CONTAINER_NAME, "dba_server");
            ContainerController container = rt.createMainContainer(profile);

            Mapa mapa = new Mapa(mapFile);
            Point agent_pos = new Point(0, 0);
            Point obj_pos = new Point(7, 7);

            Sensores sensor = new Sensores(mapa, agent_pos);
            Environment env = new Environment(mapa, sensor, agent_pos.x, agent_pos.y, obj_pos.x, obj_pos.y);

            AgentController agent = container.createNewAgent("ComportamientoAgente", ComportamientoAgente.class.getName(), new Object[]{env});
            agent.start();

            // Espera hasta que el agente termine (puedes agregar lógica de espera más robusta)
            while (!agent.getState().equals("Deleted")) {
                Thread.sleep(100);
            }

            // Detén el contenedor antes de reiniciar para limpiar el entorno
            container.kill();

        } catch (StaleProxyException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
