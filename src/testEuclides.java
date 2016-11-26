import java.util.Scanner;

/**
 * Created by Julian on 13/10/2016.
 */
public class testEuclides {
    public static void main(String[] args) {
        int mayor, menor, mod=0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserta tu 1er numero");
        mayor = scanner.nextInt();
        System.out.println("Inserta tu 2o numero");
        menor = scanner.nextInt();
        scanner.close();
        if(menor > mayor){
            int aux = menor;
            menor = mayor;
            mayor = aux;
        }
        while(mayor % menor!= 0){   //Algoritmo de Euclides
            mod = mayor % menor;    // para Combinaciones Lineales
            mayor = menor;
            menor = mod;
        }

        System.out.println("\n"+mod);
    }
}
