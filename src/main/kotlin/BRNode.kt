class BRNode<K: Comparable<K>, T>(override var key: K, override var value: T): Node<K, T, BRNode<K, T>>() {
     var parent: BRNode<K, T>?=null
    //0-black
    //1-red
     var color: Byte=0
    fun checkColors(): Byte {return 0}
}

