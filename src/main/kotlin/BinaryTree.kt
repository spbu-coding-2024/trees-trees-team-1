interface BinaryTree <K: Comparable<K>, T, P>: Iterator<K> {
    fun insert(root: P?, key: K, value: T)
    fun delete(root: P?, key: K)
    fun find(root: P?, key: K): Boolean
    fun peek(root: BRNode<K, T>?,key: K): T?
    fun findParent(root: BRNode<K, T>?, key: K): K?
    fun findSealing(root: BRNode<K, T>?, key: K): K
    fun printNodes()
}