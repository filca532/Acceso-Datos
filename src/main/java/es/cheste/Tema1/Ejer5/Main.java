package es.cheste.Tema1.Ejer5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class Main {
    static final Logger LOGGER = LogManager.getRootLogger();

    public static void main(String[] args) {
        final File FICHERO1 = new File("src/Tema1/Ejer5/fichero/propiedades.cnf");
        final File FICHERO2 = new File("src/Tema1/Ejer5/fichero/propiedades2.cnf");

        mostrarPropiedades(FICHERO1);
        cargarPropiedades(FICHERO2);
    }

    public static boolean mostrarPropiedades(File FICHERO) {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(FICHERO));

            System.out.println("Todas las propiedades del fichero:" + properties);
            System.out.println("\n");

            properties.list(System.out);

            System.out.println("\n");

            Set<Object> keys = properties.keySet();
            System.out.println("Mis estilo:");
            for (Object key : keys) {
                System.out.println(key + " = " + properties.getProperty((String) key));
            }

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    public static boolean cargarPropiedades(File FICHERO) {
        Properties properties = new Properties();

        try {
            properties.put("nombre", "Juan");
            properties.put("edad", "25");
            properties.put("ciudad", "Madrid");
            properties.put("pais", "España");
            properties.put("idioma", "Español");

            properties.store(new FileOutputStream(FICHERO, true), "Propiedades de Juan");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }
}
