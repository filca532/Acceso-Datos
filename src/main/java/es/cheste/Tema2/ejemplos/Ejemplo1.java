package es.cheste.Tema2.ejemplos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class Ejemplo1 {

    private static final Logger LOGGER = LogManager.getRootLogger();

    public static void main(String[] args) {

        try {
            // Conexión a la url de la bd
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/empleados?user" +
                    "=root&password=filca7367");

            // Crear consulta
            Statement stmt = connection.createStatement();

            // Ejecución consulta
            ResultSet rs = stmt.executeQuery("SELECT first_name, last_name  FROM " +
                    "employees LIMIT 25");

            // Resultados
            while (rs.next()) {
                System.out.println("Empleado: " + rs.getString("first_name") + " " + rs.getString("last_name"));
            }

        } catch (SQLException e) {
            LOGGER.error("Error connecting database", e);
        }
    }
}
