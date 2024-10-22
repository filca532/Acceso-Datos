package es.cheste.Tema2.ejercicios.ejercicio4;

import es.cheste.Tema2.ejercicios.ejercicio2.ConexionBD;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio4 {
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

            resultSet.last();

            System.out.println("Hay " + resultSet.getRow() + " tuplas");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
