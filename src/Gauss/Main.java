package Gauss;


import Listas.Cola;

/**
 * Created by Julian on 14/10/2016.
 */
public class Main {
    public static void main(String[] args) {
        double[][] matest = {
                {1, 2, -1, -4},
                {2, 3, -1, -11},
                {-2, 0, -3, 22}
        };
        double[][] matrix2 = {
                {1, 1, 0, 1, 4},
                {1, 2, 1, 1, 6},
                {0, 1, 1, 1, 3},
                {2, 2, 0, 1, 7}
        };
        double[][] libroCoba2 = {
                {2,  -5,  3, -4, 2},
                {3,  -7,  2, -5, 4},
                {5, -10, -5, -4, 7}
        };
        double[][] libroCoba = {
                {8, 2, -2,  8},
                {2, 1,  9, 12},
                {1,-8,  3, -4}
        };
        double[][] b = {{11.0, 9, 24, 2}, {1.0, 5, 2, 6}, {3.0, 17, 18, 1},
                {2.0, 5, 7, 1}};

        double[][] A = {
                {2, -3, 1, 5},
                {-1,-2, 0, 3},
                {6,  7, 4, 8}
        };
        double[][] B = {
                {2,  9,  10, 5},
                {11,-3,  12,-4},
                {6, -2, 4, 7},
                {-1,  1,  0,  8}};
        double[][] a = {{1.0, 3, 5}, {2.0, 4, 7}, {1.0, 1, 0}};
        double[][] tester ={
                {2, 1, 1},
                {4,-6, 0},
                {-2,7, 2}};

        double[][] scaryM = {
                {- 8.0, + 5.0, - 6.0, + 4.0, + 1.0, - 8.0, + 2.0, - 7.0, + 3.0, - 3.0},
                {- 7.0, - 5.0, - 5.0, + 3.0, - 3.0, - 2.0, + 3.0, - 1.0, + 2.0, + 9.0},
                {- 3.0, + 5.0, - 4.0, - 2.0, + 2.0, - 1.0, + 4.0, + 7.0, - 6.0, - 7.0},
                {- 2.0, - 4.0, + 5.0, + 8.0, - 7.0, - 1.0, + 2.0, - 6.0, - 5.0, - 4.0},
                {- 8.0, - 8.0, + 2.0, - 2.0, - 7.0, - 5.0, + 1.0, - 5.0, + 5.0, - 1.0},
                {+ 5.0, + 6.0, + 7.0, - 5.0, - 6.0, + 4.0, + 2.0, + 4.0, - 9.0, + 5.0},
                {+ 6.0, - 4.0, + 9.0, + 8.0, + 6.0, + 2.0, - 5.0, + 6.0, - 5.0, - 3.0},
                {- 3.0, + 5.0, - 6.0, - 2.0, + 8.0, - 4.0, - 3.0, + 4.0, - 8.0, - 1.0},
                {- 1.0, + 7.0, - 2.0, - 4.0, - 8.0, + 4.0, - 9.0, - 5.0, - 6.0, - 3.0},
                {- 6.0, - 1.0, - 4.0, - 5.0, - 8.0, + 8.0, + 8.0, - 5.0, - 7.0, + 4.0}};
        Matriz matrizTest = new Matriz(scaryM);

        double[][] matrixGerry = new double[3][3];
        double valor = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrixGerry[i][j]=valor;
                valor++;
            }
        }
        //Matrix.imprimirMatriz(Matrix.multiplicacion(A,B));
        //double[][] test = Sustitucion.getIdentidad(4);
        //Factorizacion.imprimirMatriz(test);
        System.out.println("\u001B[33m"+"A ="+"\u001B[0m");
        //Output.imprimirMatriz(matrixGerry);
        Cola ass = new Cola();
        ass.enqueue(0);
        ass.enqueue(1);
        ass.enqueue(2);
        Matriz matriz = new Matriz(b);

        //Gauss.elimination(a);
        System.out.println("REALIZANDO ELIMINACION");
        //Gauss.jordan(b);
        //Gauss.jordan(tester);
        Input input = new Input("x,y,z,w","3w=1\n12x + 4y + 5z +3w = 13\n3y+3w = 12\nz = 1");

        //Input.parser("12x + 4y + 5z = 13\n3x+3y = 12");
        //Factorizacion.escalonar(matriz);



        //Matrix.imprimirHorizontal(b,ass);

    }
}
