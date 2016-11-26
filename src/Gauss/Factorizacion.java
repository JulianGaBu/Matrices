package Gauss;

import Listas.Stack;

import static java.util.stream.IntStream.range;

/**
 * Created by Julian on 14/10/2016.
 */
public class Factorizacion {
    public static int FILAS;
    public static int COLUMNAS;
    private static double[][] identidad;

    //vector b en Ax = b. Escrito directo, en necesidad de interfaz
    public static double[] vectorSolucion;

    //guarda los pasos de escalonar en una pila con 2 valores, el numero de la matriz y su valor modificado.
    //Se usaran las inversas de estas matrices para generar L en [(LUx = Lc) = Ax = b]
    public static Stack pila;
    //este numero representa el "espacio" desde el cual se generará la matriz. (Mas info en TriangularInferior)
    public static int luNumber;

    public static double[][][] PALU;

    //todo hacer algo con el det
    static double determinante;


    public static double[][][] escalonar(Matriz MATRIZ) {
        //se saca el arreglo matriz del objeto
        double[][] matriz = MATRIZ.getMATRIX();

        //se saca el tamaño de la matriz
        FILAS = matriz.length;
        COLUMNAS = matriz[0].length;

        //se saca su identidad
        identidad = MATRIZ.getIDENTIDAD();

        //todo: vector insertion
        vectorSolucion = new double[]{+85.0, -109.0, +201.0, -62.0, -37.0, -27.0, +38.0, +258.0, +77.0, -101.0};
        Output.imprimirVector(vectorSolucion);

        //genera un nuevo arraylist con los pasos
        pila = new Stack();
        //contador de lu. Sirve para poder insertar en orden las matrices de eliminacion
        luNumber = 1;

    //se declaran o derivan las matrices de factorizacion
        double[][] P = permutar(matriz,identidad);
        double[][] A = matriz;
        double[][] L = new double[FILAS][FILAS];
        double[][] U = new double[FILAS][FILAS];

        PALU = new double[][][]{P,matriz,};
        matriz = Operacion.multiplicacion(P,matriz);

        //se notifica si se usa una matriz de permutacion
        if(P.equals(identidad)){
            System.out.println("La matriz se multiplica por la matriz de permutacion \n"+"\u001B[33m"+"P = "+"\u001B[0m");
            Output.imprimirMatriz(P);
        }else //esto de aqui es un test message. ... tengo que debuggearlo
            System.out.println("\n\n\n\n--------------WHAT THE FUCK IS GOING ONN---------------------\n\n");
        //todo: debuggear esta ptm

        System.out.println("Ahora, sacamos la forma escalonada "+"\u001B[33m"+"U"+"\u001B[0m");

        int pivote = 0; //columna actual que se manipula. n-1 espacios por cada iteracion
        int renglon; //renglón actual. Se usará para recorrer las filas de la matriz
        boolean alto = false; //Se usara para detener la escalonación cuando este terminada

        //Inicio del algoritmo de U.
        for (int fila = 0; fila < FILAS && !alto; fila++) {
            //dafuq is this print
            //System.out.println();

            //Si el pivote esta en la ultima columna (o mas alla), se detiene la escalonacion
            if (COLUMNAS <= pivote) {
                alto = true;
                break;
            }

            renglon = fila;
            //Si algun espacio es 0, se lo salta
            while (!alto && matriz[renglon][pivote] == 0) {
                renglon++;
                //todo: pushea ahora un espacio vacio en caso de ser necesario
                //todo: solo es necesario en matrices obscenamente inestables, but still
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
        System.out.println("\u001B[33m"+"=U"+"\u001B[0m"+"\n");

        //todo el determinante solo saca la version escalonada permutada. Escalonar simple para esto
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
        //PALU PRINT
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
        MATRIZ.setPALU(PALU);
        return PALU;
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
        //a veces creo que mis test messages son demasiado psicoticos
        System.out.println("\n\n\n WHO DO YOU THINK YOU ARE \n\n");
        Output.imprimirMatriz(matriz);
        double aux = vectorSolucion[Ri];
        vectorSolucion[Ri] = vectorSolucion[Rj];
        vectorSolucion[Rj] = aux;

        //MATRICES DE ELIMINACION
        /*
        //genera la matriz de escalonar a partir de la identidad
        double[][] step = range(0, FILAS).mapToObj(j -> range(0, COLUMNAS)
                .mapToDouble(i -> i == j ? 1 : 0).toArray())
                .toArray(double[][]::new);
        for (int columna1 = 0; columna1 < step[0].length; columna1++) {
            double aux2 = step[Ri][columna1];
            step[Ri][columna1] = step[Rj][columna1];
            step[Rj][columna1] = aux2;

            System.out.println("STEP Columna "+columna1+" - Renglon "+Ri+" -a- Renglon "+Rj);
        }
        */

        //^Legacy code
        //Implementacion del nuevo algoritmo L
        /*pila.push(step);
        pila.push(luNumber);*/
        //todo: resolver el problema de permutacion intermedia. No urge rn, pero es necesario para sistemas grandes
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
        /*
        //genera la matriz de escalonar a partir de la identidad
        double[][] step = range(0, FILAS).mapToObj(j -> range(0, COLUMNAS)
                .mapToDouble(i -> i == j ? 1 : 0).toArray())
                .toArray(double[][]::new);
        for (int columna1 = 0; columna1 < step[0].length; columna1++)
            step[Ri][columna1] -= escalar * step[Rj][columna1];
        */

        //^Legacy code
        //Implementacion del nuevo algoritmo L
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
