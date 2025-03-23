class BRTree<K: Comparable<K>, T>: BinaryTree<K, T, BRNode<K, T>>() {

    companion object {
        const val RED=1
        const val BLACK=0
    }

    private fun leftRotation(node: BRNode<K, T>?) {
        val parent=node?.parent?.parent
        node?.parent?.right=node?.left
        node?.parent?.right?.parent=node?.parent
        node?.left=node?.parent
        node?.left?.parent=node
        if (parent==null) {
            node?.parent = null
            this.root=node
        } else {
            node.parent=parent
            if (parent.key<= node.key)
                parent.right = node
            else
                parent.left = node

        }
    }

    private fun rightRotation(node: BRNode<K, T>?) {
        val parent=node?.parent?.parent
        node?.parent?.left=node?.right
        node?.parent?.left?.parent=node?.parent
        node?.right=node?.parent
        node?.right?.parent=node
        if (parent==null) {
            node?.parent = null
            this.root=node
        } else {
            node.parent=parent
            if (parent.key<= node.key)
                parent.right=node
            else
                parent.left=node
        }
    }

    private fun balanceInsert(root: BRNode<K, T>, direction: Int) {
        val uncle=if (root.parent?.left==root) root.parent?.right else root.parent?.left
        if (root.color== BLACK)
            return
        if ((uncle?.color ?: BLACK) == BLACK) {
            uncle?.color= BLACK
            root.color= BLACK
            root.parent?.color=if (root.parent != this.root) RED else BLACK
            if (root == root.parent?.right)
                balanceInsert(root.parent?.parent ?: return, 1)
            else
                balanceInsert(root.parent?.parent ?: return, 0)
        } else {
            if (direction==1 && root==root.parent?.right) {
                root.color= BLACK
                root.parent?.color= RED
                leftRotation(root)
            } else if (direction==0 && root==root.parent?.right){
                val t=root.left
                rightRotation(t)
                t?.color= BLACK
                t?.parent?.color= RED
                leftRotation(t)
            }else if (direction==0 && root==root.parent?.left) {
                root.color= BLACK
                root.parent?.color= RED
                rightRotation(root)
            } else if (direction==1 && root==root.parent?.left){
                val t=root.right
                leftRotation(t)
                t?.color= BLACK
                t?.parent?.color= RED
                rightRotation(t)
            }
        }
    }

    override fun insert(key: K, value: T, root: BRNode<K, T>?) {
        if (this.root==root && root==null) {
            this.root = BRNode(key, value, null)
            this.root?.color = BLACK
            return
        }
        if (root==null)
            return
        if (root.key<key) {
            if (root.right == null){
                root.right = BRNode(key, value, root)
                balanceInsert(root, 1)
            } else
                insert( key, value, root.right)
        } else {
            if (root.left==null) {
                root.left = BRNode(key, value, root)

                balanceInsert(root, 0)
            } else
                insert(key, value, root.left)
        }

    }

    private fun deleteRed(root: BRNode<K, T>?) {
        if (root?.right == null && root?.left == null) {
            if (root?.parent?.left == root)
                root?.parent?.left = null
            else
                root?.parent?.right = null
        } else {
            val sub: BRNode<K, T>? = if (root.left == null) root.right else findCeiling(root.left)
            swapValues(root, sub ?: return)
            if (sub.color == RED)
                deleteRed(sub)
            else
                deleteBlack(sub)
        }
    }

    private fun deleteBlack(root: BRNode<K, T>?) {
        if (root?.left!=null) {
            val sub: BRNode<K, T>?=if (root.left?.right!=null) findCeiling(root.left) else root.left
            swapValues(root, sub ?: return)
            if (sub.color == RED)
                deleteRed(sub)
            else
                deleteBlack(sub)
        } else if (root?.right!=null){
            swapValues(root.right ?: return, root)
            if (root.right?.color == BLACK)
                deleteBlack(root.right)
            else
                deleteRed(root.right)
        } else {
            deleteBalance(root)
            if (root?.parent == null) {
                this.root = null
            } else if (root.parent?.left == root)
                root.parent?.left = null
            else
                root.parent?.right = null
        }
    }

    override fun delete(key: K, root: BRNode<K, T>?) {
        if (root==null)
            return
        else if (root.key==key) {
            if (root.color == RED) {
                deleteRed(root)
            } else {
                deleteBlack(root)
            }
        } else if (root.key<key)
            delete(key, root.right)
        else
            delete(key, root.left)
    }

    private fun deleteBalance(root: BRNode<K, T>?) {
        if (root==root?.parent?.left) {
            if ((root?.parent?.right?.color ?: BLACK) == BLACK) {
                if ((root?.parent?.right?.right?.color ?: BLACK) == RED) {
                    val color = root?.parent?.color ?: BLACK
                    root?.parent?.right?.color = color
                    root?.parent?.color= BLACK
                    root?.parent?.right?.right?.color = BLACK
                    leftRotation(root?.parent?.right)
                } else if ((root?.parent?.right?.right?.color ?: BLACK) == BLACK
                    && (root?.parent?.right?.left?.color ?: BLACK) == RED) {
                    root?.parent?.right?.left?.color = BLACK
                    root?.parent?.right?.color = RED
                    root?.parent?.right?.right?.color = BLACK
                    val color = root?.parent?.right?.color ?: BLACK
                    root?.parent?.color = root?.color ?: BLACK
                    root?.color = color
                    rightRotation(root?.parent?.right?.left)
                    leftRotation(root?.parent?.right)
                } else if ((root?.parent?.right?.right?.color ?: BLACK) == BLACK
                    && (root?.parent?.right?.left?.color ?: BLACK) == BLACK
                ){
                    root?.color = BLACK
                    root?.parent?.right?.color = RED
                    if (root != this.root && (root?.parent?.color ?: BLACK) == BLACK)
                        deleteBalance(root?.parent)
                }
            } else {
                leftRotation(root?.parent?.right)
                root?.parent?.color = RED
                root?.parent?.right?.color = BLACK
                deleteBalance(root)
            }
        } else {
            if ((root?.parent?.left?.color ?: BLACK) == BLACK) {
                if ((root?.parent?.left?.left?.color ?: BLACK) == RED) {
                    root?.parent?.left?.left?.color= BLACK
                    val color=root?.parent?.left?.color ?: BLACK
                    root?.parent?.color=root?.color ?: BLACK
                    root?.color =color
                    rightRotation(root?.parent?.left)
                } else if ((root?.parent?.left?.left?.color ?: BLACK) == BLACK
                    && (root?.parent?.left?.right?.color ?: BLACK) == RED) {
                    root?.parent?.left?.right?.color= BLACK
                    root?.parent?.left?.color= RED
                    root?.parent?.left?.left?.color= RED
                    val color=root?.parent?.left?.color ?: BLACK
                    root?.parent?.color=root?.color ?: BLACK
                    root?.color =color
                    leftRotation(root?.parent?.left?.right)
                    rightRotation(root?.parent?.left)
                } else if ((root?.parent?.left?.right?.color ?: BLACK) == BLACK
                    && (root?.parent?.left?.left?.color ?: BLACK) == BLACK){
                    root?.color = BLACK
                    root?.parent?.left?.color = RED
                    if (root != this.root && (root?.parent?.color ?: BLACK) == BLACK)
                        deleteBalance(root?.parent)
                }
            } else {
                rightRotation(root?.parent?.left)
                root?.parent?.color = RED
                root?.parent?.left?.color = BLACK
                deleteBalance(root)
            }
        }
    }

    private fun swapValues(root: BRNode<K, T>, second:BRNode<K, T>) {
        val tempKey=root.key
        val tempVal=root.value
        root.key=second.key
        root.value=second.value
        second.key=tempKey
        second.value=tempVal
    }

    override fun find(key: K, root: BRNode<K, T>?): Boolean {
        if (root==null)
            return false
        return if (root.key==key) true else if (root.key<key) find(key, root.right) else find(key, root.left)
    }

    override fun peek(key: K, root: BRNode<K, T>?): T? {
        if (root==null)
            return null
        return if (root.key==key) root.value else if (root.key<key) peek(key, root.right) else peek(key, root.left)
    }

    override fun findParent(key: K, root: BRNode<K, T>?): K? {
        if (root==null)
            return null
        return if (root.key==key) root.parent?.key else if (root.key<key) findParent(key, root.right) else findParent(key, root.left)
    }

    override fun findCeiling(root: BRNode<K, T>?): BRNode<K,T> {
        if (root?.right==null && root!=null) {
            root.parent?.right=null
            return root
        } else
            return findCeiling(root?.right)
    }
}
