package es.dam47.jaspergatos.controllers;

import es.dam47.jaspergatos.services.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/getReport") // La URL completa será "/report/getReport".
    public ResponseEntity<byte[]> getCarta() {
        System.out.println("Obteniendo informe"); // Mensaje en consola para indicar que se está procesando la solicitud.

        try {
            // Llama al servicio para generar el informe y lo almacena como un array de bytes.
            byte[] report = reportService.generarReport("carta");

            // Crea encabezados HTTP para especificar que la respuesta será un archivo PDF.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF); // Indica que el contenido es un PDF.
            headers.add("Content-Disposition", "inline; filename=report.pdf"); // Especifica cómo se debe mostrar el archivo (en línea).

            // Retorna el informe con los encabezados y un código de estado HTTP 200 (OK).
            return new ResponseEntity<>(report, headers, HttpStatus.OK);

        } catch (JRException e) {
            // Maneja excepciones relacionadas con JasperReports.
            System.out.println(e.getMessage()); // Muestra el mensaje de error en consola.

        } catch (FileNotFoundException e) {
            // Maneja excepciones cuando no se encuentra el archivo del informe.
            System.out.println(e.getMessage()); // Muestra el mensaje de error en consola.

        } catch (Exception e) {
            // Maneja cualquier otra excepción no anticipada.
            throw new RuntimeException(e); // Lanza una excepción para que sea manejada por el framework.
        }

        // En caso de error, retorna una respuesta con estado 500 (Error interno del servidor) y un cuerpo nulo.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @GetMapping("/getEventos") // La URL completa será "/report/getReport".
    public ResponseEntity<byte[]> getEventos() {
        System.out.println("Obteniendo informe"); // Mensaje en consola para indicar que se está procesando la solicitud.

        try {
            // Llama al servicio para generar el informe y lo almacena como un array de bytes.
            byte[] report = reportService.generarReport("eventos");

            // Crea encabezados HTTP para especificar que la respuesta será un archivo PDF.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF); // Indica que el contenido es un PDF.
            headers.add("Content-Disposition", "inline; filename=report.pdf"); // Especifica cómo se debe mostrar el archivo (en línea).

            // Retorna el informe con los encabezados y un código de estado HTTP 200 (OK).
            return new ResponseEntity<>(report, headers, HttpStatus.OK);

        } catch (JRException e) {
            // Maneja excepciones relacionadas con JasperReports.
            System.out.println(e.getMessage()); // Muestra el mensaje de error en consola.

        } catch (FileNotFoundException e) {
            // Maneja excepciones cuando no se encuentra el archivo del informe.
            System.out.println(e.getMessage()); // Muestra el mensaje de error en consola.

        } catch (Exception e) {
            // Maneja cualquier otra excepción no anticipada.
            throw new RuntimeException(e); // Lanza una excepción para que sea manejada por el framework.
        }

        // En caso de error, retorna una respuesta con estado 500 (Error interno del servidor) y un cuerpo nulo.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


    @GetMapping("/getFichaMedica/{idGato}") // La URL completa será "/report/getReport".
    public ResponseEntity<byte[]> getFicha(@PathVariable int idGato) {
        System.out.println("Obteniendo informe"); // Mensaje en consola para indicar que se está procesando la solicitud.

        try {
            // Llama al servicio para generar el informe y lo almacena como un array de bytes.
            byte[] report = reportService.generarReport2("fichaMedica", idGato);

            // Crea encabezados HTTP para especificar que la respuesta será un archivo PDF.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF); // Indica que el contenido es un PDF.
            headers.add("Content-Disposition", "inline; filename=report.pdf"); // Especifica cómo se debe mostrar el archivo (en línea).

            // Retorna el informe con los encabezados y un código de estado HTTP 200 (OK).
            return new ResponseEntity<>(report, headers, HttpStatus.OK);

        } catch (JRException e) {
            // Maneja excepciones relacionadas con JasperReports.
            System.out.println(e.getMessage()); // Muestra el mensaje de error en consola.

        } catch (FileNotFoundException e) {
            // Maneja excepciones cuando no se encuentra el archivo del informe.
            System.out.println(e.getMessage()); // Muestra el mensaje de error en consola.

        } catch (Exception e) {
            // Maneja cualquier otra excepción no anticipada.
            throw new RuntimeException(e); // Lanza una excepción para que sea manejada por el framework.
        }

        // En caso de error, retorna una respuesta con estado 500 (Error interno del servidor) y un cuerpo nulo.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @GetMapping("/getEventosCliente/{nombreEvento}") // La URL completa será "/report/getReport".
    public ResponseEntity<byte[]> getFicha(@PathVariable String nombreEvento) {
        System.out.println("Obteniendo informe"); // Mensaje en consola para indicar que se está procesando la solicitud.

        try {
            // Llama al servicio para generar el informe y lo almacena como un array de bytes.
            byte[] report = reportService.generarReportEventos("eventosClientes", nombreEvento);

            // Crea encabezados HTTP para especificar que la respuesta será un archivo PDF.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF); // Indica que el contenido es un PDF.
            headers.add("Content-Disposition", "inline; filename=report.pdf"); // Especifica cómo se debe mostrar el archivo (en línea).

            // Retorna el informe con los encabezados y un código de estado HTTP 200 (OK).
            return new ResponseEntity<>(report, headers, HttpStatus.OK);

        } catch (JRException e) {
            // Maneja excepciones relacionadas con JasperReports.
            System.out.println(e.getMessage()); // Muestra el mensaje de error en consola.

        } catch (FileNotFoundException e) {
            // Maneja excepciones cuando no se encuentra el archivo del informe.
            System.out.println(e.getMessage()); // Muestra el mensaje de error en consola.

        } catch (Exception e) {
            // Maneja cualquier otra excepción no anticipada.
            throw new RuntimeException(e); // Lanza una excepción para que sea manejada por el framework.
        }

        // En caso de error, retorna una respuesta con estado 500 (Error interno del servidor) y un cuerpo nulo.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    //abi hola


}
