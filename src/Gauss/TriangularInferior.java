package Gauss;

import Listas.Cola;
import Listas.Stack;

import static java.util.stream.IntStream.range;

/**
 * Created by Julian on 21/11/2016.
 */
public class TriangularInferior {
    private double[][] Lmatrix;
    private Cola colaInversas;

    public TriangularInferior(Stack pila, int n) {
        overloadProcessors(pila, n);
    }

    //esta funcion recibe dos valores que representan una matriz de escalonar
    //donde el numero representa n en e_n
    //y donde el valor representa el escalar
    //se puede visualizar mejor con la siguiente matriz
    //  1  0  0  0 0
    // e1  1  0  0 0
    // e2 e3  1  0 0
    // e4 e5 e6  1 0
    // e7 e8 e9 e10 1
    // e11...........
    //
    //el numero "lunumber" es el numero de e que se envia
    private void checkTuple(int numero, double valor, int filas) {

        int i = 0;
        int j = 0;
    //System.out.println(numero + "valor: " + valor);
        double[][] EMatrix = range(0, filas).mapToObj(u -> range(0, filas)
                .mapToDouble(v -> v == u ? 1 : 0).toArray())
                .toArray(double[][]::new);
    //System.out.println(numero);
        //aqui checa el numero y le asigna su espacio correspondiente
        if (numero == 1) { //linea 2
            i = 1;
            j = 1 - 1;
        } else if (numero < 4) { //linea 3
            i = 2;
            j = numero - 1 - 1;
        } else if (numero < 7) { //linea 4
            i = 3;
            j = numero - 3 - 1;
        } else if (numero < 11) { //linea 5
            i = 4;
            j = numero - 6 - 1;
        } else if (numero < 16){//linea 6
            i = 5;
            j = numero - 10 - 1;
        } else if (numero < 22){//linea 7
            i = 6;
            j = numero - 15 - 1;
        } else if (numero < 29){//linea 8
            i = 7;
            j = numero - 21 - 1;
        } else if (numero < 37) {//linea 9
            i = 8;
            j = numero - 28 - 1;
        } else if (numero < 46) {//linea 10
            i = 9;
            j = numero - 36 - 1;
        }

        //los guarda directamente con la inversa
        if (i == j)
            EMatrix[i][j] = 1 / valor;
        else
            EMatrix[i][j] = -valor;

//master imprimirMatriz
        Output.imprimirMatriz(EMatrix);
        colaInversas.enqueue(EMatrix);
    }

    //en algun momento freia intellij
    //comienza el algoritmo de derivacion de L desde En
    public void overloadProcessors(Stack pila, int n) {
        colaInversas = new Cola();
        System.out.println("\nAhora se deriva "+"\u001B[33m"+"L"+"\u001B[0m"
                +" desde las matrices de escalonar inversas "+"\u001B[33m"+"Ln"+"\u001B[0m"+":");
        while (!pila.isEmpty()) {
            //manda los 2 valores clave en forma de tupla, para que sean procesados
            checkTuple((int) pila.pop(), (double) pila.pop(), n);
        }

        //inicia la multiplicacion de elementales recursiva
        //todo cambiar la multiplicacion recursiva por insercion secuencial
        clusterMultiplication(colaInversas, (double[][]) colaInversas.dequeue());
    }

    public void clusterMultiplication(Cola cola, double[][] matriz){
        //multiplicacion de L recursiva
        //drena la cola hasta llegar al ultimo valor (solo faltaria la ultima multiplicacion)
        if(cola.size()>1){
            clusterMultiplication(cola, Operacion.multiplicacion((double[][])cola.dequeue(),matriz));
        }
        else {
            //se imprime la ultima multiplicacion
            //System.out.println("--------------H U H--------------");
            System.out.println("\u001B[33m"+"L ="+"\u001B[0m");
            double[][] L = Operacion.multiplicacion((double[][]) cola.dequeue(), matriz);
            Output.imprimirMatriz(L);
            Lmatrix = L;
        }
    }

    public double[][] getMatrix() {
        return Lmatrix;
    }
}
