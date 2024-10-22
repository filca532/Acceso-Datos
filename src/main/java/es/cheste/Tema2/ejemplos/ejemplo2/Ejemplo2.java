package es.cheste.Tema2.ejemplos.ejemplo2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejemplo2 {

    private static final Logger LOGGER = LogManager.getRootLogger();

    public static void main(String[] args) {

        ConexionBD conexion = new ConexionBD();
//        consultaEmpleado(conexion);
//        crearTabla(conexion);

        borrarDatos(conexion);
        insertarDatos(conexion);
//        mostrarTuplas(conexion);
        conexion.desconectar();
    }

    private static void consultaEmpleado(ConexionBD conexion) {

        String sql = "SELECT first_name, last_name  FROM employees LIMIT 25";
        if(conexion == null) {
            LOGGER.error("No hay conexión con base de datos, no se puede ejecutar la consulta.");
            System.out.println("No hay conexión con base de datos, no se puede ejecutar la consulta.");
            return;
        }

        try (Statement stmt = conexion.getConnection().createStatement();){
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Empleado: " + rs.getString("first_name") + " " + rs.getString("last_name"));
            }
        } catch (Exception e) {
            LOGGER.error("Error al consultar empleados", e);
        }
    }

    private static void crearTabla(ConexionBD conexion) {
        String sql = "CREATE TABLE CLIENTES (DNI CHAR(9) NOT NULL, APELLIDOS VARCHAR(32) NOT NULL, CP CHAR(5), PRIMARY KEY (DNI))";
        if(conexion == null) {
            LOGGER.error("No hay conexión con base de datos, no se puede ejecutar la consulta.");
            System.out.println("No hay conexión con base de datos, no se puede ejecutar la consulta.");
            return;
        }

        try {
            Statement statement = conexion.getConnection().createStatement();
            if (statement.execute(sql)) {
                System.out.println("Se ha creado");
            } else {
                System.out.println("No se ha creado");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private static void borrarDatos(ConexionBD conexion) {
        String sql = "DELETE FROM CLIENTES";

        if(conexion == null) {
            LOGGER.error("No hay conexión con base de datos, no se puede ejecutar la consulta.");
            System.out.println("No hay conexión con base de datos, no se puede ejecutar la consulta.");
            return;
        }

        try {
            Statement statement = conexion.getConnection().createStatement();

            int nFil = statement.executeUpdate(sql);
            System.out.println(nFil + "Filas BORRADAS");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private static void insertarDatos(ConexionBD conexion) {
        String sql = "INSERT INTO CLIENTES (DNI, APELLIDOS, CP) VALUES " +
                "('78901234X', 'NADALES', '44126')," +
                "('89012345E', 'HOJAS', NULL)," +
                "('56789012B', 'SAMPER', '29730')," +
                "('09876543K', 'LAMIQUIZ', NULL)";

        if(conexion == null) {
            LOGGER.error("No hay conexión con base de datos, no se puede ejecutar la consulta.");
            System.out.println("No hay conexión con base de datos, no se puede ejecutar la consulta.");
            return;
        }

        try {
            Statement statement = conexion.getConnection().createStatement();

            int nFil = statement.executeUpdate(sql);
            System.out.println(nFil + "Filas insertadas");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private static void mostrarTuplas(ConexionBD conexion) {
        String sql = "SELECT * FROM CLIENTES";

        if(conexion == null) {
            LOGGER.error("No hay conexión con base de datos, no se puede ejecutar la consulta.");
            System.out.println("No hay conexión con base de datos, no se puede ejecutar la consulta.");
            return;
        }

        try {
            Statement statement = conexion.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            int i = 1;
            while (resultSet.next()) {
                System.out.println("[" + (i++) + "]");
                System.out.println("DNI: " + resultSet);
                System.out.println("APELLIDOS: " + resultSet);
                System.out.println("CP: " + resultSet);
                System.out.println();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
