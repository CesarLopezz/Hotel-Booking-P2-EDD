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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ProyectoHotel {
    private static ArbolABB arbolReservas; // Árbol binario de búsqueda para almacenar las reservaciones
    //private static HashMap<String, String> reservas;
    private static HashMap<String, String> habitaciones;
    private static HashMap<String, String> estado;
    private static ArbolHistorial arbolHistorial;
    //private static HashMap<String, String> clientesHospedados;


    public static void main(String[] args) {
        try {
            //clientesHospedados = new HashMap<>();
            cargarArchivosCSV();
            imprimirDatos();
            buscarClientesHospedados();
            buscarReservacion();
            buscarHistorial();
            String numHab = "7";
            realizarCheckIn(numHab);
            
            
        } catch (IOException e) {
            System.err.println("Error al cargar datos desde los archivos CSV: " + e.getMessage());
        }
    }
    
    public static void realizarCheckIn(String numHabitacion) {
        // Verificar si la habitación está disponible para realizar el check-in
        if (estado.containsKey(numHabitacion) && estado.get(numHabitacion).equals("disponible")) {
            estado.put(numHabitacion, "ocupado"); // Actualizar el estado de la habitación a ocupado
            guardarEstadoEnCSV(); // Guardar el estado actualizado en el archivo CSV
            System.out.println("Check-in realizado correctamente en la habitación " + numHabitacion);
        } else {
            System.out.println("La habitación " + numHabitacion + " no está disponible para realizar el check-in.");
        }
    }
    
    public static void guardarEstadoEnCSV() {
        // Código para guardar el estado actualizado en el archivo CSV
        try (PrintWriter writer = new PrintWriter(new FileWriter("estado.csv"))) {
            for (Map.Entry<String, String> entry : estado.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
            }
            System.out.println("Estado de las habitaciones guardado correctamente en el archivo CSV.");
        } catch (IOException e) {
            System.err.println("Error al guardar el estado en el archivo CSV: " + e.getMessage());
        }
    }
    

    public static void cargarArchivosCSV() throws IOException {
        cargarReservasDesdeCSV();
        cargarHabitacionesDesdeCSV();
        cargarEstadoDesdeCSV();
        cargarHistorialDesdeCSV();
        // Cargar estado y historico desde CSV de manera similar
    }
    
    public static void buscarClientesHospedados() {
        // Solicitar al usuario que ingrese el nombre completo del cliente
        String nombreCompleto = JOptionPane.showInputDialog("Ingrese el nombre completo del cliente:");
        if (nombreCompleto != null && !nombreCompleto.isEmpty()) {
            // Buscar en el HashMap el número de habitación asociado al nombre completo del cliente
            String numHabitacion = estado.get(nombreCompleto);
            if (numHabitacion != null) {
                JOptionPane.showMessageDialog(null, nombreCompleto + " se encuentra hospedado en la habitación número " + numHabitacion);
            } else {
                JOptionPane.showMessageDialog(null, nombreCompleto + " no se encuentra hospedado en el hotel.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe ingresar un nombre completo.");
        }
    }

    public static void cargarEstadoDesdeCSV() {
        estado = new HashMap<>();

        // Abre el diálogo de selección de archivo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo de estado");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos CSV", "csv"));

        int seleccion = fileChooser.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            String rutaArchivo = archivoSeleccionado.getAbsolutePath();

            try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] campos = linea.split(",");
                    if (campos.length >= 2) { // Asegurarse de que hay al menos dos campos (número de habitación y nombre del cliente)
                        String numHabitacion = campos[0].trim();
                        String nombreCompleto = campos[2].trim() + " " + campos[1].trim(); // Concatenar apellido y nombre
                        estado.put(nombreCompleto, numHabitacion);
                    }
                }
                System.out.println("Estado de las habitaciones cargado correctamente desde el archivo CSV.");
            } catch (IOException e) {
                System.err.println("Error al leer el archivo CSV de estado: " + e.getMessage());
            }
        } else {
            System.out.println("No se seleccionó ningún archivo de estado.");
        }
    }
    
    public static void cargarReservasDesdeCSV() {
        arbolReservas = new ArbolABB();

        // Abre el diálogo de selección de archivo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo de reservas");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos CSV", "csv"));

        int seleccion = fileChooser.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            String rutaArchivo = archivoSeleccionado.getAbsolutePath();

            try (BufferedReader br = new BufferedReader(new FileReader(new File(rutaArchivo)))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] campos = linea.split(",");
                    String cedula = campos[0].trim();
                    String[] datosReserva = new String[campos.length - 1];
                    System.arraycopy(campos, 1, datosReserva, 0, datosReserva.length);
                    arbolReservas.insertar(cedula, datosReserva);
                }
                System.out.println("Reservas cargadas correctamente desde el archivo CSV.");
            } catch (IOException e) {
                System.err.println("Error al leer el archivo CSV de reservas: " + e.getMessage());
            }
        } else {
            System.out.println("No se seleccionó ningún archivo de reservas.");
        }
    }
    public static void buscarReservacion() {
        // Solicitar al usuario que ingrese la cédula del cliente
        String cedula = JOptionPane.showInputDialog("Ingrese la cédula del cliente:");

        if (cedula != null && !cedula.isEmpty()) {
            // Buscar la reservación correspondiente a la cédula ingresada
            String[] datosReserva = arbolReservas.buscar(cedula);
            if (datosReserva != null) {
                // Mostrar los datos de la reservación
                String mensaje = "Datos de la reservación:\n";
                mensaje += "Cédula: " + cedula + "\n";
                mensaje += "Nombre: " + datosReserva[0] + "\n";
                mensaje += "Apellido: " + datosReserva[1] + "\n";
                mensaje += "Email: " + datosReserva[2] + "\n";
                // Agregar más información si es necesario
                JOptionPane.showMessageDialog(null, mensaje);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ninguna reservación para la cédula ingresada.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe ingresar la cédula del cliente.");
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
    
    public static void cargarHistorialDesdeCSV() {
        arbolHistorial = new ArbolHistorial();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo de historial de habitaciones");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos CSV", "csv"));

        int seleccion = fileChooser.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            String rutaArchivo = archivoSeleccionado.getAbsolutePath();

            try (BufferedReader br = new BufferedReader(new FileReader(new File(rutaArchivo)))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] campos = linea.split(",");
                    String numHabitacion = campos[6].trim();
                    String[] historial = new String[campos.length - 1];
                    System.arraycopy(campos, 1, historial, 0, historial.length);
                    arbolHistorial.insertar(numHabitacion, historial);
                }
                System.out.println("Historial de habitaciones cargado correctamente desde el archivo CSV.");
            } catch (IOException e) {
                System.err.println("Error al leer el archivo CSV de historial de habitaciones: " + e.getMessage());
            }
        } else {
            System.out.println("No se seleccionó ningún archivo de historial de habitaciones.");
        }
    }
    
    public static void buscarHistorial() {
        while (true) {
            String numHabitacion = JOptionPane.showInputDialog("Ingrese el número de habitación (o escriba 'salir' para terminar):");

            if (numHabitacion == null || numHabitacion.equalsIgnoreCase("salir")) {
                break; // Salir del bucle si se ingresa "salir" o se cierra la ventana
            }

            if (!numHabitacion.isEmpty()) {
                String[] historial = arbolHistorial.buscarHistorial(numHabitacion);
                if (historial != null) {
                    String mensaje = "Historial de la habitación " + numHabitacion + ":\n";
                    for (String info : historial) {
                        mensaje += info + "\n";
                    }
                    JOptionPane.showMessageDialog(null, mensaje);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró historial para la habitación " + numHabitacion + ".");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe ingresar el número de habitación.");
            }
        }
    }


    // Métodos para cargar estado y historico desde CSV de manera similar

    public static void imprimirDatos() {
        // Imprimir las reservas
        //System.out.println("Reservas:");
        //for (String cedula : reservas.keySet()) {
        //    System.out.println("Cédula: " + cedula + ", Datos: " + String.join(", ", reservas.get(cedula)));
        //}

        // Imprimir las habitaciones
        System.out.println("\nHabitaciones:");
        for (String numHabitacion : habitaciones.keySet()) {
            System.out.println("Número de habitación: " + numHabitacion + ", Datos: " + habitaciones.get(numHabitacion));
        }

        // Imprimir el estado
        System.out.println("\nEstado:");
        for (String numHabitacion1 : estado.keySet()) {
            System.out.println("Número de habitación: " + numHabitacion1 + ", Datos: " + estado.get(numHabitacion1));
        }
        // Imprimir el historico
        //System.out.println("\nHistorico:");
        //for (String cedula : historico.keySet()) {
        //    System.out.println("Cédula: " + cedula + ", Datos: " + String.join(", ", historico.get(cedula)));
        //}
    }
}
