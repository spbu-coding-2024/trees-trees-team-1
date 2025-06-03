package trees

import nodes.AVLNode
/**Класс, реализующий AVL дерево
 * @see [trees.BinaryTree]*/
class AVLTree<K:Comparable<K>, T>(): BinaryTree<K,T, AVLNode<K, T>>() {

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
	 * @param node узел, для которого вычисляется баланс-фактор
	 * @return разницу высот правого и левого поддеревьев
	 */
	private fun calculateBalanceFactor(node: AVLNode<K, T>?): Int {
		return checkHeight(node?.right) - checkHeight(node?.left)
	}

	/**
	 * Функция обновление высоты текущего узла на основе высот потомков
	 * @param node узел, высоту которого нужно обновить
	 */
	private fun fixHeight(node: AVLNode<K, T>?) {
		val hl = checkHeight(node?.left)
		val hr = checkHeight(node?.right)
		node?.height = maxOf(hl, hr) + 1
	}

	/**
	 * Функция обмена ключами и значениями между двумя узлами
	 * @param nodeA первый узел для обмена
	 * @param nodeB второй узел для обмена
	 */
	private fun swapNodes(nodeA: AVLNode<K, T>, nodeB: AVLNode<K, T>) {
		val aKey: K = nodeA.key
		nodeA.key = nodeB.key
		nodeB.key = aKey
		val aValue: T = nodeA.value
		nodeA.value = nodeB.value
		nodeB.value = aValue
	}

	/**
	 * Функция левого поворота вокруг указанного узла
	 * @param node узел, вокруг которого выполняется поворот
	 * @return новый корень поддерева после поворота
	 */
	private fun leftRotation(node: AVLNode<K, T>): AVLNode<K, T> {
		/* Без правого ребёнка поворот невозможен */
		val rightChild = node.right ?: return node
		swapNodes(node, rightChild)
		val buffer = node.left
		node.left = rightChild
		node.right = rightChild.right
		rightChild.right = rightChild.left
		rightChild.left = buffer
		fixHeight(rightChild)
		fixHeight(node)
		return node
	}

	/**
	 * Функция правого поворота вокруг указанного узла
	 * @param node узел, вокруг которого выполняется поворот
	 * @return новый корень поддерева после поворота
	 */
	private fun rightRotation(node: AVLNode<K, T>): AVLNode<K, T> {
		/* Без левого ребёнка поворот невозможен */
		val leftChild = node.left ?: return node
		swapNodes(node, leftChild)
		val buffer = node.right
		node.right = leftChild
		node.left = leftChild.left
		leftChild.left = leftChild.right
		leftChild.right = buffer
		fixHeight(leftChild)
		fixHeight(node)
		return node
	}

	/**
	 * Функция балансировки узла после операций вставки/удаления
	 * @param node узел, который требуется сбалансировать
	 * @return сбалансированный узел
	 */
	private fun balance(node: AVLNode<K, T>): AVLNode<K, T> {
		fixHeight(node)
		val balanceFactor = calculateBalanceFactor(node)
		/* Если левое поддерево выше */
		if (balanceFactor == -2) {
			val leftChild = node.left
			/* Если у левого ребёнка перевес вправо, делаем левый поворот */
			if (leftChild != null && calculateBalanceFactor(leftChild) == 1) {
				node.left = leftRotation(leftChild)
			}
			return rightRotation(node)
		}
		/* Если правое поддерево выше */
		else if (balanceFactor == 2) {
			val rightChild = node.right
			/* Если у правого ребёнка перевес влево, делаем правый поворот */
			if (rightChild != null && calculateBalanceFactor(rightChild) == -1) {
				node.right = rightRotation(rightChild)
			}
			return leftRotation(node)
		}
		/* Если балансировка не требуется */
		else {
			return node
		}
	}


	/**[trees.BinaryTree.insert]*/
	@Override
	override fun insert(key: K, value: T, root: AVLNode<K, T>?) {
		/**
		* Функция вставки узла в дерево
		* @param node текущий узел для проверки
		* @param key ключ нового узла
		* @param value значение нового узла
		* @return новый узел или измененное поддерево
		*/
		fun insertNode(node: AVLNode<K, T>?, key: K, value: T): AVLNode<K, T> {
			/* Если место пустое - добавляем узел */
			if (node == null) return AVLNode(key, value)
			if (key < node.key) node.left = insertNode(node.left, key, value)
			else if (key > node.key) node.right = insertNode(node.right, key, value)
			/* Если ключ существует - обновляем значения */
			else node.value = value
			return balance(node)
		}
		this.root = insertNode(this.root, key, value)
	}

	/**
	 * Функция удаления узла с минимальным ключом в поддереве
	 * @param node корень поддерева для поиска минимума
	 * @return новое поддерево без минимального узла
	 */
	private fun removeMin(node: AVLNode<K, T>?): AVLNode<K,T>? {
		if (node?.left == null) return node?.right
		node.left = removeMin(node.left)
		return balance(node)
	}

	/**
	 * Функция поиска узла с минимальным ключом в поддереве
	 * @param node корень поддерева для поиска
	 * @return узел с минимальным ключом
	 */
	private fun findMin(node: AVLNode<K, T>?): AVLNode<K, T>? {
		var current = node
		while (current?.left != null) {
			current = current.left
		}
		return current
	}


	/**[trees.BinaryTree.delete]*/
	@Override
	override fun delete(key: K, root: AVLNode<K, T>?) {
		/**
		* Функция удаления узла по ключу
		* @param node текущий узел для проверки
		* @param key ключ узла для удаления
		* @return новое поддерево без удаленного узла
		*/
		fun deleteNode(node: AVLNode<K, T>?, key: K): AVLNode<K, T>? {
			/* Если узла не существует */
			if (node == null) return null
			if (key < node.key) {
				node.left = deleteNode(node.left, key)
			}
			else if (key > node.key) {
				node.right = deleteNode(node.right, key)
			}
			else {
				/* Узел найден, key == node.key */
				if (node.left == null) return node.right
				else if (node.right == null) return node.left
				else {
					/* Если есть оба ребёнка */
					val minNode = findMin(node.right) ?: return node
					node.right = removeMin(node.right)
					/* Дети удалённого узла */
					minNode.left = node.left
					minNode.right = node.right
					return balance(minNode)
				}
			}
			return balance(node)
		}
		this.root = deleteNode(this.root, key)
	}


	/**[trees.BinaryTree.find]*/
	@Override
	override fun find(key: K, root: AVLNode<K, T>?): Boolean {
		if (root == null) return false
		return when {
			key == root.key -> true
			key < root.key -> find(key, root.left)
			else -> find(key, root.right)
		}
	}


	/**[trees.BinaryTree.peek]*/
	@Override
	override fun peek(key: K, root: AVLNode<K, T>?): T? {
		if (root == null) return null
		return when {
			key == root.key -> root.value
			key < root.key -> peek(key, root.left)
			else -> peek(key, root.right)
		}
	}


	/**[trees.BinaryTree.findParent]*/
	@Override
	override fun findParent(key: K, root: AVLNode<K, T>?): K? {
		if (root == null) return null
		return when {
			root.left?.key == key || root.right?.key == key -> root.key
			key < root.key -> findParent(key, root.left)
			else -> findParent(key, root.right)
		}
	}


	/**
	 * Функция поиска узла по ключу
	 * @param key ключ для поиска
	 * @param node текущий узел для проверки
	 * @return найденный узел или null
	 */
	private fun findNode(key: K, node: AVLNode<K, T>? = this.root): AVLNode<K, T>? {
		if (node == null) return null
		return when {
			key == node.key -> node
			key < node.key -> findNode(key, node.left)
			else -> findNode(key, node.right)
		}
	}


	/**[trees.BinaryTree.findCeiling]*/
	@Override
	override fun findCeiling(root: AVLNode<K, T>?): AVLNode<K, T>? {
		if (root == null) return null
		if (root.left != null) {
			var current = root.left
			while (current?.right != null) {
				current = current.right
			}
			return current
		}
		var parentKey = findParent(root.key, this.root)
		var parent: AVLNode<K, T>? = if (parentKey != null) findNode(parentKey) else null
		var child = root
		while (parent != null && child == parent.left) {
			child = parent
			parentKey = findParent(parent.key, this.root)
			parent = if (parentKey != null) findNode(parentKey) else null
		}
		return parent
	}
}
