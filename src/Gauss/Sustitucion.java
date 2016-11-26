package Gauss;

import java.util.ArrayList;

import static java.util.stream.IntStream.range;

/**
 * Created by Julian on 21/11/2016.
 */
public class Sustitucion {
    double[][] matriz;
    double[][] matrizEscalonada;
    ArrayList<double[][]> matricesL;

    //Genera una matriz con una diagonal de 1s. Todos los demas valores son 0
    public static double[][] generarIdentidad(int rows, int cols){
        double[][] matrizId = new double[rows][cols];
        for(int i = 0; i<rows; i++){
            for(int j = 0; j<cols; j++){
                //si es una diagonal, le da 1. si no, le da 0
                if(i==j)
                    matrizId[i][j] = 1;
                else
                    matrizId[i][j] = 0;
            }
        }
        return matrizId;
    }

    //Genera la identidad desde un Stream, usando expresiones lambda
    //(me tomo toda la noche aprender esta ptm)
    public static double[][] getIdentidad(int n){
        double[][] identidad =
        range(0, n).parallel()
                .mapToObj(j -> range(0, n)
                        .mapToDouble(i -> i == j ? 1 : 0)
                        .toArray())
                .toArray(double[][]::new);
        return identidad;
    }

    public void backSubstitution(){
        for(int i = 0; i < matricesL.size(); i++){

        }
    }

}
