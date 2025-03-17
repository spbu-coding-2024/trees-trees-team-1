class AVLNode<K: Comparator<K>, T>(override var key: K, override var value: T) : Node<K, T, AVLNode<K, T>>() {
	private var height: Int=0

	// Может работать с null
	private fun checkHeight(node: AVLNode<K, T>?): Int {
		return node?.height ?: 0
	}

	// Только с не null
	private fun calculateBFactor(node: AVLNode<K, T>): Int {
		return height(node.right) - height(node.left)
	}

	private fun fixHeight(node: AVLNode<K, T>) {
		val hl = height(node.left)
		val hr = height(node.right)
		node.height = maxOf(hl, hr) + 1
	}
}