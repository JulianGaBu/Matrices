/**
 * Created by Julian on 12/10/2016.
 */
public class Nodo {
    Object dato;
    Nodo siguiente;

    Nodo(Object dato){
        this.dato = dato;
        this.siguiente = null;
    }

    Nodo(){

    }

    Nodo(Object dato, Nodo siguiente){
        this.dato = dato;
        this.siguiente = siguiente;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }
}
