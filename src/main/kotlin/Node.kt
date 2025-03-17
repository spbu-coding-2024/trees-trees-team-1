abstract class Node<K: Comparator<K>, T, L> {
    abstract var key:K
    abstract var value: T
    var left: L?=null
    var  right: L?=null
}