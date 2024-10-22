package es.cheste.Tema2.ejemplos.ejemplo3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {

    private static final Logger LOGGER = LogManager.getRootLogger();
    private static final String FILENAME = "src/main/resources/application.properties";

    private Connection connection = null;

    private void conectar() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File(FILENAME)));

            String url = properties.getProperty("connectionUrl");
            String user = properties.getProperty("user");
            String password = properties.getProperty("pass");

            connection = DriverManager.getConnection(url, user, password);
        } catch (FileNotFoundException e) {
            LOGGER.error("Error loading properties file", e);
        } catch (IOException e) {
            LOGGER.error("Error loading properties file", e);
        } catch (SQLException e) {
            LOGGER.error("Error connecting database", e);
        }
    }

    public void desconectar() {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Error disconnecting database", e);
        }
    }

    public Connection getConnection() {
        if(connection == null) {
            conectar();
        }
        return connection;
    }
}
