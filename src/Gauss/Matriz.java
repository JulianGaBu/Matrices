package Gauss;

import static java.util.stream.IntStream.range;

/**
 * Created by Julian on 24/11/2016.
 */
public class Matriz {
    private double[][] MATRIX;
    private double[][][] PALU;
    public double[][] IDENTIDAD;

    //Matriz cuadrada
    public Matriz(int n){
        this.MATRIX = new double[n][n];
        this.IDENTIDAD = range(0, n).mapToObj(j -> range(0, n)
                .mapToDouble(i -> i == j ? 1 : 0).toArray())
                .toArray(double[][]::new);
    }

    //Matriz rectangular
    public Matriz(int n, int m){
        this.MATRIX = new double[n][m];
    }

    //Transferencia de matriz
    public Matriz(double[][] matriz){
        this.MATRIX = matriz;
    }

    //PALU MFCKR
    public Matriz(double[][][] PALU){
        this.PALU = PALU;
    }

    public double[][] getMATRIX() {
        return MATRIX;
    }

    public double[][][] getPALU() {
        return PALU;
    }
}
