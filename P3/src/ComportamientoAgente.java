/**
 * @file
 * @brief Define la clase del agente con su configuración y comportamientos asociados.
 */

import jade.core.Agent;

/**
 * @class ComportamientoAgente
 * @brief Clase que define un agente con comportamientos específicos.
 */
public class ComportamientoAgente extends Agent {
    private Environment env;

    /**
     * @brief Constructor de la clase ComportamientoAgente.
     * Inicializa el agente e imprime un mensaje de inicialización.
     */
    public ComportamientoAgente() {
        System.out.println("Inicializando agente");
    }

    /**
     * @brief Método de configuración del agente.
     * Inicializa el entorno con argumentos personalizados y agrega comportamientos al agente.
     */
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

        // Insertamos los comportamientos al agente
        addBehaviour(new MostrarMapa(env));
        addBehaviour(new GetInformation(env));
        addBehaviour(new VerificarObjetivo(env));
        addBehaviour(new Movimiento(env));
    }
}
