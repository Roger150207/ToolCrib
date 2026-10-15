package com.toolcrib.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase encargada de crear y entregar conexiones a la base de datos
 * ToolCribDB usando los datos definidos en db.properties
 *
 * @author rogeer
 */
public class ConexionBD {

    private static final Properties PROPS = new Properties();

    // Bloque estático: carga el archivo db.properties una sola vez,
    // cuando la clase se usa por primera vez
    static {
        try (InputStream input = ConexionBD.class.getResourceAsStream("/db.properties")) {
            if (input == null) {
                throw new RuntimeException("No se encontró db.properties en el classpath. "
                        + "Verifica que exista en src/main/resources.");
            }
            PROPS.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar db.properties", e);
        }
    }

    // Constructor privado: esta clase no se instancia, solo se usa su método estátic
    private ConexionBD() {
    }

    /**
     * Abre y devuelve una nueva conexión a la base de datos.
     * Quien la use es responsable de cerrarla (idealmente con try-with-resources)
     */
    public static Connection getConexion() throws SQLException {
        String url = PROPS.getProperty("db.url");
        String user = PROPS.getProperty("db.user");
        String password = PROPS.getProperty("db.password");
        return DriverManager.getConnection(url, user, password);
    }
}