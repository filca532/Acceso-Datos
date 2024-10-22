package es.cheste.Tema1.Ejer2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MergearFichero {
    private static Logger LOGGER = LogManager.getRootLogger();

    public static void main(String[] args) {
        File directorio = new File("src/Tema1/Ejer2/archivos");

        if (mergearFichero(directorio)) {
            System.out.println("Ficheros mergeados correctamente");
        } else {
            System.out.println("Error al mergear los ficheros");
        }
    }

    public static boolean mergearFichero(File directorio) {
        ArrayList<File> ficheros = new ArrayList<>(List.of(directorio.listFiles()));
        Iterator<File> it = ficheros.iterator();

        borrarFichero();

        while (it.hasNext()) {
            String nombreFichero = it.next().getName();

            try {
                FileReader fr = new FileReader("src/Tema1/Ejer2/archivos/" + nombreFichero);
                FileWriter fw = new FileWriter("src/Tema1/Ejer2/archivo_mergeado/merge.txt", true);

                int caracter;
                while ((caracter = fr.read()) != -1) {
                    fw.write(caracter);
                }

                fw.write("\n");
                fw.write("\n");

                fr.close();
                fw.close();
            } catch (FileNotFoundException e) {
                LOGGER.error("El fichero " + nombreFichero + " no existe");
                return Boolean.FALSE;
            } catch (IOException e) {
                LOGGER.error("Error al leer el fichero " + nombreFichero);
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    public static void borrarFichero() {
        File fichero = new File("src/Tema1/Ejer2/archivo_mergeado/merge.txt");
        if (fichero.exists()) {
            fichero.delete();
        }
    }
}
