package nodes

/**
 * Класс реализующий узел бинарного дерева поиска
 */

class BSNode<K: Comparable<K>, T>(override var key: K, override var value: T,
): Node<K, T, BSNode<K, T>>() {
}