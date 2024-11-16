import java.util.Random;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] tamaños = {10, 100, 1000, 5000, 10000, 30000};
        int rango = 30000;

        // Generar los arreglos según las especificaciones
        int[][] arreglos = generarArreglos(tamaños, rango);

        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Generar Arreglos aleatorios con diferente tamaño");
            System.out.println("2. Ordenar por los 4 métodos");
            System.out.println("3. Buscar valores con búsqueda binaria normal y recursiva");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();

            if (opcion == 1) {
                System.out.println("\nArreglos generados:");
                for (int i = 0; i < tamaños.length; i++) {
                    System.out.println("Tamaño " + tamaños[i] + ": " + java.util.Arrays.toString(arreglos[i]));
                }
            } else if (opcion == 2) {
                ordenarArreglos(arreglos, tamaños);
            } else if (opcion == 3) {
                buscarValores(arreglos, tamaños);
            } else if (opcion == 4) {
                System.out.println("Saliendo del programa...");
                break;
            } else {
                System.out.println("Opción no válida. Intente nuevamente.");
            }
        }

        scanner.close();
    }

    // Generar los arreglos cumpliendo con las especificaciones
    public static int[][] generarArreglos(int[] tamaños, int rango) {
        Random random = new Random();
        int[][] arreglos = new int[tamaños.length][];
        for (int i = 0; i < tamaños.length; i++) {
            arreglos[i] = new int[tamaños[i]];
            for (int j = 0; j < tamaños[i]; j++) {
                if (i > 0 && j < arreglos[i - 1].length) {
                    arreglos[i][j] = arreglos[i - 1][j];
                } else {
                    arreglos[i][j] = random.nextInt(rango) + 1;
                }
            }
        }
        return arreglos;
    }

    // Ordenar los arreglos y medir el tiempo de ejecución para los 4 métodos
    public static void ordenarArreglos(int[][] arreglos, int[] tamaños) {
        for (int i = 0; i < tamaños.length; i++) {
            int tamaño = tamaños[i];
            int[] arregloOriginal = arreglos[i];

            System.out.println("\nOrdenando arreglo de tamaño: " + tamaño);

            // Burbuja con ajuste
            int[] arregloBurbuja = arregloOriginal.clone();
            long inicioBurbuja = System.nanoTime();
            Ordenamiento.burbujaConAjuste(arregloBurbuja);
            long finBurbuja = System.nanoTime();
            System.out.println("Burbuja con ajuste: " + (finBurbuja - inicioBurbuja) + " nanosegundos");

            // Burbuja simple
            int[] arregloBurbujaSimple = arregloOriginal.clone();
            long inicioBurbujaSimple = System.nanoTime();
            Ordenamiento.burbuja(arregloBurbujaSimple);
            long finBurbujaSimple = System.nanoTime();
            System.out.println("Burbuja simple: " + (finBurbujaSimple - inicioBurbujaSimple) + " nanosegundos");

            // Selección
            int[] arregloSeleccion = arregloOriginal.clone();
            long inicioSeleccion = System.nanoTime();
            Ordenamiento.seleccion(arregloSeleccion);
            long finSeleccion = System.nanoTime();
            System.out.println("Selección: " + (finSeleccion - inicioSeleccion) + " nanosegundos");

            // Inserción
            int[] arregloInsercion = arregloOriginal.clone();
            long inicioInsercion = System.nanoTime();
            Ordenamiento.insercion(arregloInsercion);
            long finInsercion = System.nanoTime();
            System.out.println("Inserción: " + (finInsercion - inicioInsercion) + " nanosegundos");
        }
    }

    // Buscar valores usando búsqueda binaria normal y recursiva
    public static void buscarValores(int[][] arreglos, int[] tamaños) {
        int[] valoresBusqueda = {9, 98, 957, 4000, 9876, 29475};

        for (int i = 0; i < tamaños.length; i++) {
            int[] arregloOrdenado = arreglos[i].clone();
            Ordenamiento.burbujaConAjuste(arregloOrdenado); // Ordenar antes de buscar
            int valor = valoresBusqueda[i];

            System.out.println("\nBuscando en arreglo de tamaño " + tamaños[i] + ": Valor " + valor);

            // Búsqueda binaria normal
            long inicioBinaria = System.nanoTime();
            int posicion = Busqueda.busquedaBinaria(arregloOrdenado, valor);
            long finBinaria = System.nanoTime();
            System.out.println("Búsqueda binaria normal: " + (finBinaria - inicioBinaria) + " nanosegundos. Posición: " + posicion);

            // Búsqueda binaria recursiva
            long inicioBinariaRec = System.nanoTime();
            posicion = Busqueda.busquedaBinariaRecursiva(arregloOrdenado, valor, 0, arregloOrdenado.length - 1);
            long finBinariaRec = System.nanoTime();
            System.out.println("Búsqueda binaria recursiva: " + (finBinariaRec - inicioBinariaRec) + " nanosegundos. Posición: " + posicion);
        }
    }
}

// Métodos de ordenamiento
class Ordenamiento {
    public static void burbujaConAjuste(int[] arreglo) {
        boolean intercambio;
        for (int i = 0; i < arreglo.length - 1; i++) {
            intercambio = false;
            for (int j = 0; j < arreglo.length - 1 - i; j++) {
                if (arreglo[j] > arreglo[j + 1]) {
                    int temp = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = temp;
                    intercambio = true;
                }
            }
            if (!intercambio) break;
        }
    }

    public static void burbuja(int[] arreglo) {
        for (int i = 0; i < arreglo.length - 1; i++) {
            for (int j = 0; j < arreglo.length - 1 - i; j++) {
                if (arreglo[j] > arreglo[j + 1]) {
                    int temp = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = temp;
                }
            }
        }
    }

    public static void seleccion(int[] arreglo) {
        for (int i = 0; i < arreglo.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arreglo.length; j++) {
                if (arreglo[j] < arreglo[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arreglo[minIndex];
            arreglo[minIndex] = arreglo[i];
            arreglo[i] = temp;
        }
    }

    public static void insercion(int[] arreglo) {
        for (int i = 1; i < arreglo.length; i++) {
            int key = arreglo[i];
            int j = i - 1;
            while (j >= 0 && arreglo[j] > key) {
                arreglo[j + 1] = arreglo[j];
                j--;
            }
            arreglo[j + 1] = key;
        }
    }
}

// Métodos de búsqueda
class Busqueda {
    public static int busquedaBinaria(int[] arreglo, int valor) {
        int inicio = 0, fin = arreglo.length - 1;
        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;
            if (arreglo[medio] == valor) return medio;
            if (arreglo[medio] < valor) inicio = medio + 1;
            else fin = medio - 1;
        }
        return -1;
    }

    public static int busquedaBinariaRecursiva(int[] arreglo, int valor, int inicio, int fin) {
        if (inicio > fin) return -1;
        int medio = inicio + (fin - inicio) / 2;
        if (arreglo[medio] == valor) return medio;
        if (arreglo[medio] < valor)
            return busquedaBinariaRecursiva(arreglo, valor, medio + 1, fin);
        else
            return busquedaBinariaRecursiva(arreglo, valor, inicio, medio - 1);
    }
}

