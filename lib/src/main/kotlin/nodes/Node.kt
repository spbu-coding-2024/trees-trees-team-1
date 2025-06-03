package nodes

/**
 *  Абстрактный класс, задающий структуру различных модификаций узлов бинарных деревьев, которые можно создать на их основе
 *  @param K тип ключа значения
 *  @param T тип значения
 *  @param L тип (класс) указателей на потомков данного узла (реализуют абстрактный класс [Node])
 *  @property key ключ, по которому определяется положение узла в дереве
 *  @property value значение, хранимое в узле
 *  @property left левый (меньший) потомок узла
 *  @property right правый (больший) потомок узла
 *  */

abstract class Node<K: Comparable<K>, T, L: Node<K, T, L>> {
    internal abstract var key:K
    internal abstract var value: T
    var left: L?=null
    var  right: L?=null
}
