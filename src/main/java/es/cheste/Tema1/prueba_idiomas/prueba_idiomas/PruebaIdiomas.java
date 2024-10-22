package es.cheste.Tema1.prueba_idiomas.prueba_idiomas;

import java.util.Locale;
import java.util.ResourceBundle;

public class PruebaIdiomas {
    public static void main(String[] args) {
        Locale locale = new Locale("es", "ES");
        ResourceBundle bundle = ResourceBundle.getBundle("languages.mensajes", locale);

        String mensaje = bundle.getString("greeting");
        System.out.println(mensaje);

        mensaje = bundle.getString("farewell");
        System.out.println(mensaje);
    }
}
