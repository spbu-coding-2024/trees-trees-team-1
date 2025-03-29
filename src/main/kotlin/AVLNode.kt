class AVLNode<K: Comparable<K>, T>(override var key: K, override var value: T) : Node<K, T, AVLNode<K, T>>() {
	private var height: Int = 0

	/**
	 * Функция проверки высоты узла с обработкой null
	 * @param node проверяемый узел (может быть null)
	 * @return высоту узла или -1 если узел null
	 */
	private fun checkHeight(node: AVLNode<K, T>?): Int {
		/* Для null возвращаем -1 чтобы корректно работали calculateBalanceFactor и fixHeight */
		return node?.height ?: -1
	}

	/**
	 * Функция вычисления баланс-фактора текущего узла
	 * @return разницу высот правого и левого поддеревьев
	 */
	fun calculateBalanceFactor(): Int {
		return checkHeight(this.right) - checkHeight(this.left)
	}

	/**
	 * Функция обновление высоты текущего узла на основе высот потомков
	 */
	fun fixHeight() {
		val hl = checkHeight(this.left)
		val hr = checkHeight(this.right)
		this.height = maxOf(hl, hr) + 1
	}
}