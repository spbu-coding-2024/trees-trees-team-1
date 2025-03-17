abstract class Node<K: Comparator<K>, T, L> {
    protected abstract var key:K
    protected abstract var value: T
    protected var left: L?=null
    protected var  right: L?=null
}