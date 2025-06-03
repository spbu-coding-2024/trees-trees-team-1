package test.AVLTree.AVLTree

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import trees.AVLTree
import kotlin.test.assertEquals
import kotlin.test.assertNull

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
		assertNull(tree.peek(10))
		assertEquals("null ", tree.printNodes())
	}

	@Test
	@DisplayName("Delete non-existing key should not modify tree")
	fun deleteNonExistingKey() {
		tree.insert(10, 1)
		tree.delete(20)
		assertEquals(1, tree.peek(10))
		assertEquals("10 null null ", tree.printNodes())
	}

	@Test
	@DisplayName("Checking delete root from single-node tree")
	fun deleteRootSingleNode() {
		tree.insert(10, 1)
		tree.delete(10)
		assertNull(tree.peek(10))
		assertEquals("null ", tree.printNodes())
	}

	@Test
	@DisplayName("Checking delete node with left rotation")
	fun deleteWithLeftRotation() {
		tree.insert(10, 100)
		tree.insert(5, 50)
		tree.insert(15, 150)
		tree.insert(20, 200)
		tree.delete(5)
		val expected = "15 10 20 null null null null "
		assertEquals(expected, tree.printNodes())
		assertEquals(100, tree.peek(10))
		assertEquals(150, tree.peek(15))
		assertEquals(200, tree.peek(20))
		assertNull(tree.peek(5))
		}

	@Test
	@DisplayName("Checking delete node with right rotation")
	fun deleteWithRightRotation() {
		tree.insert(10, 100)
		tree.insert(5, 50)
		tree.insert(15, 150)
		tree.insert(1, 10)
		tree.delete(15)
		val expected = "5 1 10 null null null null "
		assertEquals(expected, tree.printNodes())
		assertEquals(100, tree.peek(10))
		assertEquals(50, tree.peek(5))
		assertEquals(10, tree.peek(1))
		assertNull(tree.peek(15))
	}

	@Test
	@DisplayName("Checking delete node with left-right rotation")
	fun deleteWithLeftRightRotation() {
		tree.insert(10, 100)
		tree.insert(5, 50)
		tree.insert(15, 150)
		tree.insert(7, 70)
		tree.delete(15)
		val expected = "7 5 10 null null null null "
		assertEquals(expected, tree.printNodes())
		assertEquals(100, tree.peek(10))
		assertEquals(50, tree.peek(5))
		assertEquals(70, tree.peek(7))
		assertNull(tree.peek(15))
	}

	@Test
	@DisplayName("Checking delete node with right-left rotation")
	fun deleteWithRightLeftRotation() {
		tree.insert(10, 100)
		tree.insert(5, 50)
		tree.insert(15, 150)
		tree.insert(12, 120)
		tree.delete(5)
		val expected = "12 10 15 null null null null "
		assertEquals(expected, tree.printNodes())
		assertEquals(100, tree.peek(10))
		assertEquals(150, tree.peek(15))
		assertEquals(120, tree.peek(12))
		assertNull(tree.peek(5))
	}

	@Test
	@DisplayName("Checking delete node with two children")
	fun deleteNodeWithTwoChildren() {
		tree.insert(10, 100)
		tree.insert(5, 50)
		tree.insert(15, 150)
		tree.insert(12, 120)
		tree.insert(20, 200)
		tree.delete(15)
		val expected = "10 5 20 null null 12 null null null "
		assertEquals(expected, tree.printNodes())
		assertEquals(100, tree.peek(10))
		assertEquals(50, tree.peek(5))
		assertEquals(120, tree.peek(12))
		assertEquals(200, tree.peek(20))
		assertNull(tree.peek(15))
	}

	@Test
	@DisplayName("Checking delete negative key")
	fun deleteNegativeKey() {
		tree.insert(-5, 1)
		tree.insert(-10, 2)
		tree.insert(0, 3)
		tree.delete(-5)
		val expected = "0 -10 null null null "
		assertEquals(expected, tree.printNodes())
		assertEquals(2, tree.peek(-10))
		assertEquals(3, tree.peek(0))
		assertNull(tree.peek(-5))
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
		assertEquals("null ", tree.printNodes())
		values.forEach { assertNull(tree.peek(it)) }
	}
}
