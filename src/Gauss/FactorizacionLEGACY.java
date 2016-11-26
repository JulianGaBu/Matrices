package Gauss;

import Listas.Stack;

import static java.util.stream.IntStream.range;

/**
 * Created by Julian on 14/10/2016.
 */
public class FactorizacionLEGACY {

    public static double[][][] PALU;
    public static Stack pila;
    static double determinante;
    public static int luNumber;
    public static int nfilas;
    public static int mcolumnas;
    //guarda los pasos de escalonar en una pila con 2 valores, el numero de la matriz y su valor modificado.
    //Se usaran las inversas de estas matrices para generar L en [(LUx = Lc) = Ax = b]

    //TODO: IS FLOAT BETTER THAN DOUBLE???

    public static double[] vectorSolucion;
    //vector b en Ax = b. Escrito directo, en necesidad de interfaz

    public static void escalonar(double[][] matriz) {
        //se saca el tamaño de la matriz
        int FILAS = matriz.length;
        int COLUMNAS = matriz[0].length;
        nfilas = FILAS;
        mcolumnas = COLUMNAS;



        luNumber = 1;
        //todo: vector insertion
        vectorSolucion = new double[]{+85.0, -109.0, +201.0, -62.0, -37.0, -27.0, +38.0, +258.0, +77.0, -101.0};
        Output.imprimirVector(vectorSolucion);


        /*
        vectorSolucion = new double[FILAS];
        vectorSolucion[0] = 5.0;
        vectorSolucion[1] = -2.0;
        vectorSolucion[2] = 9.0;

        vectorSolucion[3] = 1;
        */

        //se saca su identidad
        final double[][] identidad = range(0, FILAS).mapToObj(j -> range(0, FILAS)
                .mapToDouble(i -> i == j ? 1 : 0).toArray())
                .toArray(double[][]::new);

        //se declaran o derivan las matrices del proceso
        double[][] P = permutar(matriz,identidad);
        //double[][] P = identidad;
        double[][] A = matriz;
        double[][] L = new double[FILAS][FILAS];
        double[][] U = new double[FILAS][FILAS];

        PALU = new double[][][]{P,matriz,};
        matriz = Operacion.multiplicacion(P,matriz);

        //se notifica si se usa una matriz de permutacion
        if(P.equals(identidad)){
            System.out.println("La matriz se multiplica por la matriz de permutacion \n"+"\u001B[33m"+"P = "+"\u001B[0m");
            Output.imprimirMatriz(P);
        }
        System.out.println("Ahora, sacamos la forma escalonada "+"\u001B[33m"+"U"+"\u001B[0m");



        pila = new Stack();
        //genera un nuevo arraylist con los pasos

        int pivote = 0; //Columna Actual que se manipula. n-1 espacios por cada iteracion




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
                //todo: pushea ahora un espacio vacio en caso de ser necesario
                //pila.push(identidad);
                //todo que hacer con los espacios que desaparecen? help me coba
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
                //intercambia la fila_i con la fila_j
                if(fila != renglon)
                    intercambiarRenglones(matriz, renglon, fila);

                //resta Matriz[renglon,pivote] multiplicada por la fila() de la fila renglon
                //matriz, lambda, Rj, Ri
                //ri - lambda*rj
                for(renglon = fila+1; renglon < FILAS; renglon++) {
                    sumarRenglones(matriz, (matriz[renglon][pivote]/matriz[fila][pivote]), fila, renglon);
                }
            }
        }
        //imprime el sistema Ax = b resuelto
        //todo : impresion de sistemas enteros
        //Matrix.printSystem(matriz,vectorSolucion);

        System.out.println("\u001B[33m"+"=U"+"\u001B[0m"+"\n");
        //saca la determinante. Multiplica las diagonales
        determinante = 1;
        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j< matriz[0].length; j++){
                if(i == j){
                    determinante *= matriz[i][j];
                }
            }
        }

        System.out.println("\u001B[36m"+"Determinante = "+determinante+"\u001B[0m");

        //llama al algoritmo de L
        L = TriangularInferior.overloadProcessors(pila);

        //FORMA FINAL
        PALU = new double[][][]{P,A,L,matriz};
        System.out.println("");
        for(int i = 0; i < 4; i++) {
            switch (i){
                case 0:
                    System.out.println("\n"+"\u001B[33m"+"P="+"\u001B[0m"+"");
                    break;
                case 1:
                    System.out.println("\n"+"\u001B[33m"+"A="+"\u001B[0m"+"");
                    break;
                case 2:
                    System.out.println("\n"+"\u001B[33m"+"L="+"\u001B[0m"+"");
                    break;
                case 3:
                    System.out.println("\n"+"\u001B[33m"+"U="+"\u001B[0m"+"");
                    break;
                default:
                    System.out.println("DAFUQ!");
            }
            Output.imprimirMatriz(PALU[i]);

        }
        Output.imprimirSistema(PALU[1],vectorSolucion);
        /*Matrix.imprimirVector(vectorSolucion);
        Matrix.imprimirVector(
                Matrix.matrizvector(PALU[2],
                        Matrix.matrizvector(PALU[0],vectorSolucion)));*/


    }
    //intercambia ri con rj
    static void intercambiarRenglones(double[][] matriz, int Ri, int Rj) {
        //Arreglo temporal con los valores a cambiar
        double[] cambio = new double[matriz[0].length];
        for (int columna1 = 0; columna1 < matriz[0].length; columna1++)
            cambio[columna1] = matriz[Ri][columna1];

        //Intercambio de renglones (cambia cada elemento de cada columna individualmente
        for (int columna1 = 0; columna1 < matriz[0].length; columna1++) {
            matriz[Ri][columna1] = matriz[Rj][columna1];
            matriz[Rj][columna1] = cambio[columna1];

            System.out.println("Columna "+columna1+" - Renglon "+Ri+" -a- Renglon "+Rj);
        }

        //Intercambio de renglones en b
        //Todo: implementar el vector b correctamente
//master imprimirMatriz
        System.out.println("WELL HOND ON A SECOND BEEYTCH\n\n\n WHO DO YOU THINK YOU AREE \n\n");
        Output.imprimirMatriz(matriz);
        double aux = vectorSolucion[Ri];
        vectorSolucion[Ri] = vectorSolucion[Rj];
        vectorSolucion[Rj] = aux;
        //imprimirMatriz(matriz);
        Output.imprimirMatriz(matriz);
        //System.out.println("lolklkkkkkkkkkkk");

    //MATRICES DE ELIMINACION
        //genera la matriz de escalonar a partir de la identidad
        double[][] step = range(0, nfilas).mapToObj(j -> range(0, mcolumnas)
                .mapToDouble(i -> i == j ? 1 : 0).toArray())
                .toArray(double[][]::new);
        for (int columna1 = 0; columna1 < step[0].length; columna1++) {
            double aux2 = step[Ri][columna1];
            step[Ri][columna1] = step[Rj][columna1];
            step[Rj][columna1] = aux2;

            System.out.println("STEP Columna "+columna1+" - Renglon "+Ri+" -a- Renglon "+Rj);
        }
        //...y la guarda en el arraylist
        //pasos.add(step);
        //todo check if this works
        /*pila.push(step);
        pila.push(luNumber);*/
    }

    //suma renglones multiplicados por escalares
    static void sumarRenglones(double[][] matriz, double escalar, int Rj, int Ri) {
        if(escalar < 0)
            System.out.println("R"+(Ri+1)+" + ("+(-escalar)+")R"+(Rj+1)+" -> R"+(Ri+1));
        else
            System.out.println("R"+(Ri+1)+" - ("+escalar+")R"+(Rj+1)+" -> R"+(Ri+1));
        for (int columna1 = 0; columna1 < matriz[0].length; columna1++)
            matriz[Ri][columna1] -= escalar * matriz[Rj][columna1];
        vectorSolucion[Ri] = vectorSolucion[Ri]-(escalar*vectorSolucion[Rj]);
        //imprimirMatriz(matriz);
        Output.imprimirMatriz(matriz);
    //MATRICES DE ELIMINACION
        //genera la matriz de escalonar a partir de la identidad
        double[][] step = range(0, nfilas).mapToObj(j -> range(0, mcolumnas)
                .mapToDouble(i -> i == j ? 1 : 0).toArray())
                .toArray(double[][]::new);
        for (int columna1 = 0; columna1 < step[0].length; columna1++)
            step[Ri][columna1] -= escalar * step[Rj][columna1];
        pila.push(-escalar);
        pila.push(luNumber);
        luNumber++;
    }

    //acomoda los pivotes para que esten en su valor mas alto
    static double[][] permutar(double[][] matriz, double[][] identidad) {
        int n = matriz.length;

        //va buscando el valor mayor de cada columna
        for (int i = 0; i < n; i++) {
            double maxm = matriz[i][i];
            int fila = i;
            for (int j = i; j < n; j++)
                if (matriz[j][i] > maxm) {
                    maxm = matriz[j][i];
                    fila = j;
                }
                //si encuentra uno mayor...
            //saca su matriz de permutacion desde la identidad
            if (i != fila) {
                double[] aux = identidad[i];
                identidad[i] = identidad[fila];
                identidad[fila] = aux;
            }
        }
        return identidad;
    }



}
