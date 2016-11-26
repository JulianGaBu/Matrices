package Gauss;

import Listas.Cola;

/**
 * Created by Julian on 23/11/2016.
 */
public class Espacios {
    protected static Cola filas;
    public static void getEspacioFila(double[][][] PALU){
        filas = new Cola();
        double[][] U = PALU[3];
        for(int i = 0; i < U.length; i++){
            for(int j = 0; j < U[0].length; j++){
                if(U[i][j] == 0)
                    continue;
                else {
                    filas.enqueue(i);
                    break;
                }
            }
        }

        Gauss.Operacion.printEB(U, filas);
        //for(
    }
}
