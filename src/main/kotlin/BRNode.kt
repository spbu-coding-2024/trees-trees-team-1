class BRNode<K: Comparable<K>, T>(override var key: K, override var value: T, var parent: BRNode<K, T>?=null): Node<K, T, BRNode<K, T>>() {
    //0-black
    //1-red
     var color: Int=1
}

