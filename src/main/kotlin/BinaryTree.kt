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

    abstract fun insert(key: K, value: T, root:P?=this.root)
    abstract fun delete(key: K, root: P?=this.root)
    abstract fun find(key: K, root: P?=this.root): Boolean
    abstract fun peek(key: K, root: P?=this.root): T?
    abstract fun findParent(key: K, root: P?=this.root): K?
    protected abstract fun findSealing(root: P?=this.root): P?
    fun printNodes(): String {
        var res= StringBuilder()
        for (elem in iterator())
            res.append("$elem ")
        return res.toString()
    }
}