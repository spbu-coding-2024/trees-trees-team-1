package test.BSTree.BSTree

import trees.BSTree
import nodes.BSNode
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue
import kotlin.random.Random
import kotlin.test.assertEquals

@Tag("BSTree")
@Tag("PropertyTests")

class Property {

    private val random = Random(42)

    private fun isValidBST(root: BSNode<Int, String>?, min: Int? = null, max:Int? = null) : Boolean {
        if (root == null) return true

        if ((min != null && root.key <= min) || (max != null && root.key >= max)) return false

        return isValidBST(root.left, min, root.key) && isValidBST(root.right, root.key, max)

    }

    private fun countNodes (root: BSNode<Int, String>?): Int {
        if (root == null) return 0
        return 1 + countNodes(root.left) + countNodes(root.right)
    }

    @Test
    @Tag("insert")
    @Tag("delete")
    fun `should maintain BST properties after insertion and deletion`() {
        val tree = BSTree<Int, String>()
        val keys = mutableSetOf<Int>()

        repeat(100) {
            val key = Random.nextInt()
            keys.add(key)
            tree.insert(key, "$key")
            assertTrue(isValidBST(tree.root), "Tree should remain valid BST after insertion")
        }
        repeat(50) {
            val key = Random.nextInt()
            keys.remove(key)
            tree.delete(key)
            assertTrue(isValidBST(tree.root), "Tree should remain valid BST after deletion")
        }

        assertEquals(keys.size, countNodes(tree.root), "Node count mismatch after deletion")
    }
}
