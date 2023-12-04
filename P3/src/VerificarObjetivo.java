/**
 * @file
 * @brief Define la clase de comportamiento para verificar si se ha alcanzado el objetivo.
 */

import jade.core.behaviours.CyclicBehaviour;

/**
 * @class VerificarObjetivo
 * @brief Clase de comportamiento que verifica si el agente ha llegado al objetivo y, en caso afirmativo, termina el agente.
 */
@SuppressWarnings("FieldMayBeFinal")
class VerificarObjetivo extends CyclicBehaviour {
    private Environment env;
    private int numSteps = 0;

    /**
     * @brief Constructor de la clase VerificarObjetivo.
     * @param env Entorno en el que opera el agente.
     */
    public VerificarObjetivo(Environment env) {
        this.env = env;
    }

    /**
     * @brief Función de acción que comprueba si el agente ha alcanzado el objetivo y termina si es así.
     */
    public void action() {
        numSteps++;
        System.out.println("Comprobamos si objetivo encontrado. ");

        // Verifica si la posición actual del agente es igual al objetivo
        if (env.getAgentePos().equals(env.getObjetivo())) {
            System.out.println("ENCONTRADO en el paso: " + numSteps);

            // Termina el agente
            myAgent.doDelete();
        }
    }
}
