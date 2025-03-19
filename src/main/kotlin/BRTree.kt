class BRTree<K: Comparable<K>, T>(): BinaryTree<K, T, BRNode<K, T>> {
    var root:BRNode<K, T>?=null
    private var array=ArrayDeque<BRNode<K, T>?>()
    private var start=0

    private fun leftRotation(node: BRNode<K, T>?) {
        //перекрашивание
        //поворот
        val p=node?.parent?.parent
        //println("${p?.key} --------------")
        node?.parent?.right=node?.left
        node?.parent?.right?.parent=node?.parent
        //println(node?.parent?.key)
        node?.left=node?.parent
        node?.left?.parent=node
        //переподвешивание
        if (p==null) {
            node?.parent = null
            this.root=node
        } else {
            node.parent=p
            if (p.key<= node.key) {
                p.right = node
                //println("*")
            } else {
                //println("-")
                p.left = node
            }
            //println("${node?.parent?.key}")
        }
    }

    private fun rightRotation(node: BRNode<K, T>?) {
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
                root.color=0
                root.parent?.color=1
                leftRotation(root)
            } else if (direction==0 && root==root.parent?.right){
                val t=root.left
                t?.color=0
                t?.parent?.color=1
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
                t?.color=0
                t?.parent?.color=1
                leftRotation(t)
                t?.color=0
                t?.parent?.color=1
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
//
//    private fun deleteOneZero() {
//    if (root.right==null) {
//        swapvalues(root, root.left ?: return)
//        root.left=null
//    } else if (root.left==null) {
//        swapvalues(root, root.right ?: return)
//        root.right=null
//    }
//}

    fun deleteRed(root: BRNode<K, T>?) {
        if (root?.right == null && root?.left == null) {
            if (root?.parent == null) {
                this.root = null
            } else if (root.parent?.left == root)
                root.parent?.left = null
            else
                root.parent?.right = null
            //два ребенка
        } else {
            var sub: BRNode<K, T>?
            if (root.left==null)
                sub=root.right
            else
                sub=findSealing(root.left)
            println(sub?.key)
                swapvalues(root, sub?: return)
                if ((sub.color).toInt() ==1)
                    deleteRed(sub)
                else
                    deleteBlack(sub)
            }
    }


    fun deleteBlack(root: BRNode<K, T>?) {
        println(root?.key)
        if (root?.right!=null && root.left!=null) {

            var sub: BRNode<K, T>?=root.left
            if (root?.left!=null)
                sub=findSealing(root.left)
            println(sub?.key)
            swapvalues(root, sub ?: return)
            if ((sub.color).toInt() ==1)
                deleteRed(sub)
            else
                deleteBlack(sub)
            //один ребенок
        } else if (root?.right!=null){
            swapvalues(root.right ?: return, root)
            if (root.right?.color?.toInt() ==0)
                deleteBlack(root.right)
            else
                deleteRed(root.right)
        } else if (root?.left!=null){
            swapvalues(root.left ?: return, root )
            if (root.left?.color?.toInt() ==0)
                deleteBlack(root.left)
            else
                deleteRed(root.left)
            //нет детей
        } else {
            println("8788888888888888")
                if (root?.parent == null) {
                    this.root = null
                } else if (root.parent?.left == root)
                    root.parent?.left = null
                else
                    root.parent?.right = null
                delete_balance(root)

        }
    }

    fun delete_balance(root: BRNode<K, T>?) {
        println(root?.key)
        println(root?.parent?.key)
        println(root?.parent?.right?.key)
        println(root?.parent?.right?.right?.color)
        println(root?.parent?.right?.left?.color)
        println("************************************************************")
        if (root==root?.parent?.left) {

            if ((root?.parent?.right?.color ?: 0).toInt() ==0) {
                println("...............................................")
                if ((root?.parent?.right?.right?.color ?: 0).toInt() == 1) {
                    println(root?.parent?.parent?.key)
                    println("4444444444444444444444444444444444444444444444444444444444444444444444444444444444")
                    val color = root?.parent?.color ?: 0
                    root?.parent?.right?.color = color
                    root?.parent?.color=0
                    root?.parent?.right?.right?.color = 0
                    println("${root?.parent?.right?.right?.key}++++++++ ")

                    leftRotation(root?.parent?.right)

                } else if ((root?.parent?.right?.right?.color ?: 0).toInt() == 0 && (root?.parent?.right?.left?.color
                        ?: 0).toInt() == 1
                ) {
                    root?.parent?.right?.left?.color = 0
                    root?.parent?.right?.color = 1
                    root?.parent?.right?.right?.color = 0
                    val color = root?.parent?.right?.color ?: 0
                    root?.parent?.color = root?.color ?: 0
                    root?.color = color
                    rightRotation(root?.parent?.right)
                    leftRotation(root?.parent)


                } else if ((root?.parent?.right?.right?.color ?: 0).toInt() == 0 && (root?.parent?.right?.left?.color
                        ?: 0).toInt() == 0
                ){
                    println(",,,,,,,")
                    root?.color = 0
                    root?.parent?.right?.color = 1
                    if (root != this.root && (root?.parent?.color?.toInt() ?: 0) == 0)
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
                    leftRotation(root?.parent?.left)
                    rightRotation(root?.parent)



                } else if ((root?.parent?.left?.right?.color ?: 0).toInt() == 0 && (root?.parent?.left?.left?.color
                        ?: 0).toInt() == 0
                ){
                    println("hhhhhhhhhhhhhhhhhhh ${root?.parent?.right?.key}")
                    root?.color = 0
                    root?.parent?.left?.color = 1
                    println(root?.key)
                    println(root?.parent?.key)
                    if (root != this.root && (root?.parent?.color?.toInt() ?: 0) == 0)
                        delete_balance(root?.parent)
                }
            } else {

                rightRotation(root?.parent?.left)
                root?.color = 1
                root?.parent?.left?.color = 0
            }

        }
        println(root?.parent?.key)
        println("::::::::::::::::::::::::::::::::::::::::::;")

    }

    fun swapvalues(root: BRNode<K, T>, second:BRNode<K, T>) {
        val tempkey=root.key
        val tempval=root.value
        root.key=second.key
        root.value=second.value
        second.key=tempkey
        second.value=tempval
    }

    override fun delete(root: BRNode<K, T>?, key: K) {
        if (root==null)
            return
        else if (root.key==key) {
            //удаляем красную
            if (root.color.toInt() ==1) {
                println("---------------")
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
        if (root?.right==null && root!=null) {
            root.parent?.right=null
            println(root.key)
            return root
        } else
            return findSealing(root?.right)
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
t.delete(t.root, 6)
    var y=0
for (i in t) {
    println(i)
    y++
    if (y>50)
        break

}

}