package es.cheste.Tema2.ejemplos.ejemplo5;

import es.cheste.Tema2.ejercicios.ejercicio2.ConexionBD;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;

public class Ejemplo5 {
    public static final Logger LOGGER = LogManager.getRootLogger();

    public static void main(String[] args) {
        ConexionBD conexionBD = new ConexionBD();

        borrarTablas(conexionBD);

        if (crearTabla(conexionBD)) {
            System.out.println("Se ha creado la tabla");
        } else {
            System.out.println("Hubo un error");
        }

        if (crearTabla2(conexionBD)) {
            System.out.println("Se ha creado la tabla");
        } else {
            System.out.println("Hubo un error");
        }

        conexionBD.desconectarBD();
    }

    public static boolean borrarTablas(ConexionBD conexionBD) {
        String sql1 = "DROP TABLE LINEA_FACTURA CASCADE";
        String sql2 = "DROP TABLE FACTURA CASCADE";

        try {
            Statement statement = conexionBD.conseguirConexion().createStatement();
            statement.execute(sql1);
            statement.execute(sql2);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    public static boolean crearTabla(ConexionBD conexionBD) {
        String sql = "CREATE TABLE FACTURA (NUM_FACTURA INTEGER AUTO_INCREMENT NOT NULL, " +
                "DNI_CLIENTE CHAR(9) NOT NULL, PRIMARY KEY (NUM_FACTURA), " +
                "FOREIGN KEY FK_FACT_DNI_CLIENTES (DNI_CLIENTE) REFERENCES CLIENTES(DNI))";

        try {
            Statement statement = conexionBD.conseguirConexion().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    public static boolean crearTabla2(ConexionBD conexionBD) {
        String sql = "CREATE TABLE LINEA_FACTURA (NUM_FACTURA INTEGER NOT NULL," +
                "LINEA_FACTURA SMALLINT NOT NULL," +
                "CONCEPTO VARCHAR(32) NOT NULL," +
                "CANTIDAD SMALLINT NOT NULL," +
                "PRIMARY KEY (NUM_FACTURA, LINEA_FACTURA)," +
                "FOREIGN KEY FK_LINEAFACT_NUM_FACTURA(NUM_FACTURA) " +
                "REFERENCES FACTURA(NUM_FACTURA))";

        try {
            Statement statement = conexionBD.conseguirConexion().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }
}
