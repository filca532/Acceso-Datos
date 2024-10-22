package es.cheste.Tema2.ejercicios.ejercicio7;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class Ejercicio7 {
    private static final Logger LOGGER = LogManager.getRootLogger();

    public static void main(String[] args) {
        ConexionBD conexionBD = new ConexionBD();

        devolverApellidos(conexionBD);

        conexionBD.desconectarBD();
    }

    public static void devolverApellidos(ConexionBD conexionBD) {
        CallableStatement statement = null;
        try {
            statement = conexionBD.conseguirConexion().prepareCall("{call obtener_apellidos(?)}");

            statement.setString(1, "78901234X");

            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            resultSet.next();

            System.out.println("Apellidos: " + resultSet.getString("APELLIDOS"));

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
