package es.cheste.Tema1.Ejer7;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.IOException;

public class Main {
    static final Logger LOGGER = LogManager.getRootLogger();
    public static void main(String[] args) {
        File file = new File("src/main/java/Tema1/Ejer7/modulos.xml");
        leerXMLSAX(file);
    }

    public static boolean leerXMLSAX(File file) {
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            SAXHandler handler = new SAXHandler();
            reader.setContentHandler(handler);
            reader.parse(file.getAbsolutePath());

        } catch (SAXException e) {
            LOGGER.error("Error al crear el lector XML", e);
            return Boolean.FALSE;
        } catch (IOException e) {
            LOGGER.error("Error al leer el archivo XML", e);
        }
        return Boolean.TRUE;
    }
}
