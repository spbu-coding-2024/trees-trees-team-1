package nodes

/**Класс, реализующий узел красно-черного дерева
 * @property color цвет узла (красный/черный [trees.BRTree.Companion])
 * @see [nodes.Node]
 * */
class BRNode<K: Comparable<K>, T>(override var key: K, override var value: T, var parent: BRNode<K, T>?): Node<K, T, BRNode<K, T>>() {
     var color: Int= RED
     /**
      * Companion object для передачи глобальных переменных, обозначающих цвета узла, внутрь класса
      */
     companion object {
          const val RED=1
          const val BLACK=0
     }
}

