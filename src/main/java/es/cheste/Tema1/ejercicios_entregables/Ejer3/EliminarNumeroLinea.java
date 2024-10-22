package es.cheste.Tema1.ejercicios_entregables.Ejer3;

import org.apache.logging.log4j.Logger;

import java.io.*;

public class EliminarNumeroLinea {
    private static Logger LOGGER = org.apache.logging.log4j.LogManager.getRootLogger();
    public static void main(String[] args) {
        File fileFichero = new File("src/Tema1/ejercicios_entregables/Ejer3/archivos/ficheroNumeros.txt");

        if (escribirFichero(fileFichero)) {
            LOGGER.info("Fichero copiado correctamente");
        } else {
            LOGGER.error("Error al copiar el fichero");
        }
    }

    public static boolean escribirFichero(File fileFichero) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileFichero));
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/Tema1/ejercicios_entregables/Ejer3/copia/ficheroCopia.txt"));

            String linea;
            final String REGEX = "^(\\d+):\\s";

            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split(REGEX);

                for (int i = 1; i < palabras.length; i++) {
                    bw.write(palabras[i] + " ");
                }

                bw.write("\n");
            }

            br.close();
            bw.close();
        } catch (FileNotFoundException e) {
            LOGGER.error("ERROR " + "\n" + "El fichero no ha sido encontrado " + e.getMessage());
            return Boolean.FALSE;
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }
}
