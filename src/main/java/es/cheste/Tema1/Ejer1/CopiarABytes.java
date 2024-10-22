package es.cheste.Tema1.Ejer1;

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
        byte[] bloqueBytes = new byte[32];

        try {
            FileInputStream fis = new FileInputStream(fileFichero);
            FileOutputStream fos = new FileOutputStream("src/Tema1/Ejer1/copia/Copia.txt");

            int byteLeidoFichero;
            while ((byteLeidoFichero = fis.read(bloqueBytes)) != -1) {
                fos.write(bloqueBytes, 0, byteLeidoFichero);
            }

            fis.close();
            fos.close();


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
