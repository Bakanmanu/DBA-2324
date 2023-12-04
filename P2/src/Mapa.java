/**
 * @file
 * @brief Define la clase Mapa que representa una matriz de valores cargada desde un archivo.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @class Mapa
 * @brief Clase que representa una matriz de valores cargada desde un archivo y proporciona métodos para acceder y modificar celdas.
 * Implementa la interfaz Cloneable para permitir la clonación del objeto.
 */
public class Mapa implements Cloneable {
    private ArrayList<ArrayList<Integer>> matriz;

    /**
     * @brief Constructor que carga el mapa desde un archivo.
     * @param archivoMapa Ruta del archivo que contiene el mapa.
     */
    public Mapa(String archivoMapa) {
        cargarMapaDesdeArchivo(archivoMapa);
    }

    /**
     * @brief Método privado para cargar el mapa desde un archivo.
     * @param archivoMapa Ruta del archivo que contiene el mapa.
     */
    private void cargarMapaDesdeArchivo(String archivoMapa) {
        matriz = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoMapa))) {
            // Leer número de filas y columnas desde las dos primeras líneas
            int filas = Integer.parseInt(br.readLine());
            int columnas = Integer.parseInt(br.readLine());

            // Leer el mapa desde el archivo y llenar la matriz
            for (int i = 0; i < filas; i++) {
                String linea = br.readLine();
                String[] valores = linea.split("\t"); // Delimitador de columnas (tabulación)

                ArrayList<Integer> fila = new ArrayList<>();
                for (int j = 0; j < columnas; j++) {
                    fila.add(Integer.parseInt(valores[j]));
                }

                matriz.add(fila);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * @brief Obtiene el valor de la celda en la posición dada.
     * @param fila Índice de la fila.
     * @param columna Índice de la columna.
     * @return Valor de la celda en la posición dada.
     */
    public int getValorCelda(int fila, int columna) {
        if (fila >= 0 && fila < matriz.size() && columna >= 0 && columna < matriz.get(0).size()) {
            return matriz.get(fila).get(columna);
        }
        return -1; // Valor por defecto para celdas fuera del mapa
    }

    /**
     * @brief Establece el valor de la celda en la posición dada.
     * @param fila Índice de la fila.
     * @param columna Índice de la columna.
     * @param valor Nuevo valor para la celda.
     */
    public void setValorCelda(int fila, int columna, int valor) {
        if (fila >= 0 && fila < matriz.size() && columna >= 0 && columna < matriz.get(0).size()) {
            matriz.get(fila).set(columna, valor);
        }
    }

    /**
     * @brief Obtiene el número de filas en el mapa.
     * @return Número de filas.
     */
    public int getFilas() {
        return matriz.size();
    }

    /**
     * @brief Obtiene el número de columnas en el mapa.
     * @return Número de columnas.
     */
    public int getColumnas() {
        if (!matriz.isEmpty()) {
            return matriz.get(0).size();
        }
        return 0;
    }

    /**
     * @brief Imprime el mapa en la consola.
     */
    public void mostrarMapa() {
        int filas = getFilas();
        int columnas = getColumnas();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int valor = getValorCelda(i, j);
                System.out.printf("%4d", valor); // Imprime el valor en un espacio de 4 caracteres
            }
            System.out.println(); // Salto de línea al final de cada fila
        }
    }

    /**
     * @brief Método de clonación que permite duplicar el objeto Mapa.
     * @return Copia del objeto Mapa.
     */
    @Override
    public Mapa clone() {
        try {
            Mapa copia = (Mapa) super.clone();

            // Clonar la matriz
            copia.matriz = new ArrayList<>();
            for (ArrayList<Integer> fila : this.matriz) {
                ArrayList<Integer> nuevaFila = new ArrayList<>(fila);
                copia.matriz.add(nuevaFila);
            }

            return copia;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
