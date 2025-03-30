package uvg.edu.gt.arbol;

import org.junit.Before;
import org.junit.Test;
import uvg.edu.gt.modelo.Product;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {
    private BinarySearchTree<Product> bst;

    @Before
    public void setUp() {
        bst = new BinarySearchTree<>();
    }

    @Test
    public void testAdd() {
        Product product1 = new Product("SKU1", 100.0, 90.0, "Product 1", "Category 1");
        Product product2 = new Product("SKU2", 200.0, 180.0, "Product 2", "Category 2");

        bst.add(product1);
        bst.add(product2);

        // Verificar que los productos fueron agregados correctamente
        assertEquals(2, bst.size());

        // Verificar que los productos están en el árbol
        assertNotNull(bst.search(new Product("SKU1", 0, 0, "", "")));
        assertNotNull(bst.search(new Product("SKU2", 0, 0, "", "")));
    }

    @Test
    public void testSearch() {
        Product product1 = new Product("SKU1", 100.0, 90.0, "Product 1", "Category 1");
        Product product2 = new Product("SKU2", 200.0, 180.0, "Product 2", "Category 2");

        bst.add(product1);
        bst.add(product2);

        // Buscar productos por SKU
        Product result1 = bst.search(new Product("SKU1", 0, 0, "", ""));
        assertNotNull(result1);
        assertEquals("SKU1", result1.getSku());

        Product result2 = bst.search(new Product("SKU2", 0, 0, "", ""));
        assertNotNull(result2);
        assertEquals("SKU2", result2.getSku());

        // Buscar un producto que no existe
        Product nonExistentProduct = new Product("SKU3", 0, 0, "", "");
        Product result3 = bst.search(nonExistentProduct);
        assertNull(result3);
    }
}