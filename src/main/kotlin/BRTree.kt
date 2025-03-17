class BRTree<K: Comparable<K>, T>(): BinaryTree<K, T, BRNode<K, T>> {
    private var root:BRNode<K, T>?=null

    private fun leftRotation(node: BRNode<K, T>?) {
        //перекрашивание
        node?.right?.color=0
        node?.color=1
        //поворот
        val p=node?.parent
        val t=node?.right
        node?.right=t?.left
        t?.left=node
        node?.parent=t
        //переподвешивание
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
        //перекрашивание
        node?.left?.color=0
        node?.color=1
        //поворот
        val p=node?.parent
        val t=node?.left
        node?.left=t?.right
        t?.right=node
        node?.parent=t
        //переподвешивание
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

    private fun balance_insert(root: BRNode<K, T>, direction: Byte) {
        val uncle=if ((root.parent?.key ?: root.key)<= root.key) root.parent?.left else root.parent?.right
        if ((uncle?.color ?: 0)== 1.toByte()) {
            uncle?.color=0
            if (root.parent!=this.root)
                root.parent?.color=1
        } else {
            if (direction== 0.toByte()) {
                if (uncle == root.parent?.left)
                    leftRotation(root.parent)
                else
                    leftRotation(root)
                    rightRotation(root.parent)
            } else {
                if (uncle==root.parent?.right)
                    rightRotation(root.parent)
                else {
                    rightRotation(root)
                    leftRotation(root.parent)
                }
            }
        }
    }



    override fun insert(root: BRNode<K, T>?, key: K, value: T) {
        if (this.root==root && root==null) {
            this.root = BRNode(key, value)
            this.root?.color = 0
        }
        if (root==null)
            return
       if (root.key<=key) {
            if (root.right==null)
                root.right = BRNode(key, value)
            else {
                insert(root.right, key, value)
                balance_insert(root, 0)
            }
        } else {
            if (root.left==null)
                root.left = BRNode(key, value)
            else {
                insert(root.left, key, value)
                balance_insert(root, 1)
            }
        }
    }

    private fun balance_delete(root: BRNode<K, T>, direction: Byte) {
        var brother = if (root.parent?.left==root) root.parent?.right else root.parent?.left
        if ((brother?.color ?: 0).toInt() ==1) {
            if (brother==root.parent?.left)
                rightRotation(root.parent)
            else
                leftRotation(root.parent)
        } else {
            if ((brother?.right?.color ?:0)+(brother?.left?.color ?:0) ==0){
                brother?.color=1
                root.parent?.color=0
            } else if (brother==root.parent?.right && (brother?.right?.color ?:0)==0.toByte() && (brother?.left?.color ?:0)==1.toByte()) {
                rightRotation(brother)
            } else if (brother==root.parent?.left && (brother?.left?.color ?:0)==0.toByte() && (brother?.right?.color ?:0)==1.toByte()) {
                leftRotation(brother)
            } else if (brother == root.parent?.right && (brother?.right?.color ?: 0).toInt() == 1) {
                //затычка
                val color_brother=brother?.color ?: 0
                brother?.right?.color=0
                leftRotation(root.parent)
                root.parent?.color=0
                brother?.color=color_brother
            } else if (brother == root.parent?.left && (brother?.left?.color ?: 0).toInt() == 1) {
                val color_brother=brother?.color ?: 0
                brother?.left?.color=0
                rightRotation(root.parent)
                root.parent?.color=0
                brother?.color=color_brother
            }
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
                else {
                    val parent=root.parent
                    root.parent?.right=root.left
                    if ((root.parent?.key ?: root.key) <= root.key)
                        root.left?.parent = parent
                    else
                        root.left?.parent = parent
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