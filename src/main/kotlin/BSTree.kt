abstract class BSTree<K: Comparable<K>, T>():BinaryTree<K, T, BSNode<K, T>> {
    var root:BSNode<K, T>?=null
}