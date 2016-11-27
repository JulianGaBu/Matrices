package Gauss;

import Listas.Cola;

import java.util.ArrayList;
import java.util.InputMismatchException;

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
        this.parser2(sistema);
    }

    public void parser(String string){
        //AtomicInteger columnas = new AtomicInteger(0);
        //se va a dejar de usar, pero lo dejo como nota para terminar implementacion en el futuro
        //se deberia contar el numero de variables con el AtomicInteger
        int filas;
        Cola colaVectorB = new Cola();
        Cola colaVectorX = new Cola();
        Cola elementos = new Cola();
        ArrayList<String> matrix = new ArrayList<>();

        //se divide cada linea del arreglo
        String[] lineas = string.split("\\n");
        System.out.println(string);
        System.out.println("PROBANDO DECONSTRUCCION");

        //se obtienen las filas y se instancia la matriz
        filas = lineas.length;
        matriz = new double[filas][columnas];

        stream(lineas).forEach(l -> {
            System.out.println(l);
            String[] ecuaciones = l.split("(\\h?)[=](\\h?)");
            //ecuacion[0] = sum(a_n X)  (parte izquierda)
            //ecuacion[1] = b_n         (parte derecha)

            colaVectorB.enqueue(ecuaciones[1]); //agrega la solucion a la cola de b

            String[] terminos = ecuaciones[0].split("(\\h?)[+-](\\h?)");

            //obtiene el numero de columas mayor para obtener m
            if(columnas < terminos.length) {
                columnas = terminos.length;
            }

            //ya divididos en ax de ax + by
            //(coeficiente variable)
            stream(terminos).forEach(t -> {
                System.out.println(t);
                String[] coeficientes = t.split("(?=[a-zA-Z])");
                //coeficientes[0] = coeficiente
                //coeficientes[1] = variable

                //checa si se repite el vector en la cola
                for(int i = 0; i < columnas; i++){
                    //si la variable en la ecuacion esta en la lista de variables
                    if(coeficientes[1] == vectorX[i]){
                        break;
                    }else if (i == columnas-1){
        //error de input
                        throw new InputMismatchException();
                    }
                }

                //checa que haya coeficiente. Si no hay, manda un 1
                if(coeficientes.length>1)
                    elementos.enqueue(Double.parseDouble(coeficientes[0]));
                else
                    elementos.enqueue(1.0);

            });

            //System.out.println(colaVectorB.dequeue());
        });

        //crea la matriz
        double[][] matriz = new double[filas][columnas];
        for(int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                matriz[i][j] = (double)elementos.dequeue();
            }
        }

        //crea el vector solucion
        double[] vectorSolucion = new double[colaVectorB.size()];
        int i = 0;
        System.out.println(colaVectorB.size());
        while(!colaVectorB.isEmpty()){
            vectorSolucion[i] = Double.parseDouble((String)colaVectorB.dequeue());
            i++;
        }

        //crea el vector x

        Output.imprimirMatriz(matriz);
        Output.imprimirSistema(matriz,vectorSolucion);

    }

    public void variables(String lista){
        //todo aceptar variables sin lista previa
        vectorX = lista.split("[(\\h?)([,]?)(\\h?)]");
        columnas = vectorX.length;
        System.out.println(columnas);
    }

    public void parser2(String string){
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
