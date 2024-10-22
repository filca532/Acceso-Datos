package es.cheste.Tema2.ejercicios.ejercicio1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {

    private static final Logger LOGGER = LogManager.getRootLogger();
    private static final String RUTA_PROPIEDADES = "src/main/resources/application.properties";

    Connection connection = null;

    public Connection conseguirConexion() {
        if (connection == null) {
            conectarBD();
        }

        return connection;
    }

    public void desconectarBD() {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void conectarBD() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(RUTA_PROPIEDADES)));

            String url = properties.getProperty("connectionUrl");
            String usuario = properties.getProperty("user");
            String contrasenya = properties.getProperty("pass");

            connection = DriverManager.getConnection(url, usuario, contrasenya);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
