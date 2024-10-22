package es.cheste.Tema1.Ejer7;

import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {
    public SAXHandler() {
        super();
    }

    @Override
    public void startDocument() {
        System.out.println("Comienzo del documento XML");
    }

    @Override
    public void endDocument() {
        System.out.println("Fin del documento XML");
    }

    @Override
    public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes) {
        System.out.println("Inicio del elemento: " + qName);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        System.out.println("Fin del elemento: " + qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        System.out.println("Texto: " + new String(ch, start, length));
    }
}
