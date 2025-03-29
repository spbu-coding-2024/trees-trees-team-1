package test.AVLTree.AVLTree

import trees.AVLTree

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Tag
import kotlin.test.assertEquals

@Tag("AVLTree")

class Basic {
	private lateinit var tree: AVLTree<Int, Int>

	@BeforeEach
	fun setup() {
		tree = AVLTree()
	}

	@Test
	@DisplayName("Empty tree should be empty after initialization")
	fun emptyTree() {
		assertEquals("null", tree.printNodes())
	}

	@Tag("insert")
	@Test
	@DisplayName("Checking simple insert left without rotations")
	fun simpleInsertLeft() {
		tree.insert(7, 1)
		tree.insert(3, 1)
		val expected = "7 3 null null null "
		assertEquals(expected, tree.printNodes())
	}

	@Tag("insert")
	@Test
	@DisplayName("Checking simple insert right without rotations")
	fun simpleInsertRight() {
		tree.insert(3, 1)
		tree.insert(7, 1)
		val expected = "3 null 7 null null "
		assertEquals(expected, tree.printNodes())
	}

	@Tag("delete")
	@Test
	@DisplayName("Checking simple delete left without rotations")
	fun simpleDeleteLeft() {
		tree.insert(7, 1)
		tree.insert(3, 1)
		tree.delete(3)
		val expected = "7 null null "
		assertEquals(expected, tree.printNodes())
	}

	@Tag("delete")
	@Test
	@DisplayName("Checking simple delete right without rotations")
	fun simpleDeleteRight() {
		tree.insert(3, 1)
		tree.insert(7, 1)
		tree.delete(7)
		val expected = "3 null null "
		assertEquals(expected, tree.printNodes())
	}

	@Tag("find")
	@Test
	@DisplayName("Checking find for existing nodes")
	fun checkFindExisting() {
		tree.insert(10, 1)
		tree.insert(7, 1)
		tree.insert(3, 1)
		assertEquals(true, tree.find(10))
		assertEquals(true, tree.find(7))
		assertEquals(true, tree.find(3))
	}

	@Tag("find")
	@Test
	@DisplayName("Checking find for non-existing nodes")
	fun checkFindNonExisting() {
		tree.insert(10, 1)
		tree.insert(7, 1)
		tree.insert(3, 1)
		assertEquals(false, tree.find(11))
		assertEquals(false, tree.find(6))
		assertEquals(false, tree.find(1))
	}

	@Tag("find")
	@Test
	@DisplayName("Checking find function for existing and non-existing nodes")
	fun checkFind() {
		val values = listOf(7, 10, 11, 3)
		for (i in values) {
			tree.insert(i, 1)
		}
		val actual = mutableListOf<Boolean>()
		actual.addLast(tree.find(1))
		for (i in values) {
			actual.addLast(tree.find(i))
		}
		actual.addLast(tree.find(8))
		val expected = listOf(false, true, true, true, true, false)
		assertEquals(expected, actual)
	}

	@Tag("find")
	@Test
	@DisplayName("Checking find for empty tree")
	fun checkFindForEmptyTree() {
		assertEquals(false, tree.find(10))
	}

	@Tag("find parent")
	@Test
	@DisplayName("Checking find parent for existing nodes")
	fun checkFindParentExisting() {
		val values = listOf(7, 10, 11, 3, 5)
		for (i in values) {
			tree.insert(i, 1)
		}
		assertEquals(null, tree.findParent(10))
		assertEquals(10, tree.findParent(5))
		assertEquals(10, tree.findParent(11))
		assertEquals(5, tree.findParent(3))
		assertEquals(5, tree.findParent(7))
	}

	@Tag("find parent")
	@Test
	@DisplayName("Checking find parent for non-existing nodes")
	fun checkFindParentNonExisting() {
		val values = listOf(7, 10, 11, 3, 5)
		for (i in values) {
			tree.insert(i, 1)
		}
		assertEquals(null, tree.findParent(1))
		assertEquals(null, tree.findParent(8))
		assertEquals(null, tree.findParent(15))
	}

	@Tag("find parent")
	@Test
	@DisplayName("Checking find parent for existing and non-existing nodes")
	fun checkFindParent() {
		val values = listOf(7, 10, 11, 3, 5)
		for (i in values) {
			tree.insert(i, 1)
		}
		val actual = mutableListOf<Int?>()
		actual.addLast(tree.findParent(1))
		for (i in values) {
			actual.addLast(tree.findParent(i))
		}
		actual.addLast(tree.findParent(15))
		val expected = listOf(null, 5, null, 10, 5, 10, null)
		assertEquals(expected, actual)
	}

	@Tag("find parent")
	@Test
	@DisplayName("Checking find parent for empty tree")
	fun checkFindParentEmptyTree() {
		assertEquals(null, tree.findParent(10))
	}

	@Tag("peek")
	@Test
	@DisplayName("Checking peek for existing nodes")
	fun checkPeekExciting() {
		tree.insert(10, 100)
		tree.insert(7, 70)
		tree.insert(3, 30)
		assertEquals(100, tree.peek(10))
		assertEquals(70, tree.peek(7))
		assertEquals(30, tree.peek(3))
	}

	@Tag("peek")
	@Test
	@DisplayName("Checking peek for non-existing nodes")
	fun checkPeekNonExciting() {
		tree.insert(10, 100)
		tree.insert(7, 70)
		tree.insert(3, 30)
		assertEquals(null, tree.peek(2))
		assertEquals(null, tree.peek(8))
		assertEquals(null, tree.peek(15))
	}

	@Tag("peek")
	@Test
	@DisplayName("Checking peek for existing and non-existing nodes")
	fun checkPeek() {
		tree.insert(10, 100)
		tree.insert(7, 70)
		tree.insert(3, 30)
		val actual = mutableListOf<Int?>()
		actual.addLast(tree.peek(2))
		actual.addLast(tree.peek(10))
		actual.addLast(tree.peek(7))
		actual.addLast(tree.peek(3))
		actual.addLast(tree.peek(15))
		val expected = listOf(null, 100, 70, 30, null)
		assertEquals(expected, actual)
	}

	@Tag("peek")
	@Test
	@DisplayName("Checking peek for empty tree")
	fun checkPeekEmptyTree() {
		assertEquals(null, tree.peek(10))
	}
}