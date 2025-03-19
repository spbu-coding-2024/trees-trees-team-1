abstract class BinaryTree <K: Comparable<K>, T, P:Node<K, T, P>> {
    var root: P?=null
    inner class Iterate : Iterator<K?> {
        private var array: ArrayDeque<P?> = ArrayDeque()
        private var start: Int=0
        override fun hasNext(): Boolean {
            if (start==0) {
                array.addLast(root)
                start=1
            }
            if (array.isEmpty())
                return false
            return true
        }
        override fun next(): K? {
            val t=array.removeFirst()
            if (t!=null) {
                array.addLast(t.left)
                array.addLast(t.right)
            }
            return t?.key
        }

    }
    fun iterator(): Iterate {
        return this.Iterate()
    }

    abstract fun insert(root: P?, key: K, value: T)
    abstract fun delete(root: P?, key: K)
    abstract fun find(root: P?, key: K): Boolean
    abstract fun peek(root: P?,key: K): T?
    abstract fun findParent(root: P?, key: K): K?
    protected abstract fun findSealing(root: P?): P?

    fun printNodes() {
        for (elem in iterator())
            print("$elem ")
    }
