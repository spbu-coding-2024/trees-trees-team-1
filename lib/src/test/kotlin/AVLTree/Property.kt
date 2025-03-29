package test.AVLTree.AVLTree

import trees.AVLTree
import nodes.AVLNode

import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.DisplayName

import kotlin.random.Random
import kotlin.math.abs
import kotlin.test.assertTrue
import kotlin.test.assertEquals

@Tag("AVLTree")
@Tag("PropertyBased")

class PropertyBasedTests {

	private fun checkBSTProperty(node: AVLNode<Int, Int>?, min: Int = Int.MIN_VALUE, 
	max: Int = Int.MAX_VALUE ): Boolean {
		if (node == null) return true
		if (node.key <= min || node.key >= max) return false

		return checkBSTProperty(node.left, min, node.key) && checkBSTProperty(node.right, node.key, max)
	}


	private fun checkBalanceProperty(node: AVLNode<Int, Int>?) : Boolean {
		if (node == null) return true
		val leftHeight = node.left?.height ?: 0
		val rightHeight = node.right?.height ?: 0
		
		return (abs(leftHeight - rightHeight) <= 1 && checkBalanceProperty(node.left)
		 && checkBalanceProperty(node.right))
	}


	private fun checkCountNodes(node: AVLNode<Int, Int>?): Int {
		if (node == null) return 0
		val countLeft = checkCountNodes(node.left)
		val countRight = checkCountNodes(node.right)
		return 1 + countLeft + countRight
	}


	@Tag("insert")
	@RepeatedTest(100)
	@DisplayName("AVLTree should be BST after insertion")
	fun `check BST property after insertion`() {
		val tree = AVLTree<Int, Int>()
		repeat(100) {
		val key = Random.nextInt()
		val value = Random.nextInt()
		tree.insert(key, value)
		assertTrue(checkBSTProperty(tree.root), "Failed BST property after insertion")
		}
	}

	@Tag("insert")
	@RepeatedTest(100)
	@DisplayName("AVLTree should be balanced after insertion")
	fun `check balance property after insertion`() {
		val tree = AVLTree<Int, Int>()
		repeat(100) {
		val key = Random.nextInt()
		val value = Random.nextInt()
		tree.insert(key, value)
		assertTrue(checkBalanceProperty(tree.root), "Failed balance property after insertion")
		}
	}

	@Tag("insert")
	@RepeatedTest(100)
	@DisplayName("Check countNodes after insertions")
	fun `countNodes should match number of inserted nodes`() {
		val tree = AVLTree<Int, Int>()
		val insertedKeys = mutableSetOf<Int>()
		repeat(100) {
			val key = Random.nextInt()
			val value = Random.nextInt()
			tree.insert(key, value)
			insertedKeys.add(key)
			assertEquals(insertedKeys.size, checkCountNodes(tree.root), 
			"Node count mismatch after insertion")
		}
	}


	@Tag("delete")
	@RepeatedTest(100)
	@DisplayName("Tree should be BST after deletion")
	fun `check bst property after deletion`() {
		val tree = AVLTree<Int, Int>()
		val keys = mutableSetOf<Int>()
		repeat(100) {
			val key = Random.nextInt()
			val value = Random.nextInt()
			tree.insert(key, value)
			keys.add(key)
		}
		repeat(50) {
			if (keys.isNotEmpty()) {
				val keyToDelete = keys.random()
				tree.delete(keyToDelete)
				keys.remove(keyToDelete)
				assertTrue(checkBSTProperty(tree.root), "Failed BST property after deletion")
			}
		}
	}

	@Tag("delete")
	@RepeatedTest(100)
	@DisplayName("Tree should be balance after deletion")
	fun `check balance property after deletion`() {
		val tree = AVLTree<Int, Int>()
		val keys = mutableSetOf<Int>()
		repeat(100) {
			val key = Random.nextInt()
			val value = Random.nextInt()
			tree.insert(key, value)
			keys.add(key)
		}
		repeat(50) {
			if (keys.isNotEmpty()) {
				val keyToDelete = keys.random()
				tree.delete(keyToDelete)
				keys.remove(keyToDelete)
				assertTrue(checkBalanceProperty(tree.root), "Failed balanced property after deletion")
			}
		}
	}


	@Tag("delete")
	@RepeatedTest(100)
	@DisplayName("Check countNodes after deletions")
	fun `countNodes should decrease after deletions`() {
		val tree = AVLTree<Int, Int>()
		val keys = mutableSetOf<Int>()
		repeat(100) {
			val key = Random.nextInt()
			val value = Random.nextInt()
			tree.insert(key, value)
			keys.add(key)
		}
		repeat(50) {
			if (keys.isNotEmpty()) {
				val keyToDelete = keys.random()
				tree.delete(keyToDelete)
				keys.remove(keyToDelete)			
				assertEquals(keys.size, checkCountNodes(tree.root), "Node count mismatch after deletion")
			}
		}
	}
}