package Gauss;

import Listas.Cola;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

/**
 * Created by Julian on 22/11/2016.
 */
public class Operacion {

    public static double productoInterno(double[] a, double[] b) {
        return range(0, a.length).mapToDouble(i -> a[i] * b[i]).sum();
    }

    public static double[][] multiplicacion(double[][] A, double[][] B) {
        double[][] result = new double[A.length][B[0].length];
        double[] aux = new double[B.length];

        for (int j = 0; j < B[0].length; j++) {

            for (int k = 0; k < B.length; k++)
                aux[k] = B[k][j];

            for (int i = 0; i < A.length; i++)
                result[i][j] = productoInterno(A[i], aux);

        }
        return result;
    }

    public static Matriz producto(Matriz A, Matriz B) {
        return new Matriz(multiplicacion(A.getMATRIX(),B.getMATRIX()));
    }

    public static double[] matrizvector(double[][] A, double[] v) {
        double[] result = new double[v.length];
            for (int i = 0; i < A.length; i++)
                result[i] = productoInterno(A[i], v);

        return result;
    }


}
