import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class VerificarObjetivo extends CyclicBehaviour {
    private Sensores sensores;
    private Environment env;
    private int numSteps=0;

    public VerificarObjetivo(Environment env) {
        this.env = env;
//        this.numSteps = numSteps;
    }

    public void action() {
        numSteps++;
        System.out.println("Comprobamos si objetivo encontrado. ");
        if (env.getAgentePos().equals(env.getObjetivo())) {
            String str = "ENCONTRADO en el paso: " + numSteps;
                System.out.println(str);
            myAgent.doDelete();
        }
    }
}