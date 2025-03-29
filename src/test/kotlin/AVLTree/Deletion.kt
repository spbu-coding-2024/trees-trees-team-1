package test.AVLTree

import AVLTree

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Tag
import kotlin.test.assertEquals

@Tag("AVLTree")
@Tag("delete")

class Delete {
	private lateinit var tree: AVLTree<Int, Int>

	@BeforeEach
	fun setup() {
		tree = AVLTree()
	}

	@Test
	@DisplayName("Delete from empty tree should do nothing")
	fun deleteFromEmptyTree() {
		tree.delete(10)
		assertEquals("null", tree.printNodes())
	}

	@Test
	@DisplayName("Delete non-existing key should not modify tree")
	fun deleteNonExistingKey() {
		tree.insert(10, 1)
		tree.delete(20)
		assertEquals("10 null null ", tree.printNodes())
	}

	@Test
	@DisplayName("Checking delete root from single-node tree")
	fun deleteRootSingleNode() {
		tree.insert(10, 1)
		tree.delete(10)
		assertEquals("null", tree.printNodes())
	}

	@Test
	@DisplayName("Checking delete node with left rotation")
	fun deleteWithLeftRotation() {
		tree.insert(10, 1)
		tree.insert(5, 1)
		tree.insert(15, 1)
		tree.insert(20, 1)
		tree.delete(5)
		val expected = "15 10 20 null null null null "
		assertEquals(expected, tree.printNodes())
		}

	@Test
	@DisplayName("Checking delete node with right rotation")
	fun deleteWithRightRotation() {
		tree.insert(10, 1)
		tree.insert(5, 1)
		tree.insert(15, 1)
		tree.insert(1, 1)
		tree.delete(15)
		val expected = "5 1 10 null null null null "
		assertEquals(expected, tree.printNodes())
	}

	@Test
	@DisplayName("Checking delete node with left-right rotation")
	fun deleteWithLeftRightRotation() {
		tree.insert(10, 1)
		tree.insert(5, 1)
		tree.insert(15, 1)
		tree.insert(7, 1)
		tree.delete(15)
		val expected = "7 5 10 null null null null "
		assertEquals(expected, tree.printNodes())
	}

	@Test
	@DisplayName("Checking delete node with right-left rotation")
	fun deleteWithRightLeftRotation() {
		tree.insert(10, 1)
		tree.insert(5, 1)
		tree.insert(15, 1)
		tree.insert(12, 1)
		tree.delete(5)
		val expected = "12 10 15 null null null null "
		assertEquals(expected, tree.printNodes())
	}

	@Test
	@DisplayName("Checking delete node with two children")
	fun deleteNodeWithTwoChildren() {
		tree.insert(10, 1)
		tree.insert(5, 1)
		tree.insert(15, 1)
		tree.insert(12, 1)
		tree.insert(20, 1)
		tree.delete(15)
		val expected = "10 5 20 null null 12 null null null "
		assertEquals(expected, tree.printNodes())
	}

	@Test
	@DisplayName("Checking delete negative key")
	fun deleteNegativeKey() {
		tree.insert(-5, 1)
		tree.insert(-10, 1)
		tree.insert(0, 1)
		tree.delete(-5)
		val expected = "0 -10 null null null "
		assertEquals(expected, tree.printNodes())
	}

	@Test
	@DisplayName("Checking delete all nodes")
	fun deleteAllNodes() {
		val values = listOf(10, 5, 15, 12, 20)
		for (i in values) {
			tree.insert(i, i)
		}
		for (i in values) {
			tree.delete(i)
		}
		assertEquals("null", tree.printNodes())
	}
}