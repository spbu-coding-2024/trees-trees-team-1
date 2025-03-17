class AVLTree<K:Comparator<K>, T>(): BinaryTree<K,T, AVLNode<K, T>> {
    private var root: AVLNode<K, T>?=null

    private fun leftRotation(node: AVLNode<K, T>) {}
    private fun rightRotation(node: AVLNode<K, T>) {}
}