package test.BSTree.BSTree

import trees.BSTree
import nodes.BSNode
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag

import kotlin.math.max
import kotlin.test.assertTrue


import kotlin.random.Random
import kotlin.test.assertEquals
import kotlin.test.assertEquals

@Tag("BSTree")
@Tag("PropertyTests")

class Property {

    private fun isValidBST(root: BSNode<Int, Int>?, min: Int?, max:Int?) : Boolean {
        if (root == null) return true

        if ((min != null && root.key <= min) || (max != null && root.key >= max)) return false

        return isValidBST(root.left, min, root.key) && isValidBST(root.right, root.key, max)

    }

    private fun countNodes (root: BSNode<Int, String>?): Int {
        if (root == null) return 0
        return 1 + countNodes(root.left) + countNodes(root.right)
    }

    private fun findHeight(root: BSNode<Int, String>?): Int {
        if (root == null) return 0
        return 1 + max(findHeight(root.left), findHeight(root.right))
    }

    private fun findMax(root: BSNode<Int, String>?): Int? {
        if (root == null) return null
        return findMax(root.right) ?: root.key
    }

    private fun findMin(root: BSNode<Int, String>?): Int? {
        if (root == null) return null
        return findMin(root.left) ?: root.key
    }

    @Tag("insert")
    fun `should maintain BST properties after insertion`() {
        val tree = BSTree<Int, Int>()
        tree.insert(5, 1)
        tree.insert(3, 1)
        tree.insert(7, 1)
        assertTrue(isValidBST(tree.root, null, null))
    }

}
