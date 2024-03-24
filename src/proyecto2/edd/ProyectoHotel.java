/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2.edd;

/**
 *
 * @author cesar
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ProyectoHotel {
    private static HashMap<String, String[]> reservas;
    private static HashMap<String, String> habitaciones;
    private static HashMap<String, String> estado;
    private static HashMap<String, String[]> historico;

    public static void main(String[] args) {
        try {
            cargarArchivosCSV();
            imprimirDatos();
        } catch (IOException e) {
            System.err.println("Error al cargar datos desde los archivos CSV: " + e.getMessage());
        }
    }

    public static void cargarArchivosCSV() throws IOException {
        cargarReservasDesdeCSV();
        cargarHabitacionesDesdeCSV();
        // Cargar estado y historico desde CSV de manera similar
    }

    public static void cargarReservasDesdeCSV() throws IOException {
        // Abre el diálogo de selección de archivo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo de reservas");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos CSV", "csv"));

        int seleccion = fileChooser.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            String rutaArchivo = archivoSeleccionado.getAbsolutePath();

            reservas = new HashMap<>();

            try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(","); // Separar los datos por comas
                    String cedula = datos[0].trim();
                    String[] infoReserva = new String[datos.length - 1];
                    System.arraycopy(datos, 1, infoReserva, 0, infoReserva.length);
                    reservas.put(cedula, infoReserva);
                }
            }
        } else {
            System.out.println("No se seleccionó ningún archivo de reservas.");
        }
    }

    public static void cargarHabitacionesDesdeCSV() throws IOException {
        // Abre el diálogo de selección de archivo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo de habitaciones");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos CSV", "csv"));

        int seleccion = fileChooser.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            String rutaArchivo = archivoSeleccionado.getAbsolutePath();

            habitaciones = new HashMap<>();

            try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(","); // Separar los datos por comas
                    habitaciones.put(datos[0].trim(), datos[1].trim() + ", Piso " + datos[2].trim());
                }
            }
        } else {
            System.out.println("No se seleccionó ningún archivo de habitaciones.");
        }
    }

    // Métodos para cargar estado y historico desde CSV de manera similar

    public static void imprimirDatos() {
        // Imprimir las reservas
        System.out.println("Reservas:");
        for (String cedula : reservas.keySet()) {
            System.out.println("Cédula: " + cedula + ", Datos: " + String.join(", ", reservas.get(cedula)));
        }

        // Imprimir las habitaciones
        System.out.println("\nHabitaciones:");
        for (String numHabitacion : habitaciones.keySet()) {
            System.out.println("Número de habitación: " + numHabitacion + ", Datos: " + habitaciones.get(numHabitacion));
        }

        // Imprimir el estado
        // Imprimir el historico
    }
}
