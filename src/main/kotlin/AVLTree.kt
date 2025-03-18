class AVLTree<K:Comparator<K>, T>(): BinaryTree<K,T, AVLNode<K, T>> {
	private var root: AVLNode<K, T>?=null

	private fun swapNodes(nodeA: AVLNode<K, T>, nodeB: AVLNode<K, T>) {
		aKey: K = nodeA.key
		nodeA.key = nodeB.key
		nodeB.key = aKey
		aValue: T = nodeA.value
		nodeA.value = nodeB.value
		nodeB.value = aValue
	}

	private fun leftRotation(node: AVLNode<K, T>) {
		swapNodes(node, node.right)
		buffer: Node = node.left
		node.left = node.right
		node.right = node.left.right
		node.right.left = node.right.right
		node.left.right = node.left.left
		node.left.left = buffer
		fixHeight(node.left)
		fixHeight(node)
	}

	private fun rightRotation(node: AVLNode<K, T>) {
		swapNodes(node, node.left)
		buffer: Node = node.right
		node.right = node.left
		node.left = node.right.left
		node.right.left = node.right.right
		node.right.right = buffer
		fixHeight(node.right)
		fixHeight(node)
	}
}