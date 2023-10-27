import jade.core.Agent;
//import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import java.awt.Point;

public class ComportamientoAgente extends Agent {
    private Mapa mapa;
    private Sensores sensores;


    public ComportamientoAgente(){
        System.out.println("Inicializando agente");
    }

    @Override
    public void setup() {
        // Inicialización con argumentos personalizados
        Object[] args = getArguments();
        if (args != null && args.length == 2) {
            mapa = (Mapa) args[0];
            sensores = (Sensores) args[1];
            System.out.println("Argumentos correctos.");

        } else {
            // Manejo de error si los argumentos no son válidos
            System.out.println("Argumentos incorrectos.");
            doDelete(); // Eliminar el agente si los argumentos no son válidos
        }

        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                mapa.mostrarMapa();
                sensores.setAgent(new Point(0,0));
                sensores.setObjetivo(new Point(2,2));
                System.out.println();
                sensores.getMapa().mostrarMapa();
                doDelete();
            }
        });

    }
}

