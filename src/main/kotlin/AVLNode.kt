class AVLNode<K : Comparable<K>, T>(override var key: K, override var value: T) : Node<K, T, AVLNode<K, T>>() {

	private var height: Int = 0

	// Проверка высоты с обработкой null
	private fun checkHeight(node: AVLNode<K, T>?): Int {
		// Для null -1, чтобы корректно работали calculateBalanceFactor и fixHeight
		return node?.height ?: -1
	}

	// Вычисление баланс-фактора
	fun calculateBalanceFactor(): Int {
		return checkHeight(this.right) - checkHeight(this.left)
	}

	// Обновление высоты текущего узла
	fun fixHeight() {
		val hl = checkHeight(this.left)
		val hr = checkHeight(this.right)
		this.height = maxOf(hl, hr) + 1
	}
}