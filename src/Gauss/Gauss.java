package Gauss;

import java.util.ArrayList;

/**
 * Created by Julian on 22/11/2016.
 */
public class Gauss {
    public static double[] vectorSolucion;
    public static double determinante;

    public static void jordan(Matriz MATRIZ) {

        int pivote = 0; //Columna Actual que se manipula. n-1 espacios por cada iteracion
        int FILAS = MATRIZ.getRows();
        int COLUMNAS = MATRIZ.getCols();
        double[][] matriz = MATRIZ.getMATRIX();


        vectorSolucion = new double[FILAS];

        vectorSolucion[0] = 6;
        vectorSolucion[1] = 7;
        //vectorSolucion[2] = 22.0;


        int renglon; //renglón actual. Se usará para recorrera las filas de la matriz
        boolean alto = false; //Se usara para detener la escalonar cuando este terminada


        for (int fila = 0; fila < FILAS && !alto; fila++) {
            //printSystem(matriz);
            System.out.println();

            //Si el pivote esta en la ultima columna (o mas alla), se detiene la escalonar
            if (COLUMNAS <= pivote) {
                alto = true;
                break;
            }

            renglon = fila;
            //Si algun espacio es 0, se lo salta
            while (!alto && matriz[renglon][pivote] == 0) {
                renglon++;
                //Si llega al final y no encuenra un nuevo pivote
                if (FILAS == renglon) {
                    //reinicia las filas y mueve el pivote a la derecha
                    renglon = fila;
                    pivote++;

                    //detiene el ciclo si el pivote llega a la ultima columna
                    if (COLUMNAS == pivote) {
                        alto = true;
                        break;
                    }
                }
            }

            //Operaciones elementales de renglon
            if (!alto) {
                //Si algun espacio era 0, la matriz intercambia los renglones para poder escalonar
//                intercambiarRenglones(matriz, renglon, fila);
                //intercambia la fila_i con la fila_j
                if(fila != renglon)
                    intercambiarRenglones(matriz, renglon, fila);

                //IMPORTANTE, NO BORRAR
                if (matriz[fila][pivote] != 0) {
                    multiplicarRenglones(matriz, fila, 1.0f / matriz[fila][pivote]);
                    //Multiplica la fila por la inversa del pivote
                }

                //TAMPOCO BORRAR ESTO
                //ES LA VERSION COMPLETA DE TODO GAUSS-JORDAN
                for (renglon = 0; renglon < FILAS; renglon++) {
                    //Todo: Algoritmo de euclides para determinar la mejor fila posible
                    if (renglon != fila) {
                        sumarRenglones(matriz, matriz[renglon][pivote], fila, renglon);
                        //resta Matriz[renglon,pivote] multiplicada por la fila() de la fila renglon
                        //matriz, lambda, Rj, Ri
                        //ri - lambda*rj

                    }
                }
            }
        }
    }
    //intercambia ri con rj
    static void intercambiarRenglones(double[][] matriz, int Ri, int Rj) {
        //Arreglo temporal con los valores a cambiar
        double[] cambio = new double[matriz[0].length];

        //llena el arreglo
        for (int columna1 = 0; columna1 < matriz[0].length; columna1++)
            cambio[columna1] = matriz[Ri][columna1];
        //si las filas no son las mismas (si es posible continuar la escalonar de manera normal)
        if(Ri != Rj)
            System.out.println("Intercambiando fila "+(Ri+1)+" con fila "+(Rj+1));

        //Intercambio de renglones (cambia cada elemento de cada columna individualmente
        for (int columna1 = 0; columna1 < matriz[0].length; columna1++) {
            matriz[Ri][columna1] = matriz[Rj][columna1];
            matriz[Rj][columna1] = cambio[columna1];

            System.out.println("Columna "+columna1+" - Renglon "+Ri+" -a- Renglon "+Rj);
        }

        //Intercambio de renglones en b
        //Todo: implementar el vector b correctamente
            Output.imprimirMatriz(matriz);
            double aux = vectorSolucion[Ri];
            vectorSolucion[Ri] = vectorSolucion[Rj];
            vectorSolucion[Rj] = aux;
    }

    static void multiplicarRenglones(double[][] matriz, int Ri, double lambda) {
        //Parte que hace que esto sea GAUSS-JORDAN, y no solo GAUSS
        if(lambda!=1)
            System.out.println("TIMEFORLANMBDA BABAAY");
        System.out.println("("+lambda+")R"+(Ri+1)+" -> R"+(Ri+1));
        for (int columna1 = 0; columna1 < matriz[0].length; columna1++)
            matriz[Ri][columna1] *= lambda;
        if(lambda!=1) {
            vectorSolucion[Ri] = (vectorSolucion[Ri]*lambda);
            Output.imprimirMatriz(matriz);
        }
    }

    static void sumarRenglones(double[][] matriz, double lambda, int Rj, int Ri) {
        if(lambda < 0)
            System.out.println("R"+(Ri+1)+" + ("+(-lambda)+")R"+(Rj+1)+" -> R"+(Ri+1));
        else
            System.out.println("R"+(Ri+1)+" - ("+lambda+")R"+(Rj+1)+" -> R"+(Ri+1));
        for (int columna1 = 0; columna1 < matriz[0].length; columna1++)
            matriz[Ri][columna1] -= lambda * matriz[Rj][columna1];
        vectorSolucion[Ri] = vectorSolucion[Ri]-(lambda*vectorSolucion[Rj]);
        Output.imprimirMatriz(matriz);
    }

    public static void elimination(double[][] matriz) {

        int pivote = 0; //Columna Actual que se manipula. n-1 espacios por cada iteracion
        int FILAS = matriz.length;
        int COLUMNAS = matriz[0].length;


        vectorSolucion = new double[FILAS];

        vectorSolucion[0] = 6;
        vectorSolucion[1] = 7;
        //vectorSolucion[2] = 22.0;


        int renglon; //renglón actual. Se usará para recorrera las filas de la matriz
        boolean alto = false; //Se usara para detener la escalonar cuando este terminada


        for (int fila = 0; fila < FILAS && !alto; fila++) {
            //printSystem(matriz);
            System.out.println();

            //Si el pivote esta en la ultima columna (o mas alla), se detiene la escalonar
            if (COLUMNAS <= pivote) {
                alto = true;
                break;
            }

            renglon = fila;
            //Si algun espacio es 0, se lo salta
            while (!alto && matriz[renglon][pivote] == 0) {
                renglon++;
                //Si llega al final y no encuenra un nuevo pivote
                if (FILAS == renglon) {
                    //reinicia las filas y mueve el pivote a la derecha
                    renglon = fila;
                    pivote++;

                    //detiene el ciclo si el pivote llega a la ultima columna
                    if (COLUMNAS == pivote) {
                        alto = true;
                        break;
                    }
                }
            }

            //Operaciones elementales de renglon
            if (!alto) {
                if(fila != renglon)
                    intercambiarRenglones(matriz, renglon, fila);
                for(renglon = fila+1; renglon < FILAS; renglon++) {
                    sumarRenglones(matriz, (matriz[renglon][pivote]/matriz[fila][pivote]), fila, renglon);
                }
            }
        }
        //saca la determinante. Multiplica las diagonales
        determinante = 1;
        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j< matriz[0].length; j++){
                if(i == j){
                    determinante *= matriz[i][j];
                }
            }
        }

    }
    //intercambia ri con rj
}
