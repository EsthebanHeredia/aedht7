package uvg.edu.gt.utils;

import uvg.edu.gt.modelo.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {

    public static List<Product> readProductsFromCSV(String csvFilePath) {
        List<Product> products = new ArrayList<>();

        try (InputStream inputStream = CSVReader.class.getClassLoader().getResourceAsStream(csvFilePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            // Leer la primera línea para obtener los nombres de las columnas
            String headerLine = reader.readLine();
            if (headerLine == null) {
                System.err.println("El archivo CSV está vacío");
                return products;
            }

            // Dividir la línea de encabezado por comas
            String[] headers = headerLine.split(",");

            // Mapear índices de columnas por nombre
            Map<String, Integer> headerMap = new HashMap<>();
            for (int i = 0; i < headers.length; i++) {
                headerMap.put(headers[i].trim(), i);
            }

            // Verificar que las columnas requeridas existen
            if (!headerMap.containsKey("SKU") || !headerMap.containsKey("Price_Retail") ||
                    !headerMap.containsKey("Price_Current") || !headerMap.containsKey("Product_Name") ||
                    !headerMap.containsKey("Category")) {
                System.err.println("El archivo CSV no contiene todas las columnas requeridas");
                return products;
            }

            // Leer las filas de datos
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    // Dividir la línea por comas, teniendo en cuenta posibles comas dentro de comillas
                    String[] values = parseCSVLine(line);

                    // Obtener los valores de las columnas específicas
                    String sku = values[headerMap.get("SKU")];
                    double priceRetail = parseDouble(values[headerMap.get("Price_Retail")]);
                    double priceCurrent = parseDouble(values[headerMap.get("Price_Current")]);
                    String productName = values[headerMap.get("Product_Name")];
                    String category = values[headerMap.get("Category")];

                    Product product = new Product(sku, priceRetail, priceCurrent, productName, category);
                    products.add(product);
                } catch (Exception e) {
                    // Ignoramos registros con errores de formato
                    System.err.println("Error en registro: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer archivo CSV: " + e.getMessage());
        }

        return products;
    }

    private static String[] parseCSVLine(String line) {
        List<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                // Toggle estado "entre comillas"
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                // Si encontramos una coma fuera de comillas, es un delimitador
                tokens.add(sb.toString().trim());
                sb.setLength(0);
            } else {
                // Cualquier otro carácter, agregarlo al token actual
                sb.append(c);
            }
        }

        // No olvidar el último token
        tokens.add(sb.toString().trim());

        return tokens.toArray(new String[0]);
    }

    private static double parseDouble(String value) {
        // Limpiamos caracteres no numéricos (como '$', ',', etc.)
        String cleaned = value.replaceAll("[^\\d.]", "");
        if (cleaned.isEmpty()) {
            return 0.0;
        }
        return Double.parseDouble(cleaned);
    }
}