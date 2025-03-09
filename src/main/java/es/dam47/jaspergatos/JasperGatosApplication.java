package es.dam47.jaspergatos;

import es.dam47.jaspergatos.database.SQLDatabaseManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JasperGatosApplication {

    public static void main(String[] args) {
        // Iniciar Spring Boot después de abrir el túnel
        SpringApplication.run(JasperGatosApplication.class, args);
    }
}