package Listas;

/**
 * Created by Julian on 22/11/2016.
 */
public class Stack {

    protected Lista pilaLista;
    protected int cont;

    public Stack() {
        pilaLista = new Lista();
        cont = 0;
    }

    public void push(Object x) {
        pilaLista.insertaInicio(x);
        cont++;
    }

    public Object pop() {

        Object eliminado = pilaLista.eliminaInicio();
        if (eliminado != null) {
            cont--;
        }
        return ((Nodo) eliminado).getDato();
    }

    public Object top() {
        return pilaLista.getInicio();
    }

    public int size() {
        return cont;
    }

    public boolean isEmpty() {
        return pilaLista.vacio();
    }

}
