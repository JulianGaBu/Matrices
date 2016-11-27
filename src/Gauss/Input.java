package Gauss;

import static java.util.Arrays.stream;

/**
 * Created by Julian on 27/11/2016.
 */
public class Input {
    String[] vectorX;
    int columnas;
    double[][] matriz;
    double[] vectorB;

    public Input(String variables, String sistema){
        this.variables(variables);
        this.parseSystem(sistema);
    }


    public void variables(String lista){
        //todo aceptar variables sin lista previa
        vectorX = lista.split("[(\\h?)([,]?)(\\h?)]");
        columnas = vectorX.length;
        System.out.println(columnas);
    }

    public void parseCommands(String string){
        //La estructura de los comandos sera simple.
        //El arbol de opciones no puede tener más de 3 niveles
        //Los separadores seran espacios SIEMPRE. usar esto para parsear
        //
        //  HELP
        //  ->Print Help
        //
        //  NEW
        // |    "MATRIX"
        // |      ->A
        // |            N
        // |            N M
        // |            N
        // |                M   //(unica excepcion xq la vida es dura)
        // |      ::INPUT
        // |
        // |    "SYSTEM"
        // |        VARS
        // |        ::INPUT
        // |
        // ->new Matriz(N,M,MATRIX::INPUT)
        // :>Input.parseSystem(VARS,INPUT)
        //
        //  GET
        // |    DET
        // |        ->"A"
        // |        ::INPUT
        // |    SPACE +
        // |      +>
        // |        BASE *
        // |        NULL *
        // |        ROW  *
        // |        COL  *
        // |          *>
        // |            ->"A"
        // |            ::INPUT
        // |
        // ->Gauss.getDet(A|::)
        // ->Espacios.get(+,*)
        //    todo: ^^^^
        //
        //  GAUSS
        // |    ->"A"
        // |    ::INPUT
        // |>JORDAN
        // |    ->"A"
        // |    ::INPUT
        // |
        // ->Gauss.Escalonar(A|::INPUT)
        // ->Gauss.Jordan(A|::INPUT)
        //
        //  FACTORIZAR
        // |    ->A
        // |    ::MATRIX
        // |
        // -> Factorizacion.PALU(A)
        // :> Factorizacion.PALU(MATRIX::INPUT)
        //
        // |MULTIPLICAR
        // |    AB
        // |    A B
        // |    AXB
        // |    ::MATRIX
        // |        ::MATRIX    TODO: PERMITIR ESTO?
        // |
        // ->Operacion.multiplicar(A,B)
        String[] comandos = string.split("\\h+");
        switch (comandos[0].toUpperCase()){
            case "HELP": //imprime comandos disponibles
                break;
            case "NEW":
                break;
            case "GET":
                break;
            case "GAUSS":
                break;
            case "FACTORIZAR":
                break;
            case "MULTIPLICAR":
                break;
            default:
                break;
        }
        //todo terminar
        //todo !!!!!!!!!!!!!!
    }

    public void parseSystem(String string){
        //AtomicInteger columnas = new AtomicInteger(0);
        //se va a dejar de usar, pero lo dejo como nota para terminar implementacion en el futuro
        //se deberia contar el numero de variables con el AtomicInteger
        int filas;

        //se divide cada linea del arreglo
        String[] lineas = string.split("\\n");
        System.out.println(string);
        System.out.println("PROBANDO DECONSTRUCCION");

        //se obtienen las filas y se instancia la matriz
        filas = lineas.length;
        matriz = new double[filas][columnas];
        vectorB = new double[filas];
        //stream(lineas).forEach(l -> {
        for(int i = 0; i < lineas.length; i++){ //loop de cada linea
            //System.out.println(lineas[i]);
            String[] ecuaciones = lineas[i].split("(\\h?)[=](\\h?)");
            //ecuacion[0] = sum(a_n X)  (parte izquierda)
            //ecuacion[1] = b_n         (parte derecha)

            //agrega la solucion a la cola de b
            vectorB[i] = Double.parseDouble(ecuaciones[1]);

            String[] terminos = ecuaciones[0].split("(\\h?)[+-](\\h?)");
            //ya divididos en ax de ax + by
            //(coeficiente variable)

            //int t se usa para mantener un "grupo de control" al pasar por la ecuacion
            //y asi se pueda empezar desde donde se dejo la anterior
            //todo: mejorar todo este algoritmo para aceptar cualquier orden de variables
            int t = 0;
            for(int j = 0; j < columnas; j++){ //loop cada coeficiente
                if(j == terminos.length)
                    break;
                String[] coeficientes = terminos[j].split("(?=[a-zA-Z])");
                //coeficientes[0] = coeficiente
                //coeficientes[1] = variable

                //busca hasta encontrar el lugar correcto de cada coeficiente
                for(int k = t; k < columnas; k++){

                     /*   if(k == columnas){
                            throw new InputMismatchException();
                        }*/
                    //checa si hay coeficiente
                    if(coeficientes.length>1) {
                        //si hay, lo manda a su espacio correspondiente
                        if (coeficientes[1].contains(vectorX[k])) {
                            matriz[i][k] = Double.parseDouble(coeficientes[0]);
                            t++;
                            break;
                        } else {
                            //error handling
                            //todo: exceptions
                            if(k == columnas-1)
                                System.out.println(coeficientes[1]+" is not an acceptable input");
                            //(o rellena en caso de no haver variable correcta)
                            matriz[i][k] = 0.0;
                            t++;
                            continue;
                        }
                    } else {
                        //si no hay, manda 1
                        if (coeficientes[0].contains(vectorX[k])) {
                            matriz[i][k] = (1.0);
                            t++;
                            break;
                        } else {
                            //error handling
                            //todo: exceptions
                            if(k == columnas-1)
                                System.out.println(coeficientes[0]+"is not an acceptable input");
                            matriz[i][k] = 0.0;
                            t++;
                            //if(
                            continue;
                        }
                    }
                }
            }
        }
        Output.imprimirSistema(matriz,vectorX,vectorB);
    }
}
