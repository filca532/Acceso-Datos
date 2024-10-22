package es.cheste.Tema2.ejercicios.ejercicio1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio1 {
    private static final Logger LOGGER = LogManager.getRootLogger();

    public static void main(String[] args) {
        ConexionBD conexionBD = new ConexionBD();

        if (!modificarTuplas(conexionBD)) {
            System.out.println("Hubo un error durante la modificación");
        }

        if (!eliminarTupla(conexionBD)) {
            System.out.println("Hubo un error durante la eliminación");
        }

        System.out.println();

        mostrarTuplas(conexionBD);

        conexionBD.desconectarBD();
    }

    public static boolean modificarTuplas(ConexionBD conexionBD) {
        String sql = "UPDATE CLIENTES SET APELLIDOS = 'ROJAS' WHERE DNI = '89012345E'";

        if (conexionBD == null) {
            LOGGER.error("No hay conexión con base de datos, no se puede ejecutar la consulta.");
            System.out.println("No hay conexión con base de datos, no se puede ejecutar la consulta.");
            return Boolean.FALSE;
        }

        try {
            Statement statement = conexionBD.conseguirConexion().createStatement();

            int nFilasBorradas = statement.executeUpdate(sql);
            System.out.println(nFilasBorradas + " han sido modificados");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    public static boolean eliminarTupla(ConexionBD conexionBD) {
        String sql = "DELETE FROM CLIENTES WHERE DNI = '09876543K'";

        if (conexionBD == null) {
            LOGGER.error("No hay conexión con base de datos, no se puede ejecutar la consulta.");
            System.out.println("No hay conexión con base de datos, no se puede ejecutar la consulta.");
            return Boolean.FALSE;
        }

        try {
            Statement statement = conexionBD.conseguirConexion().createStatement();

            int nFilasBorradas = statement.executeUpdate(sql);
            System.out.println(nFilasBorradas + " han sido eliminados");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    public static void mostrarTuplas(ConexionBD conexionBD) {
        String sql = "SELECT * FROM CLIENTES";

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
                        resultSet.getString("APELLIDOS") + " - " + resultSet.getString("CP"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
