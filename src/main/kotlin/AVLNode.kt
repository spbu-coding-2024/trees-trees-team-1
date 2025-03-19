class AVLNode<K: Comparator<K>, T>(override var key: K, override var value: T) : Node<K, T, AVLNode<K, T>>() {
	private var height: Int=0

	// Может работать с null
	private fun checkHeight(node: AVLNode<K, T>?): Int {
		return node?.height ?: 0
	}

	// Только с не null
	fun calculateBalanceFactor(node: AVLNode<K, T>): Int {
		return checkHeight(node.right) - checkHeight(node.left)
	}

	fun fixHeight(node: AVLNode<K, T>) {
		val hl = checkHeight(node.left)
		val hr = checkHeight(node.right)
		node.height = maxOf(hl, hr) + 1
	}
}