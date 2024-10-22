package es.cheste.Tema2.ejercicios.ejercicio5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejercicio5 {
    private static final Logger LOGGER = LogManager.getRootLogger();

    public static void main(String[] args) {
        ConexionBD conexionBD = new ConexionBD();

        mostrarClientes(conexionBD);

        conexionBD.desconectarBD();
    }

    public static void mostrarClientes(ConexionBD conexionBD) {
        try {
            PreparedStatement statement = conexionBD.conseguirConexion().prepareStatement("SELECT * FROM CLIENTES WHERE DNI=?");

            statement.setString(1, "09876543K");

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String apellidos = resultSet.getString("APELLIDOS");
                System.out.println("Apellidos: " + apellidos);
            } else {
                System.out.println("No existe el DNI");
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
