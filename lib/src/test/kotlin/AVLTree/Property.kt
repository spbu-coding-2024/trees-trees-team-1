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

	private fun checkBSTProperty(tree: AVLTree<Int, Int>, key: Int?, min: Int = Int.MIN_VALUE, 
	max: Int = Int.MAX_VALUE ): Boolean {
		if (key == null) return true
		val (leftKey, rightKey) = tree.getChildren(key)
		if (key <= min || key >= max) return false

		return checkBSTProperty(tree, leftKey, min, key) && checkBSTProperty(tree, rightKey, key, max)
	}


	private fun checkBalanceProperty(tree: AVLTree<Int, Int>, key: Int?) : Boolean {
		if (key == null) return true
		val (leftKey, rightKey) = tree.getChildren(key)
		val leftHeight = tree.getHeight(leftKey)
		val rightHeight = tree.getHeight(rightKey)
		
		return (abs(leftHeight - rightHeight) <= 1 && checkBalanceProperty(tree, leftKey)
		 && checkBalanceProperty(tree, rightKey))
	}


	private fun checkCountNodes(tree: AVLTree<Int, Int>, key: Int?): Int {
		if (key == null) return 0
		val (leftKey, rightKey) = tree.getChildren(key)
		return 1 + checkCountNodes(tree, leftKey) + checkCountNodes(tree, rightKey)
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
		assertTrue(checkBSTProperty(tree, tree.valueRoot()), "Failed BST property after insertion")
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
		assertTrue(checkBalanceProperty(tree, tree.valueRoot()), "Failed balance property after insertion")
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
			assertEquals(insertedKeys.size, checkCountNodes(tree, tree.valueRoot()), 
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
				assertTrue(checkBSTProperty(tree, tree.valueRoot()), "Failed BST property after deletion")
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
				assertTrue(checkBalanceProperty(tree, tree.valueRoot()), "Failed balanced property after deletion")
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
				assertEquals(keys.size, checkCountNodes(tree, tree.valueRoot()), "Node count mismatch after deletion")
			}
		}
	}
}
