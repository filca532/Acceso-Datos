package es.cheste.Tema1.Ejer3;

import org.apache.logging.log4j.Logger;

import java.io.*;

public class CopiarABytes {
    private static Logger LOGGER = org.apache.logging.log4j.LogManager.getRootLogger();
    public static void main(String[] args) {
        File fileFichero = new File("src/Tema1/Ejer1/archivos/fichero.txt");

        if (escribirFichero(fileFichero)) {
            LOGGER.info("Fichero copiado correctamente");
        } else {
            LOGGER.error("Error al copiar el fichero");
        }
    }

    public static boolean escribirFichero(File fileFichero) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileFichero));
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/Tema1/Ejer3/copia/ficheroCopia.txt"));

            String linea;
            int numLinea = 1;

            while ((linea = br.readLine()) != null) {
                bw.write(numLinea + ": " + linea + "\n");
                numLinea++;
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
