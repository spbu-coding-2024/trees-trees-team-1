class BRTree<K: Comparable<K>, T>: BinaryTree<K, T, BRNode<K, T>>() {

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

    //direction - 0-right 1-left

    private fun balance_insert(root: BRNode<K, T>, direction: Int) {
        val uncle=if (root.parent?.left==root) root.parent?.right else root.parent?.left
        if ((uncle?.color ?: 0) ==1 && root.color ==1) {
            uncle?.color=0
            root.color=0
            root.parent?.color=if (root.parent != this.root) 1 else 0
            if (root == root.parent?.right)
                balance_insert(root.parent?.parent ?: return, 1)
            else
                balance_insert(root.parent?.parent ?: return, 0)
        } else if ((uncle?.color ?: 0)==0 && root.color ==1) {
            if (direction==1 && root==root.parent?.right) {
                root.color=0
                root.parent?.color=1
                leftRotation(root)
            } else if (direction==0 && root==root.parent?.right){
                val t=root.left
                rightRotation(t)
                t?.color=0
                t?.parent?.color=1
                leftRotation(t)
            }else if (direction==0 && root==root.parent?.left) {
                root.color=0
                root.parent?.color=1
                rightRotation(root)
            } else if (direction==1 && root==root.parent?.left){
                val t=root.right
                leftRotation(t)
                t?.color=0
                t?.parent?.color=1
                rightRotation(t)
            }
        }
    }

    override fun insert(root: BRNode<K, T>?, key: K, value: T) {
        if (this.root==root && root==null) {
            this.root = BRNode(key, value, null)
            this.root?.color = 0
            return
        }
        if (root==null)
            return
        if (root.key<=key) {
            if (root.right == null){
                root.right = BRNode(key, value, root)

                balance_insert(root, 1)
                println(root.right?.parent?.key)
            } else
                insert(root.right, key, value)
        } else {
            if (root.left==null) {
                root.left = BRNode(key, value, root)

                balance_insert(root, 0)
                println(root.left?.parent?.key)
            } else
                insert(root.left, key, value)
        }

    }

    private fun deleteRed(root: BRNode<K, T>?) {
        if (root?.right == null && root?.left == null) {
            if (root?.parent == null) {
                this.root = null
            } else if (root.parent?.left == root)
                root.parent?.left = null
            else
                root.parent?.right = null
        } else {
            val sub: BRNode<K, T>? = if (root.left==null) root.right else findSealing(root.left)
            swapvalues(root, sub?: return)
            if ((sub.color) ==1)
                deleteRed(sub)
            else
                deleteBlack(sub)
        }
    }

    private fun deleteBlack(root: BRNode<K, T>?) {
        if (root?.left!=null) {
            val sub: BRNode<K, T>?=if (root.left?.right!=null) findSealing(root.left) else root.left
            swapvalues(root, sub ?: return)
            if (sub.color ==1)
                deleteRed(sub)
            else
                deleteBlack(sub)
        } else if (root?.right!=null){
            swapvalues(root.right ?: return, root)
            if (root.right?.color ==0)
                deleteBlack(root.right)
            else
                deleteRed(root.right)
        } else {
            delete_balance(root)
            if (root?.parent == null) {
                this.root = null
            } else if (root.parent?.left == root)
                root.parent?.left = null
            else
                root.parent?.right = null
        }
    }

    private fun delete_balance(root: BRNode<K, T>?) {

        if (root==root?.parent?.left) {

            if ((root?.parent?.right?.color ?: 0).toInt() ==0) {
                if ((root?.parent?.right?.right?.color ?: 0).toInt() == 1) {
                    val color = root?.parent?.color ?: 0
                    root?.parent?.right?.color = color
                    root?.parent?.color=0
                    root?.parent?.right?.right?.color = 0
                    leftRotation(root?.parent?.right)
                } else if ((root?.parent?.right?.right?.color ?: 0).toInt() == 0 && (root?.parent?.right?.left?.color
                        ?: 0).toInt() == 1
                ) {
                    println("+++++++++ ${root?.parent?.key}")
                    root?.parent?.right?.left?.color = 0
                    root?.parent?.right?.color = 1
                    root?.parent?.right?.right?.color = 0
                    val color = root?.parent?.right?.color ?: 0
                    root?.parent?.color = root?.color ?: 0
                    root?.color = color
                    rightRotation(root?.parent?.right?.left)
                    leftRotation(root?.parent?.right)
                } else if ((root?.parent?.right?.right?.color ?: 0).toInt() == 0 && (root?.parent?.right?.left?.color
                        ?: 0).toInt() == 0
                ){

                    root?.color = 0
                    root?.parent?.right?.color = 1
                    if (root != this.root && (root?.parent?.color ?: 0) == 0)
                        delete_balance(root?.parent)
                }
            } else {

                root?.color = 1
                root?.parent?.right?.color = 0
                leftRotation(root?.parent?.right)
            }
        } else {

            if ((root?.parent?.left?.color ?: 0).toInt() ==0) {
                if ((root?.parent?.left?.left?.color ?: 0).toInt() == 1) {
                    root?.parent?.left?.left?.color=0
                    val color=root?.parent?.left?.color ?: 0
                    root?.parent?.color=root?.color ?: 0
                    root?.color =color
                    rightRotation(root?.parent?.left)

                } else if ((root?.parent?.left?.left?.color ?: 0).toInt() == 0 && (root?.parent?.left?.right?.color ?: 0).toInt() == 1) {
                    root?.parent?.left?.right?.color=0
                    root?.parent?.left?.color=1
                    root?.parent?.left?.left?.color=0
                    val color=root?.parent?.left?.color ?: 0
                    root?.parent?.color=root?.color ?: 0
                    root?.color =color
                    leftRotation(root?.parent?.left?.right)
                    rightRotation(root?.parent?.left)
                } else if ((root?.parent?.left?.right?.color ?: 0).toInt() == 0 && (root?.parent?.left?.left?.color
                        ?: 0).toInt() == 0
                ){
                    println("-------------------------")
                    root?.color = 0
                    root?.parent?.left?.color = 1
                    if (root != this.root && (root?.parent?.color ?: 0) == 0)
                        delete_balance(root?.parent)
                }
            } else {
                rightRotation(root?.parent?.left)
                root?.color = 1
                root?.parent?.left?.color = 0
            }

        }
    }

    private fun swapvalues(root: BRNode<K, T>, second:BRNode<K, T>) {
        val tempKey=root.key
        val tempVal=root.value
        root.key=second.key
        root.value=second.value
        second.key=tempKey
        second.value=tempVal
    }

    override fun delete(root: BRNode<K, T>?, key: K) {
        if (root==null)
            return
        else if (root.key==key) {
            if (root.color==1) {
                deleteRed(root)
            } else {
                deleteBlack(root)
            }
        } else if (root.key<key)
            delete(root.right, key)
        else
            delete(root.left, key)
    }

    override fun find(root: BRNode<K, T>?, key: K): Boolean {
        if (root==null)
            return false
        return if (root.key==key) true else if (root.key<key) find(root.right, key) else find(root.left, key)
    }

    override fun peek(root: BRNode<K, T>?, key: K): T? {
        if (root==null)
            return null
        return if (root.key==key) root.value else if (root.key>key) peek(root.right, key) else peek(root.left, key)
    }

    override fun findParent(root: BRNode<K, T>?, key: K): K? {
        if (root==null)
            return null
        return if (root.key==key) root.parent?.key else if (root.key<key) findParent(root.right, key) else findParent(root.left, key)
    }

    override fun findSealing(root: BRNode<K, T>?): BRNode<K,T> {
        if (root?.right==null && root!=null) {
            root.parent?.right=null
            return root
        } else
            return findSealing(root?.right)
    }
}

fun main() {
val tree=BRTree<Int, Int>()
//    for (i in 1..20)
//        tree.insert(tree.root, i, 8)
//    //tree.delete(tree.root, 5)
    tree.insert(tree.root, 5,0)
    tree.insert(tree.root, 4,0)
    tree.insert(tree.root, 7,0)
    tree.insert(tree.root, 6,0)
    tree.delete(tree.root, 4)
    println(tree.printNodes())
    //println(tree.findParent(tree.root, 6))


}