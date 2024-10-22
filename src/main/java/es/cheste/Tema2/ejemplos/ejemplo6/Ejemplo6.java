package es.cheste.Tema2.ejemplos.ejemplo6;

import es.cheste.Tema2.ejercicios.ejercicio6.ConexionBD;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejemplo6 {
    private static final Logger LOGGER = LogManager.getRootLogger();

    public static void main(String[] args) {
        ConexionBD conexionBD = new ConexionBD();

        insertarTuplas(conexionBD);

        conexionBD.desconectarBD();
    }

    public static boolean insertarTuplas(ConexionBD conexionBD) {
        try {
            PreparedStatement sInsertFact = conexionBD.conseguirConexion().prepareStatement("INSERT INTO FACTURA(DNI_CLIENTE) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);
            PreparedStatement sInsertLineFact = conexionBD.conseguirConexion().prepareStatement("INSERT INTO LINEA_FACTURA(NUM_FACTURA, LINEA_FACTURA, CONCEPTO, CANTIDAD) VALUES (?, ?, ?, ?);");

            conexionBD.conseguirConexion().setAutoCommit(false);

            int i = 1;

            sInsertFact.setString(i++, "78901234X");
            sInsertFact.executeUpdate();
            ResultSet resultSet = sInsertFact.getGeneratedKeys();
            resultSet.next();
            int numFact = resultSet.getInt(1);

            int lineaFact = 1;

            i = 1;
            sInsertLineFact.setInt(i++, numFact);
            sInsertLineFact.setInt(i++, lineaFact++);
            sInsertLineFact.setString(i++, "TUERCAS");
            sInsertLineFact.setInt(i++, 25);
            sInsertLineFact.executeUpdate();

            i = 1;
            sInsertLineFact.setInt(i++, numFact);
            sInsertLineFact.setInt(i++, lineaFact++);
            sInsertLineFact.setString(i++, "TORNILLOS");
            sInsertLineFact.setInt(i++, 250);
            sInsertLineFact.executeUpdate();

            conexionBD.conseguirConexion().commit();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            try {
                conexionBD.conseguirConexion().rollback();
            } catch (SQLException ex) {
                LOGGER.error(ex.getMessage());
            }
        }


        return Boolean.TRUE;
    }
}
