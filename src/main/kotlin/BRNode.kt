<<<<<<< HEAD
<<<<<<< HEAD
class BRNode<K: Comparable<K>, T>(override var key: K, override var value: T, var parent: BRNode<K, T>?=null): Node<K, T, BRNode<K, T>>() {
=======
class BRNode<K: Comparable<K>, T>(override var key: K, override var value: T, var parent: BRNode<K, T>?): Node<K, T, BRNode<K, T>>() {
>>>>>>> 2c8f01f (fix deletion balance in variant with black uncle red closest nephews)
    //0-black
    //1-red
     var color: Int=1
=======
class BRNode<K: Comparable<K>, T>(override var key: K, override var value: T, var parent: BRNode<K, T>?): Node<K, T, BRNode<K, T>>() {
     var color: Int=BRTree.RED
>>>>>>> 238188d (add const values of colors through companion oblect)
}

