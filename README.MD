# AEDHT7 - Binary Search Tree for Product Management

## Descripción

Este proyecto implementa un Árbol Binario de Búsqueda (Binary Search Tree) para gestionar productos. Los productos se leen desde un archivo CSV y se almacenan en el árbol para permitir búsquedas rápidas por SKU. Además, proporciona funcionalidades para listar productos ordenados por precio en orden ascendente o descendente.

## Características

- Lectura de productos desde un archivo CSV.
- Inserción de productos en un Árbol Binario de Búsqueda.
- Búsqueda de productos por SKU.
- Listado de productos filtrados por SKU y ordenados por precio.
- Interfaz de usuario basada en consola.

## Estructura del Proyecto

- `src/main/java/uvg/edu/gt/Main.java`: Contiene la lógica principal del programa y la interfaz de usuario.
- `src/main/java/uvg/edu/gt/arbol/BinarySearchTree.java`: Implementación del Árbol Binario de Búsqueda.
- `src/main/java/uvg/edu/gt/modelo/Product.java`: Clase que representa un producto.
- `src/main/java/uvg/edu/gt/utils/CSVReader.java`: Utilidad para leer productos desde un archivo CSV.

## Instalación

1. Clona el repositorio:
   ```sh
   git clone https://github.com/EsthebanHeredia/aedht7.git
   ```
2. Navega al directorio del proyecto:
   ```sh
   cd aedht7
   ```
3. Asegúrate de tener [Maven](https://maven.apache.org/) instalado y configura el proyecto:
   ```sh
   mvn clean install
   ```

## Ejecución

Para ejecutar el programa principal, utiliza el siguiente comando:
```sh
mvn exec:java -Dexec.mainClass="uvg.edu.gt.Main"
```

## Uso

1. Al iniciar el programa, los productos se cargarán desde el archivo `data.csv`.
2. Selecciona una opción del menú:
    - **Buscar producto por SKU**: Ingresa el SKU del producto que deseas buscar.
    - **Listar productos ordenados por precio (ascendente)**: Lista los productos filtrados por el último SKU buscado en orden ascendente por precio.
    - **Listar productos ordenados por precio (descendente)**: Lista los productos filtrados por el último SKU buscado en orden descendente por precio.
    - **Salir**: Cierra la aplicación.

## Pruebas

El proyecto incluye pruebas unitarias para los métodos de inserción y búsqueda en el Árbol Binario de Búsqueda. Para ejecutar las pruebas, utiliza el siguiente comando:
```sh
mvn test
```

## Contribuciones

Las contribuciones son bienvenidas. Por favor, sigue estos pasos para contribuir:

1. Haz un fork del repositorio.
2. Crea una nueva rama para tu función (`git checkout -b feature/nueva-funcion`).
3. Realiza tus cambios y haz commit (`git commit -am 'Agrega nueva función'`).
4. Empuja tu rama (`git push origin feature/nueva-funcion`).
5. Abre un Pull Request.

## Autor

- **Estheban Heredia**
