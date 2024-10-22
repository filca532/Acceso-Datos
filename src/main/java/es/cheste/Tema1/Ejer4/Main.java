package es.cheste.Tema1.Ejer4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;

public class Main implements Serializable {
    private static final long serialVersionUID = 432525844324234L;
    static final Logger LOGGER = LogManager.getRootLogger();

    public static void main(String[] args) {

        String[] moduls = {"Accés a Dades", "Programació de serveis i processos", "Desenvolupament d'interfícies", "Programació Multimédia i dispositiud mòbils", "Sistemes de Gestió Empresarial", "Empresa i iniciativa emprenedora"};
        int[] hores = {6, 3, 6, 5, 5, 3};
        double[] notes = {8.45, 9.0, 8.0, 7.34, 8.2, 7.4};

        ArrayList<Modulo> modulos = new ArrayList<Modulo>();

        for (int i = 0; i < moduls.length; i++) {
            modulos.add(new Modulo(moduls[i], hores[i], notes[i]));
        }

        for (Modulo modulo : modulos) {
            System.out.println(modulo.toString());
        }

        guardarModulos(modulos);

        System.out.println();

        cargarModulos();
    }

    public static void guardarModulos(ArrayList<Modulo> modulos) {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/Tema1/Ejer4/serializado/modulos.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(modulos);
            out.close();
            fileOut.close();
            System.out.println("Fichero serializado se ha guardado en src/Tema1/Ejer4/serializado/modulos.ser");
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static void cargarModulos() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src/Tema1/Ejer4/serializado/modulos.ser"));
            ArrayList<Modulo> modulos = (ArrayList<Modulo>) objectInputStream.readObject();

            for (Modulo modulo : modulos) {
                System.out.println(modulo.toString());
            }

            objectInputStream.close();
        }catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }

    }
}
