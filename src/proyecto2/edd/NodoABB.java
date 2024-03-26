/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2.edd;

/**
 *
 * @author cesar
 */
public class NodoABB {
    String cedula;
    String[] datos;
    NodoABB izquierda, derecha;

    public NodoABB(String cedula, String[] datos) {
        this.cedula = cedula;
        this.datos = datos;
        izquierda = null;
        derecha = null;
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

    public NodoABB getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoABB izquierda) {
        this.izquierda = izquierda;
    }

    public NodoABB getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoABB derecha) {
        this.derecha = derecha;
    }

    
    
}
