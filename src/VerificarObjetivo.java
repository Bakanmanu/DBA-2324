import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class VerificarObjetivo extends OneShotBehaviour {
    private Sensores sensores;
    private Environment env;
    private int numSteps;

    public VerificarObjetivo(Environment env, int numSteps) {
        this.env = env;
        this.numSteps = numSteps;
    }

    public void action() {
        if (env.getAgentePos().equals(env.getObjetivo())) {
            String str = "ENCONTRADO en el paso: " + numSteps;
//            String rutaArchivo = "out.txt";
//            try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
//                // Escribe el texto en el archivo
//                writer.write(str);
//                writer.close();
//            } catch (IOException e) {
//
//            }
                System.out.println(str);
            myAgent.doDelete();
        }
    }
}