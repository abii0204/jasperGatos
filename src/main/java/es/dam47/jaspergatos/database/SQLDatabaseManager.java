package es.dam47.jaspergatos.database;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class SQLDatabaseManager {
    // 🔹 Datos de conexión SSH
    private static final String SSH_HOST = "mvs.sytes.net";
    private static final int SSH_PORT = 11140;
    private static final String SSH_USER = "sshuser";
    private static final String SSH_PRIVATE_KEY = "C:\\Users\\Abii\\Documents\\accesoRemoto\\id_rsa_jc";

    // 🔹 Datos de conexión a PostgreSQL
    private static final String DB_HOST = "127.0.0.1"; // PostgreSQL está en el servidor remoto
    private static final int DB_PORT = 5432; // Puerto de PostgreSQL
    private static final int LOCAL_PORT = 5439; // Puerto local donde se hará el túnel
    private static final String DB_NAME = "catfeteria";  // Reemplaza con el nombre de tu BD
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "1234";

    private static Session sshSession; // Sesión SSH
    private static Connection connection; // Conexión JDBC

    /**
     * 🔹 Establece un túnel SSH con el servidor remoto y configura la conexión a PostgreSQL.
     */
    public static void connectSSH() {
        try {
            if (sshSession != null && sshSession.isConnected()) {
                return; // Evitar múltiples conexiones SSH
            }

            JSch jsch = new JSch();
            jsch.addIdentity(SSH_PRIVATE_KEY);

            sshSession = jsch.getSession(SSH_USER, SSH_HOST, SSH_PORT);
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(config);
            sshSession.connect();

            // 🔥 Redirigir el puerto local al servidor PostgreSQL remoto
            sshSession.setPortForwardingL(LOCAL_PORT, DB_HOST, DB_PORT);
            System.out.println("✅ Túnel SSH establecido correctamente");

        } catch (Exception e) {
            throw new RuntimeException("Error al establecer túnel SSH", e);
        }
    }

    /**
     * 🔹 Obtiene la conexión JDBC a PostgreSQL.
     */
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connectSSH(); // Asegurar que el túnel SSH esté abierto
                String dbUrl = "jdbc:postgresql://127.0.0.1:" + LOCAL_PORT + "/" + DB_NAME;
                connection = DriverManager.getConnection(dbUrl, DB_USER, DB_PASSWORD);
                System.out.println("✅ Conexión a PostgreSQL establecida correctamente");
            } catch (Exception e) {
                throw new RuntimeException("Error al conectar a PostgreSQL", e);
            }
        }
        return connection;
    }

    /**
     * 🔹 Cierra la conexión JDBC y el túnel SSH.
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
                System.out.println("Conexión a PostgreSQL cerrada.");
            }
            disconnectSSH();
        } catch (Exception e) {
            throw new RuntimeException("Error cerrando la conexión", e);
        }
    }

    /**
     * 🔹 Cierra la sesión SSH.
     */
    public static void disconnectSSH() {
        if (sshSession != null && sshSession.isConnected()) {
            sshSession.disconnect();
            System.out.println("🔌 Túnel SSH cerrado.");
        }
    }
}
