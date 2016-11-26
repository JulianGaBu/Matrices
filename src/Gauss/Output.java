package Gauss;

import Listas.Cola;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Arrays.stream;

/**
 * Created by Julian on 25/11/2016.
 */
public class Output {

    public static void imprimirVector(double[] vector){
        stream(vector).forEach(a -> {
            System.out.printf(Locale.US, "%5.2f ", a);
            System.out.println();
        });
        System.out.println();

    }

    public static void imprimirSistema(double[][] matriz, double[] solucion) {
        System.out.print("[");
            AtomicInteger index = new AtomicInteger(-1);
            stream(matriz).forEach(a -> {
                stream(a).forEach(n -> {
                    //System.out.printf("%5.2f ", n);
                    if(n ==1) System.out.printf("\u001B[31m"+"%5.2f ", n);
                    else if(n==0) System.out.printf("\u001B[0m"+"%5.2f ", n);
                    else System.out.printf("\u001B[34m"+"%5.2f ", n);
                });
                System.out.println("\u001B[0m"+"][" + solucion[index.incrementAndGet()] + "]");
                System.out.print("[");
            });
    }

    public static void imprimirMatriz(double[][] m) {

        stream(m).forEach(a -> {
            System.out.print("[");
            //cada iteracion de este stream representa un renglon entero en si
            stream(a).forEach(n -> {
                //y este una columna. n es el valor dentro del renglon, columna
                if(n ==1) System.out.printf("\u001B[31m"+"%5.2f ", n);
                    else if(n==0) System.out.printf("\u001B[0m"+"%5.2f ", n);
                    else System.out.printf("\u001B[34m"+"%5.2f ", n);
            });
                System.out.println("\u001B[0m"+"]");
        });
        System.out.println();
    }

    public static void imprimirHorizontal(double[][] m, Cola cola) {
        System.out.print("[");

        AtomicInteger index = new AtomicInteger(-1);
        stream(m).forEach(a -> {
            if (index.incrementAndGet() == 2) {
                System.out.print("\u001B[31m");
            }
            stream(a).forEach(n -> {

                System.out.printf("%5.2f ", n);
            });
            System.out.println("]");
            System.out.print("\u001B[0m" + "[");
        });
        System.out.println();
    }
}
