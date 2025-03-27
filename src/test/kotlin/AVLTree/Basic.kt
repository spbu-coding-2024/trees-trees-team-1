package test.AVLTree

import AVLTree

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Tag
import kotlin.test.assertEquals

@Tag("AVLTree")

class basic {
	private lateinit var tree: AVLTRee<Int, Int>

	@BeforeEach
	fun setup() {
		tree = AVLTRee()
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
	fun simpleDeleteLeft() {
		tree.insert(3, 1)
		tree.insert(7, 1)
		tree.delete(7)
		val expected = "3 null null "
		assertEquals(expected, tree.printNodes())
	}

	@Tag("find")
	@Test
	@DisplayName("Checking find existing elements")
	fun findExisting() {
		tree.insert(10, 1)
		tree.insert(7, 1)
		tree.insert(3, 1)
		assertEquals(true, tree.find(10))
		assertEquals(true, tree.find(7))
		assertEquals(true, tree.find(3))
	}

	@Tag("find")
	@Test
	@DisplayName("Checking find non-existing elements")
	fun findNonExisting() {
		tree.insert(10, 1)
		tree.insert(7, 1)
		tree.insert(3, 1)
		assertEquals(false, tree.find(11))
		assertEquals(false, tree.find(6))
		assertEquals(false, tree.find(1))
	}

	@Tag("find")
	@Test
	@DisplayName("checking find function for existing and non-existing elements")
	fun checkingFind() {
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
	@DisplayName("checking find for empty tree")
	fun findForEmptyTree() {
		assertEquals(false, tree.find(10))
	}
}