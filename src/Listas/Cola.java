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
public class Cola {
    
    protected Lista queuelista;
    protected int cont;
    public Cola(){
        queuelista = new Lista();
    }

    public void enqueue(Object dato) {
        queuelista.insertaFinal(dato);
        cont++;
    }

    public Object dequeue() {
             Object eliminado;
        eliminado = queuelista.eliminaInicio();
        if(eliminado!=null){
            cont--;
            eliminado = ((Nodo)eliminado).getDato();
        }
        return eliminado;
    }

    public int size() {
       return cont;
    }

    public Object front() {
         Object frente = queuelista.getInicio();
        if(frente != null){
            frente = ((Nodo)frente).getDato();
        }
        return frente;
    }

    public boolean isEmpty() {
        return queuelista.vacio();
    }
    
}
