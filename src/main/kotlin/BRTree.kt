class BRTree<K: Comparable<K>, T>(): BinaryTree<K, T, BRNode<K, T>> {
    private var root:BRNode<K, T>?=null

    private fun leftRotation(node: BRNode<K, T>?) {
        val p=node?.parent
        val t=node?.right
        t?.color=0
        node?.color=1
        node?.right=t?.left
        t?.left=node
        node?.parent=t
        if (p==null) {
            t?.parent = null
            this.root=t
        } else {
            t?.parent=p
            if (t != null) {
                if ((t.parent?.key ?: t.key) <= t.key)
                    t.parent?.right=t
                else
                    t.parent?.left=t
            }
        }
    }
    private fun rightRotation(node: BRNode<K, T>?) {
        val p=node?.parent
        val t=node?.left
        t?.color=0
        node?.color=1
        node?.left=t?.right
        t?.right=node
        node?.parent=t
        if (p==null) {
            t?.parent = null
            this.root=t
        } else {
            t?.parent=p
            if (t != null) {
                if ((t.parent?.key ?: t.key) <= t.key)
                    t.parent?.right=t
                else
                    t.parent?.left=t
            }
        }
    }


    override fun insert(root: BRNode<K, T>?, key: K, value: T) {
        if (root==null) {
            this.root = BRNode(key, value)
            this.root?.color=0
        } else if (root.key<=key) {
            if (root.right==null) {
                root.right = BRNode(key, value)
                val uncle=if ((root.parent?.key ?: root.key)<= root.key) root.parent?.left else root.parent?.right
                if ((uncle?.color ?: 0)== 1.toByte()) {
                    uncle?.color=0
                    if (root.parent!=this.root)
                        root.parent?.color=1
                } else if (uncle==root.parent?.left) {
                    leftRotation(root.parent)
                } else {
                    leftRotation(root)
                    rightRotation(root.parent)
                }
            } else
                insert(root.right, key, value)
        } else {
            if (root.left==null) {
                root.left = BRNode(key, value)
                val uncle=if ((root.parent?.key ?: root.key)<= root.key) root.parent?.left else root.parent?.right
                if ((uncle?.color ?: 0)== 1.toByte()) {
                    uncle?.color=0
                    if (root.parent!=this.root)
                        root.parent?.color=1
                } else if (uncle==root.parent?.right) {
                    rightRotation(root.parent)
                } else {
                    rightRotation(root)
                    leftRotation(root.parent)
                }
            } else
                insert(root.left, key, value)
        }
    }

    override fun delete(root: BRNode<K, T>?, key: K) {
        if (root==null)
            return
        else if (root.key==key) {
            if (root.right==null) {
                if (root.parent==null)
                    this.root=root.left
                //проблема с null
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
                    //проблема с null
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

    override fun peek(root: BRNode<K, T>?, key: K): T? {
        if (root==null)
            return null
        return if (root.key==key) root.value else if (root.key>key) peek(root.right, key) else peek(root.left, key)
    }

    override fun findParent(root: BRNode<K, T>?, key: K): K? {
        return root?.parent?.key
    }

    override fun findSealing(root: BRNode<K, T>?, key: K): K {
        if (root?.left==null) {
            //проблема с null
            val k=root?.key ?: key
            root?.parent?.left=null
            return k
        } else
            findSealing(root.left, key)
        //проблема с null
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