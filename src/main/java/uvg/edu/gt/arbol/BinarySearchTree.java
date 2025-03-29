package uvg.edu.gt.arbol;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Implementación genérica de un Árbol Binario de Búsqueda.
 * Basado en el concepto presentado en el libro Java Structures, capítulo 12.
 */
public class BinarySearchTree<E extends Comparable<E>> {
    protected BSTNode<E> root;
    protected int count;

    public BinarySearchTree() {
        root = null;
        count = 0;
    }

    /**
     * Agrega un elemento al árbol.
     * @param value el elemento a agregar
     */
    public void add(E value) {
        root = addRecursive(root, value);
        count++;
    }

    /**
     * Método recursivo para agregar un elemento.
     */
    private BSTNode<E> addRecursive(BSTNode<E> current, E value) {
        if (current == null) {
            return new BSTNode<>(value);
        }

        int compareResult = value.compareTo(current.getData());

        if (compareResult < 0) {
            current.setLeft(addRecursive(current.getLeft(), value));
        } else if (compareResult > 0) {
            current.setRight(addRecursive(current.getRight(), value));
        } else {
            // El valor ya existe, lo reemplazamos
            current.setData(value);
        }

        return current;
    }

    /**
     * Busca un elemento en el árbol.
     * @param value el elemento a buscar
     * @return el elemento encontrado o null si no existe
     */
    public E search(E value) {
        return searchRecursive(root, value);
    }

    /**
     * Método recursivo para buscar un elemento.
     */
    private E searchRecursive(BSTNode<E> current, E value) {
        if (current == null) {
            return null;
        }

        int compareResult = value.compareTo(current.getData());

        if (compareResult == 0) {
            return current.getData();
        } else if (compareResult < 0) {
            return searchRecursive(current.getLeft(), value);
        } else {
            return searchRecursive(current.getRight(), value);
        }
    }

    /**
     * Recorrido inorder del árbol (ascendente).
     * @param consumer función para procesar cada elemento
     */
    public void inOrderTraversal(Consumer<E> consumer) {
        inOrderTraversal(root, consumer);
    }

    private void inOrderTraversal(BSTNode<E> node, Consumer<E> consumer) {
        if (node != null) {
            inOrderTraversal(node.getLeft(), consumer);
            consumer.accept(node.getData());
            inOrderTraversal(node.getRight(), consumer);
        }
    }

    /**
     * Recorrido inorder inverso del árbol (descendente).
     * @param consumer función para procesar cada elemento
     */
    public void reverseInOrderTraversal(Consumer<E> consumer) {
        reverseInOrderTraversal(root, consumer);
    }

    private void reverseInOrderTraversal(BSTNode<E> node, Consumer<E> consumer) {
        if (node != null) {
            reverseInOrderTraversal(node.getRight(), consumer);
            consumer.accept(node.getData());
            reverseInOrderTraversal(node.getLeft(), consumer);
        }
    }

    /**
     * Obtiene todos los elementos del árbol en orden ascendente.
     * @return lista con todos los elementos
     */
    public List<E> getInOrderList() {
        List<E> list = new ArrayList<>();
        inOrderTraversal(list::add);
        return list;
    }

    /**
     * Obtiene todos los elementos del árbol en orden descendente.
     * @return lista con todos los elementos
     */
    public List<E> getReverseInOrderList() {
        List<E> list = new ArrayList<>();
        reverseInOrderTraversal(list::add);
        return list;
    }

    /**
     * Obtiene la cantidad de elementos en el árbol.
     * @return número de elementos
     */
    public int size() {
        return count;
    }

    /**
     * Verifica si el árbol está vacío.
     * @return true si está vacío, false en caso contrario
     */
    public boolean isEmpty() {
        return count == 0;
    }
}