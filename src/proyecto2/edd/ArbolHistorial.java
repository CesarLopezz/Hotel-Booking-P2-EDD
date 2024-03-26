/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2.edd;

/**
 *
 * @author cesar
 */
public class ArbolHistorial {
    NodoHistorial raiz;

    public ArbolHistorial() {
        raiz = null;
    }

    public void insertar(String numHabitacion, String[] datos) {
        raiz = insertarRec(raiz, numHabitacion, datos);
    }

    private NodoHistorial insertarRec(NodoHistorial nodo, String numHabitacion, String[] datos) {
        if (nodo == null) {
            return new NodoHistorial(numHabitacion, datos);
        }

        if (numHabitacion.compareTo(nodo.numHabitacion) < 0) {
            nodo.izquierda = insertarRec(nodo.izquierda, numHabitacion, datos);
        } else if (numHabitacion.compareTo(nodo.numHabitacion) > 0) {
            nodo.derecha = insertarRec(nodo.derecha, numHabitacion, datos);
        } else {
            nodo.datos = datos;
        }

        return nodo;
    }

    public String[] buscarHistorial(String numHabitacion) {
        return buscarRec(raiz, numHabitacion);
    }

    private String[] buscarRec(NodoHistorial nodo, String numHabitacion) {
        if (nodo == null) {
            return null;
        }

        if (numHabitacion.compareTo(nodo.numHabitacion) < 0) {
            return buscarRec(nodo.izquierda, numHabitacion);
        } else if (numHabitacion.compareTo(nodo.numHabitacion) > 0) {
            return buscarRec(nodo.derecha, numHabitacion);
        } else {
            return nodo.datos;
        }
    }
}
