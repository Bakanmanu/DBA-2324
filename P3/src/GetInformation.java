/**
 * @file
 * @brief Define la clase de comportamiento para obtener información sobre el entorno.
 */

import jade.core.behaviours.CyclicBehaviour;

/**
 * @class GetInformation
 * @brief Clase de comportamiento cíclico que obtiene información sobre la posición del agente, el objetivo y las direcciones disponibles.
 */
class GetInformation extends CyclicBehaviour {
    private final Environment env;

    /**
     * @brief Constructor de la clase GetInformation.
     * @param env Entorno en el que opera el agente.
     */
    public GetInformation(Environment env){
        this.env = env;
    }

    /**
     * @brief Método de acción que imprime información sobre la posición del agente, el objetivo y las direcciones disponibles.
     */
    @Override
    public void action() {
        System.out.println("Posicion del agente:" + env.getAgentePos());
        System.out.println("Posicion del objetivo:" + env.getObjetivo());

        // Actualiza las posiciones disponibles en el entorno
        env.setPosiciones(env.determinarDireccion());
        System.out.println("El objetivo se encuentra al: " + env.getPosiciones());

        // Imprime las direcciones alrededor del agente
        System.out.println("Get around: " + env.getSensores().getAround(env.getPosiciones()));
    }
}
