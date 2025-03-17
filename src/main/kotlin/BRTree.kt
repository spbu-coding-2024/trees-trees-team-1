class BRTree<K: Comparable<K>, T>(): BinaryTree<K, T, BRNode<K, T>> {
    private var root:BRNode<K, T>?=null

    private fun leftRotation(node: BRNode<K, T>) {}
    private fun rightRotation(node: BRNode<K, T>) {}

    override fun insert(root: BRNode<K, T>?, key: K, value: T) {
        if (root==null)
            this.root=BRNode(key, value)
        else if (root.key<=key) {
            if (root.right==null)
                root.right=BRNode(key, value)
            else
                insert(root.right, key, value)
        } else {
            if (root.left==null)
                root.left=BRNode(key, value)
            else
                insert(root.left, key, value)
        }
    }

    override fun delete(root: BRNode<K, T>?, key: K) {
        if (root==null)
            return
        else if (root.key==key) {
            var k=root.value
            if (root.right==null) {
                if (root.parent==null)
                    this.root=root.left
                else if ((root.parent?.key ?: root.key) <= root.key) {
                    val parent=root.parent
                    root.parent?.right=root.left
                    root.left?.parent=parent
                } else {
                    val parent=root.parent
                    root.parent?.left=root.left
                    root.left?.parent=parent
                }
            } else {
                if (root.right?.left==null) {
                    root.key=(root.right?.key ?: root.key)
                    root.right=null
                } else {
                    root.key=findSealing(root.right, key)
                }
            }
        } else if (root.key<=key)
            delete(root.right, key)
        else
            delete(root.left, key)
    }

    override fun find(root: BRNode<K, T>?, key: K): Boolean {
        if (root==null)
            return false
        return if (root.key==key) true else if (root.key>key) find(root.right, key) else find(root.left, key)
    }

    override fun peek(key: K): T {
        TODO("Not yet implemented")
    }

    override fun findParent(root: BRNode<K, T>?, key: K): K {
        TODO("Not yet implemented")
    }

    override fun findSealing(root: BRNode<K, T>?, key: K): K {
        if (root?.left==null) {
            val k=root?.key ?: key
            root?.parent?.left=null
            return k
        } else
            findSealing(root.left, key)
        return key
    }

    override fun printNodes() {
        TODO("Not yet implemented")
    }

    override fun hasNext(): Boolean {
        TODO("Not yet implemented")
    }

    override fun next(): K {
        TODO("Not yet implemented")
    }
}