/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listas;

/**
 *
 * @author Julian
 */
public class Lista {
    
    protected Nodo inicio;
    protected Nodo ultimo;

    public boolean vacio()
    {
        return inicio== null;
    }

    public void insertaInicio(Object dato) {
        if (vacio()) {
            inicio = ultimo = new Nodo(dato);
        } else {
            inicio = new Nodo(dato, inicio);
        }
    }

    public void insertaFinal(Object dato) {
        if (vacio()) {
            inicio = ultimo = new Nodo(dato);
        } else {
            Nodo temp = new Nodo(dato);
            ultimo.setSiguiente(temp);
            ultimo = temp;
        }
    }

    public Object eliminaInicio() {
        Object eliminado = null;
        if (vacio()) {
            System.out.println("Lista vacia");
        } else {
            eliminado = inicio;
            inicio = inicio.siguiente;
        }
        return eliminado;
    }

    public Object eliminaFinal() {

        Object eliminado = null;
        Nodo anterior = null;
        Nodo actual = inicio;

        if (vacio()) {
            System.out.println("La lista esta vacia");
        } else if (actual.siguiente == null) {
            eliminado = inicio;
            inicio = null;

        } else {
            while (actual.siguiente != null) {
                anterior = actual;
                actual = actual.siguiente;
            }
            eliminado = anterior.getSiguiente();
            anterior.setSiguiente(null);
        }

        return eliminado;
    }

    public Nodo getInicio() {
        return inicio;
    }

 }
