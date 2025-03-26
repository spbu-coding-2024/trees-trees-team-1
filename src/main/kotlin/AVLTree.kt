abstract class AVLTree<K:Comparable<K>, T>(): BinaryTree<K,T, AVLNode<K, T>>() {

	private fun swapNodes(nodeA: AVLNode<K, T>, nodeB: AVLNode<K, T>) {
		val aKey: K = nodeA.key
		nodeA.key = nodeB.key
		nodeB.key = aKey
		val aValue: T = nodeA.value
		nodeA.value = nodeB.value
		nodeB.value = aValue
	}


	private fun leftRotation(node: AVLNode<K, T>): AVLNode<K, T> {
		val rightChild = node.right ?: return node
		swapNodes(node, rightChild)
		val buffer = node.left
		node.left = rightChild
		node.right = rightChild.right
		rightChild.right = rightChild.left
		rightChild.left = buffer
		rightChild.fixHeight()
		node.fixHeight()
		return node
	}


	private fun rightRotation(node: AVLNode<K, T>): AVLNode<K, T> {
		val leftChild = node.left ?: return node
		swapNodes(node, leftChild)
		val buffer = node.right
		node.right = leftChild
		node.left = leftChild.left
		leftChild.left = leftChild.right
		leftChild.right = buffer
		leftChild.fixHeight()
		node.fixHeight()
		return node
	}


	private fun balance(node: AVLNode<K, T>): AVLNode<K, T> {
		node.fixHeight()
		val balanceFactor = node.calculateBalanceFactor()
		if (balanceFactor == -2) {		//Если левое поддерево выше
			val leftChild = node.left
			if (leftChild != null && leftChild.calculateBalanceFactor() == 1) {
				node.left = leftRotation(leftChild)
			}
			return rightRotation(node)
		}
		else if (balanceFactor == 2) {		//Если правое поддерево выше
			val rightChild = node.right
			if (rightChild != null && rightChild.calculateBalanceFactor() == -1) {
				node.right = rightRotation(rightChild)
			}
			return leftRotation(node)
		}
		else {
			return node		//Если балансировка не требуется
		}
	}


	private fun insertNode(node: AVLNode<K, T>?, key: K, value: T): AVLNode<K, T> {
		if (node == null) return AVLNode(key, value)
		if (key < node.key) node.left = insertNode(node.left, key, value)
		else if (key > node.key) node.right = insertNode(node.right, key, value)
		else node.value = value
		return balance(node)
	}

	override fun insert(key: K, value: T, root: AVLNode<K, T>?) {
		this.root = insertNode(this.root, key, value)
	}


	private fun removeMin(node: AVLNode<K, T>?): AVLNode<K,T>? {
		if (node?.left == null) return node?.right
		node.left = removeMin(node.left)
		return balance(node)
	}

	private fun findMin(node: AVLNode<K, T>?): AVLNode<K, T>? {
		var current = node
		while (current?.left != null) {
			current = current.left
		}
		return current
	}

	private fun deleteNode(node: AVLNode<K, T>?, key: K): AVLNode<K, T>? {
		if (node == null) return null
		if (key < node.key) {
			node.left = deleteNode(node.left, key)
		}
		else if (key > node.key) {
			node.right = deleteNode(node.right, key)
		}
		else {
			if (node.left == null) return node.right
			else if (node.right == null) return node.left
			else {
				val minNode = findMin(node.right) ?: return node
				node.right = removeMin(node.right)
				minNode.left = node.left
				minNode.right = node.right
				return balance(minNode)
			}
		}
		return balance(node)
	}

	override fun delete(key: K, root: AVLNode<K, T>?) {
		this.root = deleteNode(root, key)
	}

	override fun find(key: K, root: AVLNode<K, T>?): Boolean {
		if (root == null) return false
		return when {
			key == root.key -> true
			key < root.key -> find(key, root.left)
			else -> find(key, root.right)
		}
	}


	override fun peek(key: K, root: AVLNode<K, T>?): T? {
		if (root == null) return null
		return when {
			key == root.key -> root.value
			key < root.key -> peek(key, root.left)
			else -> peek(key, root.right)
		}
	}


	override fun findParent(key: K, root: AVLNode<K, T>?): K? {
		if (root == null) return null
		return when {
			root.left?.key == key || root.right?.key == key -> root.key
			key < root.key -> findParent(key, root.left)
			else -> findParent(key, root.right)
		}
	}

	private fun findNode(key: K, node: AVLNode<K, T>? = this.root): AVLNode<K, T>? {
		if (node == null) return null
		return when {
			key == node.key -> node
			key < node.key -> findNode(key, node.left)
			else -> findNode(key, node.right)
		}
	}

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