import jade.core.Agent;

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
            System.out.println("Argumentos incorrectos.");
            doDelete(); // Eliminar el agente si los argumentos no son válidos
        }
        CicloComportamiento ciclo = new CicloComportamiento(env);
        addBehaviour(ciclo);
    }
}