class BRTree<K: Comparable<K>, T>(): BinaryTree<K, T, BRNode<K, T>> {
    var root:BRNode<K, T>?=null
    private var array=ArrayDeque<BRNode<K, T>?>()
    private var start=0

    private fun leftRotation(node: BRNode<K, T>?) {
        //println(node?.key)
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
        println(node?.key)
        node?.color=0
        node?.parent?.color=1
        //перекрашивание
        //поворот
        //println(node?.parent?.key)
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
            println("${p?.key} ${node?.key} ${node?.right?.key} ${node?.left?.key}")
            node.parent=p
            if (p.key<= node.key)
                p.right=node
            else
                p.left=node
            println("${p?.right?.key} ${p?.left?.key}")
        }
    }
    //direction - 0-right 1-left
    private fun balance_insert(root: BRNode<K, T>, direction: Int) {
        val uncle=if (root.parent?.left==root) root.parent?.right else root.parent?.left
        if ((uncle?.color ?: 0).toInt() ==1 && root.color.toInt() ==1) {
            println("CC")
            uncle?.color=0
            root.color=0
            root.parent?.color=if (root.parent != this.root) 1 else 0
        } else if ((uncle?.color ?: 0).toInt() ==0 && root.color.toInt() ==1) {
            if (direction==1 && root==root.parent?.right) {
                println("LL")
                leftRotation(root)
            } else if (direction==0 && root==root.parent?.right){
                println("RL")
                var t=root.left
                rightRotation(t)
                leftRotation(t)
            }else if (direction==0 && root==root.parent?.left) {
                println("RR")
                rightRotation(root)
            } else if (direction==1 && root==root.parent?.left){
                println("LR")
                var t=root.right
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
            } else {
                insert(root.right, key, value)

            }

        } else {
            if (root.left==null) {
                //println("$key .................. ${root?.key}")
                root.left = BRNode(key, value, root)
                balance_insert(root, 0)
            } else {
                insert(root.left, key, value)

            }


                //println("${root.key} ++++++++")


        }

    }

    private fun balance_delete(root: BRNode<K, T>) {
        if ((root.color ?: 0).toInt() ==1)
            return
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
            balance_delete(root)
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
        var t=array.removeFirst()
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
for (i in t) {
    println(i)

}

}