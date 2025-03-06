package es.dam47.jaspergatos;

import es.dam47.jaspergatos.database.SQLDatabaseManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JasperGatosApplication {

    public static void main(String[] args) {
        try {
            SQLDatabaseManager.connectSSH();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("No se pudo abrir el túnel SSH: " + e.getMessage());
            System.exit(1); // Detiene la aplicación si falla la conexión SSH
        }

        // Iniciar Spring Boot después de abrir el túnel
        SpringApplication.run(JasperGatosApplication.class, args);

        // Cerrar el túnel cuando Spring Boot se detenga
        Runtime.getRuntime().addShutdownHook(new Thread(SQLDatabaseManager::disconnectSSH));

    }
}