class BRNode<K: Comparable<K>, T>(override var key: K, override var value: T, var parent: BRNode<K, T>?=null): Node<K, T, BRNode<K, T>>() {

    //0-black
    //1-red
     var color: Byte=1
    //returns type of rotation
    //0 - None, 1 - LL, 2 -RR, 3 -LR, 4 - RL
    fun checkColors(): Byte {return 0}
}

