/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2.edd;

/**
 *
 * @author cesar
 */
public class ListaReservas {
    NodoReserva cabeza;

    public ListaReservas() {
        this.cabeza = null;
    }

    public void agregar(String cedula, String[] datos) {
        NodoReserva nuevoNodo = new NodoReserva(cedula, datos);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoReserva temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevoNodo;
        }
    }

    public String[] buscarReserva(String cedula) {
        NodoReserva temp = cabeza;
        while (temp != null) {
            if (temp.cedula.equals(cedula)) {
                return temp.datos;
            }
            temp = temp.siguiente;
        }
        return null;
    }

    public void eliminar(String cedula) {
        if (cabeza == null) {
            return;
        }
        if (cabeza.cedula.equals(cedula)) {
            cabeza = cabeza.siguiente;
            return;
        }
        NodoReserva temp = cabeza;
        while (temp.siguiente != null) {
            if (temp.siguiente.cedula.equals(cedula)) {
                temp.siguiente = temp.siguiente.siguiente;
                return;
            }
            temp = temp.siguiente;
        }
    }
}
