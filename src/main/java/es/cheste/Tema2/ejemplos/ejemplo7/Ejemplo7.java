package es.cheste.Tema2.ejemplos.ejemplo7;

import es.cheste.Tema2.ejercicios.ejercicio6.ConexionBD;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class Ejemplo7 {
    private static final Logger LOGGER = LogManager.getRootLogger();

    public static void main(String[] args) {
        ConexionBD conexionBD = new ConexionBD();

        llamarProcedimiento(conexionBD);

        conexionBD.desconectarBD();
    }

    public static boolean llamarProcedimiento(ConexionBD conexionBD) {
        CallableStatement statement = null;
        try {
            statement = conexionBD.conseguirConexion().prepareCall("{call listado_parcial_clientes(?, ?)}");

            statement.setString(1, "78901234X");
            statement.setInt(2, 0);
            statement.registerOutParameter(2, Types.INTEGER);

            statement.execute();

            ResultSet resultSet = statement.getResultSet();

            int inout_long = statement.getInt(2);
            System.out.println("=> inout_long: " + inout_long);
            int nCLi = 0;
            while (resultSet.next()) {
                System.out.println("[" + (++nCLi) + "]");
                System.out.println("DNI: " + resultSet.getString("DNI"));
                System.out.println("Apellidos: " + resultSet.getString("APELLIDOS"));
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return Boolean.TRUE;
    }
}
