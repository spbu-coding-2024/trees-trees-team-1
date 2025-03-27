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
}