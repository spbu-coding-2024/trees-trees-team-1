class BRNode<K: Comparable<K>, T>(override var key: K, override var value: T, var parent: BRNode<K, T>?): Node<K, T, BRNode<K, T>>() {
     var color: Int=BRTree.RED
}

