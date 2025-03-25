class BRTree<K: Comparable<K>, T>: BinaryTree<K, T, BRNode<K, T>>() {

<<<<<<< HEAD
<<<<<<< HEAD
=======
    /**
     * Companion object для передачи глобальных переменных, обозначающих цвета узла, внутрь класса
     */
=======
>>>>>>> 238188d (add const values of colors through companion oblect)
    companion object {
        const val RED=1
        const val BLACK=0
    }

<<<<<<< HEAD
    /**
     * Функция левого поворота поддерева
     * @param node - узел, относительно которого происходит поворот, причем он является центральным из тройки, которая меняет свое положение
     * (то есть он становится новым корнем данного поддерева)
     */
>>>>>>> a90db15 (add comments)
=======
>>>>>>> 238188d (add const values of colors through companion oblect)
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

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    //direction - 0-right 1-left

=======
>>>>>>> 91d29c7 (remake project test files structure)
=======
>>>>>>> 91d29c7 (remake project test files structure)
    private fun balance_insert(root: BRNode<K, T>, direction: Int) {
        val uncle=if (root.parent?.left==root) root.parent?.right else root.parent?.left
<<<<<<< HEAD
        if ((uncle?.color ?: 0) ==1 && root.color ==1) {
=======
    private fun balanceInsert(root: BRNode<K, T>, direction: Int) {
        val uncle=if (root.parent?.left==root) root.parent?.right else root.parent?.left
        if (root.color==0)
            return
        if ((uncle?.color ?: 0) ==1) {
>>>>>>> ed2657a (final tests)
            uncle?.color=0
            root.color=0
            root.parent?.color=if (root.parent != this.root) 1 else 0
=======
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
>>>>>>> a90db15 (add comments)
=======
        if (root.color== BLACK)
            return
        if ((uncle?.color ?: BLACK) == BLACK) {
            uncle?.color= BLACK
            root.color= BLACK
            root.parent?.color=if (root.parent != this.root) RED else BLACK
>>>>>>> 238188d (add const values of colors through companion oblect)
            if (root == root.parent?.right)
                balanceInsert(root.parent?.parent ?: return, 1)
            else
<<<<<<< HEAD
<<<<<<< HEAD
                balance_insert(root.parent?.parent ?: return, 0)
        } else if ((uncle?.color ?: 0)==0 && root.color ==1) {
=======
                balanceInsert(root.parent?.parent ?: return, 0)
        } else {
<<<<<<< HEAD
            /*Случай, если текущий узел - правый, при этом вставка произошла в правом поддереве*/
>>>>>>> a90db15 (add comments)
=======
>>>>>>> 238188d (add const values of colors through companion oblect)
=======
                balanceInsert(root.parent?.parent ?: return, 0)
        } else if ((uncle?.color ?: 0)==0) {
>>>>>>> ed2657a (final tests)
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

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    override fun insert(root: BRNode<K, T>?, key: K, value: T) {
=======
    /**{@link BinaryTree # insert(key: K, value: T, root: BRNode<K, T>?)}*/
    @Override
    override fun insert(key: K, value: T, root: BRNode<K, T>?) {
        /*Случай вставки в корень дерева*/
>>>>>>> a90db15 (add comments)
=======
    override fun insert(key: K, value: T, root: BRNode<K, T>?) {
>>>>>>> 91d29c7 (remake project test files structure)
=======
    override fun insert(key: K, value: T, root: BRNode<K, T>?) {
>>>>>>> 91d29c7 (remake project test files structure)
        if (this.root==root && root==null) {
<<<<<<< HEAD
<<<<<<< HEAD
            this.root = BRNode(key, value)
=======
            this.root = BRNode(key, value, null)
>>>>>>> 2c8f01f (fix deletion balance in variant with black uncle red closest nephews)
            this.root?.color = 0
=======
            this.root = BRNode(key, value, null)
            this.root?.color = BLACK
>>>>>>> 238188d (add const values of colors through companion oblect)
            return
        }
        if (root==null)
            return
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        if (root.key<=key) {
=======
        /*Поиск поддерева для продолжения вставки и сама вставка*/
        if (root.key<key) {
>>>>>>> a90db15 (add comments)
            if (root.right == null){
                root.right = BRNode(key, value, root)
                balanceInsert(root, 1)
=======
=======
>>>>>>> 91d29c7 (remake project test files structure)
        if (root.key<key) {
            if (root.right == null){
                root.right = BRNode(key, value, root)
                balance_insert(root, 1)
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 91d29c7 (remake project test files structure)
=======
                println(root.right?.parent?.key)
>>>>>>> 2c8f01f (fix deletion balance in variant with black uncle red closest nephews)
=======
>>>>>>> 91d29c7 (remake project test files structure)
            } else
                insert( key, value, root.right)
        } else {
            if (root.left==null) {
                root.left = BRNode(key, value, root)
<<<<<<< HEAD
<<<<<<< HEAD
                balance_insert(root, 0)
<<<<<<< HEAD
=======

                balanceInsert(root, 0)
>>>>>>> ed2657a (final tests)
=======
>>>>>>> 91d29c7 (remake project test files structure)
=======

                balance_insert(root, 0)
<<<<<<< HEAD
                println(root.left?.parent?.key)
>>>>>>> 2c8f01f (fix deletion balance in variant with black uncle red closest nephews)
=======
>>>>>>> 91d29c7 (remake project test files structure)
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
<<<<<<< HEAD
<<<<<<< HEAD
                root.parent?.right = null
=======
                root?.parent?.right = null
            /*Случай удаления узла с двумя потомками*/
>>>>>>> a90db15 (add comments)
        } else {
<<<<<<< HEAD
            val sub: BRNode<K, T>? = if (root.left==null) root.right else findSealing(root.left)
            swapvalues(root, sub?: return)
            if ((sub.color) ==1)
=======
            val sub: BRNode<K, T>? = if (root.left == null) root.right else findCeiling(root.left)
            swapValues(root, sub ?: return)
            if (sub.color == RED)
>>>>>>> 238188d (add const values of colors through companion oblect)
=======
                root?.parent?.right = null
        } else {
            val sub: BRNode<K, T>? = if (root.left == null) root.right else findCeiling(root.left)
            swapValues(root, sub ?: return)
            if ((sub.color) == 1)
>>>>>>> ed2657a (final tests)
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
            val sub: BRNode<K, T>?=if (root.left?.right!=null) findSealing(root) else root.left
=======
            val sub: BRNode<K, T>?=if (root.left?.right!=null) findSealing(root.left) else root.left
>>>>>>> 2c8f01f (fix deletion balance in variant with black uncle red closest nephews)
            swapvalues(root, sub ?: return)
=======
            val sub: BRNode<K, T>?=if (root.left?.right!=null) findCeiling(root.left) else root.left
            swapValues(root, sub ?: return)
>>>>>>> ed2657a (final tests)
            if (sub.color ==1)
=======
            val sub: BRNode<K, T>?=if (root.left?.right!=null) findCeiling(root.left) else root.left
            swapValues(root, sub ?: return)
            if (sub.color == RED)
>>>>>>> 238188d (add const values of colors through companion oblect)
                deleteRed(sub)
            else
                deleteBlack(sub)
            /*Случай, если только правое поддерево непустое */
        } else if (root?.right!=null){
<<<<<<< HEAD
<<<<<<< HEAD
            swapvalues(root.right ?: return, root)
=======
            swapValues(root.right ?: return, root)
>>>>>>> ed2657a (final tests)
            if (root.right?.color ==0)
=======
            swapValues(root.right ?: return, root)
            if (root.right?.color == BLACK)
>>>>>>> 238188d (add const values of colors through companion oblect)
                deleteBlack(root.right)
            else
                deleteRed(root.right)
            /*Случай, если удаляемый элемент - лист (нет потомков) */
        } else {
<<<<<<< HEAD
<<<<<<< HEAD
=======
            deleteBalance(root)
>>>>>>> ed2657a (final tests)
=======
            delete_balance(root)
>>>>>>> 2c8f01f (fix deletion balance in variant with black uncle red closest nephews)
            if (root?.parent == null) {
                this.root = null
            } else if (root.parent?.left == root)
                root.parent?.left = null
            else
                root.parent?.right = null
        }
    }

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    private fun delete_balance(root: BRNode<K, T>?) {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
    private fun deleteBalance(root: BRNode<K, T>?) {
>>>>>>> ed2657a (final tests)
=======
>>>>>>> 91d29c7 (remake project test files structure)
=======

>>>>>>> 2c8f01f (fix deletion balance in variant with black uncle red closest nephews)
=======
>>>>>>> 91d29c7 (remake project test files structure)
        if (root==root?.parent?.left) {
            if ((root?.parent?.right?.color ?: 0).toInt() ==0) {
                if ((root?.parent?.right?.right?.color ?: 0).toInt() == 1) {
                    val color = root?.parent?.color ?: 0
<<<<<<< HEAD
=======
    /**{@link BinaryTree # delete(key: K, root: BRNode<K, T>?)} */
    @Override
=======
                    root?.parent?.right?.color = color
                    root?.parent?.color=0
                    root?.parent?.right?.right?.color = 0
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
                    rightRotation(root?.parent?.right?.left)
                    leftRotation(root?.parent?.right)
                } else if ((root?.parent?.right?.right?.color ?: 0).toInt() == 0 && (root?.parent?.right?.left?.color
                        ?: 0).toInt() == 0
                ){
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> 2c8f01f (fix deletion balance in variant with black uncle red closest nephews)
=======
>>>>>>> 91d29c7 (remake project test files structure)
                    root?.color = 0
                    root?.parent?.right?.color = 1
                    if (root != this.root && (root?.parent?.color ?: 0) == 0)
                        deleteBalance(root?.parent)
                }
            } else {
<<<<<<< HEAD
<<<<<<< HEAD
=======

                root?.color = 1
                root?.parent?.right?.color = 0
>>>>>>> 2c8f01f (fix deletion balance in variant with black uncle red closest nephews)
                leftRotation(root?.parent?.right)
                root?.parent?.color = 1
                root?.parent?.right?.color = 0
                deleteBalance(root)
=======
                leftRotation(root?.parent?.right)
                root?.parent?.color = 1
                root?.parent?.right?.color = 0
                delete_balance(root)
>>>>>>> 91d29c7 (remake project test files structure)
            }
        } else {
            if ((root?.parent?.left?.color ?: 0).toInt() ==0) {
                if ((root?.parent?.left?.left?.color ?: 0).toInt() == 1) {
                    root?.parent?.left?.left?.color=0
                    val color=root?.parent?.left?.color ?: 0
                    root?.parent?.color=root?.color ?: 0
                    root?.color =color
                    rightRotation(root?.parent?.left)
<<<<<<< HEAD
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
                    root?.color = 0
                    root?.parent?.left?.color = 1
                    if (root != this.root && (root?.parent?.color ?: 0) == 0)
                        deleteBalance(root?.parent)
                }
            } else {
                rightRotation(root?.parent?.left)
                root?.parent?.color = 1
                root?.parent?.left?.color = 0
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

>>>>>>> ed2657a (final tests)
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
>>>>>>> a90db15 (add comments)
                    root?.parent?.right?.color = color
                    root?.parent?.color=0
                    root?.parent?.right?.right?.color = 0
                    leftRotation(root?.parent?.right)
<<<<<<< HEAD
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
=======
                    /*Случай, если его левый предок красный, а правый - черный*/
=======
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
>>>>>>> 238188d (add const values of colors through companion oblect)
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
<<<<<<< HEAD
                    /*Случай, если его левый предок черный, а правый - черный*/
                } else if ((root?.parent?.right?.right?.color ?: BLACK) == BLACK
                    && (root?.parent?.right?.left?.color ?: BLACK) == BLACK
>>>>>>> a90db15 (add comments)
                ){
=======
>>>>>>> 91d29c7 (remake project test files structure)
                    root?.color = 0
                    root?.parent?.right?.color = 1
                    if (root != this.root && (root?.parent?.color ?: 0) == 0)
                        delete_balance(root?.parent)
                }
                /*Случай, если дядя оказался красным*/
            } else {
<<<<<<< HEAD
                root?.color = 1
                root?.parent?.right?.color = 0
=======
>>>>>>> 91d29c7 (remake project test files structure)
                leftRotation(root?.parent?.right)
                root?.parent?.color = 1
                root?.parent?.right?.color = 0
                delete_balance(root)
            }
            /*Текущий узел - правый*/
        } else {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 91d29c7 (remake project test files structure)
            if ((root?.parent?.left?.color ?: 0).toInt() ==0) {
                if ((root?.parent?.left?.left?.color ?: 0).toInt() == 1) {
                    root?.parent?.left?.left?.color=0
                    val color=root?.parent?.left?.color ?: 0
                    root?.parent?.color=root?.color ?: 0
                    root?.color =color
                    rightRotation(root?.parent?.left)
=======
>>>>>>> 91d29c7 (remake project test files structure)
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
                    root?.color = 0
                    root?.parent?.left?.color = 1
                    if (root != this.root && (root?.parent?.color ?: 0) == 0)
                        delete_balance(root?.parent)
=======
            /*Случай, если дядя оказался черным*/
            if ((root?.parent?.left?.color ?: BLACK) == BLACK) {
                /*Случай, если его левый предок красный*/
=======
                } else if ((root?.parent?.right?.right?.color ?: BLACK) == BLACK
                    && (root?.parent?.right?.left?.color ?: BLACK) == BLACK
                ){
                    root?.color = BLACK
                    root?.parent?.right?.color = RED
                    if (root != this.root && (root?.parent?.color ?: BLACK) == BLACK)
                        deleteBalance(root?.parent)
                }
            } else {
<<<<<<< HEAD
                leftRotation(root?.parent?.right)
                root?.parent?.color = RED
                root?.parent?.right?.color = BLACK
                deleteBalance(root)
            }
        } else {
            if ((root?.parent?.left?.color ?: BLACK) == BLACK) {
>>>>>>> 238188d (add const values of colors through companion oblect)
                if ((root?.parent?.left?.left?.color ?: BLACK) == RED) {
                    root?.parent?.left?.left?.color= BLACK
                    val color=root?.parent?.left?.color ?: BLACK
                    root?.parent?.color=root?.color ?: BLACK
                    root?.color =color
                    rightRotation(root?.parent?.left)
<<<<<<< HEAD
                    /*Случай, если его правый предок красный, а левый - черный*/
=======
>>>>>>> 238188d (add const values of colors through companion oblect)
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
<<<<<<< HEAD
                    /*Случай, если его левый предок черный, а правый - черный*/
=======
>>>>>>> 238188d (add const values of colors through companion oblect)
                } else if ((root?.parent?.left?.right?.color ?: BLACK) == BLACK
                    && (root?.parent?.left?.left?.color ?: BLACK) == BLACK){
                    root?.color = BLACK
                    root?.parent?.left?.color = RED
                    if (root != this.root && (root?.parent?.color ?: BLACK) == BLACK)
                        deleteBalance(root?.parent)
<<<<<<< HEAD
>>>>>>> a90db15 (add comments)
                }
                /*Случай, если дядя оказался красным*/
            } else {
=======
>>>>>>> 91d29c7 (remake project test files structure)
                rightRotation(root?.parent?.left)
                root?.parent?.color = 1
                root?.parent?.left?.color = 0
                delete_balance(root)
            }
        }
    }

<<<<<<< HEAD
    private fun swapvalues(root: BRNode<K, T>, second:BRNode<K, T>) {
=======
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
>>>>>>> 238188d (add const values of colors through companion oblect)
        val tempKey=root.key
        val tempVal=root.value
        root.key=second.key
        root.value=second.value
<<<<<<< HEAD
=======
    /**Функция обмена значений узлами
     * @param first
     * @param second - узлы, между которыми происходит обмен*/
    private fun swapValues(first: BRNode<K, T>, second:BRNode<K, T>) {
        val tempKey=first.key
        val tempVal=first.value
        first.key=second.key
        first.value=second.value
>>>>>>> a90db15 (add comments)
=======
>>>>>>> 238188d (add const values of colors through companion oblect)
        second.key=tempKey
        second.value=tempVal
    }

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    override fun delete(root: BRNode<K, T>?, key: K) {
=======
    override fun delete(key: K, root: BRNode<K, T>?) {
>>>>>>> 91d29c7 (remake project test files structure)
=======
    override fun delete(key: K, root: BRNode<K, T>?) {
>>>>>>> 91d29c7 (remake project test files structure)
        if (root==null)
            return
        else if (root.key==key) {
            if (root.color==1) {
                deleteRed(root)
            } else {
                deleteBlack(root)
            }
        } else if (root.key<key)
            delete(key, root.right)
        else
            delete(key, root.left)
    }

<<<<<<< HEAD
<<<<<<< HEAD
    override fun find(root: BRNode<K, T>?, key: K): Boolean {
=======
    /**{@link BinaryTree # find(key: K, root: BRNode<K, T>?): Boolean*/
    @Override
=======
>>>>>>> 238188d (add const values of colors through companion oblect)
    override fun find(key: K, root: BRNode<K, T>?): Boolean {
>>>>>>> a90db15 (add comments)
        if (root==null)
            return false
<<<<<<< HEAD
        return if (root.key==key) true else if (root.key>key) find(root.right, key) else find(root.left, key)
=======
    override fun find(key: K, root: BRNode<K, T>?): Boolean {
        if (root==null)
            return false
        return if (root.key==key) true else if (root.key<key) find(key, root.right) else find(key, root.left)
>>>>>>> 91d29c7 (remake project test files structure)
=======
        return if (root.key==key) true else if (root.key<key) find(root.right, key) else find(root.left, key)
>>>>>>> 2c8f01f (fix deletion balance in variant with black uncle red closest nephews)
=======
    override fun find(key: K, root: BRNode<K, T>?): Boolean {
        if (root==null)
            return false
        return if (root.key==key) true else if (root.key<key) find(key, root.right) else find(key, root.left)
>>>>>>> 91d29c7 (remake project test files structure)
    }
<<<<<<< HEAD

<<<<<<< HEAD
<<<<<<< HEAD
    override fun peek(root: BRNode<K, T>?, key: K): T? {
=======
    /**{@link BinaryTree # peek(key: K, root: BRNode<K, T>?): T?*/
    @Override
    override fun peek(key: K, root: BRNode<K, T>?): T? {
>>>>>>> a90db15 (add comments)
=======
    override fun peek(key: K, root: BRNode<K, T>?): T? {
>>>>>>> 91d29c7 (remake project test files structure)
=======
    override fun peek(key: K, root: BRNode<K, T>?): T? {
>>>>>>> 91d29c7 (remake project test files structure)
        if (root==null)
            return null
        return if (root.key==key) root.value else if (root.key<key) peek(key, root.right) else peek(key, root.left)
    }
<<<<<<< HEAD

<<<<<<< HEAD
<<<<<<< HEAD
    override fun findParent(root: BRNode<K, T>?, key: K): K? {
<<<<<<< HEAD
        return root?.parent?.key
=======
    override fun findParent(key: K, root: BRNode<K, T>?): K? {
        if (root==null)
            return null
        return if (root.key==key) root.parent?.key else if (root.key<key) findParent(key, root.right) else findParent(key, root.left)
>>>>>>> 91d29c7 (remake project test files structure)
=======
        if (root==null)
            return null
        return if (root.key==key) root.parent?.key else if (root.key<key) findParent(root.right, key) else findParent(root.left, key)
>>>>>>> 2c8f01f (fix deletion balance in variant with black uncle red closest nephews)
=======
    override fun findParent(key: K, root: BRNode<K, T>?): K? {
        if (root==null)
            return null
        return if (root.key==key) root.parent?.key else if (root.key<key) findParent(key, root.right) else findParent(key, root.left)
>>>>>>> 91d29c7 (remake project test files structure)
    }

    override fun findSealing(root: BRNode<K, T>?): BRNode<K,T> {
=======
    /**{@link BinaryTree # findParent(key: K, root: BRNode<K, T>?): K?*/
    @Override
    override fun findParent(key: K, root: BRNode<K, T>?): K? {
        if (root==null)
            return null
        return if (root.key==key) root.parent?.key else if (root.key<key) findParent(key, root.right) else findParent(key, root.left)
    }

<<<<<<< HEAD
    /**{@link BinaryTree # findParent(key: K, root: BRNode<K, T>?): K?*/
    @Override
    override fun findCeiling(root: BRNode<K, T>?): BRNode<K,T> {
>>>>>>> a90db15 (add comments)
=======
    override fun findCeiling(root: BRNode<K, T>?): BRNode<K,T> {
>>>>>>> ed2657a (final tests)
        if (root?.right==null && root!=null) {
            root.parent?.right=null
            return root
        } else
            return findCeiling(root?.right)
    }
}
<<<<<<< HEAD
<<<<<<< HEAD
=======

fun main() {
    val tree=BRTree<Int, Int>()
    var values= listOf(7,5,10,2,8,6,13,1,12,15,14)
    for (i in values)
        tree.insert(i, 0)
    tree.delete(8)
    println(tree.printNodes())
}
>>>>>>> 91d29c7 (remake project test files structure)
=======

fun main() {
    val tree=BRTree<Int, Int>()
    var values= listOf(7,5,10,2,8,6,13,1,12,15,14)
    for (i in values)
        tree.insert(i, 0)
    tree.delete(8)
    println(tree.printNodes())
<<<<<<< HEAD
    //println(tree.findParent(tree.root, 6))


}
>>>>>>> 2c8f01f (fix deletion balance in variant with black uncle red closest nephews)
=======
}
>>>>>>> 91d29c7 (remake project test files structure)
