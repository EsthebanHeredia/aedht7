package uvg.edu.gt;

import uvg.edu.gt.arbol.BinarySearchTree;
import uvg.edu.gt.modelo.Product;
import uvg.edu.gt.utils.CSVReader;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static List<Product> allProducts;
    private static List<Product> filteredProducts;

    public static void main(String[] args) {
        // Cargar datos desde el CSV
        System.out.println("Cargando datos desde CSV...");
        allProducts = CSVReader.readProductsFromCSV("data.csv");

        System.out.println("Se han cargado " + allProducts.size() + " productos.");

        // Interfaz de usuario
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Buscar productos por SKU");
            System.out.println("2. Listar productos ordenados por precio (ascendente)");
            System.out.println("3. Listar productos ordenados por precio (descendente)");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1:
                    searchProductsBySku(scanner);
                    break;
                case 2:
                    listProductsByPrice(true);
                    break;
                case 3:
                    listProductsByPrice(false);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }

        scanner.close();
        System.out.println("¡Gracias por usar el sistema!");
    }

    private static void searchProductsBySku(Scanner scanner) {
        System.out.print("Ingrese el SKU del producto: ");
        String sku = scanner.nextLine();

        // Filtrar productos por SKU
        filteredProducts = allProducts.stream()
                .filter(product -> product.getSku().equals(sku))
                .collect(Collectors.toList());

        if (!filteredProducts.isEmpty()) {
            System.out.println("Se encontraron " + filteredProducts.size() + " productos con el SKU: " + sku);
        } else {
            System.out.println("No se encontró ningún producto con el SKU: " + sku);
        }
    }

    private static void listProductsByPrice(boolean ascending) {
        if (filteredProducts == null || filteredProducts.isEmpty()) {
            System.out.println("Primero busque los productos por SKU utilizando la opción 1.");
            return;
        }

        System.out.println("\nListado de productos (precio " + (ascending ? "ascendente" : "descendente") + "):");
        List<Product> sortedProducts = filteredProducts.stream()
                .sorted((p1, p2) -> ascending
                        ? Double.compare(p1.getPriceCurrent(), p2.getPriceCurrent())
                        : Double.compare(p2.getPriceCurrent(), p1.getPriceCurrent()))
                .collect(Collectors.toList());

        displayProducts(sortedProducts);
    }

    private static void displayProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No hay productos para mostrar.");
            return;
        }

        int count = 0;
        for (Product product : products) {
            System.out.println(product);
            count++;

            // Para evitar imprimir demasiados productos a la vez
            if (count % 20 == 0) {
                System.out.print("Presione Enter para continuar...");
                new Scanner(System.in).nextLine();
            }
        }
    }
}