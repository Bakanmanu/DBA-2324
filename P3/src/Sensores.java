/**
 * @file
 * @brief Define la clase que representa los sensores del agente.
 */

import java.awt.Point;
import java.util.ArrayList;

/**
 * @class Sensores
 * @brief Clase que modela los sensores del agente, permitiendo la observación del entorno.
 */
public class Sensores {
    private ArrayList<Integer> vision;

    /**
     * @brief Constructor de la clase Sensores.
     * @param memoria Información de la memoria del agente.
     * @param agent_pos Posición actual del agente.
     */
    public Sensores(Mapa memoria, Point agent_pos) {
        see(agent_pos, memoria);
    }

    /**
     * @brief Obtiene la visión actual del agente.
     * @return Lista de valores que representan la visión del agente.
     */
    public ArrayList<Integer> getVision(){
        return vision;
    }

    /**
     * @brief Establece la visión actual del agente.
     * @param v Lista de valores que representan la nueva visión del agente.
     */
    public void setVision(ArrayList<Integer> v){
        vision = v;
    }

    /**
     * @brief Obtiene las posiciones alrededor del agente en función de su posición actual y la información de memoria.
     * @param agent_pos Posición actual del agente.
     * @param memoria Información de la memoria.
     * @return Lista de posiciones alrededor del agente.
     */
    public ArrayList<Integer> see(Point agent_pos, Mapa memoria) {
        int fila = agent_pos.x;
        int columna = agent_pos.y;

        ArrayList<Integer> submatriz = new ArrayList<>();
        int filas = memoria.getFilas();
        int columnas = memoria.getColumnas();

        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {
                if (i >= 0 && i < filas && j >= 0 && j < columnas) {
                    submatriz.add(memoria.getValorCelda(i, j));
                } else {
                    submatriz.add(null);
                }
            }
        }
        vision = submatriz;
        return submatriz;
    }

    /**
     * @brief Obtiene las direcciones alrededor de una lista de posiciones.
     *
     * Esta función toma una lista de posiciones y devuelve una lista de direcciones
     * asociadas a esas posiciones. Utiliza la función getSimpleAround para obtener la
     * dirección para cada posición individual.
     *
     * @param p Lista de posiciones para las cuales se deben obtener las direcciones.
     * @return Lista de direcciones asociadas a las posiciones.
     */
    public ArrayList<Integer> getAround(ArrayList<POSICIONES> p) {
        ArrayList<Integer> direcciones = new ArrayList<>();
        for(POSICIONES pos : p){
            direcciones.add(getSimpleAround(pos));
        }
        return direcciones;
    }

    /**
     * @brief Obtiene la dirección simple alrededor de una posición.
     *
     * Esta función toma una posición y devuelve la dirección asociada a esa posición.
     * Utiliza una expresión switch para determinar la dirección basada en la posición dada.
     *
     * @param p Posición para la cual se debe obtener la dirección.
     * @return Dirección asociada a la posición.
     */
    public Integer getSimpleAround(POSICIONES p) {
        return switch (p) {
            case ARRIBA -> this.vision.get(1);
            case ABAJO -> this.vision.get(7);
            case DERECHA -> this.vision.get(5);
            case IZQUIERDA -> this.vision.get(3);
        };
    }
}
