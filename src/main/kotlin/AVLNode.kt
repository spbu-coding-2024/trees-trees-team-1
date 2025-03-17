class AVLNode<K: Comparator<K>, T>(override var key: K, override var value: T) : Node<K, T, AVLNode<K, T>>() {
    private var height: Int=0
    private fun checkHeight(): Int {}
}