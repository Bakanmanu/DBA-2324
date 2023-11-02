import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Mapa implements Cloneable {
    private ArrayList<ArrayList<Integer>> matriz;

    public Mapa(String archivoMapa) {
        cargarMapaDesdeArchivo(archivoMapa);
    }

    Mapa() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

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
            e.printStackTrace();
        }
    }

    public int getValorCelda(int fila, int columna) {
        if (fila >= 0 && fila < matriz.size() && columna >= 0 && columna < matriz.get(0).size()) {
            return matriz.get(fila).get(columna);
        }
        return -1; // Valor por defecto para celdas fuera del mapa
    }

    public void setValorCelda(int fila, int columna, int valor) {
        if (fila >= 0 && fila < matriz.size() && columna >= 0 && columna < matriz.get(0).size()) {
            matriz.get(fila).set(columna, valor);
        }
    }

    public int getFilas() {
        return matriz.size();
    }

    public int getColumnas() {
        if (!matriz.isEmpty()) {
            return matriz.get(0).size();
        }
        return 0;
    }

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
