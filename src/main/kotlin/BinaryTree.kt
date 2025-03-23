/**
 * Абстрактный класс, задающий структуру различных модификаций бинарных деревьев, которые можно создать на их основе
 * @param <K> тип ключа значения
 * @param <T> тип значения
 * @param <P> тип (класс) указателей на потомков данного узла (релизуют абстрактный класс Node)
 * @see Node
 */
abstract class BinaryTree <K: Comparable<K>, T, P:Node<K, T, P>> {

    /**
     * корень дерева
     */
    var root: P?=null

    /**
     * Внутренний класс, реализующий интерфейс Iterator для возможности прохода по всем узлам дерева
     */
    inner class Iterate : Iterator<K?> {
        /**
         * Очередь для последовательного хранения узлов дерева
         */
        private var array: ArrayDeque<P?> = ArrayDeque()

        /**
         * Маркер, инициализирующий заполнение очереди
         */
        private var start: Int=0

        @Override
        override fun hasNext(): Boolean {
            if (start==0) {
                array.addLast(root)
                start=1
            }
            if (array.isEmpty())
                return false
            return true
        }

        @Override
        override fun next(): K? {
            val t=array.removeFirst()
            if (t!=null) {
                array.addLast(t.left)
                array.addLast(t.right)
            }
            return t?.key
        }

    }

    /**
     * Метод, возвращающий новый объект класса Iterate для данного дерева
     */
    fun iterator(): Iterate {
        return this.Iterate()
    }

    /**
     * Метод вставки значение в дерево
     * @param key ключ, определяющий положение значения в дереве
     * @param value значение, хранимое в узле
     * @param root ссылка на узел, начиная с которого осуществляется вставка (для рекурсивного обхода дерева); дефолтное значение установлено в корень дерева
     */
    abstract fun insert(key: K, value: T, root:P?=this.root)
    /**
     * Метод удаления значение из дерева
     * @param key ключ, по которому происходи поиск значения в дереве
     * @param root ссылка на узел, в поддереве которого осуществляется поиск узла для удаления (для рекурсивного обхода дерева); дефолтное значение установлено в корень дерева
     */
    abstract fun delete(key: K, root: P?=this.root)
    /**
     * Метод поиска значение в дереве
     * @param key ключ, по которому происходи поиск значения в дереве
     * @param root ссылка на узел, начиная с которого осуществляется поиск (для рекурсивного обхода дерева); дефолтное значение установлено в корень дерева
     */
    abstract fun find(key: K, root: P?=this.root): Boolean
    /**
     * Метод просмотра значения в дереве
     * @param key ключ, по которому происходи поиск значения в дереве
     * @param root ссылка на узел, начиная с которого осуществляется поиск (для рекурсивного обхода дерева); дефолтное значение установлено в корень дерева
     */
    abstract fun peek(key: K, root: P?=this.root): T?
    /**
     * Метод поиска родителя данного узла дереве
     * @param key ключ, по которому происходи поиск значения в дереве
     * @param root ссылка на узел, начиная с которого осуществляется поиск (для рекурсивного обхода дерева); дефолтное значение установлено в корень дерева
     */
    abstract fun findParent(key: K, root: P?=this.root): K?
    /**
     * Метод поиска наибольшего значение, меньшего данного в дереве (возвращает null, если элемент минимален)
     * @param root ссылка на узел, начиная с которого осуществляется поиск (для рекурсивного обхода дерева); дефолтное значение установлено в корень дерева
     */
    protected abstract fun findCeiling(root: P?=this.root): P?
    /**
     * Метод печати всех узлов дерева
     */
    fun printNodes(): String {
        var res= StringBuilder()
        for (elem in iterator())
            res.append("$elem ")
        return res.toString()
    }
}