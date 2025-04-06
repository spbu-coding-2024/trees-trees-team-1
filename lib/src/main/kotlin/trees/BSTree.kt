package trees

import nodes.BSNode

/**
 * Реализация бинарного дерева поиска (BST).
 * @param K Тип ключа (должен поддерживать сравнение)
 * @param T тип хранимых значений
 */

class BSTree<K: Comparable<K>, T>():BinaryTree<K, T, BSNode<K, T>>() {

    /**
     * Функция для вставки нового элемента.
     * Вставляет пару ключ значение в дерево или обновляет значение если ключ уже существует.
     *
     * @param key Ключ для вставки
     * @param value Значение для связывания с ключом
     * @param root Текущий узел
     */
    override fun insert(key: K, value: T, root: BSNode<K, T>?) {
        if (root == null) {
            this.root = BSNode(key, value)
            return
        }

        when {
            key == root.key -> root.value = value // Если такой ключ уже есть обновляем значение
            key > root.key -> root.right = if (root.right == null) BSNode(key, value) else {
                insert(key, value, root.right)
                root.right
            }

            else -> root.left = if (root.left == null) BSNode(key, value) else {
                insert(key, value, root.left)
                root.left
            }
        }

    }

    /**
     * Внутренняя рекурсивная функция для удаления узла по ключу
     *
     * @param key Ключ для удаления
     * @param root Текущий узел
     *
     * @return Новый корень дерева после удаления
     */
    private fun deleteHelper(key: K, root: BSNode<K, T>?): BSNode<K, T>? {

        if (root == null) return null

        if (key > root.key) root.right = deleteHelper(key, root.right)
        if (key < root.key) root.left = deleteHelper(key, root.left)

        if (key == root.key) {

            // узел с одним потомком или без потомков
            if (root.left == null) return root.right
            if (root.right == null) return root.left

            // узел с двумя потомками: находим преемника
            val ceiling = findCeiling(root.right) ?: return root

            root.key = ceiling.key
            root.value = ceiling.value
            root.right = deleteHelper(ceiling.key, root.right) // удаляем преемника

        }
        return root
    }

    /** {@Link BinaryTree # delete(key: K, root: BSNode<K, T>? */
    override fun delete(key: K, root: BSNode<K, T>?) {
        this.root = deleteHelper(key, root ?: root)
    }

    /**{@link BinaryTree # find(key: K, root: BRNode<K, T>?): Boolean*/
    override fun find(key: K, root: BSNode<K, T>?): Boolean {
        return findNode(key, root) != null

    }

    /**
     * Функция для поиска узла
     *
     * @param key Ключ для поиска узла
     * @param root Текущий узел
     *
     * @return Возвращает найденный узел
     */
    private fun findNode(key: K?, root: BSNode<K, T>?): BSNode<K, T>? {
        if (root == null || key == null) return null
        return when {
            key == root.key -> root
            key < root.key -> findNode(key, root.left)
            else -> findNode(key, root.right)
        }
    }

    /**{@link BinaryTree # peek(key: K, root: BRNode<K, T>?): T?*/
    override fun peek(key: K, root: BSNode<K, T>?): T? {
        return findNode(key, root)?.value
    }

    /**{@link BinaryTree # findParent(key: K, root: BRNode<K, T>?): K?*/
    override fun findParent(key: K, root: BSNode<K, T>?): K? {
        if (root == null) return null

        var current = root // переменная для хранения предшественника
        var parent: K? = null
        var found = false

        while (current != null) {
            if (current.key == key) {
                found = true
                break
            }
            parent = current.key
            current = if (key < current.key) current.left else current.right
        }
        return if (found) parent else null
    }

    /**{@link BinaryTree # findParent(key: K, root: BRNode<K, T>?): K?*/
    override fun findCeiling(root: BSNode<K, T>?): BSNode<K, T>? {
        if (root == null) return null
        var current = root

        while (current?.left != null) {
            current = current.left
        }
        return current
    }
}