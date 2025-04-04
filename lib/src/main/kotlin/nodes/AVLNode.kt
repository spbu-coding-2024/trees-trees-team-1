package nodes

class AVLNode<K: Comparable<K>, T>(override var key: K, override var value: T) : Node<K, T, AVLNode<K, T>>() {
	var height: Int = 0
}
