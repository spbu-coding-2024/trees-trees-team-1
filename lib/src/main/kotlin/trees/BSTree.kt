package trees

import nodes.BSNode

abstract class BSTree<K: Comparable<K>, T>(): BinaryTree<K, T, BSNode<K, T>>() {

}