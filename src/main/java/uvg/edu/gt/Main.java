package uvg.edu.gt;

import uvg.edu.gt.arbol.BinarySearchTree;
import uvg.edu.gt.modelo.Product;
import uvg.edu.gt.utils.CSVReader;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static BinarySearchTree<Product> productsBySku = new BinarySearchTree<>();

    public static void main(String[] args) {
        // Cargar datos desde el CSV
        System.out.println("Cargando datos desde CSV...");
        List<Product> products = CSVReader.readProductsFromCSV("data.csv");

        // Agregar productos al árbol
        for (Product product : products) {
            productsBySku.add(product);
        }

        System.out.println("Se han cargado " + productsBySku.size() + " productos.");

        // Interfaz de usuario
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Buscar producto por SKU");
            System.out.println("2. Listar productos ordenados por SKU (ascendente)");
            System.out.println("3. Listar productos ordenados por SKU (descendente)");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1:
                    searchProductBySku(scanner);
                    break;
                case 2:
                    listProductsAscending();
                    break;
                case 3:
                    listProductsDescending();
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

    private static void searchProductBySku(Scanner scanner) {
        System.out.print("Ingrese el SKU del producto: ");
        String sku = scanner.nextLine();

        // Crear un producto temporal con solo el SKU para la búsqueda
        Product searchKey = new Product(sku, 0, 0, "", "");
        Product foundProduct = productsBySku.search(searchKey);

        if (foundProduct != null) {
            System.out.println("\nProducto encontrado:");
            System.out.println("SKU: " + foundProduct.getSku());
            System.out.println("Nombre: " + foundProduct.getProductName());
            System.out.println("Categoría: " + foundProduct.getCategory());
            System.out.println("Precio actual: $" + foundProduct.getPriceCurrent());
            System.out.println("Precio retail: $" + foundProduct.getPriceRetail());
        } else {
            System.out.println("No se encontró ningún producto con el SKU: " + sku);
        }
    }

    private static void listProductsAscending() {
        System.out.println("\nListado de productos (SKU ascendente):");
        List<Product> sortedProducts = productsBySku.getInOrderList();
        displayProducts(sortedProducts);
    }

    private static void listProductsDescending() {
        System.out.println("\nListado de productos (SKU descendente):");
        List<Product> sortedProducts = productsBySku.getReverseInOrderList();
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