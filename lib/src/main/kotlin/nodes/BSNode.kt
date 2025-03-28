package nodes

class BSNode<K: Comparable<K>, T>(override var key: K, override var value: T,
): Node<K, T, BSNode<K, T>>() {
}