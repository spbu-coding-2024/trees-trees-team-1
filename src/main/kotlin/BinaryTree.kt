interface BinaryTree <K: Comparable<K>, T, P>: Iterator<Pair<K?, Byte>> {
    fun insert(root: P?, key: K, value: T)
    fun delete(root: P?, key: K)
    fun find(root: P?, key: K): Boolean
    fun peek(root: P?,key: K): T?
    fun findParent(root: P?, key: K): K?
    fun findSealing(root: P?): P?
    fun printNodes()

}