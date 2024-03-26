/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2.edd;

/**
 *
 * @author cesar
 */
public class NodoReserva {
    String cedula;
    String[] datos;
    NodoReserva siguiente;

    public NodoReserva(String cedula, String[] datos) {
        this.cedula = cedula;
        this.datos = datos;
        this.siguiente = null;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String[] getDatos() {
        return datos;
    }

    public void setDatos(String[] datos) {
        this.datos = datos;
    }

    public NodoReserva getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoReserva siguiente) {
        this.siguiente = siguiente;
    }
    
    

}

