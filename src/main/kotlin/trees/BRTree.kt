package trees

import nodes.BRNode

class BRTree<K: Comparable<K>, T>: BinaryTree<K, T, BRNode<K, T>>() {
    /**
     * Companion object для передачи глобальных переменных, обозначающих цвета узла, внутрь класса
     */
    companion object {
        const val RED=1
        const val BLACK=0
    }

    /**
     * Функция левого поворота поддерева
     * @param node - узел, относительно которого происходит поворот, причем он является центральным из тройки, которая меняет свое положение
     * (то есть он становится новым корнем данного поддерева)
     */
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

    /**
     * Функция правого поворота поддерева
     * @param node - узел, относительно которого происходит поворот, причем он является центральным из тройки, которая меняет свое положение
     * (то есть он становится новым корнем данного поддерева)
     */
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

    /**
     * Функция балансировки после вставки нового узла
     * @param root узел, относительно которого рассматривается корректность дерева
     * @param direction указание, в каком поддереве лежит вставленный элемент
     * (0 - в левом, 1 - в правом)
     */
    private fun balanceInsert(root: BRNode<K, T>, direction: Int) {
        /*Ссылка на брата root (по совместительству дядя вставленного элемента)*/
        val uncle=if (root.parent?.left==root) root.parent?.right else root.parent?.left
        /*Если узел черный - дерево корректно */
        if (root.color== BLACK)
            return
        /*Случай, если дядя оказывается черным*/
        if ((uncle?.color ?: BLACK) == RED) {
            uncle?.color= BLACK
            root.color= BLACK
            root.parent?.color=if (root.parent != this.root) RED else BLACK
            if (root == root.parent?.right)
                balanceInsert(root.parent?.parent ?: return, 1)
            else
                balanceInsert(root.parent?.parent ?: return, 0)
        } else {
            /*Случай, если текущий узел - правый, при этом вставка произошла в правом поддереве*/
            if (direction==1 && root==root.parent?.right) {
                root.color= BLACK
                root.parent?.color= RED
                leftRotation(root)
                /*Случай, если текущий узел - правый, при этом вставка произошла в левом поддереве*/
            } else if (direction==0 && root==root.parent?.right){
                val t=root.left
                rightRotation(t)
                t?.color= BLACK
                t?.parent?.color= RED
                leftRotation(t)
                /*Случай, если текущий узел - левом, при этом вставка произошла в правом поддереве*/
            }else if (direction==0 && root==root.parent?.left) {
                root.color= BLACK
                root.parent?.color= RED
                rightRotation(root)
                /*Случай, если текущий узел - левый, при этом вставка произошла в левом поддереве*/
            } else if (direction==1 && root==root.parent?.left){
                val t=root.right
                leftRotation(t)
                t?.color= BLACK
                t?.parent?.color= RED
                rightRotation(t)
            }
        }
    }

    /**{@link BinaryTree # insert(key: K, value: T, root: BRNode<K, T>?)}*/
    @Override
    override fun insert(key: K, value: T, root: BRNode<K, T>?) {
        /*Случай вставки в корень дерева*/
        if (this.root==root && root==null) {
            this.root = BRNode(key, value, null)
            this.root?.color = BLACK
            return
        }
        if (root==null)
            return
        /*Поиск поддерева для продолжения вставки и сама вставка*/
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

    /**Функция удаления красного узла
     * @param root удаляемый узел*/
    private fun deleteRed(root: BRNode<K, T>?) {
        /*Случай удаления узла*/
        if (root?.right == null && root?.left == null) {
            if (root?.parent?.left == root)
                root?.parent?.left = null
            else
                root?.parent?.right = null
            /*Случай удаления узла с двумя потомками*/
        } else {
            val sub: BRNode<K, T>? = if (root.left == null) root.right else findCeiling(root.left)
            swapValues(root, sub ?: return)
            if (sub.color == RED)
                deleteRed(sub)
            else
                deleteBlack(sub)
        }
    }

    /**Функция удаление черного узла
     * @param root удаляемый узел*/
    private fun deleteBlack(root: BRNode<K, T>?) {
        /*Случай, если левое поддерево непустое; поиск узла для замещения*/
        if (root?.left!=null) {
            val sub: BRNode<K, T>?=if (root.left?.right!=null) findCeiling(root.left) else root.left
            swapValues(root, sub ?: return)
            if (sub.color == RED)
                deleteRed(sub)
            else
                deleteBlack(sub)
            /*Случай, если только правое поддерево непустое */
        } else if (root?.right!=null){
            swapValues(root.right ?: return, root)
            if (root.right?.color == BLACK)
                deleteBlack(root.right)
            else
                deleteRed(root.right)
            /*Случай, если удаляемый элемент - лист (нет потомков) */
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

    /**{@link BinaryTree # delete(key: K, root: BRNode<K, T>?)} */
    @Override
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

    /**
     * Функция балансировки после удаления нового узла
     * @param root узел, относительно которого рассматривается корректность дерева
     * */
    private fun deleteBalance(root: BRNode<K, T>?) {
        /*Текущий узел - левый*/
        if (root==root?.parent?.left) {
            /*Случай, если дядя оказался черным*/
            if ((root?.parent?.right?.color ?: BLACK) == BLACK) {
                /*Случай, если его правый предок красный*/
                if ((root?.parent?.right?.right?.color ?: BLACK) == RED) {
                    val color = root?.parent?.color ?: BLACK
                    root?.parent?.right?.color = color
                    root?.parent?.color= BLACK
                    root?.parent?.right?.right?.color = BLACK
                    leftRotation(root?.parent?.right)
                    /*Случай, если его левый предок красный, а правый - черный*/
                } else if ((root?.parent?.right?.right?.color ?: BLACK) == BLACK
                    && (root?.parent?.right?.left?.color ?: BLACK) == RED
                ) {
                    root?.parent?.right?.left?.color = BLACK
                    root?.parent?.right?.color = RED
                    root?.parent?.right?.right?.color = BLACK
                    val color = root?.parent?.right?.color ?: BLACK
                    root?.parent?.color = root?.color ?: BLACK
                    root?.color = color
                    rightRotation(root?.parent?.right?.left)
                    leftRotation(root?.parent?.right)
                    /*Случай, если его левый предок черный, а правый - черный*/
                } else if ((root?.parent?.right?.right?.color ?: BLACK) == BLACK
                    && (root?.parent?.right?.left?.color ?: BLACK) == BLACK
                ){
                    root?.color = BLACK
                    root?.parent?.right?.color = RED
                    if (root != this.root && (root?.parent?.color ?: BLACK) == BLACK)
                        deleteBalance(root?.parent)
                }
                /*Случай, если дядя оказался красным*/
            } else {
                leftRotation(root?.parent?.right)
                root?.parent?.color = RED
                root?.parent?.right?.color = BLACK
                deleteBalance(root)
            }
            /*Текущий узел - правый*/
        } else {
            /*Случай, если дядя оказался черным*/
            if ((root?.parent?.left?.color ?: BLACK) == BLACK) {
                /*Случай, если его левый предок красный*/
                if ((root?.parent?.left?.left?.color ?: BLACK) == RED) {
                    root?.parent?.left?.left?.color= BLACK
                    val color=root?.parent?.left?.color ?: BLACK
                    root?.parent?.color=root?.color ?: BLACK
                    root?.color =color
                    rightRotation(root?.parent?.left)
                    /*Случай, если его правый предок красный, а левый - черный*/
                } else if ((root?.parent?.left?.left?.color ?: BLACK) == BLACK
                    && (root?.parent?.left?.right?.color ?: BLACK) == RED
                ) {
                    root?.parent?.left?.right?.color= BLACK
                    root?.parent?.left?.color= RED
                    root?.parent?.left?.left?.color= RED
                    val color=root?.parent?.left?.color ?: BLACK
                    root?.parent?.color=root?.color ?: BLACK
                    root?.color =color
                    leftRotation(root?.parent?.left?.right)
                    rightRotation(root?.parent?.left)
                    /*Случай, если его левый предок черный, а правый - черный*/
                } else if ((root?.parent?.left?.right?.color ?: BLACK) == BLACK
                    && (root?.parent?.left?.left?.color ?: BLACK) == BLACK
                ){
                    root?.color = BLACK
                    root?.parent?.left?.color = RED
                    if (root != this.root && (root?.parent?.color ?: BLACK) == BLACK)
                        deleteBalance(root?.parent)
                }
                /*Случай, если дядя оказался красным*/
            } else {
                rightRotation(root?.parent?.left)
                root?.parent?.color = RED
                root?.parent?.left?.color = BLACK
                deleteBalance(root)
            }
        }
    }

    /**Функция обмена значений узлами
     * @param first
     * @param second - узлы, между которыми происходит обмен*/
    private fun swapValues(first: BRNode<K, T>, second:BRNode<K, T>) {
        val tempKey=first.key
        val tempVal=first.value
        first.key=second.key
        first.value=second.value
        second.key=tempKey
        second.value=tempVal
    }

    /**{@link BinaryTree # find(key: K, root: BRNode<K, T>?): Boolean*/
    @Override
    override fun find(key: K, root: BRNode<K, T>?): Boolean {
        if (root==null)
            return false
        return if (root.key==key) true else if (root.key<key) find(key, root.right) else find(key, root.left)
    }
    /**{@link BinaryTree # peek(key: K, root: BRNode<K, T>?): T?*/
    @Override
    override fun peek(key: K, root: BRNode<K, T>?): T? {
        if (root==null)
            return null
        return if (root.key==key) root.value else if (root.key<key) peek(key, root.right) else peek(key, root.left)
    }
    /**{@link BinaryTree # findParent(key: K, root: BRNode<K, T>?): K?*/
    @Override
    override fun findParent(key: K, root: BRNode<K, T>?): K? {
        if (root==null)
            return null
        return if (root.key==key) root.parent?.key else if (root.key<key) findParent(key, root.right) else findParent(key, root.left)
    }

    /**{@link BinaryTree # findParent(key: K, root: BRNode<K, T>?): K?*/
    @Override
    override fun findCeiling(root: BRNode<K, T>?): BRNode<K,T> {
        if (root?.right==null && root!=null) {
            root.parent?.right=null
            return root
        } else
            return findCeiling(root?.right)
    }
}
