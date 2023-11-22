import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;


public class ComportamientoAgente extends Agent {
    private Environment env;

    public ComportamientoAgente(){
        System.out.println("Inicializando agente");
    }

    @Override
    public void setup() {
        // Inicialización con argumentos personalizados
        Object[] args = getArguments();
        if (args != null && args.length == 1) {

            env = (Environment) args[0];
            System.out.println("Argumentos correctos.");

        } else {
            // Manejo de error si los argumentos no son válidos
            System.out.println("Argumentos incorrectos.");
            doDelete(); // Eliminar el agente si los argumentos no son válidos
        }


//        CicloComportamiento ciclo = new CicloComportamiento(env);
//        addBehaviour(ciclo);

        addBehaviour(new MostrarMapa(env));
        addBehaviour(new GetInformation(env));
//        VerificarObjetivo verificarObjetivo = new VerificarObjetivo(env);
        addBehaviour(new VerificarObjetivo(env));
        addBehaviour(new MovimientoComplejo(env,1));

//        SequentialBehaviour secuencia = new SequentialBehaviour();
//        secuencia.addSubBehaviour(new MostrarMapa(env));
    }
}