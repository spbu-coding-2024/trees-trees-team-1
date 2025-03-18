class BRTree<K: Comparable<K>, T>(): BinaryTree<K, T, BRNode<K, T>> {
    var root:BRNode<K, T>?=null
    private var array=ArrayDeque<BRNode<K, T>?>()
    private var start=0

    private fun leftRotation(node: BRNode<K, T>?) {
        //перекрашивание
        node?.color=0
        node?.parent?.color=1
        //поворот
        val p=node?.parent?.parent
        node?.parent?.right=node?.left
        node?.parent?.right?.parent=node?.parent
        //println(node?.parent?.key)
        node?.left=node?.parent
        node?.left?.parent=node
        //переподвешивание
        if (p==null) {
            node?.parent = null
            node?.color=0
            this.root=node
        } else {
            node.parent=p
            if (p.key<= node.key)
                p.right=node
            else
                p.left=node
        }
    }

    private fun rightRotation(node: BRNode<K, T>?) {
        node?.color=0
        node?.parent?.color=1
        //перекрашивание
        //поворот
        val p=node?.parent?.parent
        node?.parent?.left=node?.right
        node?.parent?.left?.parent=node?.parent
        //println(node?.parent?.key)
        node?.right=node?.parent
        node?.right?.parent=node
        //переподвешивание
        if (p==null) {
            node?.parent = null
            node?.color=0
            this.root=node
        } else {
            node.parent=p
            if (p.key<= node.key)
                p.right=node
            else
                p.left=node
        }
    }
    //direction - 0-right 1-left
    private fun balance_insert(root: BRNode<K, T>, direction: Int) {
        val uncle=if (root.parent?.left==root) root.parent?.right else root.parent?.left
        if ((uncle?.color ?: 0).toInt() ==1 && root.color.toInt() ==1) {
            uncle?.color=0
            root.color=0
            root.parent?.color=if (root.parent != this.root) 1 else 0
            if (root == root.parent?.right)
                balance_insert(root.parent?.parent ?: return, 1)
            else
                balance_insert(root.parent?.parent ?: return, 0)
        } else if ((uncle?.color ?: 0).toInt() ==0 && root.color.toInt() ==1) {
            if (direction==1 && root==root.parent?.right) {
                leftRotation(root)
            } else if (direction==0 && root==root.parent?.right){
                val t=root.left
                rightRotation(t)
                leftRotation(t)
            }else if (direction==0 && root==root.parent?.left) {
                rightRotation(root)
            } else if (direction==1 && root==root.parent?.left){
                val t=root.right
                leftRotation(t)
                rightRotation(t)
            }
        }
    }

    override fun insert(root: BRNode<K, T>?, key: K, value: T) {
        if (this.root==root && root==null) {
            this.root = BRNode(key, value)
            this.root?.color = 0
            return
        }
        if (root==null)
            return
        if (root.key<=key) {
            if (root.right == null){
                root.right = BRNode(key, value, root)
                balance_insert(root, 1)
            } else
                insert(root.right, key, value)

        } else {
            if (root.left==null) {
                root.left = BRNode(key, value, root)
                balance_insert(root, 0)
            } else
                insert(root.left, key, value)

        }

    }

    private fun balance_delete(root: BRNode<K, T>?) {
        println("${root?.key} ----------")
        if (root==root?.parent?.left) {
            var brother=root?.parent?.right
            if (brother?.color?.toInt() ==1) {
                brother.color=0
                root?.parent?.color=1
                leftRotation(root?.parent)
                brother=brother.parent?.right
            }
            if (brother?.left?.color?.toInt() ==0 && brother.right?.color?.toInt() ==0) {
                brother.color=1
                balance_delete(root?.parent)
            } else {
                if ((brother?.right?.color ?: 0 ).toInt()==0) {
                    brother?.left?.color=0
                    brother?.color=1
                    rightRotation(brother)
                    brother=root?.parent?.right
                }
                brother?.color=root?.parent?.color ?: 0
                root?.parent?.color=0
                brother?.right?.color=0
                leftRotation(root?.parent)
            }
        } else {
            var brother=root?.parent?.left
            if (brother?.color?.toInt() ==1) {
                brother.color=0
                root?.parent?.color=1
                rightRotation(root?.parent)
                brother=brother.parent?.left
            }
            if (brother?.left?.color?.toInt() ==0 && brother.right?.color?.toInt() ==0) {
                brother.color=1
                balance_delete(root?.parent)
            } else {
                if ((brother?.left?.color ?: 0 ).toInt()==0) {
                    brother?.right?.color=0
                    brother?.color=1
                    leftRotation(brother)
                    brother=root?.parent?.left
                }
                brother?.color=root?.parent?.color ?: 0
                root?.parent?.color=0
                brother?.left?.color=0
                leftRotation(root?.parent)
            }
        }
        this.root?.color=0
    }


    override fun delete(root: BRNode<K, T>?, key: K) {
        if (root==null)
            return
        else if (root.key==key) {
            var child:BRNode<K, T>?=null
            if (root.right==null) {
                if (root.parent==null) {
                    this.root = root.left
                    this.root?.color=0
                    return
                } else {
                    child = root.left
                    root.left=null
                }
            } else {
                if (root.right?.left!=null)
                    child=findSealing(root.right)
                else {
                    child=root.right
                    root.right = null
                }
            }
            println(root?.right?.key)
            println("---------")
            child?.parent=root.parent
            child?.left=root.left
            child?.right=root?.right
            child?.color=root?.color ?: 0
            if (root==root.parent?.left)
                root.parent?.left=child
            else
                root.parent?.right=child
            if (root.color.toInt() ==0) {
                if (child?.color?.toInt() ==1)
                    child.color=0
                else
                    balance_delete(root)
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
        this.iterator()
        if (root==null)
            return null
        return if (root.key==key) root.value else if (root.key>key) peek(root.right, key) else peek(root.left, key)
    }

    override fun findParent(root: BRNode<K, T>?, key: K): K? {
        return root?.parent?.key
    }

    override fun findSealing(root: BRNode<K, T>?): BRNode<K,T> {
        if (root?.left==null && root!=null) {
            println(root.key)
            root.parent?.left=null
            return root
        } else
            return findSealing(root?.left)
    }


    override fun printNodes() {
        TODO("Not yet implemented")
    }

    override fun hasNext(): Boolean {
        if (start==0) {
            array.addLast(root)
            start=1
        }
        //println(array.size)
        if (array.isNotEmpty())
            return true
        return false
    }

    override fun next(): Pair<K?, Byte> {
        val t=array.removeFirst()
        if (t!=null) {
            array.addLast(t.left)
            array.addLast(t.right)
        }
        return Pair(t?.key, t?.color ?: 0)
    }


}

fun main() {
    var t=BRTree<Int, Int>()
    t.insert(t.root, 5, 4)
    t.insert(t.root, 4, 4)
    t.insert(t.root, 3, 4)

t.insert(t.root, 7, 4)
t.insert(t.root, 2, 4)
t.insert(t.root, 6, 4)
t.insert(t.root, 9, 4)
t.insert(t.root, 1,0)
t.insert(t.root, 10,11)
t.insert(t.root, 11, 7)
   t.insert(t.root, 12,1)
    t.insert(t.root, 13,1)
t.insert(t.root, 14,1)
t.insert(t.root, 15,1)
//
 t.insert(t.root, 16,0)
t.insert(t.root, 17,0)
 t.insert(t.root, 18,0)
t.insert(t.root, 19,0)
t.delete(t.root, 11)
for (i in t) {
    println(i)

}

}