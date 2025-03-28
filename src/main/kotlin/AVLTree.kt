abstract class AVLTree<K:Comparable<K>, T>(): BinaryTree<K,T, AVLNode<K, T>>() {

	private fun swapNodes(nodeA: AVLNode<K, T>, nodeB: AVLNode<K, T>) {
		val aKey: K = nodeA.key
		nodeA.key = nodeB.key
		nodeB.key = aKey
		val aValue: T = nodeA.value
		nodeA.value = nodeB.value
		nodeB.value = aValue
	}


	private fun leftRotation(node: AVLNode<K, T>): AVLNode<K, T>? {
		val rightChild = node.right ?: return null	// Если правый потомок null, возвращаем null
		swapNodes(node, rightChild)
		val buffer = node.left
		node.left = rightChild
		node.right = rightChild.right
		rightChild.right = rightChild.left
		rightChild.left = buffer
		node.fixHeight(node)
		rightChild.fixHeight(rightChild)
		return rightChild
	}


	private fun rightRotation(node: AVLNode<K, T>): AVLNode<K, T>? {
		val leftChild = node.left ?: return null //Если левый потомок null, возвращаем null
		swapNodes(node, leftChild)
		val buffer = node.right
		node.right = leftChild
		node.left = leftChild.left
		leftChild.left = leftChild.right
		leftChild.right = buffer
		node.fixHeight(node)
		leftChild.fixHeight(leftChild)
		return leftChild
	}


	private fun balance(node: AVLNode<K, T>?): AVLNode<K, T>? {
		if (node == null) return null
		node.fixHeight(node)
		val balanceFactor: Int = node.calculateBalanceFactor(node)
		if (balanceFactor == -2) {		//Если левое поддерево выше
			val leftChild = node.left
			if (leftChild != null && leftChild.calculateBalanceFactor(leftChild) == 1) {
				node.left = leftRotation(leftChild)
			}
			return rightRotation(node) ?: node
		}
		else if (balanceFactor == 2) {		//Если правое поддерево выше
			val rightChild = node.right
			if (rightChild != null && rightChild.calculateBalanceFactor(rightChild) == -1) {
				node.right = rightRotation(rightChild)
			}
			return leftRotation(node) ?: node
		}
		else {
			return node		//Если балансировка не требуется
		}
	}


	override fun insert(root: AVLNode<K, T>?, key: K, value: T) {
		if (root == null) {
			this.root = AVLNode(key, value)		//Если дерево пустое, создаем корень
			return
		}
		if (key < root.key) {
			if (root.left == null) root.left = AVLNode(key, value)
			else insert(root.left, key, value)
		}
		else if (key > root.key) {
			if (root.right == null) root.right = AVLNode(key, value)
			else insert(root.right, key, value)
		}
		else {
			root.value = value	//Если ключ существует, обновляем значение
		}
		this.root = balance(this.root) ?: return
	}


	private fun removeMin(node: AVLNode<K, T>): AVLNode<K,T>? {
		if (node.left == null) return node.right
		node.left = removeMin(node.left ?: return node.right)
		return balance(node)
	}


	override fun delete(root: AVLNode<K, T>?, key: K) {
		if (root == null) return
		if (key < root.key) {
			if (root.left != null) delete(root.left, key)
		}
		else if (key > root.key) {
			if (root.right != null) delete(root.right, key)
		}
		else {
			val left = root.left
			val right = root.right
			if (right == null) {
				this.root = left
			}
			else {
				val sealingNode = findSealing(right)
				if (sealingNode != null) {
					sealingNode.right = removeMin(right)
					sealingNode.left = left
					this.root = balance(sealingNode)
				}
				else {
					this.root = balance(right)
				}
			}
		}
		this.root = balance(root) ?: return
	}


	override fun find(root: AVLNode<K, T>?, key: K): Boolean {
		if (root == null) return false
		return when {
			key == root.key -> true
			key < root.key -> find(root.left, key)
			else -> find(root.right, key)
		}
	}


	override fun peek(root: AVLNode<K, T>?, key: K): T? {
		if (root == null) return null
		return when {
			key == root.key -> root.value
			key < root.key -> peek(root.left, key)
			else -> peek(root.right, key)
		}
	}


	override fun findParent(root: AVLNode<K, T>?, key: K): K? {
		if (root == null) return null
		return when {
			root.left?.key == key || root.right?.key == key -> root.key
			key < root.key -> findParent(root.left, key)
			else -> findParent(root.right, key)
		}
	}


	override fun findSealing(root: AVLNode<K, T>?): AVLNode<K, T>? {
		if (root == null) return null
		var current = root.right
		while (current?.left != null) {
			current = current.left
		}
		return current
	}
}