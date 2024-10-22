package es.cheste.Tema1.ejercicios_entregables.Ejer6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class Main {
    static final Logger LOGGER = LogManager.getRootLogger();
    public static void main(String[] args) {
        File fileFichero = new File("src/Tema1/ejercicios_entregables/Ejer6/moduls.xml");

        crearFichero(fileFichero);

        escribirFicheroXML(fileFichero);

        System.out.println("\n");

        leerFicheroXML(fileFichero);
    }

    public static boolean crearFichero(File fileFichero) {
        try {
            if (fileFichero.createNewFile()) {
                System.out.println("Fichero creado");
                return Boolean.TRUE;
            } else {
                System.out.println("El fichero ya existe");
                return Boolean.FALSE;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return Boolean.FALSE;
        }
    }

    public static void escribirFicheroXML(File fileFichero) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("modulos");
            document.appendChild(root);

            String[] moduls = {"Accés a Dades", "Programació de serveis i processos", "Desenvolupament d'interfícies", "Programació Multimédia i dispositiud mòbils", "Sistemes de Gestió Empresarial", "Empresa i iniciativa emprenedora"};
            int[] hores = {6, 3, 6, 5, 5, 3};
            double[] notes = {8.45, 9.0, 8.0, 7.34, 8.2, 7.4};

            for (int i = 0; i < moduls.length; i++) {
                Element modulo = document.createElement("modul");
                root.appendChild(modulo);

                Element nom = document.createElement("nom");
                nom.appendChild(document.createTextNode(moduls[i]));
                modulo.appendChild(nom);

                Element horesElement = document.createElement("hores");
                horesElement.appendChild(document.createTextNode(String.valueOf(hores[i])));
                modulo.appendChild(horesElement);

                Element nota = document.createElement("qualificació");
                nota.appendChild(document.createTextNode(String.valueOf(notes[i])));
                modulo.appendChild(nota);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(fileFichero);
            transformer.transform(source, result);

            System.out.println("Fichero escrito");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            System.out.println("Error al escribir el fichero");
        }
    }

    public static void leerFicheroXML(File fileFichero) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fileFichero);

            Element root = document.getDocumentElement();
            System.out.println("Elemento raíz: " + root.getNodeName());

            for (int i = 0; i < root.getChildNodes().getLength(); i++) {
                if (root.getChildNodes().item(i) instanceof Element) {
                    Element modul = (Element) root.getChildNodes().item(i);
                    System.out.println("Módulo: " + modul.getElementsByTagName("nom").item(0).getTextContent());
                    System.out.println("Horas: " + modul.getElementsByTagName("hores").item(0).getTextContent());
                    System.out.println("Nota: " + modul.getElementsByTagName("qualificació").item(0).getTextContent());
                    System.out.println();
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            System.out.println("Error al leer el fichero");
        }
    }
}
