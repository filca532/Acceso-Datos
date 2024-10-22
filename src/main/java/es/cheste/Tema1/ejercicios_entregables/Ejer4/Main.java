package es.cheste.Tema1.ejercicios_entregables.Ejer4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static final Logger LOGGER = LogManager.getRootLogger();

    public static void main(String[] args) {
        File fileFichero = new File("src/Tema1/ejercicios_entregables/Ejer4/archivos/ficheroModulos.txt");

        String[] moduls = {"Accés a Dades", "Programació de serveis i processos", "Desenvolupament d'interfícies", "Programació Multimédia i dispositiud mòbils", "Sistemes de Gestió Empresarial", "Empresa i iniciativa emprenedora"};
        int[] hores = {6, 3, 6, 5, 5, 3};
        double[] notes = {8.45, 9.0, 8.0, 7.34, 8.2, 7.4};

        ArrayList<Modulo> modulos = new ArrayList<>();

        for (int i = 0; i < moduls.length; i++) {
            modulos.add(new Modulo(moduls[i], hores[i], notes[i]));
        }

        escribirFichero(fileFichero, modulos);

        leerFicheroOrdenado(fileFichero);
    }

    public static void escribirFichero(File fileFichero, ArrayList<Modulo> modulos) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileFichero));

            for (Modulo modulo : modulos) {
                bw.write(modulo.getNombre() + ", " + modulo.getHoras() + ", " + modulo.getNota() + "\n");
            }

            bw.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static void leerFicheroOrdenado(File fileFichero) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileFichero));
            String linea;
            ArrayList<Modulo> modulos = new ArrayList<>();

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(", ");
                modulos.add(new Modulo(datos[0], Integer.parseInt(datos[1]), Double.parseDouble(datos[2])));
            }

            Collections.sort(modulos);

            for (Modulo modulo : modulos) {
                System.out.println(modulo.getNombre() + ", " + modulo.getHoras() + ", " + modulo.getNota());
            }

            br.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

}
