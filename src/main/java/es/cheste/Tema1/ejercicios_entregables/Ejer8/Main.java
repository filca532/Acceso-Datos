package es.cheste.Tema1.ejercicios_entregables.Ejer8;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;

public class Main {
    static final Logger LOGGER = LogManager.getRootLogger();

    public static void main(String[] args) {
        File file = new File("src/main/java/Tema1/ejercicios_entregables/Ejer8/SW.json");

        if (leerJSON(file)) {
            System.out.println("Fichero JSON leído correctamente y convertido a XML.");
        } else {
            System.out.println("Error al leer el fichero JSON.");
        }
    }

    public static boolean leerJSON(File file) {
        Gson gson = new Gson();

        try {
            FileReader reader = new FileReader(file);
            PersonajeWrapper personajesWrapper = gson.fromJson(reader, PersonajeWrapper.class);

            Collections.sort(personajesWrapper.getPersonajes());

            escribirFicheroXML(personajesWrapper);

        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
            return Boolean.FALSE;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    public static void escribirFicheroXML(PersonajeWrapper personajesWrapper) {
        try {
            File fileFichero = new File("src/main/java/Tema1/ejercicios_entregables/Ejer8/personajes.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("characters");
            document.appendChild(root);

            for (Personaje personaje : personajesWrapper.getPersonajes()) {
                Element personajeElement = document.createElement("character");
                personajeElement.setAttribute("films", String.valueOf(personaje.getPeliculas().size()));
                personajeElement.setAttribute("vehicles", String.valueOf(personaje.getVehiculos().size()));
                root.appendChild(personajeElement);

                Element nombre = document.createElement("name");
                nombre.appendChild(document.createTextNode(personaje.getNombre()));
                personajeElement.appendChild(nombre);

                Element peso = document.createElement("mass");
                peso.appendChild(document.createTextNode(personaje.getPeso()));
                personajeElement.appendChild(peso);

                Element url = document.createElement("url");
                url.appendChild(document.createTextNode(personaje.getUrl()));
                personajeElement.appendChild(url);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(fileFichero);
            transformer.transform(source, result);
        } catch (ParserConfigurationException e) {
            LOGGER.error(e.getMessage());
        } catch (TransformerConfigurationException e) {
            LOGGER.error(e.getMessage());
        } catch (TransformerException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
