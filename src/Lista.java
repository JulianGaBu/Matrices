/**
 * Created by Julian on 12/10/2016.
 */
public class Lista {
    Nodo inicio;

    public void prepend(Object dato){ //insertar al inicio
        if(inicio == null) {
            inicio = new Nodo(dato);
        }else{
            Nodo aux = inicio;
            inicio = new Nodo(dato, aux);
        }
    }

    public void add(Object dato){ //insertar al final
        if(inicio == null){
            inicio = new Nodo(dato);
        }else{
            Nodo actual = inicio;
            while(actual.getSiguiente() != null){
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(new Nodo(dato));
        }

    }

    public Object getAt(int index){
        if(index <0 || inicio == null) return null;
        Nodo actual = inicio;
        for(int i = 0; i<index; i++){
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

}
