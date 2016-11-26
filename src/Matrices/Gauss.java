package Matrices;


/**
 * Created by Julian on 13/10/2016.
 */
public class Gauss {
    /*ColaListaSimple trianguloMenor;
    Matriz matriz;

    public Gauss(ColaListaSimple trianguloMenor, Matriz matriz) {
        this.trianguloMenor = trianguloMenor;
        this.matriz = matriz;
    }*/

    /*public void getRowOperation(Tiles tile){
        int coeficiente = tile.getNumero();
        int row = tile.getPosRow();
        int col = tile.getPosCol();
        int rowsum = 0;
        int bestRow = 0;
        int aux = 0;
        for(int i = 0; i<matriz.rango; i++){
                aux = euclides(coeficiente,matriz.get(i,col).getNumero());
                if((matriz.getRowSum(i)+(aux-1*matriz.get(i,col).getNumero()))>rowsum){
                    rowsum = matriz.getRowSum(i);
                    bestRow = i;
                }
                System.out.println(i + " by " + aux);

        }
        System.out.println(bestRow + " by " + aux);
    }*/

    public int euclides(int num1, int num2){
        int mayor = num1;
        int menor = num2;
        int mod = 0;
        if(menor > mayor){
            int aux = menor;
            menor = mayor;
            mayor = aux;
        }
        if(menor == 1)
            return mayor;
        while(mayor % menor!= 0){   //Algoritmo de Euclides
            mod = mayor % menor;    // para Combinaciones Lineales
            mayor = menor;
            menor = mod;
        }
        return mod;
    }
}
