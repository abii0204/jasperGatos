package es.dam47.jaspergatos.services;

import es.dam47.jaspergatos.database.SQLDatabaseManager;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.stereotype.Service;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

    public byte[] generarReport(String reportName) throws Exception {
        Connection connection = null;
        try {
            // 1️⃣ Establecer conexión con la base de datos
            System.out.println("Conectando al túnel SSH y PostgreSQL...");
            connection = SQLDatabaseManager.connect();

            // 2️⃣ Cargar el archivo del informe Jasper (.jasper)
            InputStream reportStream = new FileInputStream("src/main/resources/reports/" + reportName + ".jasper");
            if (reportStream == null) {
                throw new JRException("El informe no se encontró: " + reportName);
            }

            // 3️⃣ No necesitamos pasar parámetros, simplemente dejamos el mapa vacío
            Map<String, Object> params = new HashMap<>(); // Mapa vacío

            // 4️⃣ Rellenar el informe Jasper con la conexión de la base de datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, params, connection);

            // 5️⃣ Exportar el informe a PDF
            System.out.println("Generando reporte...");
            return JasperExportManager.exportReportToPdf(jasperPrint);

        } finally {
            // 6️⃣ Cerrar la conexión al finalizar
            if (connection != null) {
                SQLDatabaseManager.disconnect(connection);
            }
        }
    }

    public byte[] generarReport2(String reportName, int idGato) throws Exception {
        Connection connection = null;
        try {
            // 1️⃣ Establecer conexión con la base de datos
            System.out.println("Conectando al túnel SSH y PostgreSQL...");
            connection = SQLDatabaseManager.connect();

            // 2️⃣ Cargar el archivo del informe Jasper (.jasper)
            InputStream reportStream = new FileInputStream("src/main/resources/reports/" + reportName + ".jasper");
            if (reportStream == null) {
                throw new JRException("El informe no se encontró: " + reportName);
            }

            // 3️⃣ No necesitamos pasar parámetros, simplemente dejamos el mapa vacío
            Map<String, Object> params = new HashMap<>(); // Mapa vacío
            params.put("idGato", idGato);

            // 4️⃣ Rellenar el informe Jasper con la conexión de la base de datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, params, connection);

            // 5️⃣ Exportar el informe a PDF
            System.out.println("Generando reporte...");
            return JasperExportManager.exportReportToPdf(jasperPrint);

        } finally {
            // 6️⃣ Cerrar la conexión al finalizar
            if (connection != null) {
                SQLDatabaseManager.disconnect(connection);
            }
        }
    }

    public byte[] generarReportEventos(String reportName, String nombreEventoCliente) throws Exception {
        Connection connection = null;
        try {
            // 1️⃣ Establecer conexión con la base de datos
            System.out.println("Conectando al túnel SSH y PostgreSQL...");
            connection = SQLDatabaseManager.connect();

            // 2️⃣ Cargar el archivo del informe Jasper (.jasper)
            InputStream reportStream = new FileInputStream("src/main/resources/reports/" + reportName + ".jasper");
            if (reportStream == null) {
                throw new JRException("El informe no se encontró: " + reportName);
            }

            // 3️⃣ No necesitamos pasar parámetros, simplemente dejamos el mapa vacío
            Map<String, Object> params = new HashMap<>(); // Mapa vacío
            params.put("nombreEvento", nombreEventoCliente);

            // 4️⃣ Rellenar el informe Jasper con la conexión de la base de datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, params, connection);

            // 5️⃣ Exportar el informe a PDF
            System.out.println("Generando reporte...");
            return JasperExportManager.exportReportToPdf(jasperPrint);

        } finally {
            // 6️⃣ Cerrar la conexión al finalizar
            if (connection != null) {
                SQLDatabaseManager.disconnect(connection);
            }
        }
    }

}
