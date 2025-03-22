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
		val buffer: Node = node.left
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
		val buffer: Node = node.right
		node.right = leftChild
		node.left = leftChild.left
		leftChild.left = leftChild.right
		leftChild.right = buffer
		node.fixHeight(node)
		leftChild.fixHeight(leftChild)
		return leftChild
	}

	private fun balance(node: AVLNode<K, T>): AVLNode<K, T>? {
		node.fixHeight()
		val balanceFactor: Int = node.calculateBalanceFactor()
		if (balanceFactor == -2) {
			if (node.left != null && node.left.calculateBalanceFactor() == 1) {
				node.left = leftRotation(node.left)
			}
			return rightRotation(node)
		}
		else if (balanceFactor == 2) {
			if (node.right != null && node.right.calculateBalanceFactor() == -1) {
				node.right = rightRotation(node.right)
			}
			return leftRotation(node)
		}
		else {
			return node
		}
	}

	override fun insert(root: AVLNode<K, T>?, key: K, value: T): AVLNode<K, T>? {
		if (root == null) return AVLNode(key, value)
		if (key < root.key) {
			root.left = insert(root.left, key, value)
		}
		else if (key > root.key) {
			root.right = insert(root.right, key, value)
		}
		else {
			root.value = value
		}
		return balance(root)
	}

	private fun findMin(node: AVLNode<K, T>): AVLNode<K, T> {
		var current = node
		while (current.left != null) {
			current = current.left ?: break
		}
		return current
	}

	private fun removeMin(node: AVLNode<K, T>): AVLNode<K,T>? {
		if (node.left == null) return node.right
		node.left = removeMin(node.left ?: return node.right)
		return balance(node)
	}

	override fun delete(root: AVLNode<K, T>?, key: K): AVLNode<K, T>? {
		if (root == null) return null
		if (key < root.key) {
			root.left = delete(root.left, key)
		}
		else if (key > root.key) {
			root.right = delete(root.right, key)
		} else {
				val left = root.left
				val right = root.right
				if (right == null) return left
				val minNode = findMin(right)
				minNode.right = removeMin(right)
				minNode.left = left
				return balance(minNode)
		}
		return balance(root)
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