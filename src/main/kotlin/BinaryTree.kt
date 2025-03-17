interface BinaryTree <K: Comparator<K>, T, P>: Iterator<K> {
    fun insert(root: P, key: K, value: T)
    fun delete(root: P, key: K): T
    fun find(root: P, key: K): Boolean
    fun peek(key: K): T
    fun findParent(key: K): K
    fun findSealing(key: K): K
    fun printNodes()
}