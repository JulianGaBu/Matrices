package Gauss;

import static java.util.stream.IntStream.range;

/**
 * Created by Julian on 24/11/2016.
 */
public class Matriz {
    private double[][] MATRIX;
    public double[][] IDENTIDAD;
    private double[][][] PALU;

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
    //Crea identidad. Solo sirve con cuadradas?
    public Matriz(double[][] matriz){
        this.MATRIX = matriz;
        this.IDENTIDAD = range(0, matriz.length).mapToObj(j -> range(0, matriz.length)
                .mapToDouble(i -> i == j ? 1 : 0).toArray())
                .toArray(double[][]::new);
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

    public void setPALU(double[][][] PALU) {
        this.PALU = PALU;
    }

    public double[][] getIDENTIDAD() {
        return IDENTIDAD;
    }

    public int getRows(){
        return MATRIX.length;
    }

    public int getCols(){
        return MATRIX[0].length;
    }
}
