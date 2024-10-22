package es.cheste.Tema2.ejercicios.ejercicio6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ejercicio6 {
    public static final Logger LOGGER = LogManager.getRootLogger();

    public static void main(String[] args) {
        ConexionBD conexionBD = new ConexionBD();

        if (insertarDatosGestionado(conexionBD)) {
            System.out.println("Se ha realizado correctamente");
        } else {
            System.out.println("Hubo un problema en la ejecución");
        }

    }

    public static boolean insertarDatosGestionado(ConexionBD conexionBD) {
        try {
            PreparedStatement sqlInsert = conexionBD.conseguirConexion().prepareStatement("INSERT INTO CLIENTES(DNI, APELLIDOS, CP) VALUES (?, ?, ?)");

            int i = 0;
            sqlInsert.setString(++i, "54320198V");
            sqlInsert.setString(++i, "CARVAJAL");
            sqlInsert.setString(++i, "10109");
            sqlInsert.executeUpdate();

            sqlInsert.setString(i = 1, "76543210S");
            sqlInsert.setString(++i, "MARQUEZ");
            sqlInsert.setString(++i, "46987");
            sqlInsert.executeUpdate();

            sqlInsert.setString(i = 1, "90123456A");
            sqlInsert.setString(++i, "MOLINA");
            sqlInsert.setString(++i, "351536");
            sqlInsert.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }
}
