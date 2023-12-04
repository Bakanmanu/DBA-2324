/**
 * @file
 * @brief Define la clase de comportamiento cíclico para mostrar el mapa y la memoria del agente.
 */

import jade.core.behaviours.CyclicBehaviour;

/**
 * @class MostrarMapa
 * @brief Clase de comportamiento cíclico que muestra el mapa y la memoria del agente en la consola.
 */
class MostrarMapa extends CyclicBehaviour {
    private final Environment env;

    /**
     * @brief Constructor de la clase MostrarMapa.
     * @param env Entorno en el que opera el agente.
     */
    public MostrarMapa(Environment env) {
        this.env = env;
    }

    /**
     * @brief Método de acción que imprime en la consola el mapa y la memoria del agente.
     */
    public void action() {
        System.out.println("Mostrando mapa:");
        env.getMapa().mostrarMapa();
        System.out.println("---------------------------------------------");
        env.getMemoria().mostrarMapa();
        System.out.println("---------------------------------------------");
    }
}
