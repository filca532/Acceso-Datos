package es.cheste.Tema2.ejemplos.ejemplo3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class Ejemplo3 {
    public static final Logger LOGGER = LogManager.getRootLogger();

    public static void main(String[] args) {
        ConexionBD conexionBD = new ConexionBD();

        insertarDatos(conexionBD);
    }

    public static void insertarDatos(ConexionBD conexionBD) {
        PreparedStatement sqlInsert = null;
        try {
            sqlInsert = conexionBD.getConnection().prepareStatement("INSERT INTO CLIENTES(DNI, APELLIDOS, CP) VALUES  (?, ?, ?)");

            sqlInsert.setString(1, "78901234X");
            sqlInsert.setString(2, "NADALES");
            sqlInsert.setInt(3, 44126);
            sqlInsert.executeUpdate();

            int i = 1;
            sqlInsert.setString(i++, "89012345E");
            sqlInsert.setString(i++, "ROJAS");
            sqlInsert.setNull(i++, Types.INTEGER);
            sqlInsert.executeUpdate();

            i = 1;
            sqlInsert.setString(i++, "56789012B");
            sqlInsert.setString(i++, "SAMPER");
            sqlInsert.setInt(i++, 29730);
            sqlInsert.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
