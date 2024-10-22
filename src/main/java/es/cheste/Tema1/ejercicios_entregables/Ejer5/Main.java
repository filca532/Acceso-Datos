package es.cheste.Tema1.ejercicios_entregables.Ejer5;

import es.cheste.Tema1.ejercicios_entregables.Ejer4.Modulo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    static final Logger LOGGER = LogManager.getRootLogger();

    public static void main(String[] args) {
        Path filePath = Paths.get("src/Tema1/ejercicios_entregables/Ejer5/archivos.csv/");

        String[] moduls = {"Accés a Dades", "Programació de serveis i processos", "Desenvolupament d'interfícies", "Programació Multimédia i dispositiud mòbils", "Sistemes de Gestió Empresarial", "Empresa i iniciativa emprenedora"};
        int[] hores = {6, 3, 6, 5, 5, 3};
        double[] notes = {8.45, 9.0, 8.0, 7.34, 8.2, 7.4};

        ArrayList<es.cheste.Tema1.ejercicios_entregables.Ejer4.Modulo> modulos = new ArrayList<>();

        for (int i = 0; i < moduls.length; i++) {
            modulos.add(new es.cheste.Tema1.ejercicios_entregables.Ejer4.Modulo(moduls[i], hores[i], notes[i]));
        }

        crearFichero(filePath);
        System.out.println("\n");

        if (escribirFicheroCSV(filePath, modulos)) {
            System.out.println("Fichero escrito");
        } else {
            System.out.println("Error al escribir el fichero");
        }

        System.out.println("\n");

        if (leerFicheroCSV(filePath)) {
            System.out.println("Fichero leído");
        } else {
            System.out.println("Error al leer el fichero");
        }
    }

    public static boolean crearFichero(Path filePath) {
        try {
            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
                System.out.println("Fichero creado");
                return Boolean.TRUE;
            } else {
                System.out.println("El fichero ya existe");
                return Boolean.FALSE;
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            return Boolean.FALSE;
        }
    }

    public static boolean escribirFicheroCSV(Path filePath, ArrayList<es.cheste.Tema1.ejercicios_entregables.Ejer4.Modulo> modulos) {
        try {
            BufferedWriter bw = Files.newBufferedWriter(filePath);

            bw.write("Nombre; Horas; Nota\n");

            for (es.cheste.Tema1.ejercicios_entregables.Ejer4.Modulo modulo : modulos) {
                bw.write(modulo.getNombre() + "; " + modulo.getHoras() + "; " + modulo.getNota() + "\n");
            }

            bw.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    public static boolean leerFicheroCSV(Path filePath) {
        try {
            BufferedReader br = Files.newBufferedReader(filePath);
            String linea;
            ArrayList<es.cheste.Tema1.ejercicios_entregables.Ejer4.Modulo> modulos = new ArrayList<>();

            br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("; ");
                modulos.add(new es.cheste.Tema1.ejercicios_entregables.Ejer4.Modulo(datos[0], Integer.parseInt(datos[1]), Double.parseDouble(datos[2])));
            }

            for (Modulo modulo : modulos) {
                System.out.println(modulo.getNombre() + ", " + modulo.getHoras() + ", " + modulo.getNota());
            }

            br.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

}
