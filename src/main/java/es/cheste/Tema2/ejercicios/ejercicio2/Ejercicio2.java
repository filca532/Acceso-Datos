package es.cheste.Tema2.ejercicios.ejercicio2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio2 {
    private static final Logger LOGGER = LogManager.getRootLogger();

    public static void main(String[] args) {
        ConexionBD conexionBD = new ConexionBD();

        mostrarTuplas(conexionBD);

        conexionBD.desconectarBD();
    }

    public static void mostrarTuplas(ConexionBD conexionBD) {
        String sql = "SELECT DNI, APELLIDOS, IFNULL(CP, 0) AS CP FROM CLIENTES";

        if (conexionBD == null) {
            LOGGER.error("No hay conexión con base de datos, no se puede ejecutar la consulta.");
            System.out.println("No hay conexión con base de datos, no se puede ejecutar la consulta.");
            return;
        }

        try {
            Statement statement = conexionBD.conseguirConexion().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                System.out.println(resultSet.getString("DNI") + " - " +
                        resultSet.getString("APELLIDOS") + " - " +
                        resultSet.getInt("CP"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
