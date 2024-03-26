/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2.edd;

/**
 *
 * @author cesar
 */
public class NodoHistorial {
    String numHabitacion;
    String[] datos;
    NodoHistorial izquierda, derecha;

    public NodoHistorial(String numHabitacion, String[] datos) {
        this.numHabitacion = numHabitacion;
        this.datos = datos;
        izquierda = null;
        derecha = null;
    }

    public String getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(String numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public String[] getDatos() {
        return datos;
    }

    public void setDatos(String[] datos) {
        this.datos = datos;
    }

    public NodoHistorial getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoHistorial izquierda) {
        this.izquierda = izquierda;
    }

    public NodoHistorial getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoHistorial derecha) {
        this.derecha = derecha;
    }
    
    
}
