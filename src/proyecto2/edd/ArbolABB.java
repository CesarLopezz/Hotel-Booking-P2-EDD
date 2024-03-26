/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2.edd;

/**
 *
 * @author cesar
 */
public class ArbolABB {
    NodoABB raiz;

    public ArbolABB() {
        raiz = null;
    }

    // Método para insertar un nodo en el árbol
    public void insertar(String cedula, String[] datos) {
        raiz = insertarRec(raiz, cedula, datos);
    }

    // Método auxiliar para insertar un nodo de manera recursiva
    private NodoABB insertarRec(NodoABB nodo, String cedula, String[] datos) {
        // Si el árbol está vacío, se crea un nuevo nodo
        if (nodo == null) {
            return new NodoABB(cedula, datos);
        }

        // Si la cédula es menor que la del nodo actual, se inserta en el subárbol izquierdo
        if (cedula.compareTo(nodo.cedula) < 0) {
            nodo.izquierda = insertarRec(nodo.izquierda, cedula, datos);
        }
        // Si la cédula es mayor que la del nodo actual, se inserta en el subárbol derecho
        else if (cedula.compareTo(nodo.cedula) > 0) {
            nodo.derecha = insertarRec(nodo.derecha, cedula, datos);
        }
        // Si la cédula ya existe, se actualizan los datos
        else {
            nodo.datos = datos;
        }

        return nodo;
    }

    // Método para buscar una reservación por cédula
    public String[] buscar(String cedula) {
        return buscarRec(raiz, cedula);
    }

    // Método auxiliar para buscar una reservación de manera recursiva
    private String[] buscarRec(NodoABB nodo, String cedula) {
        // Si el nodo es nulo, la reservación no existe
        if (nodo == null) {
            return null;
        }

        // Si la cédula es menor que la del nodo actual, se busca en el subárbol izquierdo
        if (cedula.compareTo(nodo.cedula) < 0) {
            return buscarRec(nodo.izquierda, cedula);
        }
        // Si la cédula es mayor que la del nodo actual, se busca en el subárbol derecho
        else if (cedula.compareTo(nodo.cedula) > 0) {
            return buscarRec(nodo.derecha, cedula);
        }
        // Si se encuentra la cédula, se retorna la información de la reservación
        else {
            return nodo.datos;
        }
    }
}
