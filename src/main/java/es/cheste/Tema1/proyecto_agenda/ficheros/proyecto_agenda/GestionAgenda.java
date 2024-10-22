package es.cheste.Tema1.proyecto_agenda.ficheros.proyecto_agenda;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class GestionAgenda {
    static final Logger LOGGER = LogManager.getRootLogger();
    Path filePathCSV = Paths.get("src/main/java/Tema1/proyecto_agenda/ficheros/contactos.csv");
    Path filePathXML = Paths.get("src/main/java/Tema1/proyecto_agenda/ficheros/contactos.xml");
    Path filePathJSON = Paths.get("src/main/java/Tema1/proyecto_agenda/ficheros/contactos.json");

    private static ArrayList<Contacto> contactos = new ArrayList<Contacto>();

    private Locale locale = new Locale("es", "ES");
    private ResourceBundle bundle = ResourceBundle.getBundle("idiomasAgenda", locale);

    Scanner scanner = new Scanner(System.in);

    public GestionAgenda() {
        super();
    }

    public GestionAgenda(ArrayList<Contacto> contactos) {
        this.contactos = contactos;
    }

    public void menu() {
        escogerIdioma();

        int opcion;
        do {
            System.out.println();
            System.out.println("1.- " + bundle.getString("crear"));
            System.out.println("2.- " + bundle.getString("modificar"));
            System.out.println("3.- " + bundle.getString("eliminar"));
            System.out.println("4.- " + bundle.getString("buscar"));
            System.out.println("5.- " + bundle.getString("listar"));
            System.out.println("6.- " + bundle.getString("salir"));
            System.out.println();
            System.out.print(bundle.getString("escoger") + ": ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    agregarContacto();
                    break;
                case 2:
                    modificarContacto();
                    break;
                case 3:
                    eliminarContacto();
                    break;
                case 4:
                    buscarContacto();
                    break;
                case 5:
                    listarContactos();
                    break;
                case 6:
                    break;
                default:
                    System.out.println(bundle.getString("opcionNoValida"));
                    break;
            }
        } while (opcion != 6);

    }

    private void escogerIdioma() {
        int opcion;

        System.out.println("Seleccione el idioma:");
        System.out.println("1. Español");
        System.out.println("2. Inglés");
        System.out.println("3. Valencià");
        System.out.print("Opción: ");

        opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                locale = new Locale("es", "ES");
                break;
            case 2:
                locale = new Locale("en", "US");
                break;
            case 3:
                locale = new Locale("ca", "ES");
                break;
            default:
                System.out.println("Opción no válida. Se seleccionará Español por defecto.");
                locale = new Locale("es", "ES");
                break;
        }

        try {
            bundle = ResourceBundle.getBundle("idiomasAgenda", locale);
        } catch (java.util.MissingResourceException e) {
            System.err.println("Resource bundle not found for locale: " + locale);
        }
    }

    private void agregarContacto() {
        scanner.nextLine();

        System.out.print(bundle.getString("nombre") + ": ");
        String nombre = scanner.nextLine();

        System.out.print(bundle.getString("apellidos") + ": ");
        String apellidos = scanner.nextLine();

        System.out.print(bundle.getString("email") + ": ");
        String email = scanner.nextLine();

        System.out.print(bundle.getString("telefono") + "1: ");
        String telefono1 = scanner.nextLine();

        System.out.print(bundle.getString("telefono") + "2: ");
        String telefono2 = scanner.nextLine();

        System.out.print(bundle.getString("direccion") + ": ");
        String direccion = scanner.nextLine();

        Contacto contacto = new Contacto(nombre, apellidos, email, telefono1, telefono2, direccion);

        guardarEnCSV(contacto);
        guardarEnXML(contacto);
        guardarEnJSON(contacto);
    }

    public void buscarContacto() {
        cargarContactos();

        System.out.println(bundle.getString("mensajeBuscar"));
        System.out.println("1.- " + bundle.getString("nombre"));
        System.out.println("2.- " + bundle.getString("apellidos"));
        System.out.println("3.- " + bundle.getString("email"));
        System.out.println("4.- " + bundle.getString("telefono"));
        System.out.println("6.- " + bundle.getString("direccion"));
        System.out.println();
        System.out.print(bundle.getString("escoger") + ": ");

        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                buscarPorNombre();
                break;
            case 2:
                buscarPorApellidos();
                break;
            case 3:
                buscarPorEmail();
                break;
            case 4:
                buscarPorTelefono();
                break;
            case 6:
                buscarPorDireccion();
                break;
            default:
                System.out.println(bundle.getString("opcionNoValida"));
                break;
        }
    }

    private void buscarPorNombre() {
        scanner.nextLine();
        System.out.print(bundle.getString("nombre") + ": ");
        String nombre = scanner.nextLine();

        for (Contacto contacto : contactos) {
            if (contacto.getNombre().equals(nombre)) {
                System.out.println(bundle.getString("nombre") + " - " + contacto.getNombre());
                System.out.println(bundle.getString("apellidos") + " - "  + contacto.getApellidos());
                System.out.println(bundle.getString("email") + " - "  + contacto.getEmail());
                System.out.println(bundle.getString("telefono") + "1 - "  + contacto.getTelefono1());
                System.out.println(bundle.getString("telefono") + "2 - "  + contacto.getTelefono2());
                System.out.println(bundle.getString("direccion") + " - "  + contacto.getDireccion());
                System.out.println();
            }
        }
    }

    private void buscarPorApellidos() {
        scanner.nextLine();
        System.out.print(bundle.getString("apellidos") + ": ");
        String apellidos = scanner.nextLine();

        for (Contacto contacto : contactos) {
            if (contacto.getApellidos().equals(apellidos)) {
                System.out.println(bundle.getString("nombre") + " - " + contacto.getNombre());
                System.out.println(bundle.getString("apellidos") + " - "  + contacto.getApellidos());
                System.out.println(bundle.getString("email") + " - "  + contacto.getEmail());
                System.out.println(bundle.getString("telefono") + "1 - "  + contacto.getTelefono1());
                System.out.println(bundle.getString("telefono") + "2 - "  + contacto.getTelefono2());
                System.out.println(bundle.getString("direccion") + " - "  + contacto.getDireccion());
                System.out.println();
            }
        }
    }

    private void buscarPorEmail() {
        scanner.nextLine();
        System.out.print(bundle.getString("email") + ": ");
        String email = scanner.nextLine();

        for (Contacto contacto : contactos) {
            if (contacto.getEmail().equals(email)) {
                System.out.println(bundle.getString("nombre") + " - " + contacto.getNombre());
                System.out.println(bundle.getString("apellidos") + " - "  + contacto.getApellidos());
                System.out.println(bundle.getString("email") + " - "  + contacto.getEmail());
                System.out.println(bundle.getString("telefono") + "1 - "  + contacto.getTelefono1());
                System.out.println(bundle.getString("telefono") + "2 - "  + contacto.getTelefono2());
                System.out.println(bundle.getString("direccion") + " - "  + contacto.getDireccion());
                System.out.println();
            }
        }
    }

    private void buscarPorTelefono() {
        scanner.nextLine();
        System.out.print(bundle.getString("telefono") + ": ");
        String telefono = scanner.nextLine();

        for (Contacto contacto : contactos) {
            if (contacto.getTelefono1().equals(telefono) || contacto.getTelefono2().equals(telefono)) {
                System.out.println(bundle.getString("nombre") + " - " + contacto.getNombre());
                System.out.println(bundle.getString("apellidos") + " - "  + contacto.getApellidos());
                System.out.println(bundle.getString("email") + " - "  + contacto.getEmail());
                System.out.println(bundle.getString("telefono") + "1 - "  + contacto.getTelefono1());
                System.out.println(bundle.getString("telefono") + "2 - "  + contacto.getTelefono2());
                System.out.println(bundle.getString("direccion") + " - "  + contacto.getDireccion());
                System.out.println();
            }
        }
    }

    private void buscarPorDireccion() {
        scanner.nextLine();
        System.out.print(bundle.getString("direccion") + ": ");
        String direccion = scanner.nextLine();

        for (Contacto contacto : contactos) {
            if (contacto.getDireccion().equals(direccion)) {
                System.out.println(bundle.getString("nombre") + " - " + contacto.getNombre());
                System.out.println(bundle.getString("apellidos") + " - "  + contacto.getApellidos());
                System.out.println(bundle.getString("email") + " - "  + contacto.getEmail());
                System.out.println(bundle.getString("telefono") + "1 - "  + contacto.getTelefono1());
                System.out.println(bundle.getString("telefono") + "2 - "  + contacto.getTelefono2());
                System.out.println(bundle.getString("direccion") + " - "  + contacto.getDireccion());
                System.out.println();
            }
        }
    }

    public void modificarContacto() {
        cargarContactos();

        System.out.println(bundle.getString("mensajeModificar"));
        scanner.nextLine();

        System.out.print(bundle.getString("nombre") + ": ");
        String nombre = scanner.nextLine();

        System.out.print(bundle.getString("apellidos") + ": ");
        String apellidos = scanner.nextLine();

        System.out.print(bundle.getString("email") + ": ");
        String email = scanner.nextLine();

        System.out.print(bundle.getString("telefono") + "1: ");
        String telefono1 = scanner.nextLine();

        System.out.print(bundle.getString("telefono") + "2: ");
        String telefono2 = scanner.nextLine();

        System.out.print(bundle.getString("direccion") + ": ");
        String direccion = scanner.nextLine();

        for (Contacto contacto : contactos) {
            if (contacto.getNombre().equals(nombre) && contacto.getApellidos().equals(apellidos) && contacto.getEmail().equals(email) && contacto.getTelefono1().equals(telefono1) && contacto.getTelefono2().equals(telefono2) && contacto.getDireccion().equals(direccion)) {
                System.out.println(bundle.getString("mensajeIntroducirNuevaInfo"));
                scanner.nextLine();

                System.out.print(bundle.getString("nombre") + ": ");
                contacto.setNombre(scanner.nextLine());

                System.out.print(bundle.getString("apellidos") + ": ");
                contacto.setApellidos(scanner.nextLine());

                System.out.print(bundle.getString("email") + ": ");
                contacto.setEmail(scanner.nextLine());

                System.out.print(bundle.getString("telefono") + "1: ");
                contacto.setTelefono1(scanner.nextLine());

                System.out.print(bundle.getString("telefono") + "2: ");
                contacto.setTelefono2(scanner.nextLine());

                System.out.print(bundle.getString("direccion") + ": ");
                contacto.setDireccion(scanner.nextLine());
            }
        }

        modificarEnCSV(contactos);
        modificarEnXML(contactos);
        modificarEnJSON(contactos);
    }

    private void modificarEnCSV(ArrayList<Contacto> contactos) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePathCSV.toString()));
            bw.write("Nombre; Apellidos; Telefono1; Telefono2; Email; Direccion");

            for (Contacto contacto : contactos) {
                bw.write("\n");
                bw.write(contacto.getNombre() + "; " + contacto.getApellidos() + "; " + contacto.getEmail() + "; " + contacto.getTelefono1() + "; " + contacto.getTelefono2() + "; " + contacto.getDireccion());
            }

            bw.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void modificarEnXML(ArrayList<Contacto> contactos) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("contactos");
            document.appendChild(root);

            for (Contacto contacto : contactos) {
                Element contactoElement = document.createElement("contacto");
                root.appendChild(contactoElement);

                Element nombre = document.createElement("nombre");
                nombre.appendChild(document.createTextNode(contacto.getNombre()));
                contactoElement.appendChild(nombre);

                Element apellidos = document.createElement("apellidos");
                apellidos.appendChild(document.createTextNode(contacto.getApellidos()));
                contactoElement.appendChild(apellidos);

                Element email = document.createElement("email");
                email.appendChild(document.createTextNode(contacto.getEmail()));
                contactoElement.appendChild(email);

                Element telefono1 = document.createElement("telefono1");
                telefono1.appendChild(document.createTextNode(contacto.getTelefono1()));
                contactoElement.appendChild(telefono1);

                Element telefono2 = document.createElement("telefono2");
                telefono2.appendChild(document.createTextNode(contacto.getTelefono2()));
                contactoElement.appendChild(telefono2);

                Element direccion = document.createElement("direccion");
                direccion.appendChild(document.createTextNode(contacto.getDireccion()));
                contactoElement.appendChild(direccion);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(filePathXML.toFile());
            transformer.transform(source, result);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void modificarEnJSON(ArrayList<Contacto> contactos) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(filePathJSON.toFile(), contactos);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void eliminarContacto() {
        cargarContactos();

        System.out.println(bundle.getString("mensajeEliminar"));
        scanner.nextLine();

        System.out.print(bundle.getString("nombre") + ": ");
        String nombre = scanner.nextLine();

        System.out.print(bundle.getString("apellidos") + ": ");
        String apellidos = scanner.nextLine();

        System.out.print(bundle.getString("email") + ": ");
        String email = scanner.nextLine();

        System.out.print(bundle.getString("telefono") + "1: ");
        String telefono1 = scanner.nextLine();

        System.out.print(bundle.getString("telefono") + "2: ");
        String telefono2 = scanner.nextLine();

        System.out.print(bundle.getString("direccion") + ": ");
        String direccion = scanner.nextLine();

        for (Contacto contacto : contactos) {
            if (contacto.getNombre().equals(nombre) && contacto.getApellidos().equals(apellidos) && contacto.getEmail().equals(email) && contacto.getTelefono1().equals(telefono1) && contacto.getTelefono2().equals(telefono2) && contacto.getDireccion().equals(direccion)) {
                contactos.remove(contacto);
                break;
            }
        }

        modificarEnCSV(contactos);
        modificarEnXML(contactos);
        modificarEnJSON(contactos);
    }

    public void listarContactos() {
        cargarContactos();
        mostrarContactos();
    }

    private void cargarContactos() {
        contactos.clear();

        if (Files.exists(filePathCSV)) {
            leerFicheroCSV(filePathCSV);
        } else if (Files.exists(filePathXML)) {
            leerFicheroXML(filePathXML);
        } else if (Files.exists(filePathJSON)) {
            leerFicheroJSON(filePathJSON);
        } else {
            System.out.println(bundle.getString("error"));
        }
    }

    private void mostrarContactos() {
        contactos.sort(Comparator.comparing(Contacto::getNombre));

        for (Contacto contacto : contactos) {
            System.out.println(bundle.getString("nombre") + " - " + contacto.getNombre());
            System.out.println(bundle.getString("apellidos") + " - " + contacto.getApellidos());
            System.out.println(bundle.getString("email") + " - " + contacto.getEmail());
            System.out.println(bundle.getString("telefono") + "1 - " + contacto.getTelefono1());
            System.out.println(bundle.getString("telefono") + "2 - " + contacto.getTelefono2());
            System.out.println(bundle.getString("direccion") + " - " + contacto.getDireccion());
            System.out.println();
        }
    }

    private boolean guardarEnCSV(Contacto contacto) {
        if (comprobarExisteFicheroCSV(filePathCSV)) {
            escribirEnFicheroCSV(filePathCSV, contacto);
        } else {
            System.out.println(bundle.getString("error"));
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    private boolean comprobarExisteFicheroCSV(Path filePath) {
        if (Files.notExists(filePath)) {
            try {
                Files.createFile(filePath);

                BufferedWriter bw = Files.newBufferedWriter(filePath);
                bw.write("Nombre; Apellidos; Telefono1; Telefono2; Email; Direccion");

                bw.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }

    private boolean escribirEnFicheroCSV(Path filePathCSV, Contacto contacto) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePathCSV.toString(), true));
            bw.write("\n");
            bw.write(contacto.getNombre() + "; " + contacto.getApellidos() + "; " + contacto.getEmail() + "; " + contacto.getTelefono1() + "; " + contacto.getTelefono2() + "; " + contacto.getDireccion());

            bw.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    private boolean guardarEnXML(Contacto contacto) {
        if (comprobarExisteFicheroXML(filePathXML)) {
            escribirEnFicheroXML(filePathXML, contacto);
        } else {
            System.out.println(bundle.getString("error"));
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    private boolean comprobarExisteFicheroXML(Path filePath) {
        if (Files.notExists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }

    private boolean escribirEnFicheroXML(Path filePathXML, Contacto contacto) {
    try {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document;

        File file = filePathXML.toFile();
        if (file.length() == 0) {
            document = builder.newDocument();
            Element root = document.createElement("contactos");
            document.appendChild(root);
        } else {
            document = builder.parse(file);
        }

        Element root = document.getDocumentElement();

        Element contactoElement = document.createElement("contacto");
        root.appendChild(contactoElement);

        Element nombre = document.createElement("nombre");
        nombre.appendChild(document.createTextNode(contacto.getNombre()));
        contactoElement.appendChild(nombre);

        Element apellidos = document.createElement("apellidos");
        apellidos.appendChild(document.createTextNode(contacto.getApellidos()));
        contactoElement.appendChild(apellidos);

        Element email = document.createElement("email");
        email.appendChild(document.createTextNode(contacto.getEmail()));
        contactoElement.appendChild(email);

        Element telefono1 = document.createElement("telefono1");
        telefono1.appendChild(document.createTextNode(contacto.getTelefono1()));
        contactoElement.appendChild(telefono1);

        Element telefono2 = document.createElement("telefono2");
        telefono2.appendChild(document.createTextNode(contacto.getTelefono2()));
        contactoElement.appendChild(telefono2);

        Element direccion = document.createElement("direccion");
        direccion.appendChild(document.createTextNode(contacto.getDireccion()));
        contactoElement.appendChild(direccion);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(file);
        transformer.transform(source, result);

    } catch (Exception e) {
        LOGGER.error(e.getMessage());
        System.out.println(bundle.getString("error"));
        return Boolean.FALSE;
    }

    return Boolean.TRUE;
}

    private boolean guardarEnJSON(Contacto contacto) {
        if (comprobarExisteFicheroJSON(filePathJSON)) {
            escribirEnFicheroJSON(filePathJSON, contacto);
        } else {
            System.out.println(bundle.getString("error"));
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    private boolean comprobarExisteFicheroJSON(Path filePath) {
        if (Files.notExists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }

    private boolean escribirEnFicheroJSON(Path filePathJSON, Contacto contacto) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Contacto> contactos;

            if (Files.exists(filePathJSON) && Files.size(filePathJSON) > 0) {
                contactos = mapper.readValue(filePathJSON.toFile(), new TypeReference<List<Contacto>>() {});
            } else {
                contactos = new ArrayList<>();
            }

            contactos.add(contacto);

            mapper.writerWithDefaultPrettyPrinter().writeValue(filePathJSON.toFile(), contactos);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            System.out.println(bundle.getString("error"));
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    private boolean leerFicheroCSV(Path filePathCSV) {
        try {
            BufferedReader br = Files.newBufferedReader(filePathCSV);
            String linea;

            br.readLine();

            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split("; ");
                Contacto contacto = new Contacto(campos[0], campos[1], campos[2], campos[3], campos[4], campos[5]);
                contactos.add(contacto);
            }

            br.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    private boolean leerFicheroXML(Path filePathXML) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(filePathXML.toFile());

            Element root = document.getDocumentElement();
            NodeList contactoNodes = root.getElementsByTagName("contacto");

            for (int i = 0; i < contactoNodes.getLength(); i++) {
                Node node = contactoNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element contactoElement = (Element) node;

                    String nombre = contactoElement.getElementsByTagName("nombre").item(0).getTextContent();
                    String apellidos = contactoElement.getElementsByTagName("apellidos").item(0).getTextContent();
                    String email = contactoElement.getElementsByTagName("email").item(0).getTextContent();
                    String telefono1 = contactoElement.getElementsByTagName("telefono1").item(0).getTextContent();
                    String telefono2 = contactoElement.getElementsByTagName("telefono2").item(0).getTextContent();
                    String direccion = contactoElement.getElementsByTagName("direccion").item(0).getTextContent();

                    Contacto contactoObj = new Contacto(nombre, apellidos, email, telefono1, telefono2, direccion);
                    contactos.add(contactoObj);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            System.out.println(bundle.getString("error"));
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    private boolean leerFicheroJSON(Path filePathJSON) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            contactos = (ArrayList<Contacto>) mapper.readValue(filePathJSON.toFile(), new TypeReference<List<Contacto>>() {});

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }
}